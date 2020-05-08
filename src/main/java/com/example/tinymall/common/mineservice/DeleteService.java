package com.example.tinymall.common.mineservice;

/**
 * @ClassName DeleteService
 * @Description 基础删除服务
 * @Author jzf
 * @Date 2020-5-8 15:36
 */
public interface DeleteService<PK> {
    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    int deleteByPk(PK pk);

    /**
     * 根据主键删除记录
     *
     * @param pks 主键集合
     * @return 影响记录数
     */
    int deleteByPks(Iterable<PK> pks);
}
