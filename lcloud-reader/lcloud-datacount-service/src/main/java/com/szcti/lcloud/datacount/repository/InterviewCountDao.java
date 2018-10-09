package com.szcti.lcloud.datacount.repository;

import com.szcti.lcloud.datacount.entity.vo.*;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;


/**
 * @Title: 采访统计DAO
 * @Description: 各种统计
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface InterviewCountDao {
    /**
     * 根据条件 查询订购单统计
     * @param conditionVO
     * @return List<PurchaseOrderVO>
     */
    List<PurchaseOrderVO> purchaseOrderCount(ConditionVO conditionVO);

    /**
     * 根据条件 查询借购单统计
     * @param conditionVO
     * @return List<LendBuyOrderVO>
     */
    List<LendBuyOrderVO> lendBuyOrderCount(ConditionVO conditionVO);

    /**
     * 根据条件 查询图书借购统计
     * @param conditionVO
     * @return List<LendBuyBookVO>
     */
    List<LendBuyBookVO> bookLendBuyCount(ConditionVO conditionVO);

    /**
     * 根据条件 查询图书荐购统计
     * @param conditionVO
     * @return List<RecommBuyBookVO>
     */
    List<RecommBuyBookVO> recommBuyCount(ConditionVO conditionVO);
}
