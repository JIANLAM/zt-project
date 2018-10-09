package com.szcti.lcloud.recommbuy.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.recommbuy.entity.vo.RecommBuyVO;
import com.szcti.lcloud.recommbuy.repository.RecommBuyRespository;
import com.szcti.lcloud.recommbuy.service.RecommBuyService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("recommBuyService")
public class RecommBuyServiceImpl implements RecommBuyService {

    @Autowired
    private RecommBuyRespository recommendBuyRepository;
    @Override
    public List<RecommBuyVO> queryPage(RecommBuyVO recommBuyVO) {

        return recommendBuyRepository.queryPage(recommBuyVO);
    }

    @Override
    public void insert(RecommBuyVO recommBuyVO) {
        recommendBuyRepository.insert(recommBuyVO);
    }



    @Override
    public String exportExcel(RecommBuyVO v) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<RecommBuyVO> querylist=queryPage(v);
        Map tilte =new HashMap();
        tilte.put("a","图书名称");
        tilte.put("b","isbn");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版时间");
        tilte.put("g","单价");
        tilte.put("h","荐购次数");
        tilte.put("i","图书来源");
        //tilte.put("j","备注");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(RecommBuyVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getTitle());
                m.put("b",vo.getIsbn());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getBookType());
                m.put("e",vo.getPublisher());
                m.put("f",vo.getPubdate());
                m.put("g",new DecimalFormat("##0.00").format(vo.getPrice()));
                m.put("h",vo.getRecount());
                switch(vo.getSource()+""){
                    case "1":
                        m.put("i","新华集团");
                        break;
                    case "2":
                        m.put("i","本馆图书");
                        break;

                    case "3":
                        m.put("i","豆瓣网");
                        break;
                    case "4":
                        m.put("i","成员馆");
                        break;
                    default:
                        m.put("i","");
                        break;
                }
                //m.put("j",vo.getSummary());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }
    @Override
    @Transactional(readOnly = true)
    public List<RecommBuyVO> findList(@NonNull RecommBuyVO recommBuyVO){
        return recommendBuyRepository.findList(recommBuyVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(RecommBuyVO recommBuyVO){
        RecommBuyVO recomm = recommendBuyRepository.get(recommBuyVO.getPreBookId(),recommBuyVO.getReaderId());
        if(recomm==null){
            recommBuyVO.setCreateTime(DateUtils.getDateTime());
            recommendBuyRepository.insert(recommBuyVO);
            return true;
        }else{
            return false;
        }
    }

    /*********************************     微信端  荐购     ************************************/
    @Override
    @Transactional(readOnly = true)
    public List<RecommBuyVO> weChatFindList(@NonNull RecommBuyVO recommBuyVO){
        return recommendBuyRepository.weChatFindList(recommBuyVO);
    }
}
