package com.szcti.lcloud.user.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private Long readerId;
    private String loginName;//登录名
    private String userName;//用户姓名
    @JsonIgnore
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
    private Date createTime;
    private String createAccount;
    private String createName;
    private Long orgId;
    private String organization;
    
    private String loginIp;
    private String loginTime;

    private String remark;
    
    private String roleName;
    private Long roleId;
    private String newPassword;
    
    private Date lastLoginDate;
    private String birthday;
}
