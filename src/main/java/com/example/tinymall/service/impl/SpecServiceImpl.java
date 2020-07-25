package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.Spec;
import com.example.tinymall.service.SpecService;
import org.springframework.stereotype.Service;

/**
 * @ClassName specServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-7-22 14:34
 */
@Service("specService")
public class SpecServiceImpl extends BaseMySqlServiceImpl<Spec,Integer> implements SpecService {
}