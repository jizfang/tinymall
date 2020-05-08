package com.example.tinymall.service.impl;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.example.tinymall.common.enums.ResultCode;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.constants.CouponUserConstant;
import com.example.tinymall.core.constants.GrouponConstant;
import com.example.tinymall.core.constants.OrderHandleOption;
import com.example.tinymall.core.constants.OrderUtil;
import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.entity.*;
import com.example.tinymall.model.bo.OrderInfo;
import com.example.tinymall.model.bo.UserCartInfo;
import com.example.tinymall.model.dto.UserOrderParam;
import com.example.tinymall.service.*;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tinymall.core.utils.WxResponseCode.*;

/**
 * @ClassName WxOrderServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-23 11:03
 */
@Service
public class WxOrderServiceImpl implements WxOrderService {

    @Autowired
    private TinymallGrouponRulesService grouponRulesService;
    @Autowired
    private TinymallGrouponService grouponService;
    @Autowired
    private TinymallAddressService addressService;
    @Autowired
    private TinymallCartService cartService;
    @Autowired
    private CouponVerifyService couponVerifyService;
    @Autowired
    private TinymallOrderService orderService;
    @Autowired
    private TinymallOrderGoodsService orderGoodsService;
    @Autowired
    private TinymallGoodsProductService productService;
    @Autowired
    private TinymallCouponUserService couponUserService;
    @Autowired
    private TinymallUserService userService;
    @Autowired
    private WxPayService wxPayService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object submit(Integer userId, UserCartInfo userCartInfo) {
        /*if (userId == null) {
            return ResponseUtil.unlogin();
        }
        if (userCartInfo == null) {
            return ResponseUtil.badArgument();
        }*/
        Integer cartId = userCartInfo.getCartId();
        Integer addressId = userCartInfo.getAddressId();
        Integer couponId = userCartInfo.getCouponId();
        Integer userCouponId = userCartInfo.getUserCouponId();
        String message = userCartInfo.getMessage();
        Integer grouponRulesId = userCartInfo.getGrouponRulesId();
        Integer grouponLinkId = userCartInfo.getGrouponLinkId();

        //如果是团购项目,验证活动是否有效
        if (grouponRulesId != null && grouponRulesId > 0) {
            TinymallGrouponRules rules = grouponRulesService.findById(grouponRulesId);
            //找不到记录
            /*if (rules == null) {
                return ResponseUtil.badArgument();
            }*/
            //团购规则已经过期
            /*if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_EXPIRE)) {
                return ResponseUtil.fail(GROUPON_EXPIRED, "团购已过期!");
            }*/
            //团购规则已经下线
            /*if (rules.getStatus().equals(GrouponConstant.RULE_STATUS_DOWN_ADMIN)) {
                return ResponseUtil.fail(GROUPON_OFFLINE, "团购已下线!");
            }*/

            if (grouponLinkId != null && grouponLinkId > 0) {
                //团购人数已满
                /*if(grouponService.countGroupon(grouponLinkId) >= (rules.getDiscountMember() - 1)){
                    return ResponseUtil.fail(GROUPON_FULL, "团购活动人数已满!");
                }*/
                // NOTE
                // 这里业务方面允许用户多次开团，以及多次参团，
                // 但是会限制以下两点：
                // （1）不允许参加已经加入的团购
                /*if(grouponService.hasJoin(userId, grouponLinkId)){
                    return ResponseUtil.fail(GROUPON_JOIN, "团购活动已经参加!");
                }*/
                // （2）不允许参加自己开团的团购
                TinymallGroupon groupon = grouponService.queryById(userId, grouponLinkId);
                /*if(groupon.getCreatorUserId().equals(userId)){
                    return ResponseUtil.fail(GROUPON_JOIN, "团购活动已经参加!");
                }*/
            }
        }

        /*if (cartId == null || addressId == null || couponId == null) {
            return ResponseUtil.badArgument();
        }*/

        // 收货地址
        TinymallAddress checkedAddress = addressService.query(userId, addressId);
        /*if (checkedAddress == null) {
            return ResponseUtil.badArgument();
        }*/

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0);
        TinymallGrouponRules grouponRules = grouponRulesService.findById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 货品价格
        List<TinymallCart> checkedGoodsList = null;
        if (cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
            TinymallCart cart = cartService.findById(cartId);
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        /*if (checkedGoodsList.size() == 0) {
            return ResponseUtil.badArgumentValue();
        }*/
        BigDecimal checkedGoodsPrice = new BigDecimal(0);
        for (TinymallCart checkGoods : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(checkGoods.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().subtract(grouponPrice).multiply(new BigDecimal(checkGoods.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().multiply(new BigDecimal(checkGoods.getNumber())));
            }
        }

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0);
        // 如果couponId=0则没有优惠券，couponId=-1则不使用优惠券
        if (couponId != 0 && couponId != -1) {
            TinymallCoupon coupon = couponVerifyService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            /*if (coupon == null) {
                return ResponseUtil.badArgumentValue();
            }*/
            couponPrice = coupon.getDiscount();
        }


        // 根据订单商品总价计算运费，满足条件（例如88元）则免运费，否则需要支付运费（例如8元）；
        BigDecimal freightPrice = new BigDecimal(0);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0));
        // 最终支付费用
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Integer orderId = null;
        TinymallOrder order = null;
        // 订单
        order = new TinymallOrder();
        order.setUserId(userId);
        order.setOrderSn(orderService.generateOrderSn(userId));
        order.setOrderStatus(OrderUtil.STATUS_CREATE);
        order.setConsignee(checkedAddress.getName());
        order.setMobile(checkedAddress.getTel());
        order.setMessage(message);
        String detailedAddress = checkedAddress.getProvince() + checkedAddress.getCity() + checkedAddress.getCounty() + " " + checkedAddress.getAddressDetail();
        order.setAddress(detailedAddress);
        order.setGoodsPrice(checkedGoodsPrice);
        order.setFreightPrice(freightPrice);
        order.setCouponPrice(couponPrice);
        order.setIntegralPrice(integralPrice);
        order.setOrderPrice(orderTotalPrice);
        order.setActualPrice(actualPrice);

        // 有团购
        if (grouponRules != null) {
            order.setGrouponPrice(grouponPrice);    //  团购价格
        } else {
            order.setGrouponPrice(new BigDecimal(0));    //  团购价格
        }

        // 添加订单表项
        orderService.add(order);
        orderId = order.getId();

        // 添加订单商品表项
        for (TinymallCart cartGoods : checkedGoodsList) {
            // 订单商品
            TinymallOrderGoods orderGoods = new TinymallOrderGoods();
            orderGoods.setOrderId(order.getId());
            orderGoods.setGoodsId(cartGoods.getGoodsId());
            orderGoods.setGoodsSn(cartGoods.getGoodsSn());
            orderGoods.setProductId(cartGoods.getProductId());
            orderGoods.setGoodsName(cartGoods.getGoodsName());
            orderGoods.setPicUrl(cartGoods.getPicUrl());
            orderGoods.setPrice(cartGoods.getPrice());
            orderGoods.setNumber(cartGoods.getNumber());
            //orderGoods.setSpecifications(cartGoods.getSpecifications());
            orderGoods.setAddTime(LocalDateTime.now());

            orderGoodsService.add(orderGoods);
        }

        // 删除购物车里面的商品信息
        cartService.clearGoods(userId);

        // 商品货品数量减少
        for (TinymallCart checkGoods : checkedGoodsList) {
            Integer productId = checkGoods.getProductId();
            TinymallGoodsProduct product = productService.findById(productId);

            int remainNumber = product.getNumber() - checkGoods.getNumber();
            if (remainNumber < 0) {
                throw new RuntimeException("下单的商品货品数量大于库存量");
            }
            if (productService.reduceStock(productId, checkGoods.getNumber()) == 0) {
                throw new RuntimeException("商品货品库存减少失败");
            }
        }

        // 如果使用了优惠券，设置优惠券使用状态
        if (couponId != 0 && couponId != -1) {
            TinymallCouponUser couponUser = couponUserService.findById(userCouponId);
            couponUser.setStatus(CouponUserConstant.STATUS_USED);
            couponUser.setUsedTime(LocalDateTime.now());
            couponUser.setOrderId(orderId);
            couponUserService.update(couponUser);
        }

        //如果是团购项目，添加团购信息
        if (grouponRulesId != null && grouponRulesId > 0) {
            TinymallGroupon groupon = new TinymallGroupon();
            groupon.setOrderId(orderId);
            groupon.setStatus(GrouponConstant.STATUS_NONE);
            groupon.setUserId(userId);
            groupon.setRulesId(grouponRulesId);

            //参与者
            if (grouponLinkId != null && grouponLinkId > 0) {
                //参与的团购记录
                TinymallGroupon baseGroupon = grouponService.queryById(grouponLinkId);
                groupon.setCreatorUserId(baseGroupon.getCreatorUserId());
                groupon.setGrouponId(grouponLinkId);
                groupon.setShareUrl(baseGroupon.getShareUrl());
                grouponService.createGroupon(groupon);
            } else {
                groupon.setCreatorUserId(userId);
                groupon.setCreatorUserTime(LocalDateTime.now());
                groupon.setGrouponId(0);
                grouponService.createGroupon(groupon);
                grouponLinkId = groupon.getId();
            }
        }

        // 订单支付超期任务
        //taskService.addTask(new OrderUnpaidTask(orderId));

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", orderId);
        if (grouponRulesId != null && grouponRulesId > 0) {
            data.put("grouponLinkId", grouponLinkId);
        }
        else {
            data.put("grouponLinkId", 0);
        }
        return data;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object prepay(Integer userId, OrderInfo orderInfo, HttpServletRequest request) {
        /*if (userId == null) {
            return ResponseUtil.unlogin();
        }*/
        Integer orderId = orderInfo.getOrderId();
        /*if (orderId == null) {
            return ResponseUtil.badArgument();
        }*/

        TinymallOrder order = orderService.findById(userId, orderId);
        /*if (order == null) {
            return ResponseUtil.badArgumentValue();
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.badArgumentValue();
        }*/

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        /*if (!handleOption.isPay()) {
            return ResponseUtil.fail(ORDER_INVALID_OPERATION, "订单不能支付");
        }*/

        TinymallUser user = userService.findById(userId);
        String openid = user.getWeixinOpenid();
        /*if (openid == null) {
            return ResponseUtil.fail(AUTH_OPENID_UNACCESS, "订单不能支付");
        }*/
        WxPayMpOrderResult result = null;
        try {
            /*WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setOutTradeNo(order.getOrderSn());
            orderRequest.setOpenid(openid);
            orderRequest.setBody("订单：" + order.getOrderSn());
            // 元转成分
            int fee = 0;
            BigDecimal actualPrice = order.getActualPrice();
            fee = actualPrice.multiply(new BigDecimal(100)).intValue();
            orderRequest.setTotalFee(fee);
            orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));

            result = wxPayService.createOrder(orderRequest);*/
            result = WxPayMpOrderResult.builder().appId("appid").timeStamp(String.valueOf(System.currentTimeMillis())).nonceStr("nonceStr").packageValue("prepay_id=" + "prepayId").signType("appId").build();
        } catch (Exception e) {
            e.printStackTrace();
            //return ResponseUtil.fail(ORDER_PAY_FAIL, "订单不能支付");
        }

        /*if (orderService.updateWithOptimisticLocker(order) == 0) {
            return ResponseUtil.updatedDateExpired();
        }*/
        return result;
    }

    @Override
    public PageVO<TinymallOrder> list(PageQO pageQO) {
        UserOrderParam userOrderParam = (UserOrderParam) pageQO.getCondition();
        Integer userId = userOrderParam.getUserId();
        if (userId == null) {
            throw new BusinessException(ResultCode.USER_NOT_EXIST);
        }
        Integer showType = userOrderParam.getShowType();
        List<Short> orderStatus = OrderUtil.orderStatus(showType);
        PageVO<TinymallOrder> orderList = orderService.queryByOrderStatus(userId, orderStatus, pageQO.getPageNum(), pageQO.getPageSize(),
                pageQO.getOrderBy());

        List<Map<String, Object>> orderVoList = new ArrayList<>(orderList.getList().size());
        for (TinymallOrder o : orderList.getList()) {
            Map<String, Object> orderVo = new HashMap<>();
            orderVo.put("id", o.getId());
            orderVo.put("orderSn", o.getOrderSn());
            orderVo.put("actualPrice", o.getActualPrice());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(o));
            orderVo.put("handleOption", OrderUtil.build(o));
            orderVo.put("aftersaleStatus", o.getAftersaleStatus());

            TinymallGroupon groupon = grouponService.queryByOrderId(o.getId());
            if (groupon != null) {
                orderVo.put("isGroupin", true);
            } else {
                orderVo.put("isGroupin", false);
            }

            List<TinymallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(o.getId());
            List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (TinymallOrderGoods orderGoods : orderGoodsList) {
                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", orderGoods.getId());
                orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                orderGoodsVo.put("number", orderGoods.getNumber());
                orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                //orderGoodsVo.put("specifications", orderGoods.getSpecifications());
                orderGoodsVo.put("price",orderGoods.getPrice());
                orderGoodsVoList.add(orderGoodsVo);
            }
            orderVo.put("goodsList", orderGoodsVoList);

            orderVoList.add(orderVo);
        }

        return orderList;
    }

    @Override
    public Object detail(Integer userId, Integer orderId) {
        /*if (userId == null) {
            return ResponseUtil.unlogin();
        }*/

        // 订单信息
        TinymallOrder order = orderService.findById(userId, orderId);
        /*if (null == order) {
            return ResponseUtil.fail(ORDER_UNKNOWN, "订单不存在");
        }
        if (!order.getUserId().equals(userId)) {
            return ResponseUtil.fail(ORDER_INVALID, "不是当前用户的订单");
        }*/
        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("message", order.getMessage());
        orderVo.put("addTime", order.getAddTime());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("address", order.getAddress());
        orderVo.put("goodsPrice", order.getGoodsPrice());
        orderVo.put("couponPrice", order.getCouponPrice());
        orderVo.put("freightPrice", order.getFreightPrice());
        orderVo.put("actualPrice", order.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("handleOption", OrderUtil.build(order));
        orderVo.put("aftersaleStatus", order.getAftersaleStatus());
        orderVo.put("expCode", order.getShipChannel());
        //orderVo.put("expName", expressService.getVendorName(order.getShipChannel()));
        orderVo.put("expNo", order.getShipSn());

        List<TinymallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(order.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsList);

        // 订单状态为已发货且物流信息不为空
        //"YTO", "800669400640887922"
        /*if (order.getOrderStatus().equals(OrderUtil.STATUS_SHIP)) {
            ExpressInfo ei = expressService.getExpressInfo(order.getShipChannel(), order.getShipSn());
            if(ei == null){
                result.put("expressInfo", new ArrayList<>());
            }
            else {
                result.put("expressInfo", ei);
            }
        }
        else{
            result.put("expressInfo", new ArrayList<>());
        }*/

        return result;
    }
}
