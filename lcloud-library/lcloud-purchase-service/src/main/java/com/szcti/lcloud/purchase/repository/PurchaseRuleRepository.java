package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.PurchaseRuleCriteria;
import com.szcti.lcloud.purchase.entity.vo.PurchaseRuleVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface PurchaseRuleRepository {
    int countByExample(PurchaseRuleCriteria example);

    int deleteByExample(PurchaseRuleCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(PurchaseRule record);

    int insertSelective(PurchaseRule record);

    List<PurchaseRule> selectByExample(PurchaseRuleCriteria example);

    PurchaseRule selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PurchaseRule record, @Param("example") PurchaseRuleCriteria example);

    int updateByExample(@Param("record") PurchaseRule record, @Param("example") PurchaseRuleCriteria example);

    int updateByPrimaryKeySelective(PurchaseRule record);

    int updateByPrimaryKey(PurchaseRule record);

    //扩展
    List<PurchaseRuleVO> queryPage(PurchaseRuleVO purchaseRuleVO);
    int deleteBatchIds(List<String> strings);

    int insert(PurchaseRuleVO purchaseRuleVO);

    int updateById(PurchaseRuleVO purchaseRuleVO);

    PurchaseRuleVO selectById(Long id);

    int importExcel(List<PurchaseRuleVO> list);

    List<PurchaseRuleVO> exportExcel(List<String> ids);

    List<HashMap<String,Object>> queryMapList(Map params);
}