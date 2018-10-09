package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.engine.RuleEngine;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseExportModel;
import com.szcti.lcloud.purchase.repository.*;
import com.szcti.lcloud.purchase.service.PurchaseBookService;
import com.szcti.lcloud.purchase.api.PurchaseBookResource;
import com.szcti.lcloud.purchase.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("purchaseBookService")
public class PurchaseBookServiceImpl implements PurchaseBookService
{
    
    @Autowired
    private PurchaseBookRepository  purchaseBookRepository;
    
    @Autowired
    private BookRepository          bookRepository;
    
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    
    @Autowired
    private PrebookRepository       prebookRepository;
    
    @Autowired
    private PurchaseRuleRepository  purchaseRuleRepository;
    
    /*
    * 已订购图书分页查询
    * */
    @Override
    public List<PurchaseBookVO> queryPage(PurchaseBookVO purchaseBookVO)
    {
        
        return purchaseBookRepository.queryPage(purchaseBookVO);
    }
    
    @Override
    public int countByExample(PurchaseBookCriteria example)
    {
        return purchaseBookRepository.countByExample(example);
    }
    
    @Override
    public int deleteByExample(PurchaseBookCriteria example)
    {
        return purchaseBookRepository.deleteByExample(example);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(PurchaseBook purchaseBook)
    {
        
        return purchaseBookRepository.insert(purchaseBook);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertSelective(PurchaseBook record)
    {
        return purchaseBookRepository.insertSelective(record);
    }
    
    @Override
    public List<PurchaseBook> selectByExample(PurchaseBookCriteria example)
    {
        return purchaseBookRepository.selectByExample(example);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExampleSelective(PurchaseBook record, PurchaseBookCriteria example)
    {
        return purchaseBookRepository.updateByExampleSelective(record, example);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByExample(PurchaseBook record, PurchaseBookCriteria example)
    {
        return purchaseBookRepository.updateByExample(record, example);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKeySelective(PurchaseBook record)
    {
        return purchaseBookRepository.updateByPrimaryKeySelective(record);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importExcel(List<PurchaseBook> list, Long orderId)
    {
        PurchaseBookCriteria pc = new PurchaseBookCriteria();
        int count = 0;
        for (PurchaseBook pb : list)
        {
            pc = new PurchaseBookCriteria();
            pc.createCriteria().andIsbnEqualTo(pb.getIsbn()).andOrderIdEqualTo(pb.getOrderId());
            List<PurchaseBook> pblist = selectByExample(pc);
            if ( pblist != null && pblist.size() > 0 )
            {
                PurchaseBook pbo = pblist.get(0);
                int q = pbo.getBookQty();
                Float t = pbo.getTotalPrice();
                pbo.setBookQty(pb.getBookQty() + q);
                pbo.setTotalPrice(t + pb.getTotalPrice());
                int c = purchaseBookRepository.updateByPrimaryKey(pbo);
                if ( c > 0 )
                {
                    count++;
                }
            }
            else
            {
                int c = purchaseBookRepository.insert(pb);
                if ( c > 0 )
                {
                    count++;
                }
            }
        }
        if ( count > 0 )
        {
            purchaseOrderRepository.refleshPurchaseOrder(orderId);
        }
        return count;
    }
    
    @Override
    public String exportExcel(Long orderId, List ids)
    {
        PurchaseBookVO o = new PurchaseBookVO();
        o.setOrderId(orderId);
        if ( ids != null && ids.size() > 0 )
        {
            o.setIds(ids);
        }
        List<PurchaseBookVO> querylist = purchaseBookRepository.queryPage(o);
        String fileName = IdGen.getDateUUId() + ".xls";
        String filePath = new POITool().getExportPath() + fileName;
        Map tilte = new HashMap();
        tilte.put("a", "订购号");
        tilte.put("b", "订单号");
        tilte.put("c", "书名");
        tilte.put("d", "作者");
        tilte.put("e", "isbn");
        tilte.put("f", "书类号");
        tilte.put("g", "出版日期");
        tilte.put("h", "出版社");
        tilte.put("i", "价格");
        tilte.put("j", "数量");
        tilte.put("k", "总价");
        if ( querylist != null && querylist.size() > 0 )
        {
            List<Object> exportList = new ArrayList<Object>();
            for (PurchaseBookVO vo : querylist)
            {
                PurchaseExportModel m = new PurchaseExportModel(vo.getPurchaseCode(), vo.getOrderCode(), vo.getTitle(),
                        vo.getAuthor(), vo.getIsbn(), vo.getBookType(), vo.getPubdate(), vo.getPublisher(),
                        new DecimalFormat("##0.00").format(vo.getPrice()), vo.getBookQty(),
                        new DecimalFormat("##0.00").format(vo.getTotalPrice()));
                exportList.add(m);
            }
            return POITool.ExportData(tilte, exportList, filePath);
        }
        return fileName;
    }
    
    @Override
    public List<Long> getBooks(BookInfo b)
    {
        
        return purchaseBookRepository.getBooks(b);
    }
    
    @Override
    public PurchaseBook selectByPrimaryKey(Long orderBuyBookId)
    {
        return purchaseBookRepository.selectByPrimaryKey(orderBuyBookId);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteByPrimaryKey(Long orderBuyBookId)
    {
        return purchaseBookRepository.deleteByPrimaryKey(orderBuyBookId);
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateByPrimaryKey(PurchaseBook purchaseBook)
    {
        return purchaseBookRepository.updateByPrimaryKey(purchaseBook);
    }
    
    /*
     *  设置订购数
     *   array:id-Qt组成，orderBuyId：订单id，params:其它额外参数
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int setBookQt(String[] array, Long orderBuyId, Map<String, Object> params)
    {
        int count = 0;
        for (String idn : array)
        {
            String[] arr = idn.split("-");
            String id = arr[0];
            String bookQty = arr[1];
            Integer qt = Integer.parseInt(bookQty);
            Long idl = Long.parseLong(id);
            if ( idl > 0 )
            {
                PurchaseBook ob = purchaseBookRepository.selectByPrimaryKey(idl);
                Float price = new Float(0);
                if ( ob.getPrebookId() != null && ob.getPrebookId() > 0 )
                {
                    Prebook pre = prebookRepository.selectByPrimaryKey(ob.getPrebookId());
                    price = pre.getPrice();
                }
                else if ( ob.getBookId() != null && ob.getBookId() > 0 )
                {
                    Book book = bookRepository.selectByPrimaryKey(ob.getBookId());
                    price = book.getPrice();
                }
                Float totalPrice = price * qt;
                ob.setTotalPrice(totalPrice);
                ob.setBookQty(qt);
                ob.setStatus(new Integer("0"));
                ob.setCheckedmsg("");
                purchaseBookRepository.updateByPrimaryKey(ob);
                count++;
            }
        }
        purchaseOrderRepository.refleshPurchaseOrder(orderBuyId);
        return count;
    }
    
    /*
     *  根据BookInfo里的条件查出订单中重复图书
     *   b:查询条件
     * */
    @Override
    public List<Long> getDupBooks(BookInfo b)
    {
        return purchaseBookRepository.getDupBooks(b);
    }
    
    /*
     *  根据自动审核订购单图书
     *   l:订购单中已选图书id，m检查结果集
     * */
    @Override
    public Boolean autoCheck(long l, Map m)
    {
        PurchaseBookVO vo = getPurchaseBookVO(l);
        PurchaseRule pr = new PurchaseRule();
        Map dupmsg = new HashMap();
        if ( vo != null && vo.getRuleId() > 0 )
        {
            pr = purchaseRuleRepository.selectByPrimaryKey(vo.getRuleId());
            dupmsg = dupCheckBookRes(vo, pr);
        }
        RuleEngine e = new RuleEngine();
        PurchaseRuleCheckService check = new PurchaseRuleCheckService();
        //getDataBuyId(l,vo,pr,dupmsg);
        if ( vo != null && vo.getRuleId() > 0 && pr != null && pr.getId() > 0 )
        {
            check.setDataBuyId(vo, pr, dupmsg);
            e.setRuleService(check);
            e.run();
            m.put("map", e.getResultMap());
            return e.getResultBoolean();
        }
        else if ( pr == null && vo.getRuleId() > 0 )
        {
            m.put("res", "规则已丢失，请选择订购规则！");
        }
        m.put("res", "无限制");
        return true;
    }
    
    public void getDataBuyId(Long purchaseBookId, PurchaseBookVO vo, PurchaseRule pr, Map dupmsg)
    {
        vo = getPurchaseBookVO(purchaseBookId);
        if ( vo != null && vo.getRuleId() > 0 )
        {
            pr = purchaseRuleRepository.selectByPrimaryKey(vo.getRuleId());
            dupmsg = dupCheckBookRes(vo, pr);
        }
    }
    
    /*
     *  用规则检查图书
     *   vo:订购单中已选图书，r检查规则，m检查结果集
     * */
    @Override
    public Boolean checkRule(PurchaseBookVO vo, PurchaseRule r, Map m)
    {
        RuleEngine e = new RuleEngine();
        PurchaseRuleCheckService check = new PurchaseRuleCheckService();
        e.setRuleService(check);
        check.setData(vo, r);
        e.run();
        m = e.getResultMap();
        return e.getResultBoolean();
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delete(String[] array, Long orderId)
    {
        int count = 0;
        if ( array != null && array.length > 0 )
        {
            for (String id : array)
            {
                Long idl = Long.parseLong(id);
                if ( idl > 0 )
                {
                    int c = purchaseBookRepository.deleteByPrimaryKey(Long.parseLong(id));
                    if ( c > 0 )
                    {
                        count++;
                    }
                }
            }
            purchaseOrderRepository.refleshPurchaseOrder(orderId);//刷新订购单中的种数、册数、总价数
        }
        return count;
    }
    
    /*
    * 根据订购单中已选图书id，获取订购图书信息
    *   id:订购单中已选图书id
    * */
    public PurchaseBookVO getPurchaseBookVO(Long id)
    {
        PurchaseBookVO pbvo = null;
        PurchaseBook pb = purchaseBookRepository.selectByPrimaryKey(id);
        if ( pb != null )
        {
            PurchaseBookVO prameter = new PurchaseBookVO();
            prameter.setId(pb.getId());
            if ( pb.getPrebookId() != null && pb.getPrebookId() > 0 )
            {
                pbvo = purchaseBookRepository.queryPage(prameter).get(0);
            }
            else if ( pb.getBookId() != null && pb.getBookId() > 0 )
            {
                pbvo = purchaseBookRepository.queryLibraryBookPage(prameter).get(0);
            }
        }
        return pbvo;
    }
    
    @Override
    public List<PurchaseBookVO> getAcceptBook(PurchaseBookVO purchaseBookVO)
    {
        List<PurchaseBookVO> list = purchaseBookRepository.getAcceptBook(purchaseBookVO);
        for (PurchaseBookVO vo : list)
        {
            viewdesc(vo);
        }
        return list;
    }
    
    private void viewdesc(PurchaseBookVO vo)
    {
        setBatchMsg(vo);
    }
    
    private void setBatchMsg(PurchaseBookVO vo)
    {
        if ( vo.getBookQty() != null && vo.getBookQty() > 0 )
        {
            if ( vo.getRemainQty() > 0 )
            {
                vo.setBatchMsg("缺" + vo.getRemainQty() + "册");
            }
            else if ( vo.getCheckedQty() > 0 && vo.getRemainQty() == 0 )
            {
                vo.setBatchMsg("完全到货");
            }
            else if ( vo.getBatchQty() > 0 && vo.getCheckedQty() == 0 )
            {
                vo.setBatchMsg("未验收");
            }
            else if ( vo.getBatchQty() == 0 )
            {
                vo.setBatchMsg("未发货");
            }
        }
    }
    
    /*
     * 根据图书条件，查重
     *   PurchaseBookVO:查重条件,r允许用的规则(可空)
     * */
    public Map dupCheckBookRes(PurchaseBookVO pb, PurchaseRule r)
    {
        BookInfo b = new BookInfo();
        b.setTitle(pb.getTitle());
        b.setIsbn(pb.getIsbn());
        b.setPublisher(pb.getPublisher());
        b.setLibraryId(pb.getLibraryId());
        b.setPrice(pb.getPrice());
        b.setAuthor(pb.getAuthor());
        return getDubBooks(b, pb.getLibraryId(), r);
    }
    
    /*
     * 对一个图书馆，根据图书条件，查重
     *   BookInfo:查重条件，libraryId图书馆id,r允许用的规则(可空)
     * */
    public Map getDubBooks(BookInfo b, Long libraryId, PurchaseRule r)
    {
        Map m = new HashMap();
        b.setLibraryId(libraryId);
        b.setStatus(1);//审核通过的书
        List<Long> checkedList = purchaseBookRepository.getDupBooks(b);
        b.setStatus(null);//正在采购中的书
        List<Long> commitList = purchaseBookRepository.getPurchasingBooks(b);
        b.setStatus(7);//已到馆可借阅的书
        List<Long> libraryList = purchaseBookRepository.getDupBooks(b);
        List<Long> liall = new ArrayList<Long>();
        m.put("checked", 0);
        m.put("commit", 0);
        m.put("library", 0);
        int checkbook = 0, commibook = 0, librarybook = 0;
        if ( checkedList != null && checkedList.size() > 0 )
        {
            for (Long pbid : checkedList)
            {
                checkbook = checkbook + selectByPrimaryKey(pbid).getBookQty();
            }
            liall.addAll(checkedList);
            m.put("checked", checkbook);
        }
        if ( commitList != null && commitList.size() > 0 )
        {
            for (Long pbid : commitList)
            {
                commibook = commibook + selectByPrimaryKey(pbid).getBookQty();
            }
            liall.addAll(commitList);
            m.put("commit", commibook);
        }
        if ( libraryList != null && libraryList.size() > 0 )
        {
            for (Long pbid : libraryList)
            {
                librarybook = librarybook + selectByPrimaryKey(pbid).getBookQty();
            }
            liall.addAll(libraryList);
            m.put("library", librarybook);
        }
        if ( r != null && r.getId() != null && r.getId() > 0 )
        {
            m.put("rule", r.getDuplicateQty());
            if ( liall != null && liall.size() > 0 && r.getId() != null && r.getId() > 0 )
            {
                int qt = r.getDuplicateQty() - checkbook - commibook - librarybook;
                m.put("remain", qt);
            }
        }
        return m;
    }
    
    /*
     * 根据采访图书id，查重
     *   id:采访图书id,libraryId 图书馆id
     * */
    @Override
    public Map dupCheckPrebookRes(Long id, Long libraryId, Long orderId)
    {
        Prebook prebook = prebookRepository.selectByPrimaryKey(id);
        PurchaseBookVO pbvo = new PurchaseBookVO();
        pbvo.setTitle(prebook.getTitle());
        pbvo.setIsbn(prebook.getIsbn());
        pbvo.setLibraryId(libraryId);
        //pbvo.setPubdate(DateUtils.formatDateTime(prebook.getPubdate()));
        pbvo.setPublisher(prebook.getPublisher());
        pbvo.setAuthor(prebook.getAuthor());
        pbvo.setPrice(prebook.getPrice());
        PurchaseRule r = null;
        if ( orderId != null && orderId > 0 )
        {
            PurchaseOrder purchaseOrder = purchaseOrderRepository.selectByPrimaryKey(orderId);
            r = purchaseRuleRepository.selectByPrimaryKey(purchaseOrder.getRuleId());
            pbvo.setLibraryId(purchaseOrder.getLibraryId());
        }
        return dupCheckBookRes(pbvo, r);
    }
    
    /*
     * 根据订购单中已选图书id，查重
     *   id:订购单中已选图书id
     * */
    @Override
    public Map dupCheckBookRes(Long id)
    {
        PurchaseBook pb = purchaseBookRepository.selectByPrimaryKey(id);
        PurchaseBookVO pbvo = null;
        PurchaseBookVO prameter = new PurchaseBookVO();
        prameter.setId(pb.getId());
        if ( pb.getPrebookId() != null && pb.getPrebookId() > 0 )
        {
            pbvo = purchaseBookRepository.queryPage(prameter).get(0);
        }
        else if ( pb.getBookId() != null && pb.getBookId() > 0 )
        {
            pbvo = purchaseBookRepository.queryLibraryBookPage(prameter).get(0);
        }
        PurchaseRule r = purchaseRuleRepository.selectByPrimaryKey(pbvo.getRuleId());
        return dupCheckBookRes(pbvo, r);
    }
    
    /*
    * id:订购单的ID
    * */
    @Override
    public String exportLack(long id)
    {
        PurchaseBookVO o = new PurchaseBookVO();
        o.setOrderId(id);
        List<PurchaseBookVO> querylist = purchaseBookRepository.getAcceptBook(o);
        String fileName = IdGen.getDateUUId() + ".xls";
        String filePath = new POITool().getExportPath() + fileName;
        Map tilte = new HashMap();
        tilte.put("a", "订购号");
        tilte.put("b", "订单号");
        tilte.put("c", "书名");
        tilte.put("d", "作者");
        tilte.put("e", "isbn");
        tilte.put("f", "书类号");
        tilte.put("g", "出版日期");
        tilte.put("h", "出版社");
        tilte.put("i", "价格");
        tilte.put("j", "订购数量");
        tilte.put("k", "总价");
        tilte.put("l", "总到货数");
        tilte.put("m", "状态");
        if ( querylist != null && querylist.size() > 0 )
        {
            List<Object> exportList = new ArrayList<Object>();
            for (PurchaseBookVO vo : querylist)
            {
                Map m = new HashMap();
                m.put("a", vo.getPurchaseCode());
                m.put("b", vo.getOrderCode());
                m.put("c", vo.getTitle());
                m.put("d", vo.getAuthor());
                m.put("e", vo.getIsbn());
                m.put("f", vo.getBookType());
                m.put("g", vo.getPubdate());
                m.put("h", vo.getPublisher());
                m.put("i", new DecimalFormat("##0.00").format(vo.getPrice()));
                m.put("j", vo.getBookQty());
                m.put("k", vo.getTotalPrice());
                m.put("l", vo.getCheckedQty());
                m.put("m", vo.getRemainQty());
                if ( vo.getRemainQty() > 0 )
                {
                    m.put("m", "缺货" + vo.getRemainQty() + "册");
                }
                else if ( vo.getRemainQty() == 0 )
                {
                    m.put("m", "完全到货");
                }
                
                exportList.add(m);
            }
            return POITool.ExportData(tilte, exportList, filePath);
        }
        return fileName;
    }
    
    @Override
    public List<PurchaseBookVO> getPageBook(PurchaseBookVO purchaseBookVO)
    {
        return purchaseBookRepository.getPageBook(purchaseBookVO);
    }
    
    @Override
    public List<PurchaseBookVO> queryPagePreBook(PurchaseBookVO purchaseBookVO)
    {
        return purchaseBookRepository.queryPagePreBook(purchaseBookVO);
    }
    
    @Override
    public List<PurchaseBookVO> queryPageBookXH(PurchaseBookVO purchaseBookVO)
    {
        return purchaseBookRepository.queryPageBookXH(purchaseBookVO);
    }
}
