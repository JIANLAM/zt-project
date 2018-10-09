package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.purchase.entity.Acceptance;
import com.szcti.lcloud.purchase.entity.AcceptanceCriteria;
import com.szcti.lcloud.purchase.entity.AcceptanceDetail;
import com.szcti.lcloud.purchase.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.purchase.repository.AcceptanceDetailRepository;
import com.szcti.lcloud.purchase.repository.AcceptanceRepository;
import com.szcti.lcloud.purchase.service.AcceptanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("acceptanceService")
public class AcceptanceServiceImp implements AcceptanceService {
    @Autowired
    AcceptanceRepository acceptanceRepository;
    @Autowired
    AcceptanceDetailRepository acceptanceDetailRepository;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return acceptanceRepository.deleteByPrimaryKey( id);
    }
    @Override
    public int insert(Acceptance record) {
        return acceptanceRepository.insert( record);
    }
    @Override
    public List<Acceptance> selectByExample(AcceptanceCriteria example) {
        return acceptanceRepository.selectByExample( example);
    }
    @Override
    public Acceptance selectByPrimaryKey(Long id) {
        return acceptanceRepository.selectByPrimaryKey( id);
    }
    @Override
    public int updateByPrimaryKey(Acceptance record) {
        return acceptanceRepository.updateByPrimaryKey( record);
    }
    //刷新每个验收单的编目状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void refleshStatus(String acceptCode) {
        if(acceptCode!=null&&!acceptCode.equals("")){
            AcceptanceCriteria ac= new AcceptanceCriteria();
            ac.createCriteria().andAcceptCodeEqualTo(acceptCode);
            List<Acceptance> alist=acceptanceRepository.selectByExample(ac);
            if(alist!=null&&alist.size()>0){
                Acceptance a =alist.get(0);
                AcceptanceDetailCriteria ad=new AcceptanceDetailCriteria();
                AcceptanceDetailCriteria.Criteria adc=ad.createCriteria();
                adc.andAcceptanceIdEqualTo(a.getId());
                List<AcceptanceDetail> adlist=acceptanceDetailRepository.selectByExample(ad);
                HashMap<Long,String> map=new HashMap<Long,String>();
                for(AcceptanceDetail aDetail:adlist){
                    if(aDetail.getcatalogQty()>0&&(aDetail.getAcceptQuantity()>aDetail.getcatalogQty())){
                        map.put(aDetail.getId(),"2");
                        return;
                    }if(aDetail.getAcceptQuantity()==aDetail.getcatalogQty()){
                        map.put(aDetail.getId(),"3");
                    }
                }
                if(map.containsValue("2")){
                    a.setStatus("2");//部分验收
                    acceptanceRepository.updateByPrimaryKey(a);
                }else if(!map.containsValue("2")&&map.containsValue("3")){
                    a.setStatus("3");//部分验收
                    acceptanceRepository.updateByPrimaryKey(a);
                }
            }
        }
    }
}