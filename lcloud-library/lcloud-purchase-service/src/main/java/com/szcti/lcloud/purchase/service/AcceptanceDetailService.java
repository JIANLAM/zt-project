package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.AcceptanceDetail;
import com.szcti.lcloud.purchase.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.purchase.entity.vo.AcceptanceDetailVO;

import java.util.List;

public interface AcceptanceDetailService {


    int deleteByPrimaryKey(Long id);

    int insert(AcceptanceDetail record);

    List<AcceptanceDetail> selectByExample(AcceptanceDetailCriteria example);

    AcceptanceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(AcceptanceDetail record);

    List<AcceptanceDetailVO> queryPage(AcceptanceDetailVO vo);
}