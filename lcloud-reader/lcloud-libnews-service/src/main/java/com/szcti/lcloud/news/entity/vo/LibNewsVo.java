package com.szcti.lcloud.news.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 最新资讯对应用户记录实体     （t_libNews）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Data
public class LibNewsVo extends QueryParam implements Serializable {
    private  Long id;
    private String title;
    private String content;
    private String createTime;
    private String libNewsImg;
    private Long createBy;
    private String orgName;
    private Long lookCount;
    private String startTime;
    private String closureTime;
}
