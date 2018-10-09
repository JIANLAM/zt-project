package com.szcti.lcloud.dataquery.repository;

import com.szcti.lcloud.dataquery.entity.vo.*;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * @Title: 财经记录DAO
 * @Description: 处理财经记录
 * @author: fengda
 * @date: 2018/8/8 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface FinanceDao {
    /**
     * 根据条件查找用户的财经记录信息
     * @param finance
     * @return List<FinanceVO>
     */
    List<FinanceVO> findList(FinanceVO finance);

    /**
     * 根据条件查找用户的污损记录信息
     * @param forfeitBook
     * @return List<ForfeitBookVO>
     */
    List<ForfeitBookVO> findForfeitList(ForfeitBookVO forfeitBook);



}
