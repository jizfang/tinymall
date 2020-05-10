package com.example.tinymall.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_comment")
public class TinymallComment extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 如果type=0，则是商品评论；如果是type=1，则是专题评论。
     */
    @Column(name = "value_id")
    private Integer valueId;

    /**
     * 评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；
     */
    @Column(name = "type")
    private Byte type;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 管理员回复内容
     */
    @Column(name = "admin_content")
    private String adminContent;

    /**
     * 用户表的用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 是否含有图片
     */
    @Column(name = "has_picture")
    private Boolean hasPicture;

    /**
     * 图片地址列表，采用JSON数组格式
     */
    //@Column(name = "pic_urls")
    //private List<String> picUrls;

    /**
     * 评分， 1-5
     */
    @Column(name = "star")
    private Short star;
}