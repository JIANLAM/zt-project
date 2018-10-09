package com.szcti.lcloud.dataquery.service;

import com.szcti.lcloud.dataquery.entity.vo.BookVO;
import com.szcti.lcloud.dataquery.entity.vo.HoldingVO;
import com.szcti.lcloud.dataquery.entity.vo.LendRecordVO;

import java.util.List;

/**
 * @Title: 借阅数据查询Service
 * @Description: 处理借阅数据查询的Service
 * @author: fengda
 * @date: 2018/8/6 8:50
 */
public interface LendService {

    /**
     * 查找用户的借还信息
     * @param lendBackVO
     * @return List<LendBackVO>
     */
    List<BookVO> findBookList(BookVO lendBackVO);

    /**
     * 导出借阅图书的馆藏数据
     * @param holding
     * @return
     */
    String exportHoldingExcel(HoldingVO holding);

    /**
     * 导出借阅图书的借阅记录数据
     * @param record
     * @return
     */
    String exportRecordExcel(LendRecordVO record);

    /**
     * 导出读者借阅数据
     * @param holding
     * @return
     */
    String exportReaderLendExcel(HoldingVO holding);

}
