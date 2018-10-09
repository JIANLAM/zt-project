package com.szcti.lcloud.purchase.repository;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface BudgetRepository {
    List<HashMap<String,Object>> queryMapList(Map params);
}