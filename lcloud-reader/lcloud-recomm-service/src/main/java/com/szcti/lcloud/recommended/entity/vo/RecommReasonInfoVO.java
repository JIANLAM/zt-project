package com.szcti.lcloud.recommended.entity.vo;

import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;

/**
 * @Title: 推荐图书信息VO
 * @Description: 推荐图书信息
 * @author: fengda
 * @date: 2018/5/30 15:13
 */
@Data
public class RecommReasonInfoVO extends QueryParam implements Serializable {

    private static final long serialVersionUID = 1L;

    private String recommReason;

    private String username;

    private String nickName;

    private String icon;

}
