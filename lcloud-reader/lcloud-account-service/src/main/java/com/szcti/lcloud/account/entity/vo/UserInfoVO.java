package com.szcti.lcloud.account.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 用户信息VO
 * @Description: 包含t_user表和t_people表的信息
 * @author: fengda
 * @date: 2018/5/15 18:02
 */
@Data
public class UserInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private Long peopleId;
    private String loginName;//登录名
    private String userName;//用户姓名
    private String password;
    private Integer type;
    private Integer status;//新建|激活|注销
    private Integer sex;
    private String email;
    private String phone;
    private String icon;
    private Integer cardType;
    private String cardNumber;

    private Long createBy;
    private String createTime;

    private String remark;

    private String nickName;        //昵称
    private String signature;       //个性签名
    private String loginIp;         //登录IP
    private String  loginTime;      //最近一次登录的时间
}
