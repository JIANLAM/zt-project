package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.MarcMouldEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @Title: mrc模板 Dao
 * @Description: mrc模板 Dao
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface MarcTempletDao {

    /*************************************************    MARC字段       ***************************************************/

    /**
     * 查询mrc字段 List集合
     * @param
     * @return List<MarcFieldEntity>
     */
    List<MarcFieldEntity> findList(@Param("libraryId") Long libraryId);

    /**
     * 根据ID 查询一条mrc字段  记录
     * @param   id
     * @return MarcFieldEntity
     */
    MarcFieldEntity get(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 mrc字段 数据记录
     * @param idArray
     */
    void delMarc(@Param("idArray") Long[] idArray);

    /**
     * 新增一条mrc字段  数据记录
     * @param   marcFieldEntity
     * @return
     */
    void insertMarc(MarcFieldEntity marcFieldEntity);

    /**
     * 修改一条mrc字段 数据记录
     * @param   marcFieldEntity
     * @return
     */
    void updateMarc(MarcFieldEntity marcFieldEntity);

    /**
     *   MARC字段      同一图书馆 代码不能有相同的
     * @param
     * @return Integer
     */
    Integer existsMarcName(@Param("name") String name , @Param("id") Long id , @Param("libraryId") Long libraryId);

    /*************************************************    MARC模版       ***************************************************/

    /**
     * 查询mrc模板List集合
     * @param
     * @return List<MarcMouldEntity>
     */
    List<MarcMouldEntity> findMouldList(@Param("libraryId") Long libraryId);

    /**
     * 根据ID 查询一条mrc模板 记录
     * @param   id
     * @return MarcFieldEntity
     */
    MarcMouldEntity getMould(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 mrc模板数据记录
     * @param idArray
     */
    void delMarcMould(@Param("idArray") Long[] idArray);

    /**
     * 新增一条mrc模板 数据记录
     * @param   marcMouldEntity
     * @return
     */
    void insertMarcMould(MarcMouldEntity marcMouldEntity);

    /**
     * 修改一条mrc模板 数据记录
     * @param   marcMouldEntity
     * @return
     */
    void updateMarcMould(MarcMouldEntity marcMouldEntity);

    /**
     *  MARC模板      同一图书馆 代码不能有相同的
     * @param
     * @return Integer
     */
    Integer existsMarcValue(@Param("value") String value , @Param("id") Long id , @Param("libraryId") Long libraryId);

    /**
     *  MARC模板     启用一个模版 设为默认
     * @param
     * @return
     */
    void openMould(@Param("id") Long id );

    /**
     *  MARC模板     启用时修改其他状态为禁用
     * @param
     * @return
     */
    void colseAll(@Param("libraryId") Long libraryId);

    /**
     *  MARC模板     禁用一个模版 设为禁用
     * @param
     * @return
     */
    void colseMould(@Param("id") Long id );

}
