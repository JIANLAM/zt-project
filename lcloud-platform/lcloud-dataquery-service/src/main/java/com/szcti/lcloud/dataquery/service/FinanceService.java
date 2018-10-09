package com.szcti.lcloud.dataquery.service;

import com.szcti.lcloud.dataquery.entity.vo.*;

import java.util.List;

/**
 * @Title: 借还Service
 * @Description: 处理借还信息的Service
 * @author: fengda
 * @date: 2018/8/9 8:50
 */
public interface FinanceService {


    /**
     * 导出财经查询的数据
     * @param finance
     * @return
     */
    String exportExcel(FinanceVO finance);

    /**
     * 导出图书污损或丢失的数据
     * @param forfeitBook
     * @return
     */
    String exportUnusualDataExcel(ForfeitBookVO forfeitBook);

}
