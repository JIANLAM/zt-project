package com.szcti.lcloud.account.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 职员VO
 * @Description: 职员信息
 * @author: fengda
 * @date: 2018/5/15 18:02
 */
@Data
public class StaffVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long peopleId;
    private String staffCode;
    private String possie;
    private Integer isLeader;
    private Integer status;  //0离职，1在职
    private Long orgId;
    private String orgName;
    private String remark;
}
