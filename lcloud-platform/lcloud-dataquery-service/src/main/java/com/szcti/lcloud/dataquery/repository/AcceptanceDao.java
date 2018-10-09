package com.szcti.lcloud.dataquery.repository;

import com.szcti.lcloud.dataquery.entity.vo.*;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Title: 验收处理DAO
 * @Description: 验收处理
 * @author: fengda
 * @date: 2018/8/9 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface AcceptanceDao {
    /**
     * 根据条件查找验收记录信息
     * @param acceptanceVO
     * @return List<AcceptanceVO>
     */
    List<AcceptanceVO> findList(AcceptanceVO acceptanceVO);


    /**
     * 查找验收书籍等详情信息
     * @param acceptDetailVO
     * @return List<AcceptDetailVO>
     */
    List<AcceptDetailVO> findDetail(AcceptDetailVO acceptDetailVO);

    /**
     * 根据条件查找退订单信息
     * @param backPurchaseVO
     * @return List<BackPurchaseVO>
     */
    List<BackPurchaseVO> findBackList(BackPurchaseVO backPurchaseVO);

    /**
     * 查找退订书籍等详情信息
     * @param backDetailVO
     * @return List<BackDetailVO>
     */
    List<BackDetailVO> findBackDetail(BackDetailVO backDetailVO);

}
