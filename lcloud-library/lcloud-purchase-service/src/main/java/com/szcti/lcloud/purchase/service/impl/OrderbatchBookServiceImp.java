package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.JSONUtil;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.purchase.entity.*;
import com.szcti.lcloud.purchase.entity.vo.HoldingVO;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;
import com.szcti.lcloud.purchase.entity.vo.OrderbatchBookVO;
import com.szcti.lcloud.purchase.repository.*;
import com.szcti.lcloud.purchase.service.OrderbatchBookService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("orderbatchBookService")
public class OrderbatchBookServiceImp implements OrderbatchBookService {
    @Autowired
    private OrderbatchBookRepository orderbatchBookRepository;
    @Autowired
    private OrderBatchRepository orderBatchRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    private PurchaseBookRepository purchaseBookRepository;
    @Autowired
    private AcceptanceRepository acceptanceRepository;
    @Autowired
    private AcceptanceDetailRepository acceptanceDetailRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderbatchBookRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(OrderbatchBook record) {
        return orderbatchBookRepository.insert(record);
    }

    @Override
    public List<OrderbatchBook> selectByExample(OrderbatchBookCriteria example) {
        return orderbatchBookRepository.selectByExample(example);
    }

    @Override
    public OrderbatchBook selectByPrimaryKey(Long id) {
        return orderbatchBookRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(OrderbatchBook record) {
        return orderbatchBookRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderbatchBookVO> queryPage(OrderbatchBookVO orderbatchBookVO) {
        return orderbatchBookRepository.queryPage(orderbatchBookVO);
    }
    /*
     *  注意此方法只用于 已创建的发运单情况
     *  设置 验收数
     *   array:id-Qt组成，orderBuyId：订单id，params:其它额外参数
     * */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int acceptQty(String[] array, Long orderBuyId,Map<String, Object> params) {
        int count=0;
        for(String idn :array){
            String[] arr = idn.split("-");
            String id=arr[0];
            String acceptQty=arr[1];
            Integer qt= Integer.parseInt(acceptQty);
            Long idl=Long.parseLong(id);
            List<AcceptanceDetail> listAd=new ArrayList<AcceptanceDetail>();
            OrderBatch orderBatch=null;
            PurchaseOrder purchaseOrder=null;
            if(idl>0) {
                OrderbatchBook ob= orderbatchBookRepository.selectByPrimaryKey(idl);
                if(orderBatch==null){
                    orderBatch=orderBatchRepository.selectByPrimaryKey(ob.getOrderBatchId());
                }
                if(purchaseOrder==null){
                    purchaseOrder=purchaseOrderRepository.getByPurchaseCode(orderBatch.getPurchaseCode());
                }
                ob.setCheckedQty(qt);
                ob.setIsHandIn("1");
                ob.setChecker(Long.parseLong((String)params.get("checker")));
                ob.setCheckTime(DateUtils.parseDate(DateUtils.getDateTime()));
                if(ob.getBookQty()-qt==0){
                    ob.setStatus("1");
                    ob.setCheckmsg("已完成");
                }else if(ob.getBookQty()-qt>0){
                    ob.setStatus("2");
                    ob.setCheckmsg("缺"+(ob.getBookQty()-qt)+"本");
                }
                orderbatchBookRepository.updateByPrimaryKey(ob);
                count++;
            }
        }
        return count;
    }
    //导出发货验收单
    @Override
    public String exportExcel(List<String> list) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        OrderbatchBookVO p=new OrderbatchBookVO();
        if(list!=null&&list.size()>0){
            p.setIds(list);
        }
        List<OrderbatchBookVO> querylist =queryPage(p);
        Map tilte =new HashMap();
        tilte.put("a","序号");
        tilte.put("b","图书名称");
        tilte.put("c","isbn");
        tilte.put("d","作者");
        tilte.put("e","分类号");
        tilte.put("f","出版社");
        tilte.put("g","出版时间");
        tilte.put("h","单价");
        tilte.put("i","订购数");
        tilte.put("j","本次批发数");
        tilte.put("k","实际到货数");
        int i=0;
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(OrderbatchBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",i++);
                m.put("b",vo.getTitle());
                m.put("c",vo.getIsbn());
                m.put("d",vo.getAuthor());
                m.put("e",vo.getBookType());
                m.put("f",vo.getPublisher());
                m.put("g",vo.getPubdate());
                m.put("h",new DecimalFormat("##0.00").format(vo.getPrice()));
                m.put("i",vo.getOrderQty());
                m.put("j",vo.getBookQty());
                m.put("k",vo.getCheckedQty());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int acceptBookQty(OrderBatchVO vo) {
        int count=0;
        if(vo!=null&&vo.getBooklist()!=null){
            OrderBatch o=new OrderBatch();
            if(vo.getId()!=null&&vo.getId()>0){
                o=orderBatchRepository.selectByPrimaryKey(vo.getId());
            }else if(vo.getId()==null){
                o.setCreateTime(DateUtils.parseDate(vo.getCreateTime()));
                o.setCreator(vo.getCreator());
                o.setBookTypeQty(vo.getBookTypeQty());
                o.setButgetId(vo.getButgetId());
                o.setPurchaseCode(vo.getPurchaseCode());
                o.setCoding(IdGen.randomLong()+"");
                o.setBookTypeQty(vo.getBooklist().size());
                o.setChecker(vo.getChecker());
                o.setCheckTime(DateUtils.parseDate(vo.getCheckTime()));
                o.setAcceptStatus("1");
                o.setAcceptCode(IdGen.randomLong()+"");
                orderBatchRepository.insert(o);
                OrderBatchCriteria obc=new OrderBatchCriteria();
                obc.createCriteria().andCodingEqualTo(o.getCoding());
                List<OrderBatch> lib=orderBatchRepository.selectByExample(obc);
                if(lib!=null&&lib.size()>0){o=lib.get(0);}
            }
                List hlist=vo.getBooklist();
                int total=0;
                int checkTotal=0;
                Float totalPrice=new Float(0);
                for(Object obj:hlist){
                    OrderbatchBook obvo= (OrderbatchBook)JSONObject.toBean(JSONObject.fromObject(JSONUtil.object2String(obj)), OrderbatchBook.class);
                    if(obvo.getBookQty()!=null&&obvo.getBookQty()>0){
                        total=total+obvo.getBookQty();
                        checkTotal=checkTotal+obvo.getCheckedQty();
                        PurchaseBook purchaseBook=purchaseBookRepository.selectByPrimaryKey(obvo.getPurchaseBookId());
                        totalPrice=totalPrice+obvo.getCheckedQty()*purchaseBook.getPrice();
                        obvo.setChecker(o.getChecker());
                        obvo.setCheckTime(o.getCheckTime());
                        if(obvo.getBookQty().equals(obvo.getCheckedQty())){
                            obvo.setStatus("1");
                            obvo.setCheckmsg("已完成");
                        }else{
                            obvo.setStatus("2");
                            obvo.setCheckmsg("缺"+(obvo.getBookQty()-obvo.getCheckedQty())+"");
                        }
                        if(obvo.getId()!=null&&obvo.getId()>0){
                            updateByPrimaryKey(obvo);
                        }else{insert(obvo);}
                    }
                }
            o.setTotalQuantity(total);
                if(total>checkTotal){
                    o.setAcceptStatus("3");
                }if(total==checkTotal){
                    o.setAcceptStatus("1");
            }
            String acceptCode=IdGen.randomLong()+"";
                o.setAcceptCode(acceptCode);
                orderBatchRepository.updateByPrimaryKey(o);
                Acceptance a= new Acceptance();
                a.setStatus(o.getAcceptStatus());
                a.setAcceptCode(acceptCode);
                a.setCreateTime(o.getCheckTime());
                a.setCreator(o.getChecker());
                a.setLibraryId(vo.getLibraryId());
                a.setTotalPrice(totalPrice+"");
                acceptanceRepository.insert(a);
                AcceptanceCriteria ac=new AcceptanceCriteria();
                ac.createCriteria().andAcceptCodeEqualTo(acceptCode);
                List<Acceptance> aclist=acceptanceRepository.selectByExample(ac);
                {
                    a=aclist.get(0);
                }
                OrderbatchBookCriteria newobc=new OrderbatchBookCriteria();
                newobc.createCriteria().andOrderBatchIdEqualTo(o.getId());
                List<OrderbatchBook> newObList=selectByExample(newobc);
                if(newObList!=null&&newObList.size()>0){
                    for(OrderbatchBook orbook:newObList){
                        AcceptanceDetail ad=new AcceptanceDetail();
                        ad.setAcceptanceId(a.getId());
                        ad.setAcceptQuantity(orbook.getCheckedQty());
                        ad.setOrderbatchBookId(orbook.getId());
                        ad.setPurchaseBookId(orbook.getPurchaseBookId());
                        ad.setShipQuantity(orbook.getBookQty());
                        ad.setPurchaseCode(o.getPurchaseCode());
                        ad.setIsbn(purchaseBookRepository.selectByPrimaryKey(orbook.getPurchaseBookId()).getIsbn());
                        acceptanceDetailRepository.insert(ad);
                        count++;
                    }
                }
        }
        return count;
    }
}