package com.szcti.lcloud.purchase.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 操作日志记录表
 * </p>
 *
 * @author dw
 * @since 2018-08-24
 */
@TableName("t_operation_log")
public class TOperationLog extends Model<TOperationLog> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 模块
     */
    @TableField("module_id")
    private Integer moduleId;
    /**
     * 功能模块名称
     */
    @TableField("module_name")
    private String moduleName;
    /**
     * 操作类型
     */
    @TableField("operation_type")
    private String operationType;
    /**
     * 图书馆id
     */
    @TableField("library_id")
    private Long libraryId;
    /**
     * 图书馆名称
     */
    @TableField("library_name")
    private String libraryName;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 登录名
     */
    @TableField("login_name")
    private String loginName;
    /**
     * 姓名
     */
    @TableField("user_name")
    private String userName;
    /**
     * id地址
     */
    private String ip;
    /**
     * 操作内容
     */
    @TableField("op_content")
    private String opContent;
    /**
     * 备注
     */
    private String remark;
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getOpContent() {
        return opContent;
    }

    public void setOpContent(String opContent) {
        this.opContent = opContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TOperationLog{" +
        ", id=" + id +
        ", moduleId=" + moduleId +
        ", moduleName=" + moduleName +
        ", operationType=" + operationType +
        ", libraryId=" + libraryId +
        ", libraryName=" + libraryName +
        ", userId=" + userId +
        ", loginName=" + loginName +
        ", userName=" + userName +
        ", ip=" + ip +
        ", opContent=" + opContent +
        ", remark=" + remark +
        ", createTime=" + createTime +
        "}";
    }
}
