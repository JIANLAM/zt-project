package com.szcti.lcloud.catalog.service.impl;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.szcti.lcloud.common.utils.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.BookCriteria;
import com.szcti.lcloud.catalog.entity.vo.BookVO;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.PrebookVO;
import com.szcti.lcloud.catalog.repository.BookRepository;
import com.szcti.lcloud.catalog.service.BookService;

@Service("bookService")
public class BookServiceImp implements BookService
{
    @Autowired
    BookRepository bookRepository;
    
    @Override
    public int deleteByPrimaryKey(Long id)
    {
        return bookRepository.deleteByPrimaryKey(id);
    }
    
    @Override
    public int insert(Book record)
    {
        return bookRepository.insert(record);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Book> getBookInfo(CatalogVO vo)
    {
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize(), true);
        Book bvo = new Book();
        bvo.setId(vo.getBookId());
        bvo.setIsbn(vo.getIsbn());
        bvo.setTitle(vo.getTitle());
        bvo.setAuthor(vo.getAuthor());
        bvo.setBookType(vo.getBookType());

        //wangsiyi
        bvo.setBookType(vo.getBookType());
        bvo.setBookTypeStar(vo.getBookTypeStar());
        bvo.setBookTypeEnd(vo.getBookTypeEnd());
        bvo.setOwnlib(vo.getLibraryId());
        return bookRepository.queryPage(bvo);
    }
    
    @Override
    public List<Book> selectByExample(BookCriteria example)
    {
        return bookRepository.selectByExample(example);
    }
    
    @Override
    public Book selectByPrimaryKey(Long id)
    {
        return bookRepository.selectByPrimaryKey(id);
    }
    
    @Override
    public int updateByPrimaryKey(Book record)
    {
        return bookRepository.updateByPrimaryKey(record);
    }
    
    @Override
    public List<Book> queryPage(Book vo)
    {
        return bookRepository.queryPage(vo);
    }
    
    @Override
    public List<BookVO> foreignBookPage(BookVO vo)
    {
        return bookRepository.foreignBookPage(vo);
    }
    
    @Override
    public int insertPreBook(PrebookVO book)
    {
        return bookRepository.insertPreBook(book);
    }
    
    @Override
    public List<PrebookVO> queryPagePreBook(CatalogVO vo)
    {
        
        PageHelper.startPage(vo.getPageNum(), vo.getPageSize(), true);
        PrebookVO bvo=new PrebookVO();
        bvo.setId(vo.getBookId());
        bvo.setIsbn(vo.getIsbn());
        bvo.setTitle(vo.getTitle());
        bvo.setAuthor(vo.getAuthor());
        bvo.setBookType(vo.getBookType());

        //wangsiyi
        bvo.setBookType(vo.getBookType());
        bvo.setBookTypeStar(vo.getBookTypeStar());
        bvo.setBookTypeEnd(vo.getBookTypeEnd());
        bvo.setOwnlib(vo.getLibraryId());
        return bookRepository.queryPagePreBook(bvo);
    }

    /**
     *导出采访库书目数据列表   MARC
     * @param   catalogVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportPrebookMARC(CatalogVO catalogVO) {
        String fileName = IdGen.getDateUUId()+".ISO";
        String filePath = new POITool().getExportPath()+fileName;
        PrebookVO bvo=new PrebookVO();
        bvo.setOwnlib(catalogVO.getLibraryId());
        List bookList = bookRepository.queryPagePreBook(bvo);
        String str = MarcUtil.objs2ISOStr(bookList);
        return IOUtil.outPutFile(str,filePath);
    }

    /**
     *导出中央库书目数据列表   MARC
     * @param   catalogVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportBookMARC(CatalogVO catalogVO) {
        String fileName = IdGen.getDateUUId()+".ISO";
        String filePath = new POITool().getExportPath()+fileName;
        Book bvo = new Book();
        bvo.setOwnlib(catalogVO.getLibraryId());
        List bookList = bookRepository.queryPage(bvo);
        String str = MarcUtil.objs2ISOStr(bookList);
        return IOUtil.outPutFile(str,filePath);
    }


    /**
     *导出中央库书目数据列表
     * @param   catalogVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportBookExcel(CatalogVO catalogVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        Book bvo = new Book();
        bvo.setOwnlib(catalogVO.getLibraryId());
        List<Book> bookList = bookRepository.queryPage(bvo);
        return exportBookInfo(bookList , filePath  , "export");
    }

    //导出 中央库书目 数据详情
    private String exportBookInfo( List<Book> bookList , String filePath , String type) {
        Map tilte =new HashMap(16);
        if("export".equals(type)){
            tilte.put("a","书目记录号");
        }
        tilte.put("b","图书名称");
        tilte.put("c","ISBN");
        tilte.put("d","作者");
        tilte.put("e","分类号");
        tilte.put("f","出版社");
        tilte.put("g","出版地");
        tilte.put("h","出版时间");
        tilte.put("i","单价（元）");
        tilte.put("j","开本");
        tilte.put("k","页数");
        tilte.put("l","摘要");

        if(bookList!=null && bookList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for(Book vo : bookList){
                m = new HashMap(16);
                if("export".equals(type)){
                    m.put("a",vo.getId().toString());
                }
                m.put("b",vo.getTitle());
                m.put("c",vo.getIsbn());
                m.put("d",vo.getAuthor());
                m.put("e",vo.getBookType());
                m.put("f",vo.getPublisher());
                m.put("g",vo.getPubArea());
                m.put("h",vo.getPubdate());
                if("export".equals(type)){
                    m.put("i",new DecimalFormat("##0.00").format(vo.getPrice()));
                }else {
                    m.put("i",vo.getPriceTest());
                }
                m.put("j",vo.getBookSize());
                if("export".equals(type)){
                    m.put("k",vo.getPages());
                }else {
                    m.put("k",vo.getPagesTest());
                }
                m.put("l",vo.getSummary());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     *导出采访库书目数据列表
     * @param   catalogVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportPreBookExcel(CatalogVO catalogVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        PrebookVO bvo=new PrebookVO();
        bvo.setOwnlib(catalogVO.getLibraryId());
        List<PrebookVO> bookList = bookRepository.queryPagePreBook(bvo);
        return exportPrebookInfo(bookList , filePath  , "export");
    }

    //导出 采访库书目 数据详情
    private String exportPrebookInfo( List<PrebookVO> bookList , String filePath , String type) {
        Map tilte =new HashMap(16);
        if("export".equals(type)){
            tilte.put("a","书目记录号");
        }
        tilte.put("b","图书名称");
        tilte.put("c","ISBN");
        tilte.put("d","作者");
        tilte.put("e","分类号");
        tilte.put("f","出版社");
        tilte.put("g","出版地");
        tilte.put("h","出版时间");
        tilte.put("i","单价（元）");
        tilte.put("j","开本");
        tilte.put("k","页数");
        tilte.put("l","摘要");
        if(bookList!=null && bookList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for(PrebookVO vo : bookList){
                m = new HashMap(16);
                if("export".equals(type)){
                    m.put("a",vo.getId().toString());
                }
                m.put("b",vo.getTitle());
                m.put("c",vo.getIsbn());
                m.put("d",vo.getAuthor());
                m.put("e",vo.getBookType());
                m.put("f",vo.getPublisher());
                m.put("g",vo.getPubArea());
                m.put("h",vo.getPubdate());
                if("export".equals(type)){
                    m.put("i",new DecimalFormat("##0.00").format(vo.getPrice()));
                }else {
                    m.put("i",vo.getPriceTest());
                }
                m.put("j",vo.getBookSize());
                if("export".equals(type)){
                    m.put("k",vo.getPages());
                }else {
                    m.put("k",vo.getPagesTest());
                }
                m.put("l",vo.getSummary());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     * @描述：下载导入书目 模板
     * @作者：wangsiyi
     * @时间：2018年8月31日 上午11:04:28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportMouldExcel() {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        Map tilte =new HashMap(16);
        tilte.put("a","图书名称");
        tilte.put("b","ISBN");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版地");
        tilte.put("g","出版时间");
        tilte.put("h","单价（元）");
        tilte.put("i","开本");
        tilte.put("j","页数");
        tilte.put("k","摘要");
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     * @描述：批量导入 中央书库书目 数据记录
     * @作者：wangsiyi
     * @时间：2018年8月31日 上午11:04:28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importBooklist(List<Map> maplist , Long libraryId , Long createBy  , String importType) {
        //解析导入数据
        List<Book> bookList = new ArrayList<>();
        for (Map m : maplist) {
            Book book = new Book();
            book.setOwnlib(libraryId);       //图书馆ID
            book.setCreateBy(createBy);      //创建者
            if("excel".equals(importType)) {
                book.setTitle(m.get("图书名称") == null ? null : m.get("图书名称").toString().trim());
                book.setIsbn(m.get("ISBN") == null ? null : m.get("ISBN").toString().trim());
                book.setAuthor(m.get("作者") == null ? null : m.get("作者").toString().trim());
                book.setBookType(m.get("分类号") == null ? null : m.get("分类号").toString().trim());
                book.setPublisher(m.get("出版社") == null ? null : m.get("出版社").toString().trim());
                book.setPubArea(m.get("出版地") == null ? null : m.get("出版地").toString().trim());
                book.setPubdate((m.get("出版时间") == null || m.get("出版时间").equals("")) ? null : m.get("出版时间").toString().trim());
                book.setPriceTest(m.get("单价（元）") == null ? null : m.get("单价（元）").toString().trim());
                book.setBookSize(m.get("开本") == null ? null : m.get("开本").toString().trim());
                book.setPagesTest(m.get("页数") == null ? null : m.get("页数").toString().trim());
                book.setSummary(m.get("摘要") == null ? null : m.get("摘要").toString().trim());
            }else {
                //marc导出
                book.setIsbn(m.get("ISBN") == null ? null : m.get("ISBN").toString());
                book.setPrice(m.get("price") == null ? null : Float.valueOf(m.get("price").toString().substring(3)));
                book.setPublisher(m.get("publisher") == null ? null : m.get("publisher").toString());
                book.setPubdate(m.get("pubDate") == null ? null : m.get("pubDate").toString()+"-00-00");
                book.setLanguage(m.get("language") == null ? null : m.get("language").toString());
                book.setTitle(m.get("title") == null ? null : m.get("title").toString());
                book.setRevision(m.get("revision") == null ? null : m.get("revision").toString());
                book.setPubArea(m.get("pubArea") == null ? null : m.get("pubArea").toString());
                book.setPagesTest(m.get("pages") == null ? null : m.get("pages").toString());
                book.setSummary(m.get("summary") == null ? null : m.get("summary").toString());
                book.setBookType(m.get("bookType") == null ? null : m.get("bookType").toString());
                book.setSubjectWord(m.get("subjectWord") == null ? null : m.get("subjectWord").toString());
                book.setAuthor(m.get("author") == null ? null : m.get("author").toString());
                book.setFirstDuty(m.get("firstDuty") == null ? null : m.get("firstDuty").toString());
                book.setPriceTest(book.getPrice()+"");
            }
            bookList.add(book);
        }
        //装插入的错误数据
        List<Book> errorData = new ArrayList<>();
        for (Book item : bookList) {
            boolean flg = false;
            try {
                item.setPrice(item.getPriceTest() == null ? null : Float.valueOf(item.getPriceTest()));
                item.setPages(item.getPagesTest() == null ? null : Integer.valueOf(item.getPagesTest()));
                if(item.getPubdate() != null && !item.getPubdate().equals("")){
                    if(DateUtils.parseDate(item.getPubdate()) == null){
                        flg = true;
                    }
                }
            }catch (Exception e){
                flg = true;
            }
            if( flg ){
                //判断如果存在相同的字段名称 在同一图书馆 装到错误数据集合了
                Book bookEntity  = new Book();
                bookEntity.setTitle(item.getTitle());
                bookEntity.setIsbn(item.getIsbn());
                bookEntity.setAuthor(item.getAuthor());
                bookEntity.setBookType(item.getBookType());
                bookEntity.setPublisher(item.getPublisher());
                bookEntity.setPubArea(item.getPubArea());
                bookEntity.setPubdate(item.getPubdate());
                bookEntity.setPriceTest(item.getPriceTest());
                bookEntity.setBookSize(item.getBookSize());
                bookEntity.setPagesTest(item.getPagesTest());
                bookEntity.setSummary(item.getSummary());
                errorData.add(bookEntity);
            }else{
                //不存在相同的则导入新增
                Long barCodeId = IdGen.randomLong();
                item.setId(barCodeId);
                item.setCreateTime(DateUtils.getDateTime());
                item.setPrice(item.getPriceTest() == null ? null : Float.valueOf(item.getPriceTest()));
                item.setPages(item.getPagesTest() == null ? null : Integer.valueOf(item.getPagesTest()));
                bookRepository.insert(item);

            }
        }
        //如果错误数据不为空 则导出错误数据
        if(errorData != null && errorData.size() > 0){
            String fileName = IdGen.getDateUUId()+".xls";
            String filePath = new POITool().getExportPath()+fileName;
            return exportBookInfo(errorData , filePath , "import");
        }
        return null;
    }

    /**
     * @描述：批量导入 采访书库书目 数据记录
     * @作者：wangsiyi
     * @时间：2018年8月31日 上午11:04:28
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importPrebooklist(List<Map> maplist , Long libraryId , Long createBy  , String importType) {
        //解析导入数据
        List<PrebookVO> prebookVOList = new ArrayList<>();
        for (Map m : maplist) {
            PrebookVO prebookVO = new PrebookVO();
            prebookVO.setOwnlib(libraryId);       //图书馆ID
            prebookVO.setCreateBy(createBy);      //创建者
            if("excel".equals(importType)) {
                prebookVO.setTitle(m.get("图书名称") == null ? null : m.get("图书名称").toString().trim());
                prebookVO.setIsbn(m.get("ISBN") == null ? null : m.get("ISBN").toString().trim());
                prebookVO.setAuthor(m.get("作者") == null ? null : m.get("作者").toString().trim());
                prebookVO.setBookType(m.get("分类号") == null ? null : m.get("分类号").toString().trim());
                prebookVO.setPublisher(m.get("出版社") == null ? null : m.get("出版社").toString().trim());
                prebookVO.setPubArea(m.get("出版地") == null ? null : m.get("出版地").toString().trim());
                prebookVO.setPubdate((m.get("出版时间") == null || m.get("出版时间").equals("")) ? null : m.get("出版时间").toString().trim());
                prebookVO.setPriceTest(m.get("单价（元）") == null ? null : m.get("单价（元）").toString().trim());
                prebookVO.setBookSize(m.get("开本") == null ? null : m.get("开本").toString().trim());
                prebookVO.setPagesTest(m.get("页数") == null ? null : m.get("页数").toString().trim());
                prebookVO.setSummary(m.get("摘要") == null ? null : m.get("摘要").toString().trim());
            }else {
                //marc导入
                prebookVO.setIsbn(m.get("ISBN") == null ? null : m.get("ISBN").toString());
                prebookVO.setPrice(m.get("price") == null ? null : Float.valueOf(m.get("price").toString().substring(3)));
                prebookVO.setPublisher(m.get("publisher") == null ? null : m.get("publisher").toString());
                prebookVO.setPubdate(m.get("pubDate") == null ? null : m.get("pubDate").toString()+"-00-00");
                prebookVO.setLanguage(m.get("language") == null ? null : m.get("language").toString());
                prebookVO.setTitle(m.get("title") == null ? null : m.get("title").toString());
                prebookVO.setRevision(m.get("revision") == null ? null : m.get("revision").toString());
                prebookVO.setPubArea(m.get("pubArea") == null ? null : m.get("pubArea").toString());
                prebookVO.setPagesTest(m.get("pages") == null ? null : m.get("pages").toString());
                prebookVO.setSummary(m.get("summary") == null ? null : m.get("summary").toString());
                prebookVO.setBookType(m.get("bookType") == null ? null : m.get("bookType").toString());
                prebookVO.setSubjectWord(m.get("subjectWord") == null ? null : m.get("subjectWord").toString());
                prebookVO.setAuthor(m.get("author") == null ? null : m.get("author").toString());
                prebookVO.setFirstDuty(m.get("firstDuty") == null ? null : m.get("firstDuty").toString());
                prebookVO.setPriceTest(prebookVO.getPrice()+"");
            }
            prebookVOList.add(prebookVO);
        }
        //装插入的错误数据
        List<PrebookVO> errorData = new ArrayList<>();
        for (PrebookVO item : prebookVOList) {
            boolean flg = false;
            try {
                item.setPrice(item.getPriceTest() == null ? null : Float.valueOf(item.getPriceTest()));
                item.setPages(item.getPagesTest() == null ? null : Integer.valueOf(item.getPagesTest()));
                if(item.getPubdate() != null && !item.getPubdate().equals("") ){
                    if(DateUtils.parseDate(item.getPubdate()) == null){
                        flg = true;
                    }
                }

            }catch (Exception e){
                flg = true;
            }
            if( flg ){
                //判断如果存在相同的字段名称 在同一图书馆 装到错误数据集合了
                PrebookVO PrebookVOEntity  = new PrebookVO();
                PrebookVOEntity.setTitle(item.getTitle());
                PrebookVOEntity.setIsbn(item.getIsbn());
                PrebookVOEntity.setAuthor(item.getAuthor());
                PrebookVOEntity.setBookType(item.getBookType());
                PrebookVOEntity.setPublisher(item.getPublisher());
                PrebookVOEntity.setPubArea(item.getPubArea());
                PrebookVOEntity.setPubdate(item.getPubdate());
                PrebookVOEntity.setPriceTest(item.getPriceTest());
                PrebookVOEntity.setBookSize(item.getBookSize());
                PrebookVOEntity.setPagesTest(item.getPagesTest());
                PrebookVOEntity.setSummary(item.getSummary());
                errorData.add(PrebookVOEntity);
            }else{
                //不存在相同的则导入新增
                Long barCodeId = IdGen.randomLong();
                item.setId(barCodeId);
                item.setCreateTime(DateUtils.getDateTime());
                item.setStorageTime(DateUtils.getDateTime());
                item.setPrice(item.getPriceTest() == null ? null : Float.valueOf(item.getPriceTest()));
                item.setPages(item.getPagesTest() == null ? null : Integer.valueOf(item.getPagesTest()));
                bookRepository.insertPreBook(item);
            }
        }
        //如果错误数据不为空 则导出错误数据
        if(errorData != null && errorData.size() > 0){
            String fileName = IdGen.getDateUUId()+".xls";
            String filePath = new POITool().getExportPath()+fileName;
            return exportPrebookInfo(errorData , filePath , "import");
        }
        return null;
    }
}
