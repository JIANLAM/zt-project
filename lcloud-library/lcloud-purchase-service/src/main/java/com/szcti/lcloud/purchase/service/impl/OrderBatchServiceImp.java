package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.purchase.entity.OrderBatch;
import com.szcti.lcloud.purchase.entity.OrderBatchCriteria;
import com.szcti.lcloud.purchase.entity.vo.OrderBatchVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseOrderVO;
import com.szcti.lcloud.purchase.repository.OrderBatchRepository;
import com.szcti.lcloud.purchase.repository.PurchaseOrderRepository;
import com.szcti.lcloud.purchase.service.OrderBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("orderBatchService")
public class OrderBatchServiceImp implements OrderBatchService {
    @Autowired
    private  OrderBatchRepository orderBatchRepository;
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return orderBatchRepository.deleteByPrimaryKey( id);
    }

    @Override
    public int insert(OrderBatch record) {
        return orderBatchRepository.insert(record);
    }

    @Override
    public List<OrderBatch> selectByExample(OrderBatchCriteria example) {
        return orderBatchRepository.selectByExample(example);
    }

    @Override
    public OrderBatch selectByPrimaryKey(Long id) {
        return orderBatchRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(OrderBatch record) {
        return orderBatchRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<OrderBatchVO> queryPage(OrderBatchVO orderBatchVO) {
        return orderBatchRepository.queryPage(orderBatchVO);
    }

    @Override
    public String exportExcel(List list) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        OrderBatchVO p=new OrderBatchVO();
        if(list!=null&&list.size()>0){
            p.setIds(list);
        }
        List<OrderBatchVO> querylist =queryPage(p);
        Map tilte =new HashMap();
        tilte.put("a","订购号");
        tilte.put("b","批次号");
        tilte.put("c","快递信息");
        tilte.put("d","创建时间");
        tilte.put("e","图书总数");
        tilte.put("f","总册数");
        tilte.put("g","经费类型");
        tilte.put("h","图书馆供应商");
        tilte.put("i","验收状态");
        tilte.put("j","验收人");
        tilte.put("k","验收时间");
        tilte.put("l","备注");
        Map<String,PurchaseOrderVO> order=new HashMap<String,PurchaseOrderVO>();
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(OrderBatchVO vo:querylist){
                if(!order.containsKey(vo.getPurchaseCode())){
                    order.put(vo.getPurchaseCode(),getPurchaseOrder(vo.getPurchaseCode()));
                    exportList.add(getOrderMap(order.get(vo.getPurchaseCode())));
                }
                Map m =new HashMap();
                m.put("a",vo.getPurchaseCode());
                m.put("b",vo.getCoding());
                m.put("c",vo.getExpressCode());
                m.put("d",vo.getCreatorName());
                m.put("e",vo.getBookTypeQty());
                m.put("f",vo.getTotalQuantity());
                m.put("g",vo.getButgetCode());
                m.put("h",vo.getSupplierName());
                switch(vo.getAcceptStatus()+""){
                    case "0":
                        m.put("i","待验收");
                        break;
                    case "1":
                        m.put("i","已验收");
                        break;
                    default:
                        m.put("i","待验收");
                        break;
                }
                m.put("j",vo.getCheckerName());
                m.put("k",vo.getCheckTime());
                m.put("l",vo.getRemark());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    private Object getOrderMap(PurchaseOrderVO vo) {
        Map order =new HashMap();
        order.put("a",vo.getPurchaseCode());
        order.put("b","");
        order.put("c",vo.getExpressInfo());
        order.put("d",vo.getCreateTime());
        order.put("e",vo.getBookTypeQty());
        order.put("f",vo.getTotalQuantity());
        order.put("g",vo.getBudgetCode());
        order.put("h",vo.getSupplierName());
        order.put("i","");
        order.put("j","");
        order.put("k","");
        order.put("l",vo.getSummary());
        return order;
    }

    private PurchaseOrderVO getPurchaseOrder(String purchaseCode) {
        PurchaseOrderVO vo =new PurchaseOrderVO();
        vo.setPurchaseCode(purchaseCode);
        List<PurchaseOrderVO> list= purchaseOrderRepository.queryPage(vo);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}