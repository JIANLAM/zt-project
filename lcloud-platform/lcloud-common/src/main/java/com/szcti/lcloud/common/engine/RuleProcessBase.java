package com.szcti.lcloud.common.engine;

import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class RuleProcessBase {
    //例子:Boolen b=publishYear("publishYear","出版年份","2001","2000,1996~1998,2005",1,new HashMap());

    /**
     *
     * @param ruleKey code  ruleKey="publisher" / ruleKey="publishYear" /   ruleKey="categoryNo"  /  ruleKey="bookPrice"  /  ruleKey="pages"    /   ruleKey="bookSize"   /  ruleKey="bookName"
     * @param des     名称  des="出版社"        / des="出版年份"         /   des="图书分类号"       /  des="图书价格"       /  String des="图书页数"   /  des="图书开本"        /   des="书名"
     * @param b       要校验的书籍出版社        /  要校验的书籍出版年份   /    要校验的书籍图书分类   /  要校验的书籍图书单价  /  要校验的书籍图书页数    /   要校验的书籍图书开本  /   要校验的书籍图书书名
     * @param l       规则规定出版社            /  规则规定出版年份      /    规则规定图书分类       /  规则规定图书单价      /  规则规定图书页数        /   规则规定图书开本      /   规则规定图书书名
     * @param allow_fobid 书的出版社            /  书的出版年份                /分类号              /  单价                 /   页数                   /   开本                 /   书名1只允许，0不限制，-1只禁止
     * @param m 入参 是null
     * @return
     */
    public Boolean ruleCheckRadio(String ruleKey,String des,String b,String l,Integer allow_fobid,Map m){
        if(b==null||l==null||allow_fobid==null||StringUtils.isEmpty(l)||StringUtils.isEmpty(b)||StringUtils.isEmpty(allow_fobid+"")||allow_fobid==0){
            m.put(ruleKey,"true");//不限制
            return true;
        }
        else if(allow_fobid==1){
            if(l.contains(b)){
                m.put(ruleKey,"true");
                return true;
            }else{
                if(l.contains("~")){
                    String[] ar=l.split(",");
                    for(String c:ar){
                        if(c.contains("~")){
                            String[] y=l.split("~");
                            if(y[0].compareTo(b) < 0 && y[1].compareTo(b) > 0){
                                m.put(ruleKey,"true");
                                return true;
                            }
                        }
                    }
                }
                m.put(ruleKey,des+b+"不匹配，只限定于"+l);
                return  false;
            }
        }else if(allow_fobid==-1){
            if(l.contains(b)){
                m.put(ruleKey,des+b+"不匹配，禁止在"+l);
                return false;
            }else{
                if(l.contains("~")){
                    String[] ar=l.split(",");
                    for(String years:ar){
                        if(years.contains("~")){
                            String[] y=l.split("~");
                            if(y[0].compareTo(b) < 0 && y[1].compareTo(b) > 0){
                                m.put(ruleKey,des+b+"不匹配，禁止在"+l);
                                return false;
                            }
                        }
                    }
                }
                m.put(ruleKey,"true");
                return true;
            }
        }
        m.put(ruleKey,"ture");
        return true;
    }


    public Boolean duplicateQtyLeft(Integer b,Integer l,Map m){
        if(b==null||l==null){
            m.put("duplicateQty","true");//不限制
            return true;
        }
        if(!(l-b>0)){
            m.put("duplicateQty","剩余可选数量"+b+"不匹配复本数"+l);//不限制
        }
        return (l-b>0);
    }

    /**
     * 检查副本数
     * @param b
     * @param l
     * @param m
     * @return
     */
    public Boolean duplicateQty(Integer checked,Integer commit,Integer in,Integer b,Integer l,Map m){
        if(b==null||l==null){
            m.put("duplicateQty","true");//不限制
            return true;
        }
        if(!(l-(b+checked+commit+in)>0)){
            m.put("duplicateQty","已存在"+(b+checked+commit+in)+"选书数量"+b+"不匹配复本数"+l);//不限制
        }
        return (l-(b+checked+commit+in)>0);
    }

    /**
     * 检查副本数(用于借购的)
     * @param h 已经存在复本册数
     * @param l 规则规定复本书
     * @return
     */
    public Boolean duplicateQtyA(Integer h,Integer l,Map m){
        if(l==null){
            m.put("duplicateQtyA","true");//不限制
            return true;
        }
        if(!(l-(1+h)>0)){
            m.put("duplicateQtyA","已存在"+(1+h)+"选书数量"+1+"不匹配复本数"+l);//不限制
        }
        return (l-(1+h)>0);
    }

    /**
     * 检查用户信用值
     * @param h 读者当前信用分
     * @param l 规则规定信用分
     * @return
     */
    public Boolean credit(Integer h,Integer l,Map m){
        if(l==null){
            m.put("credit","true");//不限制
            return true;
        }
        if(!(h - l>=0)){
            m.put("credit","信用值不足");//不限制
        }
        return ((h - l)>=0);
    }


    /**
     * 检查出版年份
     * @param b 要校验的书籍出版年份
     * @param l 规则规定出版年份
     * @param allow_fobid 出版年份1只允许，0不限制，-1只禁止
     * @param m
     * @return
     */
    public Boolean publishYear(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="publishYear" ;
        String des="出版年份";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查书名
     * @param b 要校验的书籍图书书名
     * @param l 规则规定图书书名
     * @param allow_fobid   书名1只允许，0不限制，-1只禁止
     * @param m
     * @return
     */
    public Boolean bookName(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="bookName" ;
        String des="书名";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查分类号
     * @param b 要校验的书籍图书分类
     * @param l 规则规定图书分类
     * @param allow_fobid   分类号1只允许，0不限制，-1只禁止
     * @param m
     * @return
     */
    public Boolean categoryNo(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="categoryNo" ;
        String des="图书分类号";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查价格
     * @param b 要校验的书籍图书单价
     * @param l 规则规定图书单价
     * @param allow_fobid 单价1只允许，0不限制，-1只禁止   m = null
     * @param m
     * @return
     */
    public Boolean bookPrice(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="bookPrice" ;
        String des="图书价格";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查图书开本
     * @param b 要校验的书籍图书开本
     * @param l 规则规定图书开本
     * @param m 开本1只允许，0不限制，-1只禁止
     * @return
     */
    public Boolean bookSize(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="bookSize" ;
        String des="图书开本";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查图书页数
     * @param b 要校验的书籍图书页数
     * @param l 规则规定图书页数
     * @param allow_fobid   页数1只允许，0不限制，-1只禁止
     * @param m
     * @return
     */
    public Boolean pages(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="pages" ;
        String des="图书页数";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }

    /**
     * 检查出版社
     * @param b 要校验的书籍出版社
     * @param l 规则规定出版社
     * @param allow_fobid 书的出版社 1只允许，0不限制，-1只禁止
     * @param m
     * @return
     */
    public Boolean publisher(String b,String l,Integer allow_fobid,Map m){
        String ruleKey="publisher" ;
        String des="出版社";
        return ruleCheckRadio(ruleKey,des,b,l,allow_fobid,m);
    }
}
