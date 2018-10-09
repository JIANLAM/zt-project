package com.szcti.lcloud.circulate.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dw
 * @since 2018-07-25
 */
@TableName("t_user")
public class TUser extends Model<TUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("login_name")
    private String loginName;
    private String password;
    /**
     * 个人用户0 | 机构用户1 | 系统用户2
     */
    private Integer type;
    /**
     * 1.正常；0禁用
     */
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
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 1.正常；0禁用
     */
    private Integer enabled;
    @TableField("last_password_reset_date")
    private Date lastPasswordResetDate;
    /**
     * 上次登录时间
     */
    @TableField("last_login_date")
    private Date lastLoginDate;
    /**
     * 登录时间
     */
    @TableField("login_date")
    private Date loginDate;
    /**
     * 图创id
     */
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

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
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
        ", lastLoginDate=" + lastLoginDate +
        ", loginDate=" + loginDate +
        ", rowid=" + rowid +
        "}";
    }
}
