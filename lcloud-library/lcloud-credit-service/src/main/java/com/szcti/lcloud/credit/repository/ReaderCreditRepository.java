package com.szcti.lcloud.credit.repository;

import com.szcti.lcloud.credit.entity.ReaderCredit;
import com.szcti.lcloud.credit.entity.vo.ReaderCreditVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface ReaderCreditRepository {
    
    int deleteByPrimaryKey(Long id);

    int insert(ReaderCredit record);

    ReaderCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReaderCredit record);

    int updateByPrimaryKey(ReaderCredit record);

    List<ReaderCreditVO> queryPage(ReaderCreditVO readerCreditVO);

}