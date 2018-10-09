package com.szcti.lcloud.account.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/6/28 0028.
 */
    @Data
    public class RoleEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String roleName;       //角色名称
    private String roleCode;       //角色代码
    private String orgType;        //机构类别
    private Integer createBy;          //创建人id
    private String createName;     //创建人姓名
    private String createAccount;  //创建人账号
    private String createTime;     //创建时间
    private Integer status;             //1启用，0禁用
    private String remark;          //备注
}
