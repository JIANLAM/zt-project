package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.PurchaseBook;
import com.szcti.lcloud.purchase.entity.PurchaseBookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class)
public interface PurchaseBookRepository
{
    int countByExample(PurchaseBookCriteria example);
    
    int deleteByExample(PurchaseBookCriteria example);
    
    int deleteByPrimaryKey(Long id);
    
    int insert(PurchaseBook record);
    
    int insertSelective(PurchaseBook record);
    
    List<PurchaseBook> selectByExample(PurchaseBookCriteria example);
    
    PurchaseBook selectByPrimaryKey(Long id);
    
    int updateByExampleSelective(@Param("record") PurchaseBook record, @Param("example") PurchaseBookCriteria example);
    
    int updateByExample(@Param("record") PurchaseBook record, @Param("example") PurchaseBookCriteria example);
    
    int updateByPrimaryKeySelective(PurchaseBook record);
    
    int updateByPrimaryKey(PurchaseBook record);
    
    //扩展
    List<PurchaseBookVO> queryPage(PurchaseBookVO purchaseBookVO);
    
    int importExcel(List<PurchaseBook> list);
    
    List<PurchaseBookVO> exportExcel(List<String> ids);
    
    List<Long> getBooks(BookInfo b);
    
    List<Long> getDupBooks(BookInfo b);
    
    List<PurchaseBookVO> queryLibraryBookPage(PurchaseBookVO purchaseBookVO);
    
    List<PurchaseBookVO> getAcceptBook(PurchaseBookVO purchaseBookVO);
    
    List<Long> getPurchasingBooks(BookInfo b);
    
    int insertPurchaseBook(PurchaseBook purchaseBook);
    
    PurchaseBook queryPurchaseBook(PurchaseBook purchaseBook);
    
    int updatePurchaseBook(PurchaseBook purchaseBook);
    
    int deletePurchaseBook(PurchaseBook purchaseBook);
    
    List<PurchaseBookVO> getPageBook(PurchaseBookVO purchaseBookVO);
    
    List<PurchaseBookVO> queryPagePreBook(PurchaseBookVO purchaseBookVO);
    
    List<PurchaseBookVO> queryPageBookXH(PurchaseBookVO purchaseBookVO);
    
}
