package com.szcti.lcloud.grade.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 班级实体类，对应 t_classes 表
 * @Description: 班级信息
 * @author: fengda
 * @date: 2018/7/30 18:02
 */
@Data
public class ClassesEntity extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long gradeId;

    private String code;

    private String name;

    private String remark;

}
