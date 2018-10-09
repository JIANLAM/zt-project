package com.szcti.lcloud.book.repository;

import com.szcti.lcloud.book.entity.vo.BookCopyVO;
import com.szcti.lcloud.book.entity.vo.BookVO;
import lombok.NonNull;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;


/**
 * @Title: 图书馆图书信息信息DAO
 * @Description: 处理图书馆图书信息信息
 * @author: fengda
 * @date: 2018/5/30 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LibBookDao {
    /**
     * 根据条件查找图书信息
     * @param bookVO
     * @return List<BookVO>
     */
    List<BookVO> findList(BookVO bookVO);

    /**
     * 根据主键查找图书信息
     * @param bookId
     * @return
     */
    BookVO get(Long bookId);

    /**
     * 根据主键查找图书所有的副本
     * @param bookId
     * @return
     */
    List<BookCopyVO> findCopys(@NonNull Long bookId);

    /**
     * 查询本馆图书详情时查询可借阅 可预览 已借出
     * @param bookId
     * @return
     */
    List<BookCopyVO> getBookInfoTable(@Param("status") Long status,@Param("bookId") Long bookId);
}
