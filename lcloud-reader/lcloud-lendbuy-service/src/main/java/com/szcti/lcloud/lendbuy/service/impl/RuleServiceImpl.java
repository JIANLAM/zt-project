package com.szcti.lcloud.lendbuy.service.impl;
import com.szcti.lcloud.common.engine.RuleEngine;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;
import com.szcti.lcloud.lendbuy.repository.LendBuyDao;
import com.szcti.lcloud.lendbuy.repository.RuleDao;
import com.szcti.lcloud.lendbuy.service.RuleService;
import lombok.NonNull;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private LendBuyDao lendBuyDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(LendBuyRuleVO lendBuyRuleVO){
        if(lendBuyRuleVO!=null&&lendBuyRuleVO.getId()!=null){
            lendBuyRuleVO.setUpdateTime(DateUtils.getDateTime());
            ruleDao.update(lendBuyRuleVO);
            return true;
        }else{
            LendBuyRuleVO bean = ruleDao.getByType(lendBuyRuleVO.getLibraryId(),lendBuyRuleVO.getReaderType(),null);
            if(bean!=null){
                //同一种读者类型的规则只能有一个！
                return false;
            }else{
                lendBuyRuleVO.setId(IdGen.randomLong());
                lendBuyRuleVO.setCreateTime(DateUtils.getDateTime());
                ruleDao.insert(lendBuyRuleVO);
                return true;
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull String ids){
        String []collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        ruleDao.delete(idArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeStatus(Long id,Integer status){
        LendBuyRuleVO rule = new LendBuyRuleVO();
        rule.setId(id);
        rule.setStatus(status);
        ruleDao.update(rule);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean autoCheck(Long id, Map m,Long userId,Integer readerType,Long libId) {
        LendBuyBookVO vo = lendBuyDao.getBookById(id);
        LendBuyRuleVO rule = ruleDao.getByType(libId,readerType,1);

        Map dupmsg = new HashMap(16);
        if(vo!=null&&rule!=null){
            dupmsg = getBookQtyAndCredit(vo.getISBN(), rule,userId,libId);
        }
        RuleEngine e = new RuleEngine();
        RuleCheckService check = new RuleCheckService();
        //getDataBuyId(l,vo,pr,dupmsg);
        if(vo!=null&&rule!=null){
            check.setDataBuyId(vo,rule,dupmsg);
            e.setRuleService(check);
            e.run();
            m.put("map",e.getResultMap());
            return e.getResultBoolean();
        }
        m.put("res","无限制");
        return true;
    }

    /**
     * 获取副本数和信用值
     * @param isbn
     * @param rule
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Map getBookQtyAndCredit(String isbn,LendBuyRuleVO rule,Long userId,Long libId){
        Map m = new HashMap(16);
        m.put("hade",0);
        m.put("rule",rule.getDuplicateQty());
        List<LendBuyBookVO> list = lendBuyDao.findReadyBooks(isbn,libId);
        Integer credit = ruleDao.getCredit(userId);
        m.put("credit",credit);
        if(list!=null&&list.size()>0){
            m.put("hade",list.size());
        }
        return m;
    }

}
