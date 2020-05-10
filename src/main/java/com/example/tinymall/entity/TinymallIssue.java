package com.example.tinymall.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.example.tinymall.model.po.BasePO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "tinymall_issue")
public class TinymallIssue extends BasePO<Integer> {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 问题标题
     */
    @Column(name = "question")
    private String question;

    /**
     * 问题答案
     */
    @Column(name = "answer")
    private String answer;
}