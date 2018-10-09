package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.OrderbatchBook;
import com.szcti.lcloud.purchase.entity.OrderbatchBookCriteria;
import com.szcti.lcloud.purchase.entity.vo.OrderbatchBookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface OrderbatchBookRepository {
    int deleteByPrimaryKey(Long id);

    int insert(OrderbatchBook record);

    List<OrderbatchBook> selectByExample(OrderbatchBookCriteria example);

    OrderbatchBook selectByPrimaryKey(Long id);

    int updateByPrimaryKey(OrderbatchBook record);
    List<OrderbatchBookVO>queryPage(OrderbatchBookVO orderbatchBookVO);
}