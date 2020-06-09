package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.entity.TinymallFootprint;
import com.example.tinymall.model.vo.FootprintVO;

import java.util.List;

/**
 * @ClassName TinymallFootprintService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 12:04
 */
public interface TinymallFootprintService extends BaseService<TinymallFootprint,Integer> {
    List<TinymallFootprint> queryByAddTime(Integer userId, Integer page, Integer size);

    List<FootprintVO> selectFootprintPage(TinymallFootprint condition);
}
