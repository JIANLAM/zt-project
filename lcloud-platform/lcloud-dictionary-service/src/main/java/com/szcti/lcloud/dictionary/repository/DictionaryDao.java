package com.szcti.lcloud.dictionary.repository;

import com.szcti.lcloud.dictionary.entity.DictionaryEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 数据字典 Dao
 * @Description: 数据字典 Dao
 * @author: wangsiyi
 * @date: 2018/7/30 10:26
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface DictionaryDao {

    /**
     * 查询数据字典   类型 List集合
     * @param
     * @return List<String>
     */
    List<String> findTypeList();

    /**
     * 查询数据字典List集合
     * @param dictionaryEntity
     * @return List<DictionaryEntity>
     */
    List<DictionaryEntity> findList(DictionaryEntity dictionaryEntity);

    /**
     * 根据ID 查询一条数据字典 记录
     * @param   id
     * @return DictionaryEntity
     */
    DictionaryEntity get(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 数据字典 数据记录
     * @param idArray
     */
    void delDictionary(@Param("idArray") Long[] idArray);

    /**
     * 新增一条数据字典  数据记录
     * @param   dictionaryEntity
     * @return
     */
    void insertTitleNumber(DictionaryEntity dictionaryEntity);

    /**
     * 修改一条数据字典  数据记录
     * @param   dictionaryEntity
     * @return
     */
    void updateTitleNumber(DictionaryEntity dictionaryEntity);

    /**
     * 新增同一类型 是否出现相同的键值
     * @param   type , value
     * @return
     */
    Integer existsValue(@Param("type") String type, @Param("value") String value, @Param("id") Long id);

}
