package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.Catalog;
import com.szcti.lcloud.catalog.entity.CatalogCriteria;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.LendBuyBookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface CatalogRepository {

    int deleteByPrimaryKey(Long id);
    int insert(Catalog record);
    List<Catalog> selectByExample(CatalogCriteria example);
    Catalog selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Catalog record);
    public List<CatalogVO> queryPage(CatalogVO vo);
    public List<LendBuyBookVO> queryLendBuyPage(LendBuyBookVO vo);
    public List<LendBuyBookVO> lendBuyBackBook(LendBuyBookVO vo);
}