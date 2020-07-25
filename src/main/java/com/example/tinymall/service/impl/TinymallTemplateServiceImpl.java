package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallTemplate;
import com.example.tinymall.service.TinymallTemplateService;
import org.springframework.stereotype.Service;

/**
 * @ClassName TinymallTemplateServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-7-22 14:34
 */
@Service("templateService")
public class TinymallTemplateServiceImpl extends BaseMySqlServiceImpl<TinymallTemplate,Integer> implements TinymallTemplateService {
}
