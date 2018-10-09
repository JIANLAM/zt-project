package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.OrderbatchBook;
import com.szcti.lcloud.purchase.entity.OrderbatchBookCriteria;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;
import com.szcti.lcloud.purchase.entity.vo.OrderbatchBookVO;

import java.util.List;
import java.util.Map;

public interface OrderbatchBookService {

    int deleteByPrimaryKey(Long id);

    int insert(OrderbatchBook record);

    List<OrderbatchBook> selectByExample(OrderbatchBookCriteria example);

    OrderbatchBook selectByPrimaryKey(Long id);
    int updateByPrimaryKey(OrderbatchBook record);
    List<OrderbatchBookVO>queryPage(OrderbatchBookVO orderbatchBookVO);
    int acceptQty(String[] array, Long orderBuyId,Map<String, Object> params);
    String exportExcel(List<String> list);
    int acceptBookQty(OrderBatchVO vo);
}