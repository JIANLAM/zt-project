package com.szcti.lcloud.catalog.repository;

import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.BookCriteria;
import com.szcti.lcloud.catalog.entity.vo.BookVO;
import com.szcti.lcloud.catalog.entity.vo.PrebookVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface BookRepository {

    int deleteByPrimaryKey(Long id);
    /**
     * 
     * @描述：新增书籍信息
     * @作者：tianbw
     * @时间：2018年8月27日 上午11:47:29
     * @param record
     * @return
     */
    int insert(Book record);
    List<Book> selectByExample(BookCriteria example);
    Book selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Book record);
    /**
     *
     * @描述：查询一条采访库记录
     * @作者：wangsiyi
     * @时间：2018年9月10日 上午11:47:29
     * @param id
     * @return
     */
    PrebookVO getPreBook(Long id);
    /**
     * 
     * @描述：查询书籍列表
     * @作者：tianbw
     * @时间：2018年8月25日 下午4:32:11
     * @param vo
     * @return
     */
    List<Book> queryPage(Book vo);
    List<BookVO> foreignBookPage(BookVO vo);
    int insertPreBook(PrebookVO book);
    List<PrebookVO> queryPagePreBook(PrebookVO b);
 
    /**
     * 
     * @描述：获取指定书信息
     * @作者：tianbw
     * @时间：2018年8月25日 下午4:31:32
     * @param book
     * @return
     */
    Book queryBook(Book book);
}