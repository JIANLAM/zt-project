package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.PurchaseOrder;
import com.szcti.lcloud.purchase.entity.PurchaseOrderCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseOrderVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PurchaseOrderRepository {
    int countByExample(PurchaseOrderCriteria example);

    int deleteByExample(PurchaseOrderCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseOrder record);

    int insertSelective(PurchaseOrder record);

    List<PurchaseOrder> selectByExampleWithBLOBs(PurchaseOrderCriteria example);

    List<PurchaseOrder> selectByExample(PurchaseOrderCriteria example);

    PurchaseOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderCriteria example);

    int updateByExampleWithBLOBs(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderCriteria example);

    int updateByExample(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderCriteria example);

    int updateByPrimaryKeySelective(PurchaseOrder record);

    int updateByPrimaryKeyWithBLOBs(PurchaseOrder record);

    int updateByPrimaryKey(PurchaseOrder record);

    //扩展
    List<PurchaseOrderVO> queryPage(PurchaseOrderVO purchaseOrderVO);

    int deleteBatchIds(@Param("list") List<String> list);

    List<PurchaseOrderVO> selectById(Long id);

    int checkBatchIds(Map map);

    int commitBatchIds(Map map);

    int importExcel(List<PurchaseBookVO> list);

    List<PurchaseOrderVO> exportExcel(List<String> ids);

    int delete(@Param("id") Long id);

    void refleshPurchaseOrder(Long id);

    List<Long> getBooks(BookInfo b);
    PurchaseOrder getByPurchaseCode(String purchaseCode);
}