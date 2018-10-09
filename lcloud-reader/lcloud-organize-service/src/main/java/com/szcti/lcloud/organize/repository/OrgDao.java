package com.szcti.lcloud.organize.repository;

import com.szcti.lcloud.organize.entity.OrgEntity;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 组织机构DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/6/6 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface OrgDao {
    /**
     * 查找组织机构列表
     * @param orgEntity
     * @return List<OrgEntity>
     */
    List<OrgEntity> findList(OrgEntity orgEntity);

    /**
     * 修改组织机构信息
     * @param orgEntity
     */
    void update(OrgEntity orgEntity);

    /**
     * 修改组织机构的parentIds
     * @param id
     * @param parentIds
     */
    void updatePrtIds(@Param("id") Long id,@Param("parentIds") String parentIds);

    /**
     * 新增组织机构信息
     * @param orgEntity
     */
    void insert(OrgEntity orgEntity);

    /**
     * 删除组织机构信息
     * @param ids
     */
    void delete(@Param("ids") Long[] ids);

    /**
     * 查询单个组织机构信息
     * @param id
     * @return OrgEntity
     */
    OrgEntity get(Long id);
}
