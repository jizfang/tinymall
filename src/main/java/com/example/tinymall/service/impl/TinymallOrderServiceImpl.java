package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallOrder;
import com.example.tinymall.mapper.TinymallOrderMapper;
import com.example.tinymall.model.qo.OrderQO;
import com.example.tinymall.service.TinymallOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.tinymall.core.utils.CharUtil.getRandomNum;

/**
 * @ClassName TinymallOrderServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 17:46
 */
@Service
public class TinymallOrderServiceImpl extends BaseMySqlServiceImpl<TinymallOrder,Integer> implements TinymallOrderService {

    @Resource
    private TinymallOrderMapper orderMapper;

    @Override
    public Map<Object, Object> orderInfo(Integer userId) {
//        TinymallOrderExample example = new TinymallOrderExample();
//        example.or().andUserIdEqualTo(userId).andDeletedEqualTo(false);
//        List<TinymallOrder> orders = orderMapper.selectByExampleSelective(example, TinymallOrder.Column.orderStatus, TinymallOrder.Column.comments);

        int unpaid = 0;
        int unship = 0;
        int unrecv = 0;
        int uncomment = 0;
        /*for (TinymallOrder order : orders) {
            if (OrderUtil.isCreateStatus(order)) {
                unpaid++;
            } else if (OrderUtil.isPayStatus(order)) {
                unship++;
            } else if (OrderUtil.isShipStatus(order)) {
                unrecv++;
            } else if (OrderUtil.isConfirmStatus(order) || OrderUtil.isAutoConfirmStatus(order)) {
                uncomment += order.getComments();
            } else {
                // do nothing
            }
        }*/

        Map<Object, Object> orderInfo = new HashMap<Object, Object>();
        orderInfo.put("unpaid", unpaid);
        orderInfo.put("unship", unship);
        orderInfo.put("unrecv", unrecv);
        orderInfo.put("uncomment", uncomment);
        return orderInfo;

    }

    public int countByOrderSn(Integer userId, String orderSn) {
        /*TinymallOrderExample example = new TinymallOrderExample();
        example.or().andUserIdEqualTo(userId).andOrderSnEqualTo(orderSn).andDeletedEqualTo(false);
        return (int) orderMapper.countByExample(example);*/
        return 0;
    }

    @Override
    public String generateOrderSn(Integer userId) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        String now = df.format(LocalDate.now());
        String orderSn = now + getRandomNum(6);
        while (countByOrderSn(userId, orderSn) != 0) {
            orderSn = now + getRandomNum(6);
        }
        return orderSn;
    }

    @Override
    public TinymallOrder findById(Integer userId, Integer orderId) {
        /*TinymallOrderExample example = new TinymallOrderExample();
        example.or().andIdEqualTo(orderId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        return orderMapper.selectOneByExample(example);*/
        return null;
    }

    @Override
    public PageVO<TinymallOrder> queryByOrderStatus(PageQO pageQO,Integer userId, List<Short> orderStatus) {
        Example example = new Example(TinymallOrder.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("createTime desc");

        /*if(StringUtils.isNotBlank(orderQO.getOrderSn())){
            criteria.andEqualTo("orderSn",orderQO.getOrderSn());
        }
        if (CollectionUtils.isNotEmpty(orderQO.getOrderStatusArray())) {
            criteria.andIn("orderStatus",orderQO.getOrderStatusArray());
        }*/
        criteria.andEqualTo("deleted",0);

        Page page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize());
        orderMapper.selectByExample(example);
        return PageVO.build(page);
    }
}
