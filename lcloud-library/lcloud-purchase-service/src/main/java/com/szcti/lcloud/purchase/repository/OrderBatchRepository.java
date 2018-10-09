package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.OrderBatch;
import com.szcti.lcloud.purchase.entity.OrderBatchCriteria;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface OrderBatchRepository {

    int deleteByPrimaryKey(Long id);

    int insert(OrderBatch record);

    List<OrderBatch> selectByExample(OrderBatchCriteria example);

    OrderBatch selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderBatch record);

    List<OrderBatchVO> queryPage(OrderBatchVO orderBatchVO);
}