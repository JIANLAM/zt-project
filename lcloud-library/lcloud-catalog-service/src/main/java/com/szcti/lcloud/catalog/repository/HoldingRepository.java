package com.szcti.lcloud.catalog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.szcti.lcloud.catalog.entity.Holding;
import com.szcti.lcloud.catalog.entity.HoldingCriteria;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;

@Mapper
public interface HoldingRepository
{
    
    int deleteByPrimaryKey(Long id);
    
    int insert(Holding record);
    
    int insertSelective(Holding record);
    
    List<Holding> selectByExample(HoldingCriteria example);
    
    Holding selectByPrimaryKey(Long id);
    
    int updateByPrimaryKey(Holding record);
    
    List<HoldingVO> queryPage(HoldingVO vo);
    
    List<LableVO> getMaxBarcode(LableVO lableVO);
    
    List<HoldingVO> getPrintLabel(LableVO lableVO);
    
    LableVO queryCurrBarcode(LableVO lableVO);
    
    List<HoldingVO> queryHoldingVO(HoldingVO vo);
    
    List<HoldingVO> queryHoldingList(HoldingVO vo);
}
