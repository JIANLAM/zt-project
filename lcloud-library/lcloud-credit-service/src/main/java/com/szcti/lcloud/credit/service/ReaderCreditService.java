package com.szcti.lcloud.credit.service;

import com.szcti.lcloud.credit.entity.ReaderCredit;
import com.szcti.lcloud.credit.entity.vo.ReaderCreditVO;

import java.util.List;

public interface ReaderCreditService {
    int deleteByPrimaryKey(Long id);

    int insert(ReaderCredit record);

    ReaderCredit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ReaderCredit record);

    int updateByPrimaryKey(ReaderCredit record);

    List<ReaderCreditVO> queryPage(ReaderCreditVO readerCreditVO);

    void getReaderCreditVO(ReaderCreditVO vo, ReaderCredit o);

    void getDataBean(ReaderCredit readerCredit, ReaderCreditVO readerCreditVO);
}
