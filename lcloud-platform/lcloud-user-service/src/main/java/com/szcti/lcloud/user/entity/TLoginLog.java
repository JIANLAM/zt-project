package com.szcti.lcloud.user.entity;

import java.util.Date;
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
 * @since 2018-08-08
 */
@TableName("t_login_log")
public class TLoginLog extends Model<TLoginLog> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 登录ip
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 登录时间
     */
    @TableField("login_time")
    private Date loginTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TLoginLog{" +
        ", id=" + id +
        ", userId=" + userId +
        ", loginIp=" + loginIp +
        ", loginTime=" + loginTime +
        "}";
    }
}
