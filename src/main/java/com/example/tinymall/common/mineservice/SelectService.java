package com.example.tinymall.common.mineservice;

import com.example.tinymall.common.page.PageQO;
import com.example.tinymall.common.page.PageVO;

import java.util.List;

/**
 * @ClassName SelectService
 * @Description 基础查询服务
 * @Author jzf
 * @Date 2020-5-8 15:40
 */
public interface SelectService<E,PK> {
    /**
     * 根据主键查询
     * @param pk 主键
     * @return 查询结果,无结果时返回{@code null}
     */
    E selectByPk(PK pk);

    /**
     * 根据多个主键查询
     * @param pks 主键集合
     * @return 查询结果,如果无结果返回空集合
     */
    List<E> selectByPks(Iterable<PK> pks);

    /**
     * 查询所有结果
     * @return 所有结果,如果无结果则返回空集合
     */
    List<E> selectAll();

    /**
     * 查询所有结果
     * @return 获取分页结果
     */
    PageVO<E> selectPage(PageQO<?> pageQO);
}
