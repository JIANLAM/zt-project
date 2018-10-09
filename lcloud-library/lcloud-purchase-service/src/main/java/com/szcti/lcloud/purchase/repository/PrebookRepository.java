package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Prebook;
import com.szcti.lcloud.purchase.entity.PrebookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PrebookRepository {
    int countByExample(PrebookCriteria example);

    int deleteByExample(PrebookCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Prebook record);

    int insertSelective(Prebook record);

    List<Prebook> selectByExampleWithBLOBs(PrebookCriteria example);

    List<Prebook> selectByExample(PrebookCriteria example);

    Prebook selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Prebook record, @Param("example") PrebookCriteria example);

    int updateByExampleWithBLOBs(@Param("record") Prebook record, @Param("example") PrebookCriteria example);

    int updateByExample(@Param("record") Prebook record, @Param("example") PrebookCriteria example);

    int updateByPrimaryKeySelective(Prebook record);

    int updateByPrimaryKeyWithBLOBs(Prebook record);

    int updateByPrimaryKey(Prebook record);

    List<PrebookVO> queryPage(PrebookVO prebookVO);

    int deleteBatchIds(List<String> strings);

    List<Long> getPrebookId(BookInfo b);
}