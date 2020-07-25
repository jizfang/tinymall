package com.example.tinymall.service.impl;

import com.example.tinymall.common.mineservice.impl.BaseMySqlServiceImpl;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.common.result.CommonResult;
import com.example.tinymall.core.qcode.QCodeService;
import com.example.tinymall.entity.TinymallGoods;
import com.example.tinymall.mapper.*;
import com.example.tinymall.model.dto.GoodsDTO;
import com.example.tinymall.model.vo.CatVo;
import com.example.tinymall.service.TinymallBrandService;
import com.example.tinymall.service.TinymallCategoryService;
import com.example.tinymall.service.TinymallGoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName TinymallGoodsServiceImpl
 * @Description
 * @Author jzf
 * @Date 2020-4-10 17:37
 */
@Service
@Primary
public class TinymallGoodsServiceImpl extends BaseMySqlServiceImpl<TinymallGoods,Integer> implements TinymallGoodsService {

    @Resource
    private TinymallGoodsMapper goodsMapper;
    @Resource
    private TinymallBrandService brandService;
    @Resource
    private TinymallCategoryService categoryService;
    @Resource
    private TinymallGoodsProductMapper productMapper;
    @Resource
    private TinymallGoodsSpecificationMapper specificationMapper;
    @Resource
    private TinymallGoodsAttributeMapper attributeMapper;
    @Resource
    private TinymallBrandMapper brandMapper;
    @Autowired
    private QCodeService qCodeService;

