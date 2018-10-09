package com.szcti.lcloud.account.repository;

import com.szcti.lcloud.account.entity.SendeeEntity;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Title: 收件人DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface SendeeDao {

    /**
     * 查询某用户信息下的所有收件人信息
     * @param peopleId
     * @return
     */
    List<SendeeEntity> findList(Long peopleId);

    /**
     * 根据主键查询一条收件人信息
     * @param sendeeId
     * @return
     */
    SendeeEntity get(Long sendeeId);

    /**
     * 新增收件人信息
     * @param sendeeEntity
     */
    void insert(SendeeEntity sendeeEntity);

    /**
     * 修改收件人信息
     * @param sendeeEntity
     */
    void update(SendeeEntity sendeeEntity);

    /**
     * 查询此用户是否存在默认地址
     * @param userID
     */
    SendeeEntity getIsDefault(Long userID);

    /**
     * 修改默认地址       为非默认  is_default = 0
     * @param peopleId
     */
    void cancelDefault(Long peopleId);

    /**
     * 修改默认地址       为默认  is_default = 1
     * @param sendeeId
     */
    void setDefault(Long sendeeId);

    /**
     * 删除收件人信息
     * @param sendeeId
     */
    void delete(Long sendeeId);
}
