package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.entity.TinymallBrand;
import com.example.tinymall.mapper.TinymallBrandMapper;
import com.example.tinymall.service.TinymallBrandService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallBrandServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:36
 */
@Service
public class TinymallBrandServiceImpl extends BaseMySqlServiceImpl<TinymallBrand,Integer> implements TinymallBrandService {

}
