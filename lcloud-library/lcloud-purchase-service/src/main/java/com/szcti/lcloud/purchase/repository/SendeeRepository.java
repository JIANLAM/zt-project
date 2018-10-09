package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Sendee;
import com.szcti.lcloud.purchase.entity.SendeeCriteria;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface SendeeRepository {
    int countByExample(SendeeCriteria example);

    int deleteByExample(SendeeCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Sendee record);

    int insertSelective(Sendee record);

    List<Sendee> selectByExample(SendeeCriteria example);

    Sendee selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Sendee record, @Param("example") SendeeCriteria example);

    int updateByExample(@Param("record") Sendee record, @Param("example") SendeeCriteria example);

    int updateByPrimaryKeySelective(Sendee record);

    int updateByPrimaryKey(Sendee record);

    Sendee getFromUserId(long userId);
}