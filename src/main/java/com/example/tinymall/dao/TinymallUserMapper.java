package com.example.tinymall.dao;

import com.example.tinymall.domain.TinymallUser;
import com.example.tinymall.domain.TinymallUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TinymallUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    long countByExample(TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int deleteByExample(TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int insert(TinymallUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int insertSelective(TinymallUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    TinymallUser selectOneByExample(TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    TinymallUser selectOneByExampleSelective(@Param("example") TinymallUserExample example, @Param("selective") TinymallUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    List<TinymallUser> selectByExampleSelective(@Param("example") TinymallUserExample example, @Param("selective") TinymallUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    List<TinymallUser> selectByExample(TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    TinymallUser selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") TinymallUser.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    TinymallUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    TinymallUser selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") TinymallUser record, @Param("example") TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") TinymallUser record, @Param("example") TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TinymallUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TinymallUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") TinymallUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tinymall_user
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}