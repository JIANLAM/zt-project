package com.szcti.lcloud.dataquery.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.dataquery.entity.vo.*;
import com.szcti.lcloud.dataquery.repository.FinanceDao;
import com.szcti.lcloud.dataquery.repository.LendDao;
import com.szcti.lcloud.dataquery.service.FinanceService;
import com.szcti.lcloud.dataquery.service.LendService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/8/9 8:53
 */
@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceDao financeDao;

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public String exportExcel(FinanceVO finance){
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        List<FinanceVO> financeList = financeDao.findList(finance);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","读者证号");
        title.put("b","读者姓名");
        title.put("c","所属馆");
        title.put("d","操作员");
        title.put("e","财经类型");
        title.put("f","日期");
        title.put("g","金额");
        title.put("h","付款方式");
        if(financeList!=null&&financeList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(FinanceVO vo:financeList){
                m = new HashMap<>(16);
                m.put("a",vo.getReaderCardNumber());
                m.put("b",vo.getUserName());
                m.put("c",vo.getLibName());
                m.put("d",vo.getOperatorName());
                m.put("e",vo.getTypeName());
                m.put("f",vo.getFinanceDatetime());
                m.put("g",new DecimalFormat("##0.00").format(vo.getForfeit()));
                switch(vo.getPayMethod()+""){
                    case "0":
                        m.put("h","现金");
                        break;
                    default:m.put("h","其他");
                }
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public String exportUnusualDataExcel(ForfeitBookVO forfeitBook){
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        List<ForfeitBookVO> list = financeDao.findForfeitList(forfeitBook);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","条码号");
        title.put("b","书名");
        title.put("c","作者");
        title.put("d","出版社");
        title.put("e","流通类型");
        title.put("f","索取号");
        title.put("g","所属馆");
        title.put("h","操作时间");
        title.put("i","操作员");
        title.put("j","价格");
        title.put("k","罚款金额");
        if(list!=null&&list.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(ForfeitBookVO vo:list){
                m = new HashMap<>(16);
                m.put("a",vo.getBarCode());
                m.put("b",vo.getTitle());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getPublisher());
                m.put("e",vo.getActName());
                m.put("f",vo.getCallNo());
                m.put("g",vo.getLibName());
                m.put("h",vo.getCreateDate());
                m.put("i",vo.getOperatorName());
                m.put("j",new DecimalFormat("##0.00").format(vo.getPrice()));
                m.put("k",new DecimalFormat("##0.00").format(vo.getForfeit()));
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

}
