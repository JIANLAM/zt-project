package com.szcti.lcloud.recommended.repository;

import java.util.List;

import com.szcti.lcloud.recommended.entity.vo.RecommReasonInfoVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import com.szcti.lcloud.recommended.entity.vo.RecommendedBookVO;
import org.apache.ibatis.annotations.Param;


/**
 * @Title: 图书馆图书信息信息DAO
 * @Description: 处理图书馆图书信息信息
 * @author: fengda
 * @date: 2018/5/30 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface RecommendedDao {
    /**
     * 根据条件查找推荐图书信息
     * @param recommendedBookVO
     * @return List<RecommendedBookVO>
     */
    List<RecommendedBookVO> findList(RecommendedBookVO recommendedBookVO);

    /**
     * 查询一条记录 推荐详情
     * @param recommendedBookVO
     * @return RecommendedBookVO
     */

    RecommendedBookVO get(RecommendedBookVO recommendedBookVO);

    /**
     * 查询一条记录 推荐详情  推荐理由集合接口
     * @param
     * @return List<RecommReasonInfoVO>
     */

    List<RecommReasonInfoVO> recommReasonList(@Param("bookId") Long bookId, @Param("recommType") Integer recommType);

    /**
     * 插入一条数据
     * @param recommendedBookVO
     */
    void insert(RecommendedBookVO recommendedBookVO);


}
