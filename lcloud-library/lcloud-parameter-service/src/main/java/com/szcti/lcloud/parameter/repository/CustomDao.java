package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.CustomTypeEntity;
import com.szcti.lcloud.parameter.entity.vo.CustomVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 自定义字段设置 Dao
 * @Description: 自定义字段设置 Dao
 * @author: wangsiyi
 * @date: 2018/7/31 17:02
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CustomDao {

    /**
     * 查询自定义字段设置List集合
     * @param
     * @return List<BasicParamEntity>
     */
    List<CustomVO> findList(CustomVO customVO);

    /**
     * 查询指定自定义字段   类型
     * @param
     * @return String
     */
    CustomTypeEntity findTypeList(@Param("type") String type,@Param("libraryId") Long libraryId);

    /**
     * 根据主键删除一条 或多条 自定义字段设置 数据记录
     * @param idArray
     */
    void delCustom(@Param("idArray") Long[] idArray);

    /**
     * 新增一条自定义字段设置  数据记录
     * @param   customVO
     * @return
     */
    void insertCustom(CustomVO customVO);

    /**
     * 新增一条自定义字段    类型设置  数据记录
     * @param   customTypeEntity
     * @return
     */
    void insertCustomType(CustomTypeEntity customTypeEntity);

    /**
     * 修改一条自定义字段设置  数据记录
     * @param   customVO
     * @return
     */
    void updateCustom(CustomVO customVO);

    /**
     * 修改一条自定义字段类型设置  数据记录
     * @param   libraryId,description,type
     * @return
     */
    void updateCustomType(@Param("libraryId") Long libraryId,@Param("description") String description,@Param("type") String type);

    /**
     * 新增同一类型 是否出现相同的键值
     * @param   type , value
     * @return
     */
    Integer existsValue(@Param("type") String type, @Param("value") String value, @Param("id") Long id,@Param("libraryId") Long libraryId);

}
