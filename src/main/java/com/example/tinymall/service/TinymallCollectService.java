package com.example.tinymall.service;

import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.entity.TinymallCollect;
import com.example.tinymall.model.vo.FootprintVO;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @ClassName TinymallCollectService
 * @Description
 * @Author jzf
 * @Date 2020-4-15 11:58
 */
public interface TinymallCollectService extends BaseService<TinymallCollect,Integer> {
    int count(int uid, Integer gid);

    List<FootprintVO> queryByType(TinymallCollect condition);

    TinymallCollect queryByTypeAndValue(Integer userId, Byte type, Integer valueId);
}
