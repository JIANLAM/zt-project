package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.Holding;
import com.szcti.lcloud.purchase.entity.HoldingCriteria;
import com.szcti.lcloud.purchase.entity.vo.HoldingVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface HoldingRepository {
    int countByExample(HoldingCriteria example);

    int deleteByExample(HoldingCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Holding record);

    int insertSelective(Holding record);

    List<Holding> selectByExample(HoldingCriteria example);

    Holding selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Holding record, @Param("example") HoldingCriteria example);

    int updateByExample(@Param("record") Holding record, @Param("example") HoldingCriteria example);

    int updateByPrimaryKeySelective(Holding record);

    int updateByPrimaryKey(Holding record);
    //扩展方法
    List<HoldingVO> queryPage(HoldingVO vo);

}