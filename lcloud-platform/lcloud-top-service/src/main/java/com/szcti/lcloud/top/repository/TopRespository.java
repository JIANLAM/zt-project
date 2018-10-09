package com.szcti.lcloud.top.repository;

import com.szcti.lcloud.top.entity.vo.TopBookVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 排行列表
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@Mapper
public interface TopRespository {
    List<TopBookVO> topLendBook(TopBookVO topBookVO);//图书借阅排名

    List<TopBookVO> topRecommBook(TopBookVO topBookVO);//图书推荐排名

    List<TopBookVO> topRecommBuyBook(TopBookVO topBookVO);//图书荐购排名

    List<TopBookVO> topLendReader(TopBookVO topBookVO);//读者借阅排名

    List<TopBookVO> topRecommBuyReader(TopBookVO topBookVO);//读者荐购排名

    List<TopBookVO> getPrebook(@Param("isbn") String isbn);

    List<TopBookVO> getBook(@Param("isbn") String isbn);
    List<TopBookVO> topLendBuyBook(TopBookVO topBookVO);
    List<TopBookVO> topLendBuyReader(TopBookVO topBookVO);
}
