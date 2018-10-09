package com.szcti.lcloud.share.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 阅读分享VO  t_share,t_share_comment,t_share_agree
 * @Description: 阅读分享数据，包含点赞数和评论数
 * @author: fengda
 * @date: 2018/6/29 15:13
 */
@Data
public class ShareVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long readerId;
    private String userName;
    private String nickName;
    private String icon;

    private String createTime;

    private String pic;

    private String content;

    private Integer reportCount;

    private Integer commentCount;

    private Integer agreeCount;

    private Integer isAgree;


}
