package com.szcti.lcloud.dataquery.repository;

import com.szcti.lcloud.dataquery.entity.vo.BookVO;

import com.szcti.lcloud.dataquery.entity.vo.HoldingVO;
import com.szcti.lcloud.dataquery.entity.vo.LendRecordVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * @Title: 借还信息DAO
 * @Description: 处理借还书以及续借操作
 * @author: fengda
 * @date: 2018/8/6 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LendDao {
    /**
     * 根据条件查找用户的借阅的图书信息
     * @param book
     * @return List<BookVO>
     */
    List<BookVO> findBookList(BookVO book);

    /**
     * 根据条件查找用户的借阅图书的馆藏信息
     * @param holding
     * @return List<HoldingVO>
     */
    List<HoldingVO> findHoldingList(HoldingVO holding);

    /**
     * 根据用户信息查询借阅图书的馆藏信息
     * @param holding
     * @return List<HoldingVO>
     */
    List<HoldingVO> findHoldingByReader(HoldingVO holding);

    /**
     * 根据条件查找用户的借阅记录
     * @param lendRecord
     * @return List<LendRecordVO>
     */
    List<LendRecordVO> findRecordList(LendRecordVO lendRecord);

    /**
     * 查询读者信息
     * @param readerCardNumber
     * @param cardNumber
     * @return
     */
    HashMap findReader(@Param("readerCardNumber") String readerCardNumber,@Param("cardNumber") String cardNumber);


}
