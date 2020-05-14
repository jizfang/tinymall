package com.example.tinymall.controller.admin;

import com.example.tinymall.common.annotation.ResponseResult;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.entity.TinymallComment;
import com.example.tinymall.service.TinymallCommentService;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName AdminCommentController
 * @Description 商品评论
 * @Author jzf
 * @Date 2020-5-14 8:32
 */
@ResponseResult
@RestController
@RequestMapping("/admin/comment")
@Validated
public class AdminCommentController {
    @Autowired
    private TinymallCommentService commentService;

    @GetMapping("/list")
    public Object list(PageQO pageQO) {
        PageVO<TinymallComment> commentList = commentService.selectPage(pageQO);
        return commentList;
    }


    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@RequestBody TinymallComment comment) {
        Integer id = comment.getId();
        AssertUtils.isFalse(id == null,"需要删除的评论不存在");

        commentService.deleteByPk(id);
    }
}
