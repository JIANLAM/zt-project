package com.szcti.lcloud.grade.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 部门实体类，对应 t_dept 表
 * @Description: 部门信息
 * @author: fengda
 * @date: 2018/8/29 18:02
 */
@Data
public class DeptEntity extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long libId;

    private String code;

    private String name;

    private Long readerType;

    private Long createBy;

    private String createTime;

    private String remark;

}
