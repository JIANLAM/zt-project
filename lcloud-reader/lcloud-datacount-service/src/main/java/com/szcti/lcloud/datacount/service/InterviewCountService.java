package com.szcti.lcloud.datacount.service;


import com.szcti.lcloud.datacount.entity.vo.ConditionVO;

/**
 * @Title: 采访统计Service
 * @Description: 各种统计的Service
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
public interface InterviewCountService {
    /**
     * 导出统计数据
     * @param conditionVO
     * @return String
     */
    String exports(ConditionVO conditionVO);

}
