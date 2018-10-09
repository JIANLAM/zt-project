package com.szcti.lcloud.datacount.service;


import com.szcti.lcloud.datacount.entity.vo.CirculationVO;
import java.util.List;

/**
 * @Title: 流通统计Service
 * @Description: 各种统计的Service
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
public interface CirculationCountService {

    /**
     * 图书借阅统计
     * @param circulationVO
     * @return String
     */
    List<Object> bookLendCount(CirculationVO circulationVO, Integer selectType);

    /**
     * 读者借阅统计
     * @param circulationVO
     * @return String
     */
    List<Object> readerLendCount(CirculationVO circulationVO, Integer selectType,Integer choiceCount);

    /**
     * 读者污损统计
     * @param circulationVO
     * @return String
     */
    List<Object> financeCount(CirculationVO circulationVO,Integer selectType);

    /**
     * 导出读者借阅统计数据
     * @param circulationVO
     * @return String
     */
    String bookLendExport(CirculationVO circulationVO , Integer selectType ,Integer choiceCount);

}
