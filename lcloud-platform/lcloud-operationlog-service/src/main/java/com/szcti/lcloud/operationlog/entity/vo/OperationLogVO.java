package com.szcti.lcloud.operationlog.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.szcti.lcloud.common.utils.QueryParam;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 *
 *
 * @author liujunliang
 * @date 2018-05-17 14:25:42
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class OperationLogVO extends QueryParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;//记录id

    private Integer moduleId;//模块id

    private String moduleName;//模块名称

    private String operationType;//操作类型

    private Long libraryId;//图书馆id

    private String libraryName;//图书馆名称

    private Long userId;//操作用户id

    private String loginName;//登录帐号

    private String userName;//用户名字

    private String ip;//操作ip

    private String opContent;//操作内容

    private String remark;//备注

    private String createTime;//操作时间
}