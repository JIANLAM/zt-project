package com.szcti.lcloud.credit.service.imp;

import com.szcti.lcloud.credit.entity.Credit;
import com.szcti.lcloud.credit.entity.vo.CreditVO;
import com.szcti.lcloud.credit.repository.CreditRepository;
import com.szcti.lcloud.credit.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("creditService")
public class CreditServiceImp implements CreditService {
    @Autowired
    CreditRepository creditRepository;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteByPrimaryKey(Long id){
        return creditRepository.deleteByPrimaryKey(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(Credit record){
        return creditRepository.insert(record);
    }
    @Override
    public Credit selectByPrimaryKey(Long id){
        return creditRepository.selectByPrimaryKey(id);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(Credit record){
        return creditRepository.updateByPrimaryKeySelective(record);
    }
    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKey(Credit record){
        return creditRepository.updateByPrimaryKey(record);
    }

    @Override
    public List<CreditVO> queryPage(CreditVO creditVO) {
        return creditRepository.queryPage(creditVO);
    }

    @Override
    public void getCreditVO(CreditVO vo, Credit o) {
        vo.setId(o.getId());
        vo.setDefaultValue(o.getDefaultValue());
        vo.setOwnValue(o.getOwnValue());
        vo.setStatus(o.getStatus());
        vo.setUserId(o.getUserId());
    }

    @Override
    public void getDataBean(Credit credit, CreditVO creditVO) {
        credit.setId(creditVO.getId());
        credit.setDefaultValue(creditVO.getDefaultValue());
        credit.setOwnValue(creditVO.getOwnValue());
        credit.setStatus(creditVO.getStatus());
        credit.setUserId(creditVO.getUserId());
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int setIslendBuy(String[] array, String status) {
        int count = 0;
        if (array != null && array.length > 0 && status != null && !status.equals("")) {
            for (String id : array) {
                Long idl = Long.parseLong(id);
                if (idl > 0) {
                    Credit p = creditRepository.selectByPrimaryKey(idl);
                    p.setIslendBuy(Integer.parseInt(status));
                    int c = creditRepository.updateByPrimaryKey(p);
                    if (c > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    @Override
    public int setOwnValue(String[] array, String ownValue) {
        int count = 0;
        if (array != null && array.length > 0 && ownValue != null && !ownValue.equals("")) {
            for (String id : array) {
                Long idl = Long.parseLong(id);
                if (idl > 0) {
                    Credit p = creditRepository.selectByPrimaryKey(idl);
                    int defalut=p.getDefaultValue();
                    int own=p.getOwnValue();
                    p.setDefaultValue(defalut+(Integer.parseInt(ownValue)-own));//默认值累加
                    p.setOwnValue(Integer.parseInt(ownValue));
                    int c = creditRepository.updateByPrimaryKey(p);
                    if (c > 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
