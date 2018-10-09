package com.szcti.lcloud.grade.repository;

import com.szcti.lcloud.grade.entity.DeptEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 部门管理DAO
 * @Description: 处理部门信息
 * @author: fengda
 * @date: 2018/8/29 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface DeptDao {

    /**
     * 查找部门列表
     * @param dept
     * @return
     */
    List<DeptEntity> findList(DeptEntity dept);

    /**
     * 根据主键查询单个部门
     * @param id
     * @return
     */
    DeptEntity get(Long id);

    /**
     * 删除部门信息
     * @param id
     */
    void delete(Long id);

    /**
     * 修改部门信息
     * @param dept
     */
    void update(DeptEntity dept);

    /**
     * 新增部门信息
     * @param dept
     */
    void insert(DeptEntity dept);

    /**
     * 查找同一code的部门
     * @param code
     * @param id
     * @return
     */
    Integer getCountByCode(@Param("code") String code, @Param("id") Long id);
}
