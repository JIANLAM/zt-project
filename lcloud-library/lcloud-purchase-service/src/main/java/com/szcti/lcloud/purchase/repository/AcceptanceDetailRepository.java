package com.szcti.lcloud.purchase.repository;

import com.szcti.lcloud.purchase.entity.AcceptanceDetail;
import com.szcti.lcloud.purchase.entity.AcceptanceDetailCriteria;
import com.szcti.lcloud.purchase.entity.vo.AcceptanceDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AcceptanceDetailRepository {
    int deleteByPrimaryKey(Long id);

    int insert(AcceptanceDetail record);

    List<AcceptanceDetail> selectByExample(AcceptanceDetailCriteria example);

    AcceptanceDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKey(AcceptanceDetail record);

    List<AcceptanceDetailVO> queryPage(AcceptanceDetailVO vo);
    List<AcceptanceDetailVO> getNeverAcceptCode(AcceptanceDetailVO vo);

}