package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.poi.ExcelUtil;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.IdcardUtils;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.purchase.entity.vo.*;
import com.szcti.lcloud.purchase.repository.*;
import com.szcti.lcloud.purchase.service.PurchaseOrderService;
import com.szcti.lcloud.purchase.entity.*;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.*;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("purchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseRuleRepository purchaseRuleRepository;
    @Autowired
    private PrebookRepository prebookRepository;
    @Autowired
    private PurchaseBookRepository purchaseBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    SendeeRepository sendeeRepository;
    @Autowired
    OrderBatchRepository orderBatchRepository;
    @Autowired
    OrderbatchBookRepository orderbatchBookRepository;
    @Autowired
    LibraryAddressRepository libraryAddressRepository;
    @Autowired
    BudgetRepository budgetRepository;

    @Override
    public int countByExample(PurchaseOrderCriteria example){
        return purchaseOrderRepository.countByExample(example);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int deleteByExample(PurchaseOrderCriteria example){
        return purchaseOrderRepository.deleteByExample(example);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteByPrimaryKey(Long id){
        return purchaseOrderRepository.deleteByPrimaryKey(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(PurchaseOrder record){
        return purchaseOrderRepository.insert(record);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int insertSelective(PurchaseOrder record){
        return purchaseOrderRepository.insertSelective(record);
    }
    @Override
    public List<PurchaseOrder> selectByExampleWithBLOBs(PurchaseOrderCriteria example){
        return purchaseOrderRepository.selectByExampleWithBLOBs(example);
    }
    @Override
    public List<PurchaseOrder> selectByExample(PurchaseOrderCriteria example){
        return purchaseOrderRepository.selectByExample(example);
    }
    @Override
    public PurchaseOrder selectByPrimaryKey(Long id){
        return purchaseOrderRepository.selectByPrimaryKey(id);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByExampleSelective(@Param("record") PurchaseOrder record, @Param("example") PurchaseOrderCriteria example){
        return purchaseOrderRepository.deleteByExample(example);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByExampleWithBLOBs(PurchaseOrder record, PurchaseOrderCriteria example){
        return purchaseOrderRepository.deleteByExample(example);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByExample(PurchaseOrder record, PurchaseOrderCriteria example){
        return purchaseOrderRepository.updateByExample(record,example);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByPrimaryKeySelective(PurchaseOrder record){
        return purchaseOrderRepository.updateByPrimaryKeySelective(record);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByPrimaryKeyWithBLOBs(PurchaseOrder record){
        return purchaseOrderRepository.updateByPrimaryKeyWithBLOBs(record);
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public int updateByPrimaryKey(PurchaseOrder record){
        return purchaseOrderRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<PurchaseOrderVO> queryPage(PurchaseOrderVO purchaseOrderVO) {

        return purchaseOrderRepository.queryPage(purchaseOrderVO);
    }

    //判断规则是否被使用
    @Override
    public int checkRulesIsUse( List<HashMap<String,Object>> rulelist,PurchaseOrderVO purchaseOrderVO) {
        List<PurchaseOrderVO>  purchaseOrderVOList=purchaseOrderRepository.queryPage(purchaseOrderVO);
        PurchaseRule purchaseRule=null;


        //判断是否有规则正在被使用
                for(HashMap<String,Object> rl:rulelist){
                    //获取当前rule对象
                    long guid=(long) Integer.parseInt(rl.get("id").toString());
                    purchaseRule =purchaseRuleRepository.selectByPrimaryKey(guid);

                    //设置规则状态（0为未使用，1为已使用）
                    for(PurchaseOrderVO pv:purchaseOrderVOList) {
                        if(rl.containsValue(pv.getRuleName())){
                            purchaseRule.setIsUseStatus("1");
                        }
                        //统一更新该对象
                    purchaseRuleRepository.updateByPrimaryKey(purchaseRule);
                    }
           }
        return 1;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteBatchIds(List<String> ids) {
        System.out.println("----------------"+ids.size());
        return purchaseOrderRepository.deleteBatchIds(ids);
    }

    @Override
    public PurchaseOrderVO selectById(Long orderBuyId) {
        List<PurchaseOrderVO> list= purchaseOrderRepository.selectById(orderBuyId);
        if(list!=null&&list.size()>0)
        {
            return list.get(0);
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int checkBatchIds(Map map) {
        return purchaseOrderRepository.checkBatchIds(map);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int commitBatchIds(Map map) {
       if(map.get("id")!=null&&!"".equals(map.get("id").toString())){
           PurchaseOrder p=selectByPrimaryKey(Long.parseLong(map.get("id").toString()));
           if(IdcardUtils.isNum(p.getAddress())){
               LibraryAddress ad=libraryAddressRepository.selectByPrimaryKey(Long.parseLong(p.getAddress()));
               String address =ad.getAddress()+","+ad.getContact()+","+ad.getPhone();
               map.put("address",address);
           }
       }
        return purchaseOrderRepository.commitBatchIds(map);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int importExcel(List<PurchaseBookVO> list) {

        return purchaseOrderRepository.importExcel(list);
    }
    @Override
    public String exportOrderExcel(List<String> list) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        PurchaseOrderVO p=new PurchaseOrderVO();
        if(list!=null&&list.size()>0){
            p.setIds(list);
        }
        List<PurchaseOrderVO> querylist =queryPage(p);
        Map tilte =new HashMap();
        tilte.put("a","订单号");
        tilte.put("b","快递号");
        tilte.put("c","下单时间");
        tilte.put("d","图书馆");
        tilte.put("e","书总类数");
        tilte.put("f","总册数");
        tilte.put("g","总价格");
        tilte.put("h","收货信息");
        tilte.put("i","订单状态");
        tilte.put("j","备注");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(PurchaseOrderVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getOrderCode());
                m.put("b",vo.getExpressCode());
                m.put("c",vo.getOrderTime());
                m.put("d",vo.getLibraryName());
                m.put("e",vo.getAddress());
                m.put("f",vo.getBookTypeQty());
                m.put("g",vo.getTotalQuantity());
                m.put("h",new DecimalFormat("##0.00").format(vo.getTotalPrice()));
                switch(vo.getOrderStatus()+""){
                    case "0":
                        m.put("i","未处理");
                        break;
                    case "1":
                        m.put("i","已发货");
                        break;

                    case "3":
                        m.put("i","已完成");
                        break;
                    default:
                        m.put("i","未处理");
                        break;
                }
                m.put("j",vo.getSummary());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String exportExcel(List<String> list) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        PurchaseOrderVO p=new PurchaseOrderVO();
        if(list!=null&&list.size()>0){
            p.setIds(list);
        }
        List<PurchaseOrderVO> querylist =queryPage(p);
        Map tilte =new HashMap();
        tilte.put("a","订购号");
        tilte.put("b","创建者");
        tilte.put("c","创建时间");
        tilte.put("d","图书种类数");
        tilte.put("e","总册数");
        tilte.put("f","总价");
        tilte.put("g","审核状态");
        tilte.put("h","审核者");
        tilte.put("i","审核时间");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(PurchaseOrderVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getPurchaseCode());
                m.put("b",vo.getCreatorName());
                m.put("c",vo.getCreateTime());
                m.put("d",vo.getBookTypeQty());
                m.put("e",vo.getTotalQuantity());
                m.put("f",new DecimalFormat("##0.00").format(vo.getTotalPrice()));
                switch(vo.getCheckStatus()+""){
                    case "0":
                        m.put("g","未审核");
                        break;
                    case "1":
                        m.put("g","通过");
                        break;

                    case "2":
                        m.put("g","不通过");
                        break;
                    default:
                        m.put("g","未审核");
                        break;
                }
                //m.put("checkStatus",vo.getCheckStatus());
                m.put("h",vo.getCheckerName());
                m.put("i",vo.getCheckTime());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }


    public String ExportData(Map m,List<Map> list,String url) {
        try{
            //File f= new File("test.xls");
            File f= new File(url);
            if(!f.exists()){
                f.createNewFile();
            }
            OutputStream out = new FileOutputStream(f);
            ExcelUtil.exportExcel(m,list, out );
            out.close();
        }catch (Exception e){e.printStackTrace();}finally{}
        return url;
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int delete(Long id){
        PurchaseBookCriteria c =new PurchaseBookCriteria();
        c.createCriteria().andOrderIdEqualTo(id);
        purchaseBookRepository.deleteByExample(c);
        return purchaseOrderRepository.delete(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public Map<String, Object> insertBooks(String[] array, PurchaseOrder p ,Map<String, Object> params) {
        int count =0;
        if(p!=null&&p.getId()!=null&&p.getId()>0){
            count=insertBooks(array,p.getId(),params);
             p=selectByPrimaryKey(p.getId());
        }else if(p!=null&&(p.getId()==null&&StringUtils.isBlank(p.getPurchaseCode()))&&p.getRuleId()!=null){
                p.setPurchaseCode(createPurchaseCode(p.getRuleId()));
                insert(p);
                p=getByPurchaseCode(p.getPurchaseCode());
            count=insertBooks(array,p.getId(),params);
        }
        Map<String ,Object> m=new HashMap<String ,Object>();
        m.put("count",count);
        m.put("purchaseOrder",p);
        return m;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insertBooks(String[] array, Long orderId,Map<String, Object> params) {
        int count=0;
        for(String idn :array){
            String[] arr = idn.split("-");
            String id=arr[0];
            String bookQty=arr[1];
            String source="1";
            if(arr.length>2){
                source=arr[2];
            }
            Integer qt= Integer.parseInt(bookQty);
            Long idl=Long.parseLong(id);
            if(idl>0) {
                Prebook t = prebookRepository.selectByPrimaryKey(idl);
                if(source.equals("2")){
                    Book book=bookRepository.selectByPrimaryKey(idl);
                    t=getPreBook(book);
                }
                Long orderBuyBookId= getPurchaseBookId(t,orderId);//该订购单下的已选书中是否有这本书，有不需要添加，只需要增加数量
                if(orderBuyBookId!=null&&orderBuyBookId>0){
                    PurchaseBook ob= purchaseBookRepository.selectByPrimaryKey(orderBuyBookId);
                    int qt1=ob.getBookQty();
                    int qt2=qt+qt1;
                    ob.setBookQty(qt2);
                    ob.setTotalPrice(qt2*t.getPrice());
                    purchaseBookRepository.updateByPrimaryKey(ob);
                }
                else{
                    PurchaseBook ob = new PurchaseBook();
                    ob.setOrderId(orderId);
                    ob.setGoodsCode(t.getGoodsCode());
                    ob.setIsbn(t.getIsbn());
                    ob.setPrebookId(idl);
                    if(t.getSource()==2){
                        ob.setBookId(idl);
                        ob.setPrebookId(null);
                    }
                    ob.setBookQty(qt);
                    ob.setTotalPrice(ob.getBookQty()* t.getPrice());
                    ob.setStatus(new Integer("0"));
                    ob.setTitle(t.getTitle());
                    ob.setAuthor(t.getAuthor());
                    ob.setBookType(t.getBookType());
                    ob.setPublisher(t.getPublisher());
                    ob.setPubdate(DateUtils.formatDateTime(t.getPubdate()));
                    ob.setPrice(t.getPrice());
                    ob.setPages(t.getBookPages());
                    ob.setBookSize(t.getBookSize());
                    ob.setSource(t.getSource());
                    purchaseBookRepository.insert(ob);
                }
                    count++;
            }
        }
        refleshPurchaseOrder(orderId);
        return count;
    }

    private Prebook getPreBook(Book b) {
        Prebook p=new Prebook();
        p.setId(b.getId());
        p.setTitle(b.getTitle());
        p.setAuthor(b.getAuthor());
        p.setBookType(b.getBookType());
        p.setPublisher(b.getPublisher());
        p.setPubdate(b.getPubdate());
        p.setPrice(b.getPrice());
        p.setIsbn(b.getIsbn());
        p.setBookPages(b.getPages());
       // p.setBookSize(b.getBookSize());
        p.setSource(2);
        return p;
    }

    @Override
    public PurchaseOrder getByPurchaseCode(String purchaseCode) {
        return purchaseOrderRepository.getByPurchaseCode(purchaseCode);
    }
    //订购号根据订购规则生成
    @Override
    public String createPurchaseCode(Long ruleId) {
        if(ruleId==null||ruleId==0){
            return "D"+IdGen.randomLong();
        }
        PurchaseRule pr=purchaseRuleRepository.selectByPrimaryKey(ruleId);
        String prefix=pr.getPrefix();
        String  begin= pr.getStartnum();
        String len=pr.getTotalnum();
        if(StringUtils.isBlank(prefix)
            ||StringUtils.isBlank(begin)
            ||StringUtils.isBlank(len)){
            return "D"+IdGen.randomLong();
        }
        int colen=Integer.parseInt(len);
        if(5-prefix.length()>0){
            colen=colen+(5-prefix.length());}//前缀小于5位时，随机号数长度加长
            int origin=Integer.parseInt(begin);
            int b=Integer.parseInt(begin);
        while (origin<=b){
             origin=(int)Math.pow(10,(colen));
        }
            return prefix+(new Random().nextInt(origin));
    }

    //根据isbn号等查重条件找出订购单已经存在的已选图书
    @Override
    public Long getPurchaseBookId(Prebook t, Long orderId) {
        BookInfo b= new BookInfo();
        b.setOrderId(orderId);
        b.setTitle(t.getTitle());
        b.setIsbn(t.getIsbn());
        //b.setAuthor(t.getAuthor());
        //b.setBookType(t.getBookType());
        //b.setPrice(t.getPrice());
        //b.setPublisher(t.getPublisher());
        //b.setPubdate(DateUtils.formatDateTime(t.getPubdate()));
        List<Long> li= new ArrayList<Long>();
        li= purchaseBookRepository.getBooks(b);
        if(li!=null&&li.size()>0)
        {
            return li.get(0);
        }
        return null;
    }
    @Transactional(rollbackFor=Exception.class)
    @Override
    public void refleshPurchaseOrder(Long orderBuyId) {
        purchaseOrderRepository.refleshPurchaseOrder(orderBuyId);
    }
    /*
    * 验收订单列表
    * */
    @Override
    public List<PurchaseOrderVO> queryAcceptPage(PurchaseOrderVO purchaseOrderVO) {
        List<PurchaseOrderVO> list=queryPage(purchaseOrderVO);
        for(PurchaseOrderVO p :list){
            OrderBatchVO bv = new OrderBatchVO();
            bv.setPurchaseCode(p.getPurchaseCode());
            List<OrderBatchVO> batchs=orderBatchRepository.queryPage(bv);
            for(OrderBatchVO batchVO:batchs){
                OrderbatchBookVO vo =new OrderbatchBookVO();
                vo.setOrderBatchId(batchVO.getId());
                batchVO.setBooklist(orderbatchBookRepository.queryPage(vo));
            }
            p.setBatchs(batchs);
        }
        return list;
    }
    /*
    * 刷新整个订购单验收状态和发运批的验收结果
    * id:订购单id
    * */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void refleshAcceptStatus(Long id){
        PurchaseOrderVO query= new PurchaseOrderVO();
        query.setId(id);
        List<PurchaseOrderVO> list=queryAcceptPage(query);
        for(PurchaseOrderVO p :list){
            Map<String,String> bm=new HashMap<String,String>();
            for(OrderBatchVO batchVO:p.getBatchs()){
                OrderBatch ob=orderBatchRepository.selectByPrimaryKey(batchVO.getId());
                ob.setAcceptStatus("0");
                Map<String,String> m=new HashMap<String,String>();
                int checkedTotal=0;
                for(OrderbatchBookVO bookVO:batchVO.getBooklist()){
                        m.put(bookVO.getId()+"",bookVO.getStatus()+"");
                        if(bookVO.getCheckedQty()!=null&&bookVO.getCheckedQty()>0)
                        {
                            checkedTotal+=bookVO.getCheckedQty();
                        }
                    }
                    if(m.containsValue("0")&&(m.containsValue("1")||m.containsValue("2"))){
                        ob.setAcceptStatus("2");//部份验收
                    }else if(!m.containsValue("0")&&!m.containsValue("2")){
                        ob.setAcceptStatus("1");//已完成
                    }else if(!m.containsValue("0")&&m.containsValue("2")){
                        ob.setAcceptStatus("3");//有缺货
                        ob.setRemark("验收数量"+checkedTotal+"比总数"+batchVO.getTotalQuantity()+"缺少"+(batchVO.getTotalQuantity()-checkedTotal));
                    }
                bm.put(ob.getId()+"",ob.getAcceptStatus());
                orderBatchRepository.updateByPrimaryKey(ob);
                }
            }
        PurchaseBookVO pbvo=new PurchaseBookVO();
        pbvo.setOrderId(id);
        List<PurchaseBookVO> pblist=purchaseBookRepository.getAcceptBook(pbvo);
        Map<String,String> res= new HashMap<String,String>();
        for(PurchaseBookVO b: pblist){
            if(b.getRemainQty()>0){
                res.put(b.getId()+"","false");
            }else if(b.getRemainQty()==0){
                res.put(b.getId()+"","true");
            }
        }
        if(res.containsValue("false")){
            PurchaseOrder purchaseOrder=selectByPrimaryKey(id);
            purchaseOrder.setOrderStatus(4);
            updateByPrimaryKey(purchaseOrder);
        }
        else if(res.containsValue("true")&&!res.containsValue("false")){
            PurchaseOrder purchaseOrder=selectByPrimaryKey(id);
            purchaseOrder.setOrderStatus(6);
            updateByPrimaryKey(purchaseOrder);
        }else if(res.containsValue("true")&&res.containsValue("false")){
            PurchaseOrder purchaseOrder=selectByPrimaryKey(id);
            purchaseOrder.setOrderStatus(4);
            updateByPrimaryKey(purchaseOrder);
        }else if(!res.containsValue("true")&&!res.containsValue("false")){
            PurchaseOrder purchaseOrder=selectByPrimaryKey(id);
            purchaseOrder.setOrderStatus(3);
            updateByPrimaryKey(purchaseOrder);
        }
    }

    @Override
    public void refleshCheckStatus(Long userid,Long orderId) {
        PurchaseBookCriteria c =new PurchaseBookCriteria();
        c.createCriteria().andStatusNotEqualTo(1).andOrderIdEqualTo(orderId);
        int n=purchaseBookRepository.countByExample(c);
        if(n==0){
            PurchaseOrder p =purchaseOrderRepository.selectByPrimaryKey(orderId);
            p.setCheckStatus(new Integer("1"));
            if(userid!=null){
                p.setChecker(userid);
                p.setCheckTime(new Date());
            }
            purchaseOrderRepository.updateByPrimaryKey(p);
        }
    }

    @Override
    public List<HashMap<String, Object>> queryBudgetMapList(Map params) {
        return budgetRepository.queryMapList(params);
    }

    @Override
    public List<HashMap<String, Object>> queryAddressMapList(Map params) {
        return libraryAddressRepository.queryMapList(params);
    }

    @Override
    public int insertPurchaseBook(PurchaseBook purchaseBook) {
        return 0;
    }

    @Override
    public PurchaseBook queryPurchaseBook(PurchaseBook purchaseBook) {
        return null;
    }

    @Override
    public int updatePurchaseBook(PurchaseBook purchaseBook) {
        return 0;
    }

    @Override
    public int deletePurchaseBook(PurchaseBook purchaseBook) {
        return 0;
    }

    public static void main(String[] args) {
        //System.out.println(getDateUUId());
    }
}
