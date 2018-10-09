package com.szcti.lcloud.grade.repository;

import com.szcti.lcloud.grade.entity.ClassesEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 班级管理DAO
 * @Description: 处理班级信息
 * @author: fengda
 * @date: 2018/7/30 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface ClassesDao {

    /**
     * 查找班级列表
     * @param classes
     * @return
     */
    List<ClassesEntity> findList(ClassesEntity classes);

    /**
     * 根据主键查询单个班级
     * @param id
     * @return
     */
    ClassesEntity get(Long id);

    /**
     * 删除班级信息
     * @param id
     */
    void delete(Long id);

    /**
     * 修改班级信息
     * @param classes
     */
    void update(ClassesEntity classes);

    /**
     * 新增班级信息
     * @param classes
     */
    void insert(ClassesEntity classes);

    /**
     * 查找同一code的班
     * @param code
     * @param id
     * @return
     */
    Integer getCountByCode(@Param("code")String code, @Param("id") Long id);
}
