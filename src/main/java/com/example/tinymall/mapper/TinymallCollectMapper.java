package com.example.tinymall.mapper;

import com.example.tinymall.common.minemappers.MyMapper;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.model.vo.FootprintVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TinymallCollectMapper extends MyMapper<TinymallCollect> {
    List<FootprintVO> queryByType(@Param("condition") TinymallCollect condition);
}