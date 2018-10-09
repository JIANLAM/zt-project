package com.szcti.lcloud.grade.entity;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 年级实体类，对应 t_grade 表
 * @Description: 年级信息
 * @author: fengda
 * @date: 2018/7/30 18:02
 */
@Data
public class GradeEntity extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long libId;

    private String type;

    private String code;

    private String name;

    private Integer level;

    private Long lendRule;

    private Integer graduate;

    private String graduateTime;

}
