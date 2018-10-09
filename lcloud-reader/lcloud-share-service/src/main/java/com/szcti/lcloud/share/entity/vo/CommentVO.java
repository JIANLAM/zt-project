package com.szcti.lcloud.share.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 评论VO   表t_share_comment
 * @Description: 阅读分享的评论数据
 * @author: fengda
 * @date: 2018/6/29 15:13
 */
@Data
public class CommentVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long shareId;

    private Long readerId;
    private String userName;
    private String nickName;
    private String icon;

    private String createTime;

    private String content;

}
