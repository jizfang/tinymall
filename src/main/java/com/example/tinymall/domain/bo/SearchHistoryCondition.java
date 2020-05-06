package com.example.tinymall.domain.bo;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName SearchHistory
 * @Description
 * @Author jzf
 * @Date 2020-5-6 11:24
 */
@Getter
@Setter
public class SearchHistoryCondition {
    private String userId;
    private String keyword;
}
