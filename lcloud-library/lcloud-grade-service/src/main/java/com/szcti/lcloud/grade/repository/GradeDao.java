package com.szcti.lcloud.grade.repository;

import com.szcti.lcloud.grade.entity.GradeEntity;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 年级管理DAO
 * @Description: 处理年级信息
 * @author: fengda
 * @date: 2018/7/30 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface GradeDao {

    /**
     * 查找年级列表
     * @param grade
     * @return
     */
    List<GradeEntity> findList(GradeEntity grade);

    /**
     * 根据主键查询单个年级
     * @param id
     * @return
     */
    GradeEntity get(Long id);

    /**
     * 根据年级类型type查询是否有下一个年级  1有0无
     * @param type
     * @param level
     * @param libId
     * @return
     */
    Integer hasNext(@Param("type") String type, @Param("level") Integer level, @Param("libId") Long libId);

    /**
     * 删除年级信息
     * @param id
     */
    void delete(Long id);

    /**
     * 修改年级信息
     * @param grade
     */
    void update(GradeEntity grade);

    /**
     * 新增年级信息
     * @param grade
     */
    void insert(GradeEntity grade);

    /**
     * 查找同一code的年级
     * @param code
     * @param id
     * @return
     */
    Integer getCountByCode(@Param("code")String code, @Param("id") Long id);
}
