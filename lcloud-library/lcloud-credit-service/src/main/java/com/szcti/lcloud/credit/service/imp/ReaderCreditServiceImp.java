package com.szcti.lcloud.credit.service.imp;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.credit.entity.vo.ReaderCreditVO;
import com.szcti.lcloud.credit.repository.ReaderCreditRepository;
import com.szcti.lcloud.credit.entity.ReaderCredit;
import com.szcti.lcloud.credit.service.ReaderCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("readerCreditService")
public class ReaderCreditServiceImp implements ReaderCreditService {
    @Autowired
    ReaderCreditRepository readerCreditRepository;
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteByPrimaryKey(Long id){
        return readerCreditRepository.deleteByPrimaryKey(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(ReaderCredit record){

        return readerCreditRepository.insert(record);
    }
    @Override
    public ReaderCredit selectByPrimaryKey(Long id){

        return readerCreditRepository.selectByPrimaryKey(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(ReaderCredit record){
        return readerCreditRepository.updateByPrimaryKeySelective(record);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKey(ReaderCredit record){

        return readerCreditRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<ReaderCreditVO> queryPage(ReaderCreditVO readerCreditVO) {

        return readerCreditRepository.queryPage(readerCreditVO);
    }

    @Override
    public void getReaderCreditVO(ReaderCreditVO vo, ReaderCredit o) {
        vo.setId(o.getId());
        vo.setStatus(o.getStatus());
        vo.setAfterDeductCreditValue(o.getAfterDeductCreditValue());
        vo.setBeforeDeductCreditValue(o.getBeforeDeductCreditValue());
        vo.setDeductScoreReason(o.getDeductScoreReason());
        vo.setDeductScore(o.getDeductScore());
        vo.setDeductScoreTime(DateUtils.formatDateTime(o.getDeductScoreTime()));
        vo.setReaderId(o.getReaderId());
        vo.setRemark(o.getRemark());
    }

    @Override
    public void getDataBean(ReaderCredit o, ReaderCreditVO vo) {
        o.setId(vo.getId());
        o.setStatus(vo.getStatus());
        o.setAfterDeductCreditValue(vo.getAfterDeductCreditValue());
        o.setBeforeDeductCreditValue(vo.getBeforeDeductCreditValue());
        o.setDeductScoreReason(vo.getDeductScoreReason());
        o.setDeductScore(vo.getDeductScore());
        o.setDeductScoreTime(DateUtils.parseDate(vo.getDeductScoreTime()));
        o.setReaderId(vo.getReaderId());
        o.setRemark(vo.getRemark());
    }
}
