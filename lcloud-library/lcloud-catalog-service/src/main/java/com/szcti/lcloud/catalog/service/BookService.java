package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.BookCriteria;
import com.szcti.lcloud.catalog.entity.vo.BookVO;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.PrebookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BookService
{
    
    int deleteByPrimaryKey(Long id);
    
    int insert(Book record);
    
    List<Book> selectByExample(BookCriteria example);
    
    Book selectByPrimaryKey(Long id);
    
    int updateByPrimaryKey(Book record);
    
    List<Book> queryPage(Book vo);
    
    List<Book> getBookInfo(CatalogVO vo);
    
    List<BookVO> foreignBookPage(BookVO vo);
    
    int insertPreBook(PrebookVO book);
    
    /**
     * 
     * @描述：获取采访库书数据
     * @作者：tianbw
     * @时间：2018年8月28日 上午11:03:28
     * @param vo
     * @return
     */
    List<PrebookVO> queryPagePreBook(CatalogVO b);



    /**
     *
     * @描述：导出中央库书目数据列表
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param catalogVO
     * @return
     */
    String exportBookExcel(CatalogVO catalogVO);

    /**
     *
     * @描述：导出采访库书目数据列表
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param catalogVO
     * @return
     */
    String exportPreBookExcel(CatalogVO catalogVO);

    /**
     * @描述：下载导入书目 模板
     * @作者：wangsiyi
     * @时间：2018年8月31日 上午11:04:28
     */
    String exportMouldExcel();

    /**
     *批量导入 中央书库书目 数据记录
     * @param   maplist
     * @return
     */
    String importBooklist(List<Map> maplist , Long libraryId , Long createBy , String importType);

    /**
     *批量导入 采访书库书目 数据记录
     * @param   maplist
     * @return
     */
    String importPrebooklist(List<Map> maplist , Long libraryId , Long createBy , String importType);

    /**
     *
     * @描述：导出中央库书目数据列表      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param catalogVO
     * @return
     */
    String exportBookMARC(CatalogVO catalogVO);

    /**
     *
     * @描述：导出采访库书目数据列表      MARC
     * @作者：wangsiyi
     * @时间：2018年8月30日 上午11:04:28
     * @param catalogVO
     * @return
     */
    String exportPrebookMARC(CatalogVO catalogVO);


}
