package com.szcti.lcloud.exchange.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.szcti.lcloud.exchange.utils.RedisUtil;
import com.zaxxer.hikari.HikariDataSource;

@RestController
@RequestMapping("xinhua")
public class XinHuaBookSyncController
{
    
    private static final Logger log            = LoggerFactory.getLogger(XinHuaBookSyncController.class);
    
    @Value("${config.xh_img_baseurl}")
    private String              xh_img_baseurl = "";
    
    @Value("${config.xh_mysql_table}")
    private String              xh_mysql_table = "";
    
    @Value("${config.xh_mysql_url}")
    private String              xh_mysql_url   = "";
    
    @Value("${config.xh_user}")
    private String              xh_user        = "";
    
    @Value("${config.xh_passwd}")
    private String              xh_passwd      = "";
    
    @Autowired
    private RedisUtil           redisUtil;
    
    @Scheduled(fixedRate = 2 * 60 * 1000)
    @GetMapping("/syncBook")
    public String syncBook()
    {
        
        int xh_currPage = 1;
        
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(xh_mysql_url);
        ds.setUsername(xh_user);
        ds.setPassword(xh_passwd);
        
        for (int i = 0; i < 10; i++)
        {
            
            xh_currPage = NumberUtils.toInt(redisUtil.strings().get("xh_currPage"), 0);
            
            try
            {
                int currpage = xh_currPage * 1000;
                
                Statement st = ds.getConnection().createStatement();
                String sql = "select SAPCODE,title,pic,author,`describe`,publisher,pubdate,book_type,"
                        + "isbn,price,pages,key_word,cip,nrty,yddx,DATE_FORMAT(createtime,'%Y-%m-%d') createtime,DATE_FORMAT(modifytime,'%Y-%m-%d') modifytime,zflag from " + xh_mysql_table
                        + " order by createtime limit " + currpage + ",1000";
                
                ResultSet rs = st.executeQuery(sql);
                int fg = 0;
                while (rs.next())
                {
                    
                    fg++;
                    
                    String sapcode = rs.getString("SAPCODE");//SAP码
                    String title = rs.getString("title");//标题
                    String pic = rs.getString("pic");//图片
                    String author = rs.getString("author");//作者
                    String describe = rs.getString("describe");//描述
                    String publisher = rs.getString("publisher");//出版商
                    String pubdate = rs.getString("pubdate");//出版日期
                    String book_type = rs.getString("book_type");
                    String isbn = rs.getString("isbn");
                    String price = rs.getString("price");
                    String pages = rs.getString("pages");//书价格
                    String key_word = rs.getString("key_word");//关键词
                    String kb = rs.getString("key_word");//开本
                    String cip = rs.getString("cip");//中图法分类号
                    String nrty = rs.getString("nrty");//内容提要
                    String yddx = rs.getString("yddx");//阅读对象
                    String createtime = rs.getString("createtime");//创建时间
                    String modifytime = rs.getString("modifytime");//修改时间
                    String zflag = rs.getString("zflag");// 标识
                    
                    String picLocal = "";
                    if ( StringUtils.hasText(pic) )
                    {
                        String p1 = pic.replace(".jpg", "_B.jpg").replace(".JPG", "_B.JPG").replace(".png", "_B.png")
                                .replace(".PNG", "_B.PNG");
                        String p2 = p1.replace("\\", "/");
                        picLocal = xh_img_baseurl + p2;
                    }
                    JSONObject obj = new JSONObject();
                    obj.put("sapcode", sapcode);
                    obj.put("title", title);
                    obj.put("pic", pic);
                    obj.put("picLocal", picLocal);
                    obj.put("author", author);
                    obj.put("describe", describe);
                    obj.put("publisher", publisher);
                    obj.put("pubdate", pubdate);
                    obj.put("bookType", book_type);
                    obj.put("isbn", isbn);
                    obj.put("price", price);
                    obj.put("pages", pages);
                    obj.put("keyWord", key_word);
                    obj.put("kb", kb);
                    obj.put("cip", cip);
                    obj.put("nrty", nrty);
                    obj.put("yddx", yddx);
                    obj.put("createtime", createtime);
                    obj.put("modifytime", modifytime);
                    obj.put("zflag", zflag);
                    
                    String s = redisUtil.hash().hget("xinhuabook", sapcode);
                    if ( StringUtils.hasText(s) )
                    {
                        log.error("重复的 SAPCODE： " + sapcode);
                    }
                    else
                    {
                        
                        redisUtil.hash().hset("xinhuabook", sapcode, obj.toJSONString());
                    }
                    
                    log.info("from xinhua get SAPCODE:" + sapcode);
                }
                
                rs.close();
                
                if ( fg < 1000 )
                {
                    break;
                }
                
                xh_currPage++;
                
                redisUtil.strings().set("xh_currPage", xh_currPage + "");
                
                try
                {
                    Thread.sleep(2 * 1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            
        }
        ds.close();
        
        return "OK";
    }
}
