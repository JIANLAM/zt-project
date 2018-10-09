package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.Prebook;
import com.szcti.lcloud.purchase.entity.PurchaseBook;
import com.szcti.lcloud.purchase.entity.PurchaseOrder;
import com.szcti.lcloud.purchase.entity.PurchaseOrderCriteria;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
public interface PurchaseOrderService {

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

    List<PurchaseOrderVO> queryPage( PurchaseOrderVO purchaseOrderVO);

    int deleteBatchIds(List<String> strings);

    PurchaseOrderVO selectById(Long orderId);

    int checkBatchIds(Map map);

    int checkRulesIsUse( List<HashMap<String,Object>> rulelist,PurchaseOrderVO purchaseOrderVO);

    int commitBatchIds(Map map);

    int importExcel(List<PurchaseBookVO> list);

    String exportExcel(List<String> ids);


     int delete(Long l);

    int insertBooks(String[] array, Long orderId,Map<String,Object> params);
    void refleshPurchaseOrder(Long orderId);
    Long getPurchaseBookId(Prebook t, Long orderId);
    PurchaseOrder getByPurchaseCode(String purchaseCode);
    Map<String, Object> insertBooks(String[] array, PurchaseOrder p ,Map<String, Object> params);
    String createPurchaseCode(Long ruleId);
    String exportOrderExcel(List<String> list);
    List<PurchaseOrderVO> queryAcceptPage(PurchaseOrderVO purchaseOrderVO);
    void refleshAcceptStatus(Long id);
    void refleshCheckStatus(Long userid,Long orderId);
    List<HashMap<String,Object>> queryBudgetMapList(Map params);
    List<HashMap<String,Object>> queryAddressMapList(Map params);
    /**
     * 新增订购单数据
     */
    int insertPurchaseBook(PurchaseBook purchaseBook);
    
    /**
     * 查询指定订购单数据
     */
    PurchaseBook queryPurchaseBook(PurchaseBook purchaseBook);
    
    int updatePurchaseBook(PurchaseBook purchaseBook);
    
    int deletePurchaseBook(PurchaseBook purchaseBook);
}

