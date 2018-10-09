package com.szcti.lcloud.newbook.resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.R;
import com.szcti.lcloud.newbook.entity.vo.NewBookVO;
import com.szcti.lcloud.newbook.service.NewBookService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.List;

@Component
@Path("newbook")
public class NewBookResource {
    @Autowired
    private NewBookService newBookService;

    /**
     * 本月新书列表
     * Ajax中的data: {"jsonStr": {"orderBydesc":"month","search":"new","pageNum":1,"pageSize":5,"searchKey":"title","searchValue":"","bookType":"","startTime":"","endTime":""}}
     */
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public R list(@QueryParam("jsonStr") String jsonStr) {
        try {
            NewBookVO newBookVO = new NewBookVO();
            if (StringUtils.isNotEmpty(jsonStr)) {
                newBookVO = (NewBookVO) JSONObject.toBean(JSONObject.fromObject(jsonStr), NewBookVO.class);
            }
            if(newBookVO.getOrderBydesc()!=null&&!"".equals(newBookVO.getOrderBydesc())){
                String year=DateUtils.getYear();
                String month=DateUtils.getMonth();
                String timeBegin=year+"-"+month+"-1";
                Date begin=DateUtils.parseDate(timeBegin);
                Date end =DateUtils.addMonths(begin,1);
                String timeEnd=DateUtils.formatDateTime(end);
                newBookVO.setStartTime(timeBegin);//入库：起时间
                newBookVO.setEndTime(timeEnd);//入库：止时间
            }
            PageHelper.startPage(newBookVO.getPageNum(), newBookVO.getPageSize());
            List<NewBookVO> newBookVOList = newBookService.queryPage(newBookVO);
            PageInfo p = new PageInfo(newBookVOList);
            return R.ok().put("page", p);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }
}