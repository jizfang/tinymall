package com.example.tinymall.common.mineservice.impl;

import com.alibaba.druid.util.StringUtils;
import com.example.tinymall.common.minemappers.MyMapper;
import com.example.tinymall.common.mineservice.BaseService;
import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;
import com.example.tinymall.core.utils.AssertUtils;
import com.example.tinymall.core.utils.BeanUtil;
import com.example.tinymall.core.utils.StringUtil;
import com.example.tinymall.model.po.PO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @ClassName BaseMySqlServiceImpl
 * @Description MYSQL通用CURD服务类
 * 备注：使用该类时，注入泛型 E，一定要有对应的 EMapper，例如：使用User的基础服务实现类需要继承MySqlCrudServiceImpl<User, String>，
 *       前提是要有UserMapper extends CrudMapper 类
 * @Author jzf
 * @Date 2020-5-8 15:44
 */
@Slf4j
public abstract class BaseMySqlServiceImpl<E extends PO<PK>,PK> implements BaseService<E,PK> {
    @Autowired
    protected MyMapper<E> crudMapper;

    protected Class<E> poType;

    /**
     * 构造函数
     */
    public BaseMySqlServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        poType = (Class<E>) pt.getActualTypeArguments()[0];
    }

    /**
     * 插入数据
     * @param record 要添加的数据
     * @return
     */
    @Override
    public PK insert(E record) {
        AssertUtils.notNull(record, "record is not null");

        if (record.getCreateTime() == null) {
            long now = System.currentTimeMillis();
            record.setCreateTime(now);
            record.setUpdateTime(now);
        }
        record.setDeleted(0);
        crudMapper.insert(record);
        return record.getId();
    }

    /**
     * 根据主键删除数据
     * @param pk 主键
     * @return
     */
    @Override
    public int deleteByPk(PK pk) {
        AssertUtils.notNull(pk, "pk is not null");
        E e = selectByPk(pk);
        AssertUtils.notNull(e, "需要删除的内容不存在");
        // 逻辑删除
        e.setDeleted(1);
        return crudMapper.updateByPrimaryKey(e);
    }

    /**
     * 根据主键集合删除数据
     * @param pks 主键集合
     * @return
     */
    @Override
    public int deleteByPks(Iterable<PK> pks) {
        AssertUtils.notNull(pks, "pks is not null");

        String pksStr = this.iterableToSpitStr(pks, ",");
        if (pksStr == null) {
            return 0;
        }
        List<E> es = crudMapper.selectByIds(pksStr);
        if(CollectionUtils.isEmpty(es)){
            return 0;
        }
        es.forEach(e -> e.setDeleted(1));
        return crudMapper.batchUpdate(es);
    }

    /**
     * 根据主键更新数据
     * @param pk 主键
     * @param record 要修改的对象
     * @return
     */
    @Override
    public int updateByPk(PK pk, E record) {
        AssertUtils.notNull(pk, "pk is not null");
        AssertUtils.notNull(record, "record is not null");

        record.setId(pk);
        if (record.getUpdateTime() == null) {
            record.setUpdateTime(System.currentTimeMillis());
        }
        return crudMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据主键更新数据
     * @param pk 主键
     * @param record 要修改的对象
     * @return
     */
    @Override
    public int updateByPkSelective(PK pk, E record) {
        AssertUtils.notNull(pk, "pk is not null");
        AssertUtils.notNull(record, "record is not null");

        record.setId(pk);
        if (record.getUpdateTime() == null) {
            record.setUpdateTime(System.currentTimeMillis());
        }
        return crudMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 新增或者更新数据
     * @param record 要修改的数据
     * @return
     */
    @Override
    public PK saveOrUpdate(E record) {
        AssertUtils.notNull(record, "record is not null");

        if (null != record.getId() && null != selectByPk(record.getId())) {
            updateByPk(record.getId(), record);
        } else {
            insert(record);
        }
        return record.getId();
    }

    /**
     * 根据主键查询数据
     * @param pk 主键
     * @return
     */
    @Override
    public E selectByPk(PK pk) {
        AssertUtils.notNull(pk, "pk is not null");

        return crudMapper.selectByPrimaryKey(pk);
    }

    /**
     * 根据主键集合查询数据
     * @param pks 主键集合
     * @return
     */
    @Override
    public List<E> selectByPks(Iterable<PK> pks) {
        AssertUtils.notNull(pks, "pks is not null");

        String pksStr = this.iterableToSpitStr(pks, ",");
        if (pksStr == null) {
            return Lists.newArrayList();
        }

        return crudMapper.selectByIds(pksStr);
    }

    /**
     * 根据分隔符号组装字符串
     * @param pks
     * @param separator
     * @return
     */
    private String iterableToSpitStr(Iterable<PK> pks, String separator) {
        StringBuilder s = new StringBuilder();
        pks.forEach(pk -> s.append(pk).append(separator));

        if (StringUtil.isEmpty(s.toString())) {
            return null;
        } else {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<E> selectAll() {
        return crudMapper.selectAll();
    }

    /**
     * 分页查询
     * @param pageQO
     * @return
     */
    @Override
    public PageVO<E> selectPage(PageQO<?> pageQO) {
        AssertUtils.notNull(pageQO, "pageQO is not null");

        Page<E> page = PageHelper.startPage(pageQO.getPageNum(), pageQO.getPageSize(), pageQO.getOrderBy());
        try {
            Object condition = pageQO.getCondition();
            if (condition == null) {
                crudMapper.selectAll();
            } else if (condition instanceof Condition) {
                crudMapper.selectByCondition(condition);
            } else if (condition instanceof Example) {
                crudMapper.selectByExample(condition);
            } else if (poType.isInstance(condition)){
                E e = (E) condition;
                selectByExample(e);
            } else {
                try {
                    E e = poType.newInstance();
                    BeanUtil.copyProperties(condition, e);
                    selectByExample(e);
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException("poType.newInstance occurs InstantiationException or IllegalAccessException", e);
                }
            }
        } finally {
            page.close();
        }

        return PageVO.build(page);
    }

    private void selectByExample(E e) {
        Class clazz = e.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, String> map = Arrays.stream(fields).collect(Collectors.toMap(Field::getName, field -> {
            String resultObj = null;
            field.setAccessible(true);
            try {
                resultObj = String.valueOf(field.get(e));
            } catch (IllegalAccessException illegalAccessException) {
                throw new RuntimeException("reflect class occurs InstantiationException or IllegalAccessException", illegalAccessException);
            }
            return Optional.ofNullable(resultObj).get();
        }, (k1, k2) -> k2));
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        map.forEach((k, v) -> {
            if (!"null".equals(v) && !"".equals(v)) {
                if (StringUtils.isNumber(v)) {
                    criteria.andEqualTo(k, v);
                } else {
                    criteria.andLike(k, "%" + v + "%");
                }
            }
        });
        criteria.andEqualTo("deleted","0");
        crudMapper.selectByExample(example);
    }

    /**
     * 查询数量
     * @param record
     * @return
     */
    @Override
    public int selectCount(E record) {
        AssertUtils.notNull(record, "record is not null");
        record.setDeleted(0);
        return crudMapper.selectCount(record);
    }

    /**
     * 根据条件查询list
     * @param record
     * @return
     */
    @Override
    public List<E> selectByCondition(E record){
        AssertUtils.notNull(record, "record is not null");
        record.setDeleted(0);
        return crudMapper.select(record);
    }

    @Override
    public E selectOne(E record) {
        record.setDeleted(0);
        return crudMapper.selectOne(record);
    }
}
