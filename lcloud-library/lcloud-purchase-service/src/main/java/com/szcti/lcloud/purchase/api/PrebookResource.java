package com.szcti.lcloud.purchase.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.JwtUtil;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.purchase.config.JwtYmlConfig;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;
import com.szcti.lcloud.purchase.entity.vo.PurchaseRuleVO;
import com.szcti.lcloud.purchase.service.BookService;
import com.szcti.lcloud.purchase.service.PrebookService;
import com.szcti.lcloud.purchase.service.PurchaseRuleService;

import net.sf.json.JSONObject;

/**
 * 
 * @描述: 征购订购服务类 
 * @版权: Copyright (c) 2018 
 * @公司: 深圳市中图信息技术有限公司
 * @作者: tianbw
 * @版本: 1.0 
 * @创建日期: 2018年9月3日 
 * @创建时间: 下午2:49:51
 */
@Component
@Path("prebook")
public class PrebookResource
{
    
    private Logger              logger     = Logger.getLogger(PrebookResource.class);
    
    private String              errorKey   = "msg";
    
    private String              errorValue = "无权操作！请联系管理员。";
    
    @Autowired
    private PrebookService      prebookService;
    
    @Autowired
    private BookService         bookService;
    
    @Autowired
    private JwtYmlConfig        jwtYmlConfig;
    
    @Autowired
    private PurchaseRuleService purchaseRuleService;
    
    /**
     * 列表 data为prebookVO的json格式
     * Ajax中的data: {"jsonStr":JSON.stringify({ "purchaseCode": "D0001","prebookNum": "2018051025",
     *      * "expressCode":"AD58652122","creatorName":"西湾管理"，"bookName":"西游记1"，"author":"LLk"
     *      * "isbn":"MN34554454","出版者":"Mr.Li"})}
     */
    /*@Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr,@HeaderParam("Authorization") String authToken){
        try {
            Long userid=JwtUtil.getUserIdByToken(authToken,jwtYmlConfig.getSecret());
            Long libraryId=JwtUtil.getLibraryIdByToken(authToken,jwtYmlConfig.getSecret());
            PrebookVO prebookVO = new PrebookVO();
            if(StringUtils.isNotEmpty(jsonStr)){
                prebookVO =(PrebookVO)JSONObject.toBean(JSONObject.fromObject(jsonStr), PrebookVO.class);
            }prebookVO.setLibraryId(libraryId);
            PageHelper.startPage(prebookVO.getPageNum(),prebookVO.getPageSize());
            List<PrebookVO> prebookVOList = prebookService.queryPage(prebookVO);
            PageInfo p = new PageInfo(prebookVOList);
            return R.ok().put("page", p);
        }catch (Exception e){
            e.printStackTrace();
            return R.error();
        }
    
    }*/
    
    /**
     * 
     * @描述：征购订购流程列表查询
     * @作者：tianbw
     * @时间：2018年9月3日 下午3:13:40
     * @param jsonStr
     * @param authToken
     * @return
     */
    @Path("/querylist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr, @HeaderParam("Authorization") String authToken)
    {
        try
        {
            /**
             *   校验用户权限
             */
            if ( !checkAuthorization(authToken) )
            {
                logger.error(errorValue);
                return R.error().put(errorKey, errorValue);
            }
            
            PrebookVO prebookVO = new PrebookVO();
            PageInfo p = null;
            if ( StringUtils.isNotEmpty(jsonStr) )
            {
                prebookVO = (PrebookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), PrebookVO.class);
                
                if ( prebookVO.getOrderId() == null || prebookVO.getOrderId() <= 0 )
                {
                    return R.error( -1, "orderId 订购号不能为空！");
                }
                
                if ( prebookVO.getRuleId() == null || prebookVO.getRuleId() <= 0 )
                {
                    return R.error( -1, "RuleId 订购规则id不能为空！");
                }
                
                if ( prebookVO.getSource() == null || prebookVO.getSource() <= 0 )
                {
                    return R.error( -1, "Source 数据源不能为空！");
                }
                
                /**
                 * 检索字段值为:isbn|id|title|author|book_type
                 */
                if ( "isbn".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setIsbn(prebookVO.getSearchValue());
                }
                if ( "id".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setAuthor(prebookVO.getSearchValue());
                }
                if ( "title".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setTitle(prebookVO.getSearchValue());
                }
                if ( "author".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setAuthor(prebookVO.getSearchValue());
                }
                if ( "bookType".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setBookType(prebookVO.getSearchValue());
                }
                if ( "publisher".equals(prebookVO.getSearchKey()) )
                {
                    prebookVO.setPublisher(prebookVO.getSearchValue());
                }
                
            }
            
			
            /**
             * 中央库查询
             */
            if ( prebookVO.getSource() == 0 )
            {
                List<PrebookVO> bookList = bookService.getPageBook(prebookVO);
                p = new PageInfo(bookList);
            }
            /**
             * 采访库查询
             */
            if ( prebookVO.getSource() == 1 )
            {
                List<PrebookVO> bookList = bookService.queryPagePreBook(prebookVO);
                p = new PageInfo(bookList);
            }
            /**
             * 新华库查询
             */
            if ( prebookVO.getSource() == 2 )
            {
                List<PrebookVO> bookList = bookService.queryPageBookXH(prebookVO);
                long ruleId = prebookVO.getRuleId();
                if ( ruleId != 0 )
                {
                    cheackRules(prebookVO, bookList);
                }
                p = new PageInfo(bookList);
            }
            
            return R.ok().put("page", p);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        
        return R.error();
    }
    
    public void cheackRules(PrebookVO prebookVO, List<PrebookVO> bookList)
    {
        //查询订购规则
        PurchaseRuleVO purchaseRuleVO = new PurchaseRuleVO();
        purchaseRuleVO.setId(prebookVO.getRuleId());
        PageHelper.startPage(purchaseRuleVO.getPageNum(), purchaseRuleVO.getPageSize());
        List<PurchaseRuleVO> purchaseRuleVOList = purchaseRuleService.queryPage(purchaseRuleVO);
        
        int duplicateqty = purchaseRuleVOList.get(0).getDuplicateQty();//复本书
        
        String publishyear = purchaseRuleVOList.get(0).getPublishYear();//出版年份
        
        String publishyearallow = purchaseRuleVOList.get(0).getPublishYearAllow();//出版年份1只允许，0不限制，-1只禁止
        
        String categoryno = purchaseRuleVOList.get(0).getCategoryNo();//分类号
        
        String categorynoallow = purchaseRuleVOList.get(0).getCategoryNoAllow();//分类号1只允许，0不限制，-1只禁止
        
        String bookprice = purchaseRuleVOList.get(0).getBookPrice();//图书单价
        
        String booksize = purchaseRuleVOList.get(0).getBookSize();//书的开本
        
        String booksizeallow = purchaseRuleVOList.get(0).getBookSizeAllow();//书的开本数1只允许，0不限制，-1只禁止
        
        String pages = purchaseRuleVOList.get(0).getPages();//书的页数
        
        String bookpagesallow = purchaseRuleVOList.get(0).getBookPagesAllow();//书的页数1只允许，0不限制，-1只禁止
        
        String publisher = purchaseRuleVOList.get(0).getPublisher();//出版社
        
        String publisherallow = purchaseRuleVOList.get(0).getPublisherAllow();//出版社1只允许，0不限制，-1只禁止
        
        for (int i = 0; i < bookList.size(); i++)
        {
            boolean a = false;
            //出版年份只允许情况处理
            if ( publishyearallow.equals("1") )
            {
                if ( publishyear.equals(bookList.get(i).getPubdate().substring(0, 4)) )
                {
                    a = true;
                }
            }
            
            //分类号只允许情况处理
            if ( categorynoallow.equals("1") )
            {
                if ( categoryno.equals("" + bookList.get(i).getBookType()) )
                {
                    a = true;
                }
            }
            
            //图书开本书只允许情况处理
            //            if ( booksizeallow.equals("1") )
            //            {
            //                if ( booksize.equals("" + bookList.get(i).getBookType()) )
            //                {
            //                    return true;
            //                }
            //            }
            
            //书的页数只允许处理
            if ( bookpagesallow.equals("1") )
            {
                if ( pages.equals("" + bookList.get(i).getPages()) )
                {
                    a = true;
                }
            }
            //出版社只允许情况处理
            if ( publisherallow.equals("-1") )
            {
                if ( publisher.equals("" + bookList.get(i).getPublisher()) )
                {
                    a = true;
                }
            }
            
            /*****************************      只禁止情况处理开始              **********************/
            //出版年份只禁止情况处理
            if ( publishyearallow.equals("-1") )
            {
                if ( !publishyear.equals(bookList.get(i).getPublisher()) )
                {
                    a = true;
                }
            }
            
            //分类号只禁止情况处理
            if ( categorynoallow.equals("-1") )
            {
                if ( !categoryno.equals("" + bookList.get(i).getBookType()) )
                {
                    a = true;
                }
            }
            
            //图书开本书只允许情况处理
            //            if ( booksizeallow.equals("1") )
            //            {
            //                if ( booksize.equals("" + bookList.get(i).getBookType()) )
            //                {
            //                    return true;
            //                }
            //            }
            
            //书的页数只禁止处理
            if ( bookpagesallow.equals("-1") )
            {
                if ( !pages.equals("" + bookList.get(i).getPages()) )
                {
                    a = true;
                }
            }
            
            //出版社只禁止情况处理
            if ( !publisherallow.equals("-1") )
            {
                if ( publisher.equals("" + bookList.get(i).getPublisher()) )
                {
                    a = true;
                }
            }
            
            /*****************************      只禁止情况处理结束              **********************/
            
            //书的单价是否
            if ( bookprice != null && bookprice != "" && bookprice.equals("" + bookList.get(i).getPrice()) )
            {
                a = true;
            }
            if ( a )
            {
                bookList.get(i).setFlag(0);//0表示可订购 1表示不可订购    
            }
            else
            {
                bookList.get(i).setFlag(1);//0表示可订购 1表示不可订购
            }
            
        }
    }
    
    /**
     * 
     * @描述：通用校验权限方法
     * @作者：tianbw
     * @时间：2018年8月25日 上午9:03:48
     * @param authToken
     * @return
     */
    public Boolean checkAuthorization(String authToken)
    {
        
        Long libraryId = JwtUtil.getLibraryIdByToken(authToken, jwtYmlConfig.getSecret());
        if ( libraryId == null || !(libraryId > 0) )
        {
            return false;
        }
        return true;
        
    }
}
