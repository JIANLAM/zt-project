package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.CatalogDefaultparam;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Title: 普通编目  默认参数设置 Dao
 * @Description: 普通编目  默认参数设置 Dao
 * @author: wangsiyi
 * @date: 2018/9/3 03:24
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CatalogDefaultparamDao {

    /**
     * 查询 普通编目  默认参数设置
     * @param   libraryId
     * @return CatalogDefaultparam
     */
    CatalogDefaultparam get(@Param("libraryId") Long libraryId);

    /**
     * 新增 普通编目  默认参数设置
     * @param   catalogDefaultparam
     * @return
     */
    void insert(CatalogDefaultparam catalogDefaultparam);

    /**
     * 修改 普通编目  默认参数设置
     * @param   catalogDefaultparam
     * @return
     */
    void update(CatalogDefaultparam catalogDefaultparam);

}
