package com.szcti.lcloud.datacount.service.impl;
import com.google.api.client.util.Data;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.datacount.entity.vo.*;
import com.szcti.lcloud.datacount.repository.InterviewCountDao;
import com.szcti.lcloud.datacount.service.InterviewCountService;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Date;

/**
 * @Title: 采访统计Service
 * @Description: 各种统计的Service
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Service
public class InterviewCountServiceImpl implements InterviewCountService {

    @Autowired
    private InterviewCountDao interviewCountDao;

    /**
     * 导出统计数据
     * @param conditionVO
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exports(ConditionVO conditionVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        //导出订购单统计
        if(conditionVO.getExportCount() == 1){
            List<PurchaseOrderVO> purchaseOrderVOList = interviewCountDao.purchaseOrderCount(conditionVO);
            Map tilte =new HashMap(16);
            tilte.put("a","订单号");
            tilte.put("b","创建者账户");
            tilte.put("c","创建者姓名");
            tilte.put("d","下单时间");
            tilte.put("e","图书种数");
            tilte.put("f","图书册数");
            tilte.put("g","总价（万元￥）");
            tilte.put("h","审核者账户");
            tilte.put("i","审核者姓名");
            tilte.put("j","状态");
            tilte.put("k","审核时间");
            if(purchaseOrderVOList!=null && purchaseOrderVOList.size()>0){
                List<Object> exportList = new ArrayList<>();
                Map m;
                for(PurchaseOrderVO vo:purchaseOrderVOList){
                    m = new HashMap(16);
                    m.put("a",vo.getPurchaseCode());
                    m.put("b",vo.getCreateLoginName());
                    m.put("c",vo.getCreateUserName());
                    if (vo.getOrderTime() != null && !"".equals(vo.getOrderTime())){
                       /* DateFormatUtils.format(java.sql.Date.valueOf(vo.getOrderTime()), "yyyy-MM-dd");*/
                       /* m.put("d", new Date(vo.getOrderTime()).toString());*/
                        m.put("d", vo.getOrderTime().substring(0,10));
                    }else{
                        m.put("d", vo.getOrderTime());
                    }
                    m.put("e",vo.getBookTypeCount());
                    m.put("f",vo.getTotalQuantity());
                    m.put("g",new DecimalFormat("##0.00").format(vo.getTotalPrice()));
                    m.put("h",vo.getCheckerLoginName());
                    m.put("i",vo.getCheckerUserName());

                    switch(vo.getCheckStatus()+""){
                        case "0":
                            m.put("j","未审核");
                            break;
                        case "1":
                            m.put("j","审核通过");
                            break;
                        case "2":
                            m.put("j","审核失败");
                            break;
                        case "3":
                            m.put("j","无需审核");
                            break;
                    }
                    if (vo.getCheckTime() != null && !"".equals(vo.getCheckTime())){
                        m.put("k",vo.getCheckTime().substring(0,10));
                    }else{
                        m.put("k",vo.getCheckTime());
                    }
                    exportList.add(m);
                }
                return POITool.ExportData(tilte,exportList,filePath);
            }
        }else if(conditionVO.getExportCount() == 2){
                //导出借购单统计
                List<LendBuyOrderVO> lendBuyOrderVOList = interviewCountDao.lendBuyOrderCount(conditionVO);
                Map tilte =new HashMap(16);
                tilte.put("a","订单号");
                tilte.put("b","读者证号");
                tilte.put("c","读者姓名");
                tilte.put("d","下单时间");
                tilte.put("e","图书册数");
                tilte.put("f","总价（元￥）");
                if(lendBuyOrderVOList!=null && lendBuyOrderVOList.size()>0){
                    List<Object> exportList = new ArrayList<>();
                    Map m;
                    for(LendBuyOrderVO vo:lendBuyOrderVOList){
                        m = new HashMap(16);
                        m.put("a",vo.getOrderNo());
                        m.put("b",vo.getReaderCardNumber());
                        m.put("c",vo.getCreateName());
                        m.put("d",vo.getCreateTime());
                        m.put("e",vo.getBookCount());
                        m.put("f",new DecimalFormat("##0.00").format(vo.getTotalMoney()));
                        exportList.add(m);
                    }
                    return POITool.ExportData(tilte,exportList,filePath);
                }
            }else if(conditionVO.getExportCount() == 3){
            //导出图书借购统计
            List<LendBuyBookVO> lendBuyBookVOList = interviewCountDao.bookLendBuyCount(conditionVO);
            Map tilte =new HashMap(16);
            tilte.put("a","图书名称");
            tilte.put("b","ISBN");
            tilte.put("c","作者");
            tilte.put("d","分类号");
            tilte.put("e","出版者");
            tilte.put("f","出版时间");
            tilte.put("g","单价（元￥）");
            tilte.put("h","总册数");
            tilte.put("i","总价格（元￥）");
            if(lendBuyBookVOList!=null && lendBuyBookVOList.size()>0){
                List<Object> exportList = new ArrayList<>();
                Map m;
                for(LendBuyBookVO vo:lendBuyBookVOList){
                    m = new HashMap(16);
                    m.put("a",vo.getTitle());
                    m.put("b",vo.getISBN());
                    m.put("c",vo.getAuthor());
                    m.put("d",vo.getBookType());
                    m.put("e",vo.getPublisher());
                    m.put("f",vo.getPubDate());
                    m.put("g",new DecimalFormat("##0.00").format(vo.getUnivalent()));
                    m.put("h",vo.getBookCount());
                    m.put("i",new DecimalFormat("##0.00").format(vo.getTotal()));
                    exportList.add(m);
                }
                return POITool.ExportData(tilte,exportList,filePath);
            }
        }else if(conditionVO.getExportCount() == 4){
            //导出图书荐购统计
            List<RecommBuyBookVO> recommBuyBookVOList = interviewCountDao.recommBuyCount(conditionVO);
            Map tilte =new HashMap(16);
            tilte.put("a","图书名称");
            tilte.put("b","ISBN");
            tilte.put("c","作者");
            tilte.put("d","分类号");
            tilte.put("e","出版者");
            tilte.put("f","出版时间");
            tilte.put("g","单价（元￥）");
            tilte.put("h","荐购次数");
            if(recommBuyBookVOList!=null && recommBuyBookVOList.size()>0){
                List<Object> exportList = new ArrayList<>();
                Map m;
                for(RecommBuyBookVO vo:recommBuyBookVOList){
                    m = new HashMap(16);
                    m.put("a",vo.getTitle());
                    m.put("b",vo.getISBN());
                    m.put("c",vo.getAuthor());
                    m.put("d",vo.getBookType());
                    m.put("e",vo.getPublisher());
                    m.put("f",vo.getPubDate());
                    m.put("g",new DecimalFormat("##0.00").format(vo.getPrice()));
                    m.put("h",vo.getRecommBuyCount());
                    exportList.add(m);
                }
                return POITool.ExportData(tilte,exportList,filePath);
            }
        }
        return fileName;
    }

}
