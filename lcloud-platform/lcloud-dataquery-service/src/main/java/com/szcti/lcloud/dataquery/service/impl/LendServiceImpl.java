package com.szcti.lcloud.dataquery.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.dataquery.entity.vo.BookVO;
import com.szcti.lcloud.dataquery.entity.vo.HoldingVO;
import com.szcti.lcloud.dataquery.entity.vo.LendRecordVO;
import com.szcti.lcloud.dataquery.repository.LendDao;
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
 * @date: 2018/8/6 8:53
 */
@Service
public class LendServiceImpl implements LendService {

    @Autowired
    private LendDao lendDao;

    @Override
    @Transactional(readOnly = true)
    public List<BookVO> findBookList(@NonNull BookVO lendBackVO){
        return lendDao.findBookList(lendBackVO);
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public String exportHoldingExcel(HoldingVO holding){
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        List<HoldingVO> holdingList = lendDao.findHoldingList(holding);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","所属图书馆");
        title.put("b","条码号");
        title.put("c","读者证号");
        title.put("d","索取号");
        title.put("e","流通类型");
        title.put("f","馆藏地点");
        title.put("g","馆藏状态");
        title.put("h","借出日期");
        title.put("i","应还日期");
        title.put("j","续借次数");
        title.put("k","价格");
        if(holdingList!=null&&holdingList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(HoldingVO vo:holdingList){
                m = new HashMap<>(16);
                m.put("a",vo.getLibName());
                m.put("b",vo.getBarCode());
                m.put("c",vo.getReaderCardNumber());
                m.put("d",vo.getCallNo());
                m.put("e",vo.getActName());
                m.put("f",vo.getShelf());
                switch(vo.getStatus()+""){
                    case "0":
                        m.put("g","可借阅");
                        break;
                    case "1":
                        m.put("g","在借中");
                        break;
                    case "2":
                        m.put("g","可阅览");
                        break;
                    case "3":
                        m.put("g","污损");
                        break;
                    case "4":
                        m.put("g","遗失");
                        break;
                    case "5":
                        m.put("g","剔除");
                        break;
                    default:m.put("g","");
                }

                m.put("h",vo.getLendTime());
                m.put("i",vo.getDueBackTime());
                m.put("j",vo.getReLendCount());
                m.put("k",new DecimalFormat("##0.00").format(vo.getPrice()));
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public String exportRecordExcel(LendRecordVO record){
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        List<LendRecordVO> recordList = lendDao.findRecordList(record);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","所属图书馆");
        title.put("b","读者姓名");
        title.put("c","读者证号");
        title.put("d","操作日期");
        title.put("e","操作类型");
        title.put("f","操作员");
        title.put("g","馆藏地点");
        title.put("h","书名");
        title.put("i","索取号");
        title.put("j","条码号");
        if(recordList!=null&&recordList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(LendRecordVO vo:recordList){
                m = new HashMap<>(16);
                m.put("a",vo.getLibName());
                m.put("b",vo.getUserName());
                m.put("c",vo.getReaderCardNumber());
                m.put("d",vo.getLendTime());
                switch(vo.getLendStatus()+""){
                    case "0":
                        m.put("e","在借");
                        break;
                    case "1":
                        m.put("e","已还");
                        break;
                    case "2":
                        m.put("e","续借");
                        break;
                    case "3":
                        m.put("e","逾期");
                        break;
                    default:m.put("e","");
                }
                m.put("f",vo.getOperator());
                m.put("g",vo.getShelf());
                m.put("h",vo.getTitle());
                m.put("i",vo.getCallNo());
                m.put("j",vo.getBarCode());
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }


    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public String exportReaderLendExcel(HoldingVO holding){
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        List<HoldingVO> holdingList = lendDao.findHoldingByReader(holding);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","所属图书馆");
        title.put("b","条码号");
        title.put("c","书名");
        title.put("d","作者");
        title.put("e","索取号");
        title.put("f","借阅状态");
        title.put("g","借出日期");
        title.put("h","应还日期");
        title.put("i","续借次数");
        title.put("j","价格");
        if(holdingList!=null&&holdingList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(HoldingVO vo:holdingList){
                m = new HashMap<>(16);
                m.put("a",vo.getLibName());
                m.put("b",vo.getBarCode());
                m.put("c",vo.getTitle());
                m.put("d",vo.getAuthor());
                m.put("e",vo.getCallNo());
                switch(vo.getLendStatus()+""){
                    case "0":
                        m.put("f","在借");
                        break;
                    case "1":
                        m.put("f","已还");
                        break;
                    case "2":
                        m.put("f","续借");
                        break;
                    case "3":
                        m.put("f","逾期");
                        break;
                    default:m.put("f","");
                }
                m.put("g",vo.getLendTime());
                m.put("h",vo.getDueBackTime());
                m.put("i",vo.getReLendCount());
                m.put("j",new DecimalFormat("##0.00").format(vo.getPrice()));
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

}
