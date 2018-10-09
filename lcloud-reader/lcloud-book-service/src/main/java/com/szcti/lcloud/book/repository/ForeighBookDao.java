package com.szcti.lcloud.book.repository;

import com.szcti.lcloud.book.entity.vo.BookVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @Title: 新华或其他途径图书信息DAO
 * @Description: 处理新华或其他途径图书信息
 * @author: fengda
 * @date: 2018/5/30 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface ForeighBookDao {
    /**
     * 根据条件查找图书信息
     * @param bookVO
     * @return List<BookVO>
     */
    List<BookVO> findList(BookVO bookVO);

    /**
     * 根据主键查找图书信息
     * @param preBookId
     * @return
     */
    BookVO get(Long preBookId);

}
