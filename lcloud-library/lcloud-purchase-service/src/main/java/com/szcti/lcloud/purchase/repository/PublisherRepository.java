package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Publisher;
import com.szcti.lcloud.purchase.entity.PublisherCriteria;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PublisherRepository {
    int countByExample(PublisherCriteria example);

    int deleteByExample(PublisherCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(Publisher record);

    int insertSelective(Publisher record);

    List<Publisher> selectByExample(PublisherCriteria example);

    Publisher selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Publisher record, @Param("example") PublisherCriteria example);

    int updateByExample(@Param("record") Publisher record, @Param("example") PublisherCriteria example);

    int updateByPrimaryKeySelective(Publisher record);

    int updateByPrimaryKey(Publisher record);
}