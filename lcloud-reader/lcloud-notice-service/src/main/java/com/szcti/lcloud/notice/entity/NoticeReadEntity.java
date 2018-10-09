package com.szcti.lcloud.notice.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 通知对应用户记录实体  （t_notice_read）
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class NoticeReadEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long noticeInfoId;

    private Long userId;

    private Integer readStatus;

    private String readTime;

}
