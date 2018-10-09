package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;
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
public interface BasicParamDao {

    /**
     * 查询自定义字段设置List集合
     * @param
     * @return List<BasicParamEntity>
     */
    List<BasicParamEntity> findList(BasicParamEntity BasicParamEntity);

    /**
     * 根据ID 查询一条自定义字段设置 记录
     * @param   id
     * @return BasicParamEntity
     */
    BasicParamEntity get(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 自定义字段设置 数据记录
     * @param idArray
     */
    void delCustom(@Param("idArray") Long[] idArray);

    /**
     * 新增一条自定义字段设置  数据记录
     * @param   BasicParamEntity
     * @return
     */
    void insertCustom(BasicParamEntity BasicParamEntity);

    /**
     * 修改一条自定义字段设置  数据记录
     * @param   BasicParamEntity
     * @return
     */
    void updateCustom(BasicParamEntity BasicParamEntity);

    /**
     * 新增同一类型 是否出现相同的键值
     * @param   type , value
     * @return
     */
    Integer existsValue(@Param("type") String type,@Param("value") String value,@Param("id") Long id,@Param("libraryId") Long libraryId);

    /**************************************************        读者证类型         ***************************************************/

    /**
     *  查询读者证类型
     * @param
     * @return List<ReaderCardEntity>
     */
    List<ReaderCardEntity> readerCardList(ReaderCardEntity readerCardEntity);

    /**
     *  查询读者证类型 一条数据
     * @param
     * @return ReaderCardEntity
     */
    ReaderCardEntity readerCardGet(@Param("id") Long id);

    /**
     * 删除 一条或多条 读者证类型
     * @param idArray
     */
    void delReaderCard(@Param("idArray") Long[] idArray);

    /**
     * 新增 一条 读者证类型
     * @param   readerCardEntity
     * @return
     */
    void insertReaderCard(ReaderCardEntity readerCardEntity);

    /**
     * 修改一条自定义字段设置  数据记录
     * @param   readerCardEntity
     * @return
     */
    void updateReaderCard(ReaderCardEntity readerCardEntity);

    /**
     * 新增读者证类型 是否出现相同的键值
     * @param    value
     * @return
     */
    Integer existsReaderValue(@Param("value") String value,@Param("id") Long id,@Param("libraryId") Long libraryId);

}
