package com.example.tinymall.mapper;

import com.example.tinymall.common.minemappers.MyMapper;
import com.example.tinymall.entity.TinymallFootprint;
import com.example.tinymall.model.vo.FootprintVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TinymallFootprintMapper extends MyMapper<TinymallFootprint> {
    List<FootprintVO> selectFootprintPage(@Param("condition") TinymallFootprint condition);
}