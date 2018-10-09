package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.common.engine.RuleBaseService;
import com.szcti.lcloud.common.engine.RuleProcessBase;
import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
public class PurchaseRuleCheckService extends RuleProcessBase implements RuleBaseService {
    private PurchaseBookVO vo;
    private PurchaseRule pr;
    private Boolean b;
    private String msg;
    private Map m;
    private Map dupmsg;

    public Boolean checkDuplicateQty(Map m,int qt , int rqt,Map rem){
        int ch=0;int co=0;int li=0;
        if(m!=null&&m.get("checked")!=null){ch=(Integer)m.get("checked");}
        if(m!=null&&m.get("commit")!=null){co=(Integer)m.get("commit");}
        if(m!=null&&m.get("library")!=null){li=(Integer)m.get("library");}
        return this.duplicateQty(ch,co,li,qt,rqt,rem);
    }
    @Override
    public List ruleAction() {
        m=new HashMap();
        //Boolean b1=this.duplicateQty((Integer)dupmsg.get("checked"),(Integer)dupmsg.get("commit"),(Integer)dupmsg.get("library"), vo.getBookQty(), pr.getDuplicateQty(),m);
        Boolean b1=checkDuplicateQty(dupmsg, vo.getBookQty(), pr.getDuplicateQty(),m);
        Boolean b2=this.publisher(vo.getPublisher(),pr.getPublisher(),(int)pr.getPublisherAllow(),m);
        Boolean b3=this.publishYear(vo.getPubdate().substring(0,3),pr.getPublishYear()+"",(int)pr.getPublishYearAllow(),m);
        Boolean b4=this.categoryNo(vo.getBookType(),pr.getCategoryNo(),(int)pr.getCategoryNoAllow(),m);
        Boolean b5=this.bookPrice(vo.getPrice()+"",pr.getBookPrice()+"",1,m);
        Boolean b6=this.pages(vo.getPages()+"",pr.getBookPages()+"",(int)pr.getBookPagesAllow(),m);
        Boolean b7=this.bookSize(vo.getBookSize()+"",pr.getBookSize()+"",(int)pr.getBookSizeAllow(),m);
        b=b1&&b2&&b3&&b4&&b5&&b6&&b7;
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
         vo=new PurchaseBookVO();
         pr=new PurchaseRule();
         b=null;
         msg ="";
         m=new HashMap();
        dupmsg=new HashMap();
    }

    public void setDataBuyId(PurchaseBookVO purchaseBookVO, PurchaseRule purchaseRule, Map m) {
        clear();
        vo = purchaseBookVO;
        pr = purchaseRule;
        dupmsg= m;
    }
    public void setData(PurchaseBookVO v,PurchaseRule r){
        vo =v;
        pr =r;
    }
    public PurchaseBookVO getVo() {
        return vo;
    }

    public void setVo(PurchaseBookVO vo) {
        this.vo = vo;
    }

    public PurchaseRule getPr() {
        return pr;
    }

    public void setPr(PurchaseRule pr) {
        this.pr = pr;
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
