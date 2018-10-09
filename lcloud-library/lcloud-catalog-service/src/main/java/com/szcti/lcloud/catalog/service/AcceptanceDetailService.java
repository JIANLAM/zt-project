package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.AcceptanceDetail;
import com.szcti.lcloud.catalog.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.catalog.entity.vo.AcceptanceDetailVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcceptanceDetailService {


    int deleteByPrimaryKey(Long id);

    int insert(AcceptanceDetail record);

    List<AcceptanceDetail> selectByExample(AcceptanceDetailCriteria example);

    AcceptanceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(AcceptanceDetail record);

    List<AcceptanceDetailVO> queryPage(AcceptanceDetailVO vo);
}