package com.szcti.lcloud.credit.service;

import com.szcti.lcloud.credit.entity.Credit;
import com.szcti.lcloud.credit.entity.vo.CreditVO;

import java.util.List;

public interface CreditService {
    int deleteByPrimaryKey(Long id);

    int insert(Credit record);

    Credit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Credit record);

    int updateByPrimaryKey(Credit record);

    List<CreditVO> queryPage(CreditVO readerCreditVO);

    void getCreditVO(CreditVO vo, Credit o);

    void getDataBean(Credit readerCredit, CreditVO readerCreditVO);

    int setIslendBuy(String[] array, String status);

    int setOwnValue(String[] array, String ownValue);
}
