package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallAftersale;
import com.example.tinymall.service.TinymallAftersaleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * @ClassName TinymallAftersaleServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-6-11 16:46
 */
@Service
@Primary
public class TinymallAftersaleServiceImpl extends BaseMySqlServiceImpl<TinymallAftersale,Integer> implements TinymallAftersaleService {
}
