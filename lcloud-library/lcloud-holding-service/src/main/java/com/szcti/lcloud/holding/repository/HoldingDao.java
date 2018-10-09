package com.szcti.lcloud.holding.repository;

import com.szcti.lcloud.holding.entity.vo.BookCopyVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * @Title: 馆藏管理DAO
 * @Description: 处理馆藏信息
 * @author: fengda
 * @date: 2018/7/26 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface HoldingDao {

    /**
     * 查找图书所有的副本
     * @param bookCopyVO
     * @return
     */
    List<BookCopyVO> findList(BookCopyVO bookCopyVO);

    /**
     * 根据多个id查找图书副本
     * @param idArray
     * @return
     */
    List<BookCopyVO> findCopysByIds(@Param("idArray") Long[] idArray);

    /**
     * 根据主键查询单个副本
     * @param id
     * @return
     */
    BookCopyVO get(Long id);

    /**
     * 根据馆藏id查询当前借阅信息
     * @param holdingId
     * @return
     */
    BookCopyVO getLendInfo(Long holdingId);

    /**
     * 批量删除馆藏书籍
     * @param idArray
     * @return Integer
     */
    Integer delete(@Param("idArray") Long[] idArray);

    /**
     * 批量修改馆藏书籍
     * @param bookCopyVO
     * @param idArray
     * @return
     */
    Integer update(@Param("bookCopyVO")BookCopyVO bookCopyVO,@Param("idArray") Long[] idArray);

    /**
     * 根据读者卡号查找读者ID
     * @param readerCardNumber
     * @return
     */
    HashMap<String,Long> getReaderIdByCard(@Param("readerCardNumber")String readerCardNumber);

    /**
     * 修改借阅记录
     * @param lendInfo
     * @param idArray
     * @return
     */
    Integer updateLend(@Param("lendInfo")BookCopyVO lendInfo,@Param("idArray") Long[] idArray);

    /**
     * 删除借阅记录
     * @param readerId
     * @param idArray
     * @return
     */
    Integer deleteLend(@Param("readerId")Long readerId,@Param("idArray") Long[] idArray);

    /**
     * 新增借阅记录
     * @param bookCopyVO
     * @return
     */
    Integer insertLend(BookCopyVO bookCopyVO);

}
