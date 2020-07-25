package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.model.po.Ad;
import com.example.tinymall.service.TinymallAdService;
import org.springframework.stereotype.Service;
/**
 * @ClassName TinymallAdServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 10:35
 */
@Service("adService")
public class TinymallAdServiceImpl extends BaseMySqlServiceImpl<Ad,Integer> implements TinymallAdService {

}
