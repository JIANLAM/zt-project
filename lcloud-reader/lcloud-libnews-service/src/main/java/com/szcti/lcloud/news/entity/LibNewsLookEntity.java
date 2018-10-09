package com.szcti.lcloud.news.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 最新资讯对应用户记录实体     （t_libNews）
 * @Description: 描述
 * @author: wangsiyi
 * @date: 2018/7/16 1:41
 */
@Data
public class LibNewsLookEntity implements Serializable {
    private  Long libNewsId;
    private Long readerId;
}
