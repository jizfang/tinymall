package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.system.SystemConfig;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.*;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tinymall.core.utils.WxResponseCode.GOODS_NO_STOCK;
import static com.example.tinymall.core.utils.WxResponseCode.GOODS_UNSHELVE;

/**
 * @ClassName WxCartController
 * @Description
 * @Author jzf
 * @Date 2020-4-15 14:17
 */
@ResponseResult
@RestController
@RequestMapping("/wx/cart")
@Slf4j
public class WxCartController {
    @Autowired
    private TinymallCartService cartService;
    @Autowired
    private TinymallGoodsService goodsService;
    @Autowired
    private TinymallGoodsProductService productService;
    @Autowired
    private TinymallAddressService addressService;
    @Autowired
    private TinymallGrouponRulesService grouponRulesService;
    @Autowired
    private TinymallCouponUserService couponUserService;
    @Autowired
    private CouponVerifyService couponVerifyService;

    /**
     * 用户购物车信息
     *
     * @return 用户购物车信息
     */
    @GetMapping("index")
    public Object index() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");

        List<TinymallCart> list = cartService.queryByUid(loginUser.getId());
        List<TinymallCart> cartList = new ArrayList<>();
        // TODO
        // 如果系统检查商品已删除或已下架，则系统自动删除。
        // 更好的效果应该是告知用户商品失效，允许用户点击按钮来清除失效商品。
        for (TinymallCart cart : list) {
            TinymallGoods goods = goodsService.selectByPk(cart.getGoodsId());
            if (goods == null || !goods.getIsOnSale()) {
                cartService.deleteById(cart.getId());
                log.debug("系统自动删除失效购物车商品 goodsId=" + cart.getGoodsId() + " productId=" + cart.getProductId());
            }
            else{
                cartList.add(cart);
            }
        }

        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (TinymallCart cart : cartList) {
            goodsCount += cart.getNumber();
            goodsAmount = goodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            if (cart.getChecked()) {
                checkedGoodsCount += cart.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        Map<String, Object> cartTotal = new HashMap<>();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>();
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);

        return result;
    }
    /**
     * 购物车商品货品数量
     * <p>
     * 如果用户没有登录，则返回空数据。
     *
     * @return 购物车商品货品数量
     */
    @GetMapping("goodscount")
    public Object goodscount() {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        int goodsCount = 0;
        List<TinymallCart> cartList = cartService.queryByUid(loginUser.getId());
        for (TinymallCart cart : cartList) {
            goodsCount += cart.getNumber();
        }

        return goodsCount;
    }

    /**
     * 立即购买
     * <p>
     * 和add方法的区别在于：
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 立即购买操作结果
     */
    @PostMapping("fastadd")
    public Object fastadd(@RequestBody TinymallCart cart) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");
        AssertUtils.notNull(cart,"购物车信息不能为空");

        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        /*if (!ObjectUtils.allNotNull(productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if(number <= 0){
            return ResponseUtil.badArgument();
        }*/

        //判断商品是否可以购买
        TinymallGoods goods = goodsService.selectByPk(goodsId);
        /*if (goods == null || !goods.getIsOnSale()) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }*/

        TinymallGoodsProduct product = productService.findById(productId);
        //判断购物车中是否存在此规格商品
        TinymallCart existCart = cartService.queryExist(goodsId, productId, loginUser.getId());
        if (existCart == null) {
            //取得规格的信息,判断规格库存
            /*if (product == null || number > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }*/

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if(StringUtils.isEmpty(product.getUrl())){
                cart.setPicUrl(goods.getPicUrl());
            }
            else{
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            //cart.setSpecifications(product.getSpecifications());
            cart.setUserId(loginUser.getId());
            cart.setChecked(true);
            cartService.add(cart);
        } else {
            //取得规格的信息,判断规格库存
            int num = number;
            /*if (num > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }*/
            existCart.setNumber((short) num);
            /*if (cartService.updateById(existCart) == 0) {
                return ResponseUtil.updatedDataFailed();
            }*/
        }

        return CommonResult.success(existCart != null ? existCart.getId() : cart.getId());
    }

    /**
     * 加入商品到购物车
     * <p>
     * 如果已经存在购物车货品，则增加数量；
     * 否则添加新的购物车货品项。
     *
     * @param cart   购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     */
    @PostMapping("add")
    @LoginAuth
    public Object add(@RequestBody TinymallCart cart) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = loginUser.getId();
        /*if (cart == null) {
            return ResponseUtil.badArgument();
        }*/

        Integer productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Integer goodsId = cart.getGoodsId();
        /*if (!ObjectUtils.allNotNull(productId, number, goodsId)) {
            return ResponseUtil.badArgument();
        }
        if(number <= 0){
            return ResponseUtil.badArgument();
        }*/

        //判断商品是否可以购买
        TinymallGoods goods = goodsService.selectByPk(goodsId);
        /*if (goods == null || !goods.getIsOnSale()) {
            return ResponseUtil.fail(GOODS_UNSHELVE, "商品已下架");
        }*/

