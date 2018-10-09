package com.szcti.lcloud.share.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 举报内容VO   表t_share_report
 * @Description: 阅读分享的举报内容数据
 * @author: fengda
 * @date: 2018/6/29 15:13
 */
@Data
public class ReportVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long shareId;

    private Long readerId;
    private String userName;
    private String nickName;
    private String icon;

    private String createTime;

    private String reason;

    private Integer type;

}
