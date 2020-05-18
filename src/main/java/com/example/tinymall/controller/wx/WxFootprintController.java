package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.JacksonUtil;
import com.example.tinymall.entity.TinymallFootprint;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.service.TinymallFootprintService;
import com.example.tinymall.service.TinymallGoodsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName WxFootprintController
 * @Description
 * @Author jzf
 * @Date 2020-5-18 9:14
 */
@ResponseResult
@RestController
@RequestMapping("/wx/footprint")
@Validated
public class WxFootprintController {
    @Autowired
    private TinymallFootprintService footprintService;
    @Autowired
    private TinymallGoodsService goodsService;

    /**
     * 删除用户足迹
     *
     * @param body   请求内容， { id: xxx }
     * @return 删除操作结果
     */
    @PostMapping("delete")
    @LoginAuth
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody String body) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        Integer footprintId = JacksonUtil.parseInteger(body, "id");
        AssertUtils.isFalse(footprintId == null,"id不能为空");
        TinymallFootprint condition = new TinymallFootprint();
        condition.setUserId(loginUser.getId());
        condition.setId(footprintId);
        TinymallFootprint footprint = footprintService.selectOne(condition);

        footprintService.deleteByPk(footprintId);
    }

    /**
     * 用户足迹列表
     *
     * @return 用户足迹列表
     */
    @GetMapping("list")
    public Object list(PageQO page) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();
        TinymallFootprint condition = new TinymallFootprint();
        condition.setUserId(loginUser.getId());
        condition.setDeleted(0);
        page.setCondition(condition);
        PageVO<TinymallFootprint> footprintList = footprintService.selectPage(page);

        return footprintList;
    }
}
