package com.szcti.lcloud.notice.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: 消息通知及接收人VO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 15:13
 */
@Data
public class NoticeVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long noticeReadId;

    private Long userId;
    private String userName;

    private String title;

    private String content;

    private Integer type;

    private Integer status;

    private String remark;

    private Long createBy;

    private String createTime;

    private Integer readStatus;

    private String readTime;

    private String createName;

    private  String orgName;

    private  String sendTime;

    private List<Object> userIds;
}
