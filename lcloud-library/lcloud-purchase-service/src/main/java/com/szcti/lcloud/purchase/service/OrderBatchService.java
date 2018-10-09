package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.OrderBatch;
import com.szcti.lcloud.purchase.entity.OrderBatchCriteria;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;

import java.util.List;

public interface OrderBatchService {

    int deleteByPrimaryKey(Long id);

    int insert(OrderBatch record);

    List<OrderBatch> selectByExample(OrderBatchCriteria example);

    OrderBatch selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderBatch record);

    List<OrderBatchVO> queryPage(OrderBatchVO orderBatchVO);

    String exportExcel(List list);
}