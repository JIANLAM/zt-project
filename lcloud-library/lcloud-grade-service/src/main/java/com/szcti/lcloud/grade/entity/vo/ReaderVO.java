package com.szcti.lcloud.grade.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 读者VO
 * @Description: 读者信息
 * @author: fengda
 * @date: 2018/7/30 18:02
 */
@Data
public class ReaderVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long peopleId;
    private Integer readerCardType;
    private String readerCardNumber;
    private Integer status;
    private Integer grade;
    private Long libraryId;
    private String libraryName;        //归属
    private String remark;
    private Integer ownValue;       //信用值

}
