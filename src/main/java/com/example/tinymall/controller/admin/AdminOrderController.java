package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.entity.TinymallOrder;
import com.example.tinymall.entity.TinymallSystem;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.qo.OrderQO;
import com.example.tinymall.service.TinymallOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.validation.constraints.NotNull;

@ResponseResult
@RestController
@RequestMapping("/admin/order")
@Validated
@LoginAuth
public class AdminOrderController {
    @Autowired
    private TinymallOrderService adminOrderService;
    //@Autowired
    //private ExpressService expressService;

    /**
     * 查询订单
     *
     * @param page
     * @param condition
     * @return
     */
    @GetMapping("/list")
    public Object list(PageQO page, OrderQO condition) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        //condition.setUserId(loginUser.getId());
        page.setCondition(condition);
        return adminOrderService.queryByOrderStatus(page);
    }

    /**
     * 查询物流公司
     *
     * @return
     */
    @GetMapping("/channel")
    public Object channel() {
        return CommonResult.success();
    }

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return adminOrderService.selectByPk(id);
    }

    /**
     * 订单退款
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单退款操作结果
     */
    /*@PostMapping("/refund")
    public Object refund(@RequestBody String body) {
        return adminOrderService.refund(body);
    }*/

    /**
     * 发货
     *
     * @param body 订单信息，{ orderId：xxx, shipSn: xxx, shipChannel: xxx }
     * @return 订单操作结果
     */
    /*@RequiresPermissions("admin:order:ship")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "订单发货")
    @PostMapping("/ship")
    public Object ship(@RequestBody String body) {
        return adminOrderService.ship(body);
    }*/


    /**
     * 删除订单
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    /*@PostMapping("/delete")
    public Object delete(@RequestBody String body) {
        return adminOrderService.delete(body);
    }*/

    /**
     * 回复订单商品
     *
     * @param body 订单信息，{ orderId：xxx }
     * @return 订单操作结果
     */
    /*@RequiresPermissions("admin:order:reply")
    @RequiresPermissionsDesc(menu = {"商场管理", "订单管理"}, button = "订单商品回复")
    @PostMapping("/reply")
    public Object reply(@RequestBody String body) {
        return adminOrderService.reply(body);
    }*/
}
