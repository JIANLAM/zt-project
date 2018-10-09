package com.szcti.lcloud.recommended.service;

import com.szcti.lcloud.recommended.entity.vo.RecommendedBookVO;

/**
 * @Title: 图书馆图书信息Service
 * @Description: 处理图书馆图书信息信息的Service
 * @author: fengda
 * @date: 2018/5/30 8:50
 */
public interface RecommendedService {

    /**
     * 新增一条
     * @param recommendedBookVO
     */
    void save(RecommendedBookVO recommendedBookVO);

    /**
     * 查询一条记录 推荐详情
     * @param
     * @return RecommendedBookVO
     */
    RecommendedBookVO get(RecommendedBookVO recommendedBookVO);



}