        TinymallGoodsProduct product = productService.findById(productId);
        //判断购物车中是否存在此规格商品
        TinymallCart existCart = cartService.queryExist(goodsId, productId, userId);
        if (existCart == null) {
            //取得规格的信息,判断规格库存
            /*if (product == null || number > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }*/

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            if(StringUtils.isEmpty(product.getUrl())){
                cart.setPicUrl(goods.getPicUrl());
            }
            else{
                cart.setPicUrl(product.getUrl());
            }
            cart.setPrice(product.getPrice());
            //cart.setSpecifications(product.getSpecifications());
            cart.setUserId(userId);
            cart.setChecked(true);
            cart.setDeleted(false);
            cartService.add(cart);
        } else {
            //取得规格的信息,判断规格库存
            int num = existCart.getNumber() + number;
            /*if (num > product.getNumber()) {
                return ResponseUtil.fail(GOODS_NO_STOCK, "库存不足");
            }*/
            existCart.setNumber((short) num);
            /*if (cartService.updateById(existCart) == 0) {
                return ResponseUtil.updatedDataFailed();
            }*/
        }

        return goodscount();
    }

    /**
     * 购物车下单
     *
     * @param cartId    购物车商品ID：
     *                  如果购物车商品ID是空，则下单当前用户所有购物车商品；
     *                  如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID：
     *                  如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId  优惠券ID：
     *                  如果优惠券ID是空，则自动选择合适的优惠券。
     * @return 购物车操作结果
     */
    @GetMapping("checkout")
    public Object checkout(Integer cartId, Integer addressId, Integer couponId, Integer userCouponId, Integer grouponRulesId) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer userId = loginUser.getId();

        // 收货地址
        TinymallAddress checkedAddress = null;
        if (addressId == null || addressId.equals(0)) {
            checkedAddress = addressService.findDefault(userId);
            // 如果仍然没有地址，则是没有收货地址
            // 返回一个空的地址id=0，这样前端则会提醒添加地址
            if (checkedAddress == null) {
                checkedAddress = new TinymallAddress();
                checkedAddress.setId(0);
                addressId = 0;
            } else {
                addressId = checkedAddress.getId();
            }

        } else {
            checkedAddress = addressService.query(userId, addressId);
            // 如果null, 则报错
            /*if (checkedAddress == null) {
                return ResponseUtil.badArgumentValue();
            }*/
        }

        // 团购优惠
        BigDecimal grouponPrice = new BigDecimal(0.00);
        TinymallGrouponRules grouponRules = grouponRulesService.findById(grouponRulesId);
        if (grouponRules != null) {
            grouponPrice = grouponRules.getDiscount();
        }

        // 商品价格
        List<TinymallCart> checkedGoodsList = null;
        if (cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
            TinymallCart cart = cartService.findById(userId, cartId);
            /*if (cart == null) {
                return ResponseUtil.badArgumentValue();
            }*/
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (TinymallCart cart : checkedGoodsList) {
            //  只有当团购规格商品ID符合才进行团购优惠
            if (grouponRules != null && grouponRules.getGoodsId().equals(cart.getGoodsId())) {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().subtract(grouponPrice).multiply(new BigDecimal(cart.getNumber())));
            } else {
                checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }

        // 计算优惠券可用情况
        BigDecimal tmpCouponPrice = new BigDecimal(0.00);
        Integer tmpCouponId = 0;
        Integer tmpUserCouponId = 0;
        int tmpCouponLength = 0;
        List<TinymallCouponUser> couponUserList = couponUserService.queryAll(userId);
        for(TinymallCouponUser couponUser : couponUserList){
            tmpUserCouponId = couponUser.getId();
            TinymallCoupon coupon = couponVerifyService.checkCoupon(userId, couponUser.getCouponId(), tmpUserCouponId, checkedGoodsPrice);
            if(coupon == null){
                continue;
            }

            tmpCouponLength++;
            if(tmpCouponPrice.compareTo(coupon.getDiscount()) == -1){
                tmpCouponPrice = coupon.getDiscount();
                tmpCouponId = coupon.getId();
            }
        }
        // 获取优惠券减免金额，优惠券可用数量
        int availableCouponLength = tmpCouponLength;
        BigDecimal couponPrice = new BigDecimal(0);
        // 这里存在三种情况
        // 1. 用户不想使用优惠券，则不处理
        // 2. 用户想自动使用优惠券，则选择合适优惠券
        // 3. 用户已选择优惠券，则测试优惠券是否合适
        if (couponId == null || couponId.equals(-1)){
            couponId = -1;
            userCouponId = -1;
        }
        else if (couponId.equals(0)) {
            couponPrice = tmpCouponPrice;
            couponId = tmpCouponId;
            userCouponId = tmpUserCouponId;
        }
        else {
            TinymallCoupon coupon = couponVerifyService.checkCoupon(userId, couponId, userCouponId, checkedGoodsPrice);
            // 用户选择的优惠券有问题，则选择合适优惠券，否则使用用户选择的优惠券
            if(coupon == null){
                couponPrice = tmpCouponPrice;
                couponId = tmpCouponId;
                userCouponId = tmpUserCouponId;
            }
            else {
                couponPrice = coupon.getDiscount();
            }
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(SystemConfig.getFreightLimit()) < 0) {
            freightPrice = SystemConfig.getFreight();
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice).max(new BigDecimal(0.00));

        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Map<String, Object> data = new HashMap<>();
        data.put("addressId", addressId);
        data.put("couponId", couponId);
        data.put("userCouponId", userCouponId);
        data.put("cartId", cartId);
        data.put("grouponRulesId", grouponRulesId);
        data.put("grouponPrice", grouponPrice);
        data.put("checkedAddress", checkedAddress);
        data.put("availableCouponLength", availableCouponLength);
        data.put("goodsTotalPrice", checkedGoodsPrice);
        data.put("freightPrice", freightPrice);
        data.put("couponPrice", couponPrice);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", actualPrice);
        data.put("checkedGoodsList", checkedGoodsList);
        return data;
    }
}
