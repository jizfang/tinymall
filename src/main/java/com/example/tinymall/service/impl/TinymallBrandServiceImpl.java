package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.entity.TinymallBrand;
import com.example.tinymall.mapper.TinymallBrandMapper;
import com.example.tinymall.model.qo.BrandQO;
import com.example.tinymall.service.TinymallBrandService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName TinymallBrandServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-11 10:36
 */
@Service
@Primary
public class TinymallBrandServiceImpl extends BaseMySqlServiceImpl<TinymallBrand,Integer> implements TinymallBrandService {
}
