package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallAftersale;
import com.example.tinymall.entity.TinymallOrderGoods;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.vo.AfterSaleVO;
import com.example.tinymall.service.TinymallAftersaleService;
import com.example.tinymall.service.TinymallOrderGoodsService;
import com.example.tinymall.service.TinymallOrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName WxAftersaleController
 * @Description
 * @Author jzf
 * @Date 2020-6-11 16:41
 */
@ResponseResult
@RestController
@RequestMapping("/wx/aftersale")
public class WxAftersaleController {
    @Autowired
    private TinymallAftersaleService aftersaleService;
    @Autowired
    private TinymallOrderService orderService;
    @Autowired
    private TinymallOrderGoodsService orderGoodsService;

    /**
     * 售后列表
     *
     * @param status   状态类型，如果是空则是全部
     * @return 售后列表
     */
    @GetMapping("list")
    @LoginAuth
    public Object list(@RequestParam Short status, PageQO pageQO) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        AssertUtils.notNull(loginUser,"用户未登录");

        Integer userId = loginUser.getId();
        TinymallAftersale condition = new TinymallAftersale();
        condition.setUserId(userId);
        condition.setStatus(status);

        pageQO.setOrderBy("c.create_time desc");
        Page<AfterSaleVO> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        List<TinymallAftersale> aftersaleList = aftersaleService.selectByCondition(condition);

        List<AfterSaleVO> aftersaleVoList = new ArrayList<>(aftersaleList.size());
        for (TinymallAftersale aftersale : aftersaleList) {
            List<TinymallOrderGoods> orderGoodsList = orderGoodsService.queryByOid(aftersale.getOrderId());
            AfterSaleVO afterSaleVO = new AfterSaleVO();
            afterSaleVO.setAftersaleSn(aftersale.getAftersaleSn());
            afterSaleVO.setAmount(aftersale.getAmount());
            afterSaleVO.setOrderGoodsList(orderGoodsList);

            aftersaleVoList.add(afterSaleVO);
        }

        return PageVO.build(page);
    }

}
