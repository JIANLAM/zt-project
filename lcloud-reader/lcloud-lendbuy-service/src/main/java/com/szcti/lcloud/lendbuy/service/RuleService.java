package com.szcti.lcloud.lendbuy.service;


import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;

import java.util.Map;

/**
 * @Title: 借购规则Service
 * @Description: 处理借购规则的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface RuleService {


    /**
     * 新增或修改规则信息
     * @param lendBuyRuleVO
     * @return
     */
    boolean save(LendBuyRuleVO lendBuyRuleVO);

    /**
     * 删除多条规则
     * @param ids
     */
    void delete(String ids);

    /**
     * 改变规则的启用状态
     * @param id
     * @param status
     */
    void changeStatus(Long id,Integer status);

    /**
     * 审核借购单图书
     * @param id 借购单中已选图书id
     * @param m 检查结果集
     * @param userId
     * @param readerType
     * @param libId
     * @return
     */
    boolean autoCheck(Long id,Map m,Long userId,Integer readerType,Long libId);


}