    @Override
    public List<TinymallGoods> queryByNew(int offset, int limit) {
        Example example = new Example(TinymallGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isNew",true);
        criteria.andEqualTo("isOnSale",true);
        criteria.andEqualTo("deleted",false);
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<TinymallGoods> queryByHot(int offset, int limit) {
        Example example = new Example(TinymallGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isHot",true);
        criteria.andEqualTo("isOnSale",true);
        criteria.andEqualTo("deleted",false);
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<TinymallGoods> queryByCategory(List<Integer> catList, int offset, Integer limit) {
        Example example = new Example(TinymallGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("categoryId",catList);
        criteria.andEqualTo("isOnSale",true);
        criteria.andEqualTo("deleted",false);
        example.setOrderByClause("create_time  desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public List<TinymallGoods> queryByCategory(Integer catId, int offset, int limit) {
        Example example = new Example(TinymallGoods.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryId",catId);
        criteria.andEqualTo("isOnSale",true);
        criteria.andEqualTo("deleted",false);
        example.setOrderByClause("create_time desc");
        PageHelper.startPage(offset, limit);

        return goodsMapper.selectByExample(example);
    }

    @Override
    public Integer queryOnSale() {
        TinymallGoods tinymallGoods = new TinymallGoods();
        tinymallGoods.setIsOnSale(true);
        tinymallGoods.setDeleted(0);
        return (int) goodsMapper.selectCount(tinymallGoods);
    }


    @Override
    public List<Integer> getCatIds(Integer brandId, String keywords, Boolean isHot, Boolean isNew) {
        return Lists.newArrayList();
        /*TinymallGoodsExample example = new TinymallGoodsExample();
        TinymallGoodsExample.Criteria criteria1 = example.or();
        TinymallGoodsExample.Criteria criteria2 = example.or();

        if (brandId != null) {
            criteria1.andBrandIdEqualTo(brandId);
            criteria2.andBrandIdEqualTo(brandId);
        }
        if (isNew != null) {
            criteria1.andIsNewEqualTo(isNew);
            criteria2.andIsNewEqualTo(isNew);
        }
        if (isHot != null) {
            criteria1.andIsHotEqualTo(isHot);
            criteria2.andIsHotEqualTo(isHot);
        }
        if (!StringUtils.isEmpty(keywords)) {
            criteria1.andKeywordsLike("%" + keywords + "%");
            criteria2.andNameLike("%" + keywords + "%");
        }
        criteria1.andIsOnSaleEqualTo(true);
        criteria2.andIsOnSaleEqualTo(true);
        criteria1.andDeletedEqualTo(false);
        criteria2.andDeletedEqualTo(false);

        List<TinymallGoods> goodsList = goodsMapper.selectByExampleSelective(example, TinymallGoods.Column.categoryId);
        List<Integer> cats = new ArrayList<Integer>();
        for (TinymallGoods goods : goodsList) {
            cats.add(goods.getCategoryId());
        }
        return cats;*/
    }


    @Override
    @Transactional
    public Object update(GoodsDTO goodsAllinone) {
        return CommonResult.success();
        /*Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        TinymallGoods goods = goodsAllinone.getGoods();
        TinymallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        TinymallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        TinymallGoodsProduct[] products = goodsAllinone.getProducts();

        //将生成的分享图片地址写入数据库
        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
        goods.setShareUrl(url);

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (TinymallGoodsProduct product : products) {
            BigDecimal productPrice = product.getPrice();
            if(retailPrice.compareTo(productPrice) == 1){
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);

        // 商品基本信息表litemall_goods
        if (goodsMapper.updateByPrimaryKey(goods) == 0) {
            throw new RuntimeException("更新数据失败");
        }

        Integer gid = goods.getId();

        // 商品规格表litemall_goods_specification
        for (TinymallGoodsSpecification specification : specifications) {
            // 目前只支持更新规格表的图片字段
            if(specification.getUpdateTime() == null){
                specification.setSpecification(null);
                specification.setValue(null);
                specificationMapper.updateByPrimaryKeySelective(specification);
            }
        }

        // 商品货品表litemall_product
        for (TinymallGoodsProduct product : products) {
            if(product.getUpdateTime() == null) {
                productMapper.updateByPrimaryKeySelective(product);
            }
        }

        // 商品参数表litemall_goods_attribute
        for (TinymallGoodsAttribute attribute : attributes) {
            if (attribute.getId() == null || attribute.getId().equals(0)){
                attribute.setGoodsId(goods.getId());
                attributeMapper.insertSelective(attribute);
            }
            else if(attribute.getDeleted()){
                attributeMapper.deleteByPrimaryKey(attribute.getId());
            }
            else if(attribute.getUpdateTime() == null){
                attributeMapper.updateByPrimaryKeySelective(attribute);
            }
        }

        // 这里需要注意的是购物车litemall_cart有些字段是拷贝商品的一些字段，因此需要及时更新
        // 目前这些字段是goods_sn, goods_name, price, pic_url
        for (TinymallGoodsProduct product : products) {
            productMapper.updateByPrimaryKeySelective(product);
        }

        return CommonResult.success();*/
    }

    @Override
    @Transactional
    public Object delete(TinymallGoods goods) {
        return CommonResult.success();
        /*Integer id = goods.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }

        Integer gid = goods.getId();
        goodsMapper.deleteByPrimaryKey(gid);
        TinymallGoodsSpecificationExample specificationExample = new TinymallGoodsSpecificationExample();
        specificationExample.or().andGoodsIdEqualTo(id);
        specificationMapper.deleteByExample(specificationExample);
        TinymallGoodsAttributeExample attributeExample = new TinymallGoodsAttributeExample();
        attributeExample.or().andGoodsIdEqualTo(id);
        attributeMapper.deleteByExample(attributeExample);
        TinymallGoodsProductExample goodsProductExample = new TinymallGoodsProductExample();
        goodsProductExample.or().andGoodsIdEqualTo(id);
        productMapper.deleteByExample(goodsProductExample);
        return CommonResult.success();*/
    }

    @Override
    @Transactional
    public Object create(GoodsDTO goodsAllinone) {
        return CommonResult.success();
        /*Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }

        TinymallGoods goods = goodsAllinone.getGoods();
        TinymallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        TinymallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        TinymallGoodsProduct[] products = goodsAllinone.getProducts();

        String name = goods.getName();
        TinymallGoodsExample example = new TinymallGoodsExample();
        example.or().andNameEqualTo(name).andIsOnSaleEqualTo(true).andDeletedEqualTo(false);
        if (goodsMapper.countByExample(example) != 0) {
            return ResponseUtil.fail(GOODS_NAME_EXIST, "商品名已经存在");
        }

        // 商品表里面有一个字段retailPrice记录当前商品的最低价
        BigDecimal retailPrice = new BigDecimal(Integer.MAX_VALUE);
        for (TinymallGoodsProduct product : products) {
            BigDecimal productPrice = product.getPrice();
            if(retailPrice.compareTo(productPrice) == 1){
                retailPrice = productPrice;
            }
        }
        goods.setRetailPrice(retailPrice);
        goods.setDeleted(false);

        // 商品基本信息表litemall_goods
        goodsMapper.insertSelective(goods);

        //将生成的分享图片地址写入数据库
        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
        if (!StringUtils.isEmpty(url)) {
            goods.setShareUrl(url);
            if (goodsMapper.updateByPrimaryKey(goods) == 0) {
                throw new RuntimeException("更新数据失败");
            }
        }

        // 商品规格表litemall_goods_specification
        for (TinymallGoodsSpecification specification : specifications) {
            specification.setGoodsId(goods.getId());
            specificationMapper.insertSelective(specification);
        }

        // 商品参数表litemall_goods_attribute
        for (TinymallGoodsAttribute attribute : attributes) {
            attribute.setGoodsId(goods.getId());
            attributeMapper.insertSelective(attribute);
        }

        // 商品货品表litemall_product
        for (TinymallGoodsProduct product : products) {
            product.setGoodsId(goods.getId());
            productMapper.insertSelective(product);
        }
        return CommonResult.success();*/
    }

    @Override
    public Object detail(Integer id) {
        return CommonResult.success();
        /*TinymallGoods goods = goodsMapper.selectByPrimaryKey(id);
        TinymallGoodsProductExample goodsProductExample = new TinymallGoodsProductExample();
        goodsProductExample.or().andGoodsIdEqualTo(id);
        List<TinymallGoodsProduct> products = productMapper.selectByExample(goodsProductExample);
        TinymallGoodsSpecificationExample specificationExample = new TinymallGoodsSpecificationExample();
        specificationExample.or().andGoodsIdEqualTo(id);
        List<TinymallGoodsSpecification> specifications = specificationMapper.selectByExample(specificationExample);
        TinymallGoodsAttributeExample attributeExample = new TinymallGoodsAttributeExample();
        attributeExample.or().andGoodsIdEqualTo(id);
        List<TinymallGoodsAttribute> attributes = attributeMapper.selectByExample(attributeExample);

        Integer categoryId = goods.getCategoryId();
        TinymallCategory category = categoryService.findById(categoryId);
        Integer[] categoryIds = new Integer[]{};
        if (category != null) {
            Integer parentCategoryId = category.getPid();
            categoryIds = new Integer[]{parentCategoryId, categoryId};
        }

        Map<String, Object> data = new HashMap<>();
        data.put("goods", goods);
        data.put("specifications", specifications);
        data.put("products", products);
        data.put("attributes", attributes);
        data.put("categoryIds", categoryIds);

        return data;*/
    }

    private Object validate(GoodsDTO goodsAllinone) {
        return CommonResult.success();
        /*TinymallGoods goods = goodsAllinone.getGoods();
        String name = goods.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String goodsSn = goods.getGoodsSn();
        if (StringUtils.isEmpty(goodsSn)) {
            return ResponseUtil.badArgument();
        }
        // 品牌商可以不设置，如果设置则需要验证品牌商存在
        Integer brandId = goods.getBrandId();
        if (brandId != null && brandId != 0) {
            if (brandService.findById(brandId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }
        // 分类可以不设置，如果设置则需要验证分类存在
        Integer categoryId = goods.getCategoryId();
        if (categoryId != null && categoryId != 0) {
            if (categoryService.findById(categoryId) == null) {
                return ResponseUtil.badArgumentValue();
            }
        }

        TinymallGoodsAttribute[] attributes = goodsAllinone.getAttributes();
        for (TinymallGoodsAttribute attribute : attributes) {
            String attr = attribute.getAttribute();
            if (StringUtils.isEmpty(attr)) {
                return ResponseUtil.badArgument();
            }
            String value = attribute.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        TinymallGoodsSpecification[] specifications = goodsAllinone.getSpecifications();
        for (TinymallGoodsSpecification specification : specifications) {
            String spec = specification.getSpecification();
            if (StringUtils.isEmpty(spec)) {
                return ResponseUtil.badArgument();
            }
            String value = specification.getValue();
            if (StringUtils.isEmpty(value)) {
                return ResponseUtil.badArgument();
            }
        }

        TinymallGoodsProduct[] products = goodsAllinone.getProducts();
        for (TinymallGoodsProduct product : products) {
            Integer number = product.getNumber();
            if (number == null || number < 0) {
                return ResponseUtil.badArgument();
            }

            BigDecimal price = product.getPrice();
            if (price == null) {
                return ResponseUtil.badArgument();
            }

            String[] productSpecifications = product.getSpecifications();
            if (productSpecifications.length == 0) {
                return ResponseUtil.badArgument();
            }
        }

        return null;*/
    }

    @Override
    public Object list2() {
        return CommonResult.success();
        /*List<TinymallCategory> l1CatList = categoryService.queryL1();
        List<CatVo> categoryList = new ArrayList<>(l1CatList.size());

        for (TinymallCategory l1 : l1CatList) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(l1.getId());
            l1CatVo.setLabel(l1.getName());

            List<TinymallCategory> l2CatList = categoryService.queryByPid(l1.getId());
            List<CatVo> children = new ArrayList<>(l2CatList.size());
            for (TinymallCategory l2 : l2CatList) {
                CatVo l2CatVo = new CatVo();
                l2CatVo.setValue(l2.getId());
                l2CatVo.setLabel(l2.getName());
                children.add(l2CatVo);
            }
            l1CatVo.setChildren(children);

            categoryList.add(l1CatVo);
        }

        // http://element-cn.eleme.io/#/zh-CN/component/select
        // 管理员设置“所属品牌商”
        TinymallBrandExample example = new TinymallBrandExample();
        example.or().andDeletedEqualTo(false);
        List<TinymallBrand> list = brandMapper.selectByExample(example);
        List<Map<String, Object>> brandList = new ArrayList<>(l1CatList.size());
        for (TinymallBrand brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getId());
            b.put("label", brand.getName());
            brandList.add(b);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);
        data.put("brandList", brandList);
        return data;*/
    }
}
