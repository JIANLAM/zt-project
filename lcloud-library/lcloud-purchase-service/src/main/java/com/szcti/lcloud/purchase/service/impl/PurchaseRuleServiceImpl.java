package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.purchase.entity.BookSizeCriteria;
import com.szcti.lcloud.purchase.entity.PublisherCriteria;
import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.vo.PurchaseRuleVO;
import com.szcti.lcloud.purchase.repository.BookSizeRepository;
import com.szcti.lcloud.purchase.repository.PublisherRepository;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.szcti.lcloud.purchase.repository.PurchaseRuleRepository;
import com.szcti.lcloud.purchase.service.PurchaseRuleService;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author liujunliang
 * @Description 规则服务Service
 * @Date  2018/7/12
 **/

@Service("purchaseRuleService")
public class PurchaseRuleServiceImpl implements PurchaseRuleService {

    @Autowired
    private PurchaseRuleRepository purchaseRuleRepository;
    @Autowired
    private BookSizeRepository bookSizeRepository;

    @Autowired
    private PublisherRepository  publisherRepository;

    @Override
    public List<PurchaseRuleVO> queryPage(PurchaseRuleVO purchaseRuleVO) {

        return purchaseRuleRepository.queryPage(purchaseRuleVO);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteBatchIds(List<String> ids) {

        return purchaseRuleRepository.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int deleteByPrimaryKey(Long ruleId) {

        return purchaseRuleRepository.deleteByPrimaryKey(ruleId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int insert(PurchaseRule purchaseRule) {
        purchaseRule.setCreateTime(new Date());
        //purchaseRule.setId(IdGen.getDateUUId());
        return purchaseRuleRepository.insert(purchaseRule);
    }

    @Override
    public PurchaseRule selectByPrimaryKey(Long ruleId) {

        return purchaseRuleRepository.selectByPrimaryKey(ruleId);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKeySelective(PurchaseRule purchaseRule) {
        return purchaseRuleRepository.updateByPrimaryKeySelective(purchaseRule);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int updateByPrimaryKey(PurchaseRule purchaseRule) {
        return purchaseRuleRepository.updateByPrimaryKey(purchaseRule);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public int importExcel(List<PurchaseRuleVO> list) {

        return purchaseRuleRepository.importExcel(list);
    }

    @Override
    public List<PurchaseRuleVO> exportExcel(List<String> ids) {

        return purchaseRuleRepository.exportExcel(ids);
    }
    @Override
    public List<HashMap<String,Object>> queryMapList(Map params){
        return purchaseRuleRepository.queryMapList(params);
    }

    @Override
    public void getDataBean(PurchaseRule o, PurchaseRuleVO vo) {
        //o.setId(vo.getId());
        //o.setRuleName(vo.getRuleName());
        o.setChecker(vo.getChecker());
        if(vo.getStartTime()!=null&&!vo.getStartTime().equals(""))
        {
            o.setStartTime(DateUtils.parseDate(vo.getStartTime()));
        }
        if(vo.getEndTime()!=null&&!vo.getEndTime().equals(""))
        {
            o.setEndTime(DateUtils.parseDate(vo.getEndTime()));
        }
        o.setCreator(vo.getCreator());
        if(vo.getCreateTime()!=null&&!vo.getCreateTime().equals(""))
        {
            o.setCreateTime(DateUtils.parseDate(vo.getCreateTime()));
        }
        o.setBudgetId(vo.getBudgetId());
        o.setBudgetPrice(vo.getBudgetPrice());
        o.setStatus(NumberUtils.toInt(vo.getStatus()));//0启用，1不启用
        o.setDuplicateQty(vo.getDuplicateQty());
        o.setPublishYear(vo.getPublishYear());
        o.setCategoryNo(vo.getCategoryNo());
        o.setBookPrice(vo.getBookPrice());
        o.setBookSize(vo.getBookSize());
        o.setBookPages(vo.getPages());
        o.setPublisher(vo.getPublisher());
        o.setLibraryId(vo.getLibraryId());
        o.setPublishYearAllow(NumberUtils.toInt(vo.getPublishYearAllow()));
        o.setCategoryNoAllow(NumberUtils.toInt(vo.getCategoryNoAllow()));
        o.setBookSizeAllow(NumberUtils.toInt(vo.getBookSizeAllow()));
        o.setBookPagesAllow(NumberUtils.toInt(vo.getBookPagesAllow()));
        o.setPublisherAllow(NumberUtils.toInt(vo.getPublishYearAllow()));
        o.setPrefix(vo.getPrefix());
        o.setTotalnum(vo.getTotalnum());
        o.setStartnum(vo.getStartnum());
        o.setSummary(vo.getSummary());
    }
    @Override
    public void getPurchaseRuleVO(PurchaseRuleVO vo, PurchaseRule ro) {
        vo.setId(ro.getId());
        vo.setRuleName(ro.getRuleName());
        vo.setChecker(ro.getChecker());
        if(ro.getStartTime()!=null&&!ro.getStartTime().equals(""))
        {
            vo.setStartTime(DateUtils.formatDateTime(ro.getStartTime()));
        }
        if(ro.getEndTime()!=null&&!ro.getEndTime().equals(""))
        {
            vo.setEndTime(DateUtils.formatDateTime(ro.getEndTime()));
        }
        vo.setCreator(ro.getCreator());
        if(ro.getCreateTime()!=null&&!ro.getCreateTime().equals(""))
        {
            vo.setCreateTime(DateUtils.formatDateTime(ro.getCreateTime()));
        }
        vo.setBudgetId(ro.getBudgetId());
        vo.setBudgetPrice(ro.getBudgetPrice());
        vo.setStatus(ro.getStatus().toString());//0启用，1不启用
        vo.setDuplicateQty(ro.getDuplicateQty());
        vo.setPublishYear(ro.getPublishYear());
        vo.setCategoryNo(ro.getCategoryNo());
        vo.setBookPrice(ro.getBookPrice());
        vo.setBookSize(ro.getBookSize());
        vo.setPages(ro.getBookPages());
        vo.setPublisher(ro.getPublisher());
        vo.setLibraryId(ro.getLibraryId());
        vo.setPublishYearAllow(ro.getPublishYearAllow().toString());
        vo.setCategoryNoAllow(ro.getCategoryNoAllow().toString());
        vo.setBookSizeAllow(ro.getBookSizeAllow().toString());
        vo.setBookPagesAllow(ro.getBookPagesAllow().toString());
        vo.setPublisherAllow(ro.getPublishYearAllow().toString());
        vo.setPrefix(ro.getPrefix());
        vo.setTotalnum(ro.getTotalnum());
        vo.setStartnum(ro.getStartnum());
        vo.setSummary(ro.getSummary());
    }
    @Override
    public PurchaseRuleVO selectEdit(Long id) {
        PurchaseRule o = selectByPrimaryKey(id);
        PurchaseRuleVO vo=new PurchaseRuleVO();
        getPurchaseRuleVO(vo,o);
        vo.setBookSizeList(bookSizeRepository.selectByExample(new BookSizeCriteria()));
        vo.setPublisherList(publisherRepository.selectByExample(new PublisherCriteria()));
        return vo;
    }

    @Override
    public PurchaseRuleVO add() {
        PurchaseRuleVO vo=new PurchaseRuleVO();
        vo.setBookSizeList(bookSizeRepository.selectByExample(new BookSizeCriteria()));
        vo.setPublisherList(publisherRepository.selectByExample(new PublisherCriteria()));
        return vo;
    }
}
