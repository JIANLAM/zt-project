package com.szcti.lcloud.parameter.repository;

import com.szcti.lcloud.parameter.entity.BookSize;
import com.szcti.lcloud.parameter.entity.BookSizeCriteria;
import com.szcti.lcloud.parameter.entity.vo.BookSizeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookSizeRepository {
    int countByExample(BookSizeCriteria example);

    int deleteByExample(BookSizeCriteria example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookSize record);

    int insertSelective(BookSize record);

    List<BookSize> selectByExample(BookSizeCriteria example);

    BookSize selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookSize record, @Param("example") BookSizeCriteria example);

    int updateByExample(@Param("record") BookSize record, @Param("example") BookSizeCriteria example);

    int updateByPrimaryKeySelective(BookSize record);

    int updateByPrimaryKey(BookSize record);

    List<BookSizeVO> queryPage(BookSizeVO bookSizeVO);
}