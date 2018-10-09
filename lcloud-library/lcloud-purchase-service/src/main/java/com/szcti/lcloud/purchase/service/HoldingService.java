package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.Holding;
import com.szcti.lcloud.purchase.entity.vo.HoldingVO;

import java.util.List;

public interface HoldingService {
    int deleteByPrimaryKey(Long id);

    int insert(Holding record);

    Holding selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Holding record);

    int updateByPrimaryKey(Holding record);

    List<HoldingVO> queryPage(HoldingVO holdingVO);

    void getVO(HoldingVO vo, Holding o);

    void getEntity(Holding holding, HoldingVO holdingVO);
}
