package com.szcti.lcloud.lendbuy.service.impl;

import com.szcti.lcloud.common.engine.RuleBaseService;
import com.szcti.lcloud.common.engine.RuleProcessBase;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RuleCheckService extends RuleProcessBase implements RuleBaseService {
    private LendBuyBookVO vo;
    private LendBuyRuleVO rule;
    private Boolean b;
    private String msg;
    private Map m;
    private Map dupmsg;

    @Override
    public List ruleAction() {
        m=new HashMap(16);
        Boolean b1=this.duplicateQtyA((Integer)dupmsg.get("hade"), rule.getDuplicateQty(),m);
        Boolean b2=this.publisher(vo.getPublisher(),rule.getPublisher(),rule.getPublisherAllow(),m);
        Boolean b3=this.publishYear(vo.getPubDate().substring(0,3),rule.getPublishYear()+"",rule.getPublishYearAllow(),m);
        Boolean b4=this.categoryNo(vo.getBookType(),rule.getCategoryNo(),rule.getCategoryNoAllow(),m);
        Boolean b5=this.bookPrice(vo.getPrice()+"",rule.getBookPrice()+"",1,m);
        Boolean b6=this.bookPages(vo.getBookPages()+"",rule.getBookPages()+"",rule.getBookPagesAllow(),m);
        Boolean b7=this.bookSize(vo.getBookSize()+"",rule.getBookSize()+"",rule.getBookSizeAllow(),m);
        Boolean b8=this.bookName(vo.getTitle(),rule.getBookName(),rule.getBookNameAllow(),m);
        Boolean b9=this.credit((Integer)dupmsg.get("credit"), rule.getCredit(),m);
        b=b1&&b2&&b3&&b4&&b5&&b6&&b7&&b8&&b9;
        msg =JSONObject.fromObject(m).toString();
        return null;
    }

    @Override
    public Boolean getResultBoolean() {
        return b;
    }

    @Override
    public String getResultMsg() {
        return msg;
    }

    @Override
    public Map getResultMap() {
        return m;
    }

    @Override
    public void clear() {
         vo=new LendBuyBookVO();
         rule=new LendBuyRuleVO();
         b=null;
         msg ="";
         m=new HashMap();
        dupmsg=new HashMap();
    }

    public void setDataBuyId(LendBuyBookVO lendBuyBookVO, LendBuyRuleVO lendBuyRuleVO, Map m) {
        clear();
        vo = lendBuyBookVO;
        rule = lendBuyRuleVO;
        dupmsg= m;
    }
    public void setData(LendBuyBookVO v,LendBuyRuleVO r){
        vo =v;
        rule =r;
    }
    public LendBuyBookVO getVo() {
        return vo;
    }

    public void setVo(LendBuyBookVO vo) {
        this.vo = vo;
    }

    public LendBuyRuleVO getPr() {
        return rule;
    }

    public void setPr(LendBuyRuleVO rule) {
        this.rule = rule;
    }

    public Boolean getB() {
        return b;
    }

    public void setB(Boolean b) {
        this.b = b;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map getM() {
        return m;
    }

    public void setM(Map m) {
        this.m = m;
    }

    public Map getDupmsg() {
        return dupmsg;
    }

    public void setDupmsg(Map dupmsg) {
        this.dupmsg = dupmsg;
    }
}
