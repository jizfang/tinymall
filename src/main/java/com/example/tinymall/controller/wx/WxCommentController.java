package com.example.tinymall.controller.wx;

import com.example.tinymall.common.annotation.LoginAuth;
import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.helper.LoginTokenHelper;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallComment;
import com.example.tinymall.entity.TinymallUser;
import com.example.tinymall.model.bo.LoginUser;
import com.example.tinymall.model.dto.UserInfo;
import com.example.tinymall.model.qo.CommentQO;
import com.example.tinymall.service.TinymallCommentService;
import com.example.tinymall.service.TinymallGoodsService;
import com.example.tinymall.service.TinymallTopicService;
import com.example.tinymall.service.TinymallUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: fang
 * @create: 2020-05-18 06:00
 **/
@ResponseResult
@RestController
@RequestMapping("/wx/comment")
@Validated
public class WxCommentController {
    @Autowired
    private TinymallCommentService commentService;
    @Autowired
    private TinymallUserService userService;
    @Autowired
    private TinymallGoodsService goodsService;
    @Autowired
    private TinymallTopicService topicService;

    /**
     * 发表评论
     *
     * @param comment 评论内容
     * @return 发表评论操作结果
     */
    @PostMapping("post")
    @LoginAuth
    public Object post(@RequestBody TinymallComment comment) {
        LoginUser loginUser = LoginTokenHelper.getLoginUserFromRequest();

        comment.setUserId(loginUser.getId());
        commentService.insert(comment);
        return comment;
    }

    /**
     * 评论数量
     *
     * @param type    类型ID。 如果是0，则查询商品评论；如果是1，则查询专题评论。
     * @param valueId 商品或专题ID。如果type是0，则是商品ID；如果type是1，则是专题ID。
     * @return 评论数量
     */
    @GetMapping("count")
    public Object count(@NotNull Byte type, @NotNull Integer valueId) {
        TinymallComment comment = new TinymallComment();
        comment.setValueId(valueId);
        comment.setType(type);
        int allCount = commentService.selectCount(comment);
        comment.setHasPicture(true);
        int hasPicCount = commentService.selectCount(comment);
        Map<String, Object> entity = new HashMap<String, Object>();
        entity.put("allCount", allCount);
        entity.put("hasPicCount", hasPicCount);
        return entity;
    }

    /**
     * 评论列表
     *
     * @return 评论列表
     */
    @GetMapping("list")
    public Object list(PageQO pageQO,CommentQO commentQO) {
        pageQO.setCondition(commentQO);

        PageVO<TinymallComment> commentList = commentService.selectPage(pageQO);

        /*List<Map<String, Object>> commentVoList = new ArrayList<>(commentList.size());
        for (TinymallComment comment : commentList.getList()) {
            Map<String, Object> commentVo = new HashMap<>();
            commentVo.put("addTime", comment.getCreateTime());
            commentVo.put("content", comment.getContent());
            commentVo.put("adminContent", comment.getAdminContent());
            commentVo.put("picList", comment.getPicUrls());

            TinymallUser userInfo = userService.findById(comment.getUserId());
            commentVo.put("userInfo", userInfo);

            commentVoList.add(commentVo);
        }*/
        return commentList;
    }
}
