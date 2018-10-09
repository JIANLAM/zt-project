package com.szcti.lcloud.exchange.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author dw
 * @since 2018-07-10
 */
@TableName("t_user")
public class TUser extends Model<TUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id")
    private Long id;
    @TableField("login_name")
    private String loginName;
    private String password;
    /**
     * 个人用户0 | 机构用户1 | 系统用户2
     */
    private Integer type;
    private Integer status;
    /**
     * 所属机构id
     */
    @TableField("org_id")
    private Long orgId;
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建人账号
     */
    @TableField("create_account")
    private String createAccount;
    /**
     * 创建人姓名
     */
    @TableField("create_name")
    private String createName;
    @TableField("create_time")
    private Date createTime;
    private Integer enabled;
    @TableField("last_password_reset_date")
    private Date lastPasswordResetDate;
    private String rowid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TUser{" +
        ", id=" + id +
        ", loginName=" + loginName +
        ", password=" + password +
        ", type=" + type +
        ", status=" + status +
        ", orgId=" + orgId +
        ", createBy=" + createBy +
        ", createAccount=" + createAccount +
        ", createName=" + createName +
        ", createTime=" + createTime +
        ", enabled=" + enabled +
        ", lastPasswordResetDate=" + lastPasswordResetDate +
        ", rowid=" + rowid +
        "}";
    }
}
