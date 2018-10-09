package com.szcti.lcloud.budget.service.impl;

import com.szcti.lcloud.budget.entity.Budget;
import com.szcti.lcloud.budget.entity.BudgetCriteria;
import com.szcti.lcloud.budget.entity.vo.BudgetVO;
import com.szcti.lcloud.budget.repository.BudgetRepository;
import com.szcti.lcloud.budget.service.BudgetService;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("budgetService")
public class BudgetServiceImp implements BudgetService {
@Autowired
private BudgetRepository budgetRepository;
    @Override
    public int countByExample(BudgetCriteria example) {
        return budgetRepository.countByExample(example);
    }

    @Override
    public int deleteByExample(BudgetCriteria example) {
        return budgetRepository.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return budgetRepository.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Budget record) {
        record.setCreatTime(DateUtils.parseDate(DateUtils.getDateTime()));
        String coding=getNextCode(record.getCoding());
        if(record.getCoding()==null||record.getCoding().equals(""))
        {record.setCoding(coding);}
        return budgetRepository.insert(record);
    }

    @Override
    public int insertSelective(Budget record) {
        return budgetRepository.insertSelective(record);
    }

    @Override
    public List<Budget> selectByExample(BudgetCriteria example) {
        return budgetRepository.selectByExample(example);
    }

    @Override
    public Budget selectByPrimaryKey(Long id) {
        return budgetRepository.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(Budget record, BudgetCriteria example) {
        return budgetRepository.updateByExampleSelective( record, example);
    }

    @Override
    public int updateByExample(Budget record, BudgetCriteria example) {
        return budgetRepository.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(Budget record) {
        return budgetRepository.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Budget record) {
        return budgetRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<BudgetVO> queryPage(BudgetVO budgetVO) {
         List<BudgetVO> list=budgetRepository.queryPage(budgetVO);
         for(BudgetVO vo:list){
             List<String> purchaseCodeList= budgetRepository.getPurchaseCode(vo.getId());
             StringBuilder s=new StringBuilder();
             for(String pcode:purchaseCodeList){
                 s.append("/");
                 s.append(pcode);
             }if(s.indexOf("/")==0){
                vo.setPurchaseCode(s.toString().substring(1));
             }
         }
        return list;
    }

    @Override
    public BudgetVO selectById(Long id) {
        BudgetVO vo =new BudgetVO();
        vo.setId(id);
        List<BudgetVO> list =budgetRepository.queryPage(vo);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public String exportExcel(List list) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        BudgetVO p=new BudgetVO();
        if(list!=null&&list.size()>0){
            p.setIds(list);
        }
        List<BudgetVO> querylist =queryPage(p);
        Map tilte =new HashMap();
        tilte.put("a","经费编码");
        tilte.put("b","经费名称");
        tilte.put("c","总金额数(万元)");
        tilte.put("d","剩余金额(万元)");
        tilte.put("e","订购号");
        tilte.put("f","创建者姓名");
        tilte.put("g","创建时间");
        tilte.put("h","是否启用");
        tilte.put("i","备注");
        Map<String,BudgetVO> order=new HashMap<String,BudgetVO>();
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(BudgetVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getCoding());
                m.put("b",vo.getName());
                m.put("c",vo.getTotal()/10000);
                m.put("d",vo.getRemain()/10000);
                m.put("e",vo.getPurchaseCode());
                m.put("f",vo.getCreatorName());
                m.put("g",vo.getCreatTime());
                switch(vo.getStatus()+""){
                    case "0":
                        m.put("h","否");
                        break;
                    case "1":
                        m.put("h","是");
                        break;
                    default:
                        m.put("h","否");
                        break;
                }
                m.put("i",vo.getRemark());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String getNextCode(String prefix) {
        if(prefix==null||"".equals(prefix)){
            prefix="Y00";
        }
        return budgetRepository.getNextCode(prefix);
    }
}