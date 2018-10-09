package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.vo.PurchaseRuleVO;

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
public interface PurchaseRuleService {

    List<PurchaseRuleVO> queryPage(PurchaseRuleVO purchaseRuleVO);

    int deleteBatchIds(List<String> strings);

    int deleteByPrimaryKey(Long ruleId);

    int insert(PurchaseRule purchaseRule);

    PurchaseRule selectByPrimaryKey(Long ruleId);

    int updateByPrimaryKeySelective(PurchaseRule purchaseRule);

    int updateByPrimaryKey(PurchaseRule purchaseRule);

    int importExcel(List<PurchaseRuleVO> list);

    List<PurchaseRuleVO> exportExcel(List<String> ids);

    List<HashMap<String,Object>> queryMapList(Map params);

    void getDataBean(PurchaseRule purchaseRule, PurchaseRuleVO purchaseRuleVO);
    void getPurchaseRuleVO(PurchaseRuleVO vo, PurchaseRule ro);

    PurchaseRuleVO selectEdit(Long id);

    PurchaseRuleVO add();
}

