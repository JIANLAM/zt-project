package com.szcti.lcloud.budget.service;

import com.szcti.lcloud.budget.entity.Budget;
import com.szcti.lcloud.budget.entity.BudgetCriteria;
import com.szcti.lcloud.budget.entity.vo.BudgetVO;

import java.util.List;

public interface BudgetService {
    int countByExample(BudgetCriteria example);

    int deleteByExample(BudgetCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Budget record);

    int insertSelective(Budget record);

    List<Budget> selectByExample(BudgetCriteria example);

    Budget selectByPrimaryKey(Long id);

    int updateByExampleSelective(Budget record, BudgetCriteria example);

    int updateByExample(Budget record,BudgetCriteria example);

    int updateByPrimaryKeySelective(Budget record);

    int updateByPrimaryKey(Budget record);

    List<BudgetVO> queryPage(BudgetVO budgetVO);

    BudgetVO selectById(Long id);

    String exportExcel(List list);
    String getNextCode(String prefix);
}