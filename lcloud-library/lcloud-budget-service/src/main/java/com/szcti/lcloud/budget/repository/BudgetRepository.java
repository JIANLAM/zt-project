package com.szcti.lcloud.budget.repository;

import com.szcti.lcloud.budget.entity.Budget;
import com.szcti.lcloud.budget.entity.BudgetCriteria;
import com.szcti.lcloud.budget.entity.vo.BudgetVO;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface BudgetRepository {
    int countByExample(BudgetCriteria example);

    int deleteByExample(BudgetCriteria example);

    int deleteByPrimaryKey(Long id);

    int insert(Budget record);

    int insertSelective(Budget record);

    List<Budget> selectByExample(BudgetCriteria example);

    Budget selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Budget record, @Param("example") BudgetCriteria example);

    int updateByExample(@Param("record") Budget record, @Param("example") BudgetCriteria example);

    int updateByPrimaryKeySelective(Budget record);

    int updateByPrimaryKey(Budget record);

    List<BudgetVO> queryPage(BudgetVO budgetVO);
    String getNextCode(@Param("prefix") String prefix);

    List<String> getPurchaseCode(@Param("id") Long id);
}