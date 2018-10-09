package com.szcti.lcloud.catalog.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.szcti.lcloud.catalog.config.MarcTemplateUtil;
import com.szcti.lcloud.catalog.entity.Acceptance;
import com.szcti.lcloud.catalog.entity.AcceptanceCriteria;
import com.szcti.lcloud.catalog.entity.AcceptanceDetail;
import com.szcti.lcloud.catalog.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.catalog.entity.Book;
import com.szcti.lcloud.catalog.entity.BookCriteria;
import com.szcti.lcloud.catalog.entity.Catalog;
import com.szcti.lcloud.catalog.entity.CatalogCriteria;
import com.szcti.lcloud.catalog.entity.Holding;
import com.szcti.lcloud.catalog.entity.MarcTemplate;
import com.szcti.lcloud.catalog.entity.MarcTemplateCriteria;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetail;
import com.szcti.lcloud.catalog.entity.MarcTemplateDetailCriteria;
import com.szcti.lcloud.catalog.entity.OperationLog;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.catalog.repository.AcceptanceDetailRepository;
import com.szcti.lcloud.catalog.repository.AcceptanceRepository;
import com.szcti.lcloud.catalog.repository.BarcodeRepository;
import com.szcti.lcloud.catalog.repository.BookRepository;
import com.szcti.lcloud.catalog.repository.CatalogRepository;
import com.szcti.lcloud.catalog.repository.HoldingRepository;
import com.szcti.lcloud.catalog.repository.MarcTemplateDetailRepository;
import com.szcti.lcloud.catalog.repository.MarcTemplateRepository;
import com.szcti.lcloud.catalog.repository.OperationLogRepository;
import com.szcti.lcloud.catalog.service.CatalogService;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.JSONUtil;
import net.sf.json.JSONObject;

@Service("catalogService")
public class CatalogServiceImp implements CatalogService
{
    @Autowired
    private CatalogRepository            catalogRepository;
    
    @Autowired
    private BookRepository               bookRepository;
    
    @Autowired
    private HoldingRepository            holdingRepository;
    
    @Autowired
    private AcceptanceDetailRepository   acceptanceDetailRepository;
    
    @Autowired
    private AcceptanceRepository         acceptanceRepository;
    
    @Autowired
    private MarcTemplateRepository       marcTemplateRepository;
    
    @Autowired
    private MarcTemplateDetailRepository marcTemplateDetailRepository;
    
    @Autowired
    private OperationLogRepository       operationLogRepository;
    
    @Autowired
    private BarcodeRepository            barcoderepository;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long id)
    {
        return catalogRepository.deleteByPrimaryKey(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(Catalog record)
    {
        
        return catalogRepository.insert(record);
    }
    
    @Override
    public List<Catalog> selectByExample(CatalogCriteria example)
    {
        return catalogRepository.selectByExample(example);
    }
    
    @Override
    public Catalog selectByPrimaryKey(Long id)
    {
        return catalogRepository.selectByPrimaryKey(id);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(Catalog record)
    {
        return catalogRepository.updateByPrimaryKey(record);
    }
    
    @Override
    public List<CatalogVO> queryPage(CatalogVO catalogVO)
    {
        setParameter(catalogVO);
        List<CatalogVO> list = catalogRepository.queryPage(catalogVO);
        return list;
    }
    
    @Override
    public CatalogVO getCatalogInfo(CatalogVO catalogVO)
    {
        if ( catalogVO != null )
        {
            setParameter(catalogVO);
            CatalogVO vo = new CatalogVO();
            vo.setRecount(catalogVO.getRecount());
            List<CatalogVO> list = queryPage(catalogVO);
            if ( list != null & list.size() > 0 )
            {
                vo = list.get(0);
                HoldingVO hv = new HoldingVO();
                hv.setOwnlib(vo.getLibraryId());
                hv.setIsbn(vo.getIsbn());
                hv.setBookId(vo.getBookId());
                hv.setLibraryName(vo.getLibraryName());
                // hv.setCallNo(vo.getCallNo());
                // hv.setBarcode(vo.getBarcode());
                vo.setHoldingList(holdingRepository.queryPage(hv));
                vo.setRecount(vo.getHoldingList().size());
            }
            if ( catalogVO.getIsbn() != null && !catalogVO.getIsbn().equals("") )
            {
                BookCriteria bc = new BookCriteria();
                bc.createCriteria().andIsbnEqualTo(catalogVO.getIsbn());
                List<Book> bolist = bookRepository.selectByExample(bc);
                if ( bolist != null && bolist.size() > 0 )
                {
                    vo.setBook(bolist.get(0));
                }
            }
            return vo;
        }
        return null;
    }
    
    private void setParameter(CatalogVO catalogVO)
    {
        if ( catalogVO != null && catalogVO.getSearchValue() != null && !catalogVO.getSearchValue().equals("") )
        {
            if ( ("isbn").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setIsbn(catalogVO.getSearchValue());
            }
            if ( ("id").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setId(Long.parseLong(catalogVO.getSearchValue()));
            }
            if ( ("title").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setTitle(catalogVO.getSearchValue());
            }
            if ( ("bookType").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setBookType(catalogVO.getSearchValue());
            }
            if ( ("barcode").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setBarcode(catalogVO.getSearchValue());
            }
            if ( ("callNo").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setCallNo(catalogVO.getSearchValue());
            }
            if ( ("author").equals(catalogVO.getSearchKey()) )
            {
                catalogVO.setAuthor(catalogVO.getSearchValue());
            }
        }
    }
    
    private void updateAcceptCode(HoldingVO hvo, CatalogVO vo)
    {
        if ( hvo.getAcceptCode() != null && !hvo.getAcceptCode().equals("") )
        {
            AcceptanceCriteria ac = new AcceptanceCriteria();
            ac.createCriteria().andAcceptCodeEqualTo(hvo.getAcceptCode());
            List<Acceptance> alist = acceptanceRepository.selectByExample(ac);
            if ( alist != null && alist.size() > 0 )
            {
                Acceptance a = alist.get(0);
                AcceptanceDetailCriteria ad = new AcceptanceDetailCriteria();
                AcceptanceDetailCriteria.Criteria adc = ad.createCriteria();
                adc.andAcceptanceIdEqualTo(a.getId());
                adc.andLibraryIdEqualTo(vo.getLibraryId());
                adc.andIsbnEqualTo(vo.getIsbn());
                List<AcceptanceDetail> adlist = acceptanceDetailRepository.selectByExample(ad);
                for (AcceptanceDetail aDetail : adlist)
                {
                    int q = aDetail.getcatalogQty() == null ? 0 : aDetail.getcatalogQty();
                    aDetail.setcatalogQty(q + 1);
                    acceptanceDetailRepository.updateByPrimaryKey(aDetail);
                }
            }
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertGeneralCatalog(CatalogVO vo)
    {
        int count = 0;
        if ( vo != null && vo.getIsbn() != null )
        {
            updateCatalogInfo(vo);
            if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
            {
                List hlist = vo.getHoldingList();
                for (Object obj : hlist)
                {
                    HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                            HoldingVO.class);
                    int c = updateHolding(hvo, vo);
                    if ( c > 0 )
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    private int updateHolding(HoldingVO hvo, CatalogVO vo)
    {
        Holding h = getHolding(hvo);
        if ( hvo.getSource() != null && !hvo.getSource().equals("") )
        {
            h.setSource(hvo.getSource());
        }
        h.setColladdressId(hvo.getColladdressId());
        h.setCatalogBatch(hvo.getCatalogBatch());
        h.setCallNo(vo.getCallNo());
        if ( StringUtils.isNotEmpty(hvo.getStatus()) )
        {
            h.setStatus(hvo.getStatus());
        }
        h.setActType(hvo.getActType());
        h.setShelf(hvo.getShelf());
        h.setPartition(vo.getPartition());
        h.setVolume(vo.getVolume());
        int count = 0;
        //        OperationLog qo = new OperationLog();
        //        qo.setUserId(vo.getCreator());
        OperationLog o = new OperationLog();
        if ( h.getId() != null && h.getId() > 0 )
        {
            if ( h.getOperation().equals("delete") )
            {
                // 判断馆藏数据馆藏状态是否为上架
                if ( h.getStatus().equals("0") )
                {
                    h.setStatus("5");
                    holdingRepository.updateByPrimaryKey(h);
                    count++;
                    o.setOperationType("修改");
                    if ( vo.getBook() != null )
                    {
                        o.setOpContent(h.getBarcode() + "(" + vo.getBook().getTitle() + ")、共1本馆藏副本被执行删除操作");
                    }
                }
                else
                {
                    // 无法删除
                    return 406;
                }
            }
            else
            {
                holdingRepository.updateByPrimaryKey(h);
                count++;
                o.setOperationType("修改");
                if ( vo.getBook() != null )
                {
                    o.setOpContent(h.getBarcode() + "(" + vo.getBook().getTitle() + ")、共1本馆藏副本被批量修改");
                }
            }
        }
        else
        {
            h.setOwnlib(vo.getLibraryId());
            h.setBookId(vo.getBook().getId());
            h.setIndate(new Date());
            h.setCurlib(vo.getLibraryId());
            h.setSingleprice(vo.getBook().getPrice());
            h.setTotalprice(vo.getBook().getPrice());
            holdingRepository.insertSelective(h);
            h.setBarcode(h.getBarcode().substring(h.getPrefix().length(), h.getBarcode().length()));
            barcoderepository.updateCurrBarcode(h);
            
            count++;
            o.setOperationType("添加");
            if ( vo.getBook() != null )
            {
                o.setOpContent(h.getBarcode() + "(" + vo.getBook().getTitle() + ")、共1本馆藏副本被添加");
            }
        }
        o.setLibraryId(vo.getLibraryId());
        o.setModuleId(8);
        o.setModuleName("馆藏管理");
        o.setUserId(vo.getCreator());
        o.setIp(vo.getIp());
        operationLogRepository.insert(o);
        return count;
    }
    
    private void updateCatalogInfo(CatalogVO vo)
    {
        BookCriteria bc = new BookCriteria();
        bc.createCriteria().andIsbnEqualTo(vo.getIsbn());
        List<Book> bookList = bookRepository.selectByExample(bc);
        if ( bookList != null && bookList.size() > 0 )
        {
            vo.getBook().setId(bookList.get(0).getId());
            bookRepository.updateByPrimaryKey(vo.getBook());
            // vo.setBook(bookList.get(0));
        }
        else
        {
            vo.getBook().setId(IdGen.randomLong());
            bookRepository.insert(vo.getBook());
            if ( bookRepository.queryPage(vo.getBook()).size() > 0 )
            {
                Book b = bookRepository.queryPage(vo.getBook()).get(0);
                vo.setBook(b);
            }
        }
        OperationLog qo = new OperationLog();
        qo.setUserId(vo.getCreator());
        OperationLog o = operationLogRepository.getUserInfo(qo);
        if ( vo.getId() == null )
        {
            Catalog c = new Catalog();
            c.setBookId(vo.getBook().getId());
            c.setIsbn(vo.getBook().getIsbn());
            c.setLibraryId(vo.getLibraryId());
            c.setCallNo(vo.getCallNo());
            c.setCreator(vo.getCreator());
            c.setCreateTime(DateUtils.parseDate(new Date()));
            insert(c);
            o.setOperationType("添加");
            CatalogCriteria cc = new CatalogCriteria();
            CatalogCriteria.Criteria cia = cc.createCriteria();
            cia.andLibraryIdEqualTo(c.getLibraryId());
            cia.andIsbnEqualTo(c.getIsbn());
            c = selectByExample(cc).get(0);
            vo.setId(c.getId());
        }
        else if ( vo.getId() != null && vo.getId() > 0 )
        {
            Catalog ca = catalogRepository.selectByPrimaryKey(vo.getId());
            ca.setCallNo(vo.getCallNo());
            ca.setIsbn(vo.getIsbn());
            catalogRepository.updateByPrimaryKey(ca);
            o.setOperationType("修改");
        }
        qo.setUserId(vo.getCreator());
        o.setLibraryId(vo.getLibraryId());
        o.setModuleId(9);
        o.setModuleName("编目管理");
        o.setUserId(vo.getCreator());
        o.setIp(vo.getIp());
        o.setOpContent(vo.getId() + "," + vo.getBook().getIsbn() + "," + vo.getBook().getTitle());
        operationLogRepository.insert(o);
    }
    
    @Override
    public List<LendBuyBookVO> queryLendBuyPage(LendBuyBookVO vo)
    {
        List<LendBuyBookVO> list = catalogRepository.queryLendBuyPage(vo);
        for (LendBuyBookVO v : list)
        {
            vo.setIsbn(v.getIsbn());
            List<LendBuyBookVO> liback = catalogRepository.lendBuyBackBook(vo);
            v.setBackCount(liback.size());
        }
        return list;
    }
    
    @Override
    public CatalogVO getMarcCatalogInfo(CatalogVO catalogVO)
    {
        if ( catalogVO != null )
        {
            setParameter(catalogVO);
            CatalogVO vo = new CatalogVO();
            vo.setRecount(catalogVO.getRecount());
            List<CatalogVO> list = queryPage(catalogVO);
            if ( list != null & list.size() > 0 )
            {
                vo = list.get(0);
                HoldingVO hv = new HoldingVO();
                hv.setOwnlib(vo.getLibraryId());
                hv.setIsbn(vo.getIsbn());
                hv.setBookId(vo.getBookId());
                hv.setLibraryName(vo.getLibraryName());
                hv.setCallNo(vo.getCallNo());
                hv.setBarcode(vo.getBarcode());
                vo.setHoldingList(holdingRepository.queryPage(hv));
                vo.setRecount(vo.getHoldingList().size());
            }
            if ( catalogVO.getIsbn() != null && !catalogVO.getIsbn().equals("") )
            {
                BookCriteria bc = new BookCriteria();
                bc.createCriteria().andIsbnEqualTo(catalogVO.getIsbn());
                List<Book> bolist = bookRepository.selectByExample(bc);
                if ( bolist != null && bolist.size() > 0 )
                {
                    vo.setBook(bolist.get(0));
                    MarcTemplateCriteria mc = new MarcTemplateCriteria();
                    MarcTemplateCriteria.Criteria mcc = mc.createCriteria();
                    if ( catalogVO.getLibraryId() != null && catalogVO.getLibraryId() > 0 )
                    {
                        mcc.andLibraryIdEqualTo(catalogVO.getLibraryId());
                    }
                    if ( catalogVO.getMarcTemplateId() != null && catalogVO.getMarcTemplateId() > 0 )
                    {
                        mcc.andIdEqualTo(catalogVO.getMarcTemplateId());
                    }
                    List<MarcTemplate> ml = marcTemplateRepository.selectByExample(mc);
                    if ( ml != null && ml.size() > 0 )
                    {
                        MarcTemplateDetailCriteria mcd = new MarcTemplateDetailCriteria();
                        MarcTemplateDetailCriteria.Criteria mccd = mcd.createCriteria();
                        mccd.andMarcTemplateIdEqualTo(ml.get(0).getId());
                        mcd.setOrderByClause("order_index asc");
                        vo.setMarcList(marcTemplateDetailRepository.selectByExample(mcd));
                        for (MarcTemplateDetail md : vo.getMarcList())
                        {
                            String m = md.getMustValue() == null || md.getMustValue().equals("null") ? ""
                                    : md.getMustValue();
                            String n = md.getSelectValue() == null || md.getSelectValue().equals("null") ? ""
                                    : md.getSelectValue();
                            String s = MarcTemplateUtil.getMarc(vo.getBook(), md.getNameDefine(), m + n);
                            md.setAllValue(s);
                        }
                    }
                }
            }
            return vo;
        }
        return null;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMarcGeneralCatalog(CatalogVO vo)
    {
        int count = 0;
        if ( vo != null && vo.getIsbn() != null )
        {
            for (MarcTemplateDetail m : vo.getMarcList())
            {
                if ( vo.getBook() == null )
                {
                    vo.setBook(new Book());
                }
                MarcTemplateUtil.setMarcBook(vo.getBook(), m.getNameDefine(), m.getAllValue());
            }
            updateCatalogInfo(vo);
            if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
            {
                List hlist = vo.getHoldingList();
                for (Object obj : hlist)
                {
                    HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                            HoldingVO.class);
                    int c = updateHolding(hvo, vo);
                    if ( c > 0 )
                    {
                        count++;
                        updateAcceptCode(hvo, vo);
                    }
                }
            }
        }
        return count;
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertMarcAcceptCatalog(CatalogVO vo)
    {
        int count = 0;
        if ( vo != null && vo.getIsbn() != null )
        {
            for (MarcTemplateDetail m : vo.getMarcList())
            {
                if ( vo.getBook() == null )
                {
                    vo.setBook(new Book());
                }
                MarcTemplateUtil.setMarcBook(vo.getBook(), m.getNameDefine(), m.getAllValue());
            }
            updateCatalogInfo(vo);
            if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
            {
                List hlist = vo.getHoldingList();
                for (Object obj : hlist)
                {
                    HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                            HoldingVO.class);
                    int c = updateHolding(hvo, vo);
                    if ( c > 0 )
                    {
                        count++;
                        updateAcceptCode(hvo, vo);
                    }
                }
            }
        }
        return count;
    }
    
    /**
     * 
     * @描述：获取编目书籍详情信息
     * @作者：tianbw
     * @时间：2018年8月25日 下午4:23:33
     * @param catalogVO
     * @return
     */
    @Override
    public CatalogVO queryCatalogInfo(CatalogVO catalogVO)
    {
        //书信息
        
        CatalogVO vo = new CatalogVO();
        HoldingVO ho = new HoldingVO();
        Book book = new Book();
        book.setId(catalogVO.getBookId());
        book.setIsbn(catalogVO.getIsbn());
        book = bookRepository.queryBook(book);
        vo.setBook(book);
        
        //馆藏信息
//        ho.setBookId(catalogVO.getBookId());
//        ho.setLibraryId(catalogVO.getLibraryId());
//        List<HoldingVO> hold = holdingRepository.queryHoldingList(ho);
//        vo.setHoldingList(hold);
        
        return vo;
    }
    
    /**
     * 
     * @描述：保存编目书籍信息
     * @作者：tianbw
     * @时间：2018年8月27日 上午9:14:45
     * @param vo
     */
    @Override
    public int insertCatalogInfo(CatalogVO vo)
    {
        int count = 0;
        //书的isbn+图书馆id+索书号确认图书是否已经唯一
        HoldingVO ho = new HoldingVO();
        Book book = new Book();
        book.setId(IdGen.randomLong());
        book.setIsbn(vo.getIsbn());
        book.setOwnlib(vo.getLibraryId());
        book = bookRepository.queryBook(book);
        
        if ( book != null )
        {
            //保存图书已经存在
            return 0;
        }
        else
        {
            bookRepository.insert(vo.getBook());
            //添加操作日志
            String op_content = "书名:" + book.getTitle() + "ISBN:" + book.getIsbn() + "在图书馆:" + book.getOwnlib()
                    + "的中央库被执行新增操作";
            insertOperationLog(9, "编目管理", "添加", vo.getLibraryId(), "library_name", vo.getCreator(), "login_name",
                    "user_name", vo.getIp(), op_content, "remark");
            
            if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
            {
                List hlist = vo.getHoldingList();
                for (Object obj : hlist)
                {
                    HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                            HoldingVO.class);
                    int c = insertHolding(hvo, vo);
                    if ( c > 0 )
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    
    /**
     * 
     * @描述：新增馆藏数据
     * @作者：tianbw
     * @时间：2018年8月27日 下午2:39:10
     * @param hvo
     * @param vo
     * @return
     */
    private int insertHolding(HoldingVO hvo, CatalogVO vo)
    {
        Holding h = getHolding(hvo);
        if ( hvo.getSource() != null && !hvo.getSource().equals("") )
        {
            h.setSource(hvo.getSource());
        }
        h.setColladdressId(hvo.getColladdressId());
        h.setCatalogBatch(hvo.getCatalogBatch());
        h.setCallNo(vo.getCallNo());
        if ( StringUtils.isNotEmpty(hvo.getStatus()) )
        {
            h.setStatus(hvo.getStatus());
        }
        h.setActType(hvo.getActType());
        h.setShelf(hvo.getShelf());
        h.setPartition(vo.getPartition());
        h.setVolume(vo.getVolume());
        int count = 0;
        h.setOwnlib(vo.getLibraryId());
        h.setBookId(vo.getBook().getId());
        h.setIndate(new Date());
        h.setCurlib(vo.getLibraryId());
        h.setSingleprice(vo.getBook().getPrice());
        h.setTotalprice(vo.getBook().getPrice());
        
        count = holdingRepository.insertSelective(h);//新增馆藏数据
        //添加操作日志
        String op_content = "书名:" + vo.getBook().getTitle() + "ISBN:" + vo.getBook().getIsbn() + "在图书馆:"
                + vo.getBook().getOwnlib() + "执行新增馆藏信息操作";
        insertOperationLog(8, "馆藏管理", "添加", vo.getLibraryId(), "library_name", vo.getCreator(), "login_name",
                "user_name", vo.getIp(), op_content, "remark");
        h.setBarcode(h.getBarcode().substring(h.getPrefix().length(), h.getBarcode().length()));
        barcoderepository.updateCurrBarcode(h);//修改系统馆藏参数
        return count;
    }
    
    @Override
    public int updateCatalog(CatalogVO vo)
    {
        int count = 0;
        Book book = vo.getBook();
        book.setOwnlib(vo.getLibraryId());
        bookRepository.updateByPrimaryKey(vo.getBook());
        //添加操作日志
        String op_content = "书名:" + book.getTitle() + "ISBN:" + book.getIsbn() + "在图书馆:" + book.getOwnlib()
                + "的中央库被执行修改操作";
        insertOperationLog(9, "编目管理", "修改", vo.getLibraryId(), "library_name", vo.getCreator(), "login_name",
                "user_name", vo.getIp(), op_content, "remark");
        
        if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
        {
            List hlist = vo.getHoldingList();
            for (Object obj : hlist)
            {
                HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                        HoldingVO.class);
                int c = updateHoldingInfo(hvo, vo);
                if ( c > 0 )
                {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * 
     * @描述：修改馆藏数据
     * @作者：tianbw
     * @时间：2018年8月27日 下午6:16:17
     * @param hvo
     * @param vo
     * @return
     */
    private int updateHoldingInfo(HoldingVO hvo, CatalogVO vo)
    {
        Holding h = getHolding(hvo);
        if ( hvo.getSource() != null && !hvo.getSource().equals("") )
        {
            h.setSource(hvo.getSource());
        }
        h.setColladdressId(hvo.getColladdressId());
        h.setCatalogBatch(hvo.getCatalogBatch());
        h.setCallNo(vo.getCallNo());
        if ( StringUtils.isNotEmpty(hvo.getStatus()) )
        {
            h.setStatus(hvo.getStatus());
        }
        h.setActType(hvo.getActType());
        h.setShelf(hvo.getShelf());
        h.setPartition(vo.getPartition());
        h.setVolume(vo.getVolume());
        int count = 0;
        if ( h.getId() != null && h.getId() > 0 )
        {
            if ( h.getOperation().equals("delete") )
            {
                // 判断馆藏数据馆藏状态是否为上架
                if ( h.getStatus().equals("0") )
                {
                    h.setStatus("5");
                    holdingRepository.updateByPrimaryKey(h);
                    count++;
                }
                else
                {
                    // 无法删除
                    return -1;
                }
            }
            else
            {
                holdingRepository.updateByPrimaryKey(h);
                count++;
            }
            
            //添加操作日志
            String op_content = "书名:" + vo.getBook().getTitle() + "ISBN:" + vo.getBook().getIsbn() + "在图书馆:"
                    + vo.getBook().getOwnlib() + "执行修改馆藏信息操作";
            insertOperationLog(8, "馆藏管理", "修改", vo.getLibraryId(), "library_name", vo.getCreator(), "login_name",
                    "user_name", vo.getIp(), op_content, "remark");
        }
        else
        {
            //修改馆藏信息中带有新增数据
            insertHolding(hvo, vo);
            //添加操作日志
            String op_content = "书名:" + vo.getBook().getTitle() + "ISBN:" + vo.getBook().getIsbn() + "在图书馆:"
                    + vo.getBook().getOwnlib() + "执行新增馆藏信息操作";
            insertOperationLog(8, "馆藏管理", "新增", vo.getLibraryId(), "library_name", vo.getCreator(), "login_name",
                    "user_name", vo.getIp(), op_content, "remark");
        }
        return count;
    }
    
    /**
     * 
     * @描述：删除馆藏数据
     * @作者：tianbw
     * @时间：2018年8月27日 下午6:13:38
     * @param vo
     * @return
     */
    @Override
    public int DeleteCatalog(CatalogVO vo)
    {
        int count = 0;
        if ( vo != null )
        {
            if ( vo.getHoldingList() != null && vo.getHoldingList().size() > 0 )
            {
                List hlist = vo.getHoldingList();
                for (Object obj : hlist)
                {
                    HoldingVO hvo = (HoldingVO) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)),
                            HoldingVO.class);
                    int c = updateHoldingInfo(hvo, vo);
                    
                    if ( c > 0 )
                    {
                        count++;
                    }
                    else
                    {
                        return -1;
                    }
                }
            }
        }
        return count;
    }
    
    /**
     * 
     * @描述：获取Holding对象
     * @作者：tianbw
     * @时间：2018年8月27日 下午2:49:30
     * @param hvo
     * @return
     */
    private Holding getHolding(HoldingVO hvo)
    {
        Holding h = (Holding) JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(hvo)), Holding.class);
        return h;
    }
    
    /**
     * 
     * @描述：操作日志记录
     * @作者：tianbw
     * @时间：2018年8月27日 下午2:19:56
     * @return
     */
    private int insertOperationLog(int moduleid, String module_name, String operation_type, long library_id,
            String library_name, long user_id, String login_name, String user_name, String ip, String op_content,
            String remark)
    {
        OperationLog o = new OperationLog();
        o.setModuleId(moduleid);//模块
        o.setModuleName(module_name);//功能模块名称
        o.setOperationType(operation_type);//操作类型
        o.setLibraryId(library_id);//图书馆id
        o.setLibraryName(library_name);//图书馆名称
        o.setUserId(user_id);//用户id
        o.setLoginName(login_name);//登录名
        o.setUserName(user_name);//姓名
        o.setIp(ip);//ip
        o.setOpContent(op_content);//操作内容
        o.setRemark(remark);//备注
        return operationLogRepository.insert(o);
    }
}
