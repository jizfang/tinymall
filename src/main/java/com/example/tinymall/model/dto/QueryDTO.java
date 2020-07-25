package com.example.tinymall.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName QueryDTO
 * @Description
 * @Author jzf
 * @Date 2020-7-22 16:06
 */
public class QueryDTO {
    private int offset;
    private int limit;
    private String queryParam;

    public QueryDTO() {
    }

    public QueryDTO(int offset, int limit, String queryParam) {
        this.offset = offset;
        this.limit = limit;
        this.queryParam = queryParam;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(String queryParam) {
        this.queryParam = queryParam;
    }
}
