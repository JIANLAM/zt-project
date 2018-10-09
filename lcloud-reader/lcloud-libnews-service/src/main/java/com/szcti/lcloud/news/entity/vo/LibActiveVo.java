package com.szcti.lcloud.news.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import com.szcti.lcloud.news.entity.LibactiveImgEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Title: 最新资讯对应用户记录实体     （t_libNews）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Data
public class LibActiveVo extends QueryParam implements Serializable {
    private  Long id;
    private String title;
    private String content;
    private String createTime;
    private String startTime;
    private String closureTime;
    private String address;
    private Long createBy;
    private List<LibactiveImgEntity> libImgList;
    private List<Object> saveImgList;
}
