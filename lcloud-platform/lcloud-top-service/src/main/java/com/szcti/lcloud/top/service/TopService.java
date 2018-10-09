package com.szcti.lcloud.top.service;

import com.szcti.lcloud.top.entity.vo.TopBookVO;
import java.util.List;
/**
 * 排名
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
public interface TopService {
    List<TopBookVO> topLendBook(TopBookVO topBookVO);//图书借阅排名
    List<TopBookVO> topRecommBook(TopBookVO topBookVO);//图书推荐排名
    List<TopBookVO> topRecommBuyBook(TopBookVO topBookVO);//图书荐购排名
    List<TopBookVO> topLendReader(TopBookVO topBookVO);//读者借阅排名
    List<TopBookVO> topRecommBuyReader(TopBookVO topBookVO);//读者荐购排名
    TopBookVO getBookByIsbn(String isbn);//根据isbn获取书
    void setBookInfo(List<TopBookVO> topBookVOList);//
    List<TopBookVO> topLendBuyBook(TopBookVO topBookVO);//图书借购排行
    List<TopBookVO> topLendBuyReader(TopBookVO topBookVO);//读者借购排行
    String topLendBookExport(List<TopBookVO> topBookVOList);//图书借阅排名导出
    String topRecommBookExport(List<TopBookVO> topBookVOList);//图书推荐排名导出
    String topRecommBuyBookExport(List<TopBookVO> topBookVOList);//图书荐购排名导出
    String topLendReaderExport(List<TopBookVO> topBookVOList);//读者借阅排名导出
    String topRecommBuyReaderExport(List<TopBookVO> topBookVOList);//读者荐购排名导出
    String topLendBuyBookExport(List<TopBookVO> topBookVOList);//图书借购排行导出
    String topLendBuyReaderExport(List<TopBookVO> topBookVOList);//读者借购排行导出
}

