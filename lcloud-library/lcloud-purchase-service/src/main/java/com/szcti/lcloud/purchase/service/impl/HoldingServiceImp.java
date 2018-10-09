package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.utils.BeanUtil;
import com.szcti.lcloud.purchase.entity.Holding;
import com.szcti.lcloud.purchase.entity.vo.HoldingVO;
import com.szcti.lcloud.purchase.repository.HoldingRepository;
import com.szcti.lcloud.purchase.service.HoldingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("holdingService")
public class HoldingServiceImp implements HoldingService {
    @Autowired
    HoldingRepository holdingRepository;
    @Override
    public int deleteByPrimaryKey(Long id){
        return holdingRepository.deleteByPrimaryKey(id);
    }
    @Override
    public int insert(Holding record){

        return holdingRepository.insert(record);
    }
    @Override
    public Holding selectByPrimaryKey(Long id){

        return holdingRepository.selectByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Holding record){
        return holdingRepository.updateByPrimaryKeySelective(record);
    }
    @Override
    public int updateByPrimaryKey(Holding record){

        return holdingRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<HoldingVO> queryPage(HoldingVO holdingVO) {

        return holdingRepository.queryPage(holdingVO);
    }

    @Override
    public void getVO(HoldingVO vo, Holding o) {
        BeanUtil.transMap2Bean(BeanUtil.transBean2Map(o),vo);
    }

    @Override
    public void getEntity(Holding o, HoldingVO vo) {
        BeanUtil.transMap2Bean(BeanUtil.transBean2Map(vo),o);
    }
}
