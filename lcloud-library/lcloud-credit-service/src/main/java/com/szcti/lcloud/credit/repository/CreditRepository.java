package com.szcti.lcloud.credit.repository;

import com.szcti.lcloud.credit.entity.Credit;
import com.szcti.lcloud.credit.entity.vo.CreditVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface CreditRepository {
    
    int deleteByPrimaryKey(Long id);

    int insert(Credit record);

    Credit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);

    List<CreditVO> queryPage(CreditVO creditVO);

}