package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.Para;
import com.example.tinymall.service.ParaService;
import org.springframework.stereotype.Service;

/**
 * @ClassName paraServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-7-22 14:34
 */
@Service("paraService")
public class ParaServiceImpl extends BaseMySqlServiceImpl<Para,Integer> implements ParaService {
}