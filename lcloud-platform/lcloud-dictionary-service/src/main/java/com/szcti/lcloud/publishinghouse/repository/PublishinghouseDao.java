package com.szcti.lcloud.publishinghouse.repository;

import com.szcti.lcloud.common.utils.RedisUtil;
import com.szcti.lcloud.publishinghouse.entity.PublishinghouseEntity;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @Title: 出版社数据 Dao
 * @Description: 出版社数据 Dao
 * @author: fengda
 * @date: 2018/8/27 3:32
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PublishinghouseDao {

    /**
     * 查询所有出版社 List集合
     * @param publishinghouseEntity
     * @return List<PublishinghouseEntity>
     */
    List<PublishinghouseEntity> findList(PublishinghouseEntity publishinghouseEntity);

    /**
     * 根据ID 查询一条出版社 记录
     * @param   id
     * @return PublishinghouseEntity
     */
    PublishinghouseEntity get(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 出版社 数据记录
     * @param idArray
     */
    void delPublishing(@Param("idArray") Long[] idArray);

    /**
     * 新增一条出版社  数据记录
     * @param   publishinghouseEntity
     * @return
     */
    void insertPublishing(PublishinghouseEntity publishinghouseEntity);

    /**
     * 修改一条出版社  数据记录
     * @param   publishinghouseEntity
     * @return
     */
    void updatePublishing(PublishinghouseEntity publishinghouseEntity);

    /**
     * 是否出现重复的出版社代码
     * @param   code
     * @param id
     * @return
     */
    Integer existsPublishing(@Param("code") String code, @Param("id") Long id);

    /**
     * 从新华书库里获取图书信息
     * @param ISBN
     * @return
     */
    HashMap<String,Object> getFromXHByISBN(String ISBN);

    /**
     * 从出版社信息表里获取出版社信息
     * @param ISBN
     * @return
     */
    HashMap<String,Object> getFromPubByISBN(String ISBN);
}
