package com.szcti.lcloud.top.config;

import com.szcti.lcloud.top.entity.vo.TopBookVO;
import com.szcti.lcloud.top.service.TopService;
import com.szcti.lcloud.top.service.impl.TopServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class QuartzTask {
    @Autowired
    TopService  topService;
    @Scheduled(fixedRate = 24*3600*1000)
    public void getTop(){
       //topLendBook();//图书借阅排名
        /* topRecommBook();//图书推荐排名
        topRecommBuyBook();//图书荐购排名
        topLendReader();//读者借阅排名
        topRecommBuyReader();//读者荐购排名
        topLendBuyBook();//图书借购排行
        topLendBuyReader();//读者借购排行*/
        //System.out.println("getTop:" + new Random().nextLong() + new SimpleDateFormat("HH:mm:ss").format(new Date()));
    }
    public void topLendBook(){
        List<TopBookVO> list= topService.topLendBook(new TopBookVO());
    }
    public void topRecommBook(){
    }
    public void topRecommBuyBook(){
    }
    public void topLendReader(){
    }
    public void topLendBuyBook(){
    }
    public void topLendBuyReader(){
    }
    public void topRecommBuyReader(){
    }



}
