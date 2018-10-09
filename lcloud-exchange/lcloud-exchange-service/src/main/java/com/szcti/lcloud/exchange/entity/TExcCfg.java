package com.szcti.lcloud.exchange.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 同步配置
 * </p>
 *
 * @author dw
 * @since 2018-08-23
 */
@TableName("t_exc_cfg")
public class TExcCfg extends Model<TExcCfg> {

    private static final long serialVersionUID = 1L;

    @TableId("cfg_key")
    private String cfgKey;
    @TableField("cfg_val")
    private String cfgVal;
    private String remark;


    public String getCfgKey() {
        return cfgKey;
    }

    public void setCfgKey(String cfgKey) {
        this.cfgKey = cfgKey;
    }

    public String getCfgVal() {
        return cfgVal;
    }

    public void setCfgVal(String cfgVal) {
        this.cfgVal = cfgVal;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.cfgKey;
    }

    @Override
    public String toString() {
        return "TExcCfg{" +
        ", cfgKey=" + cfgKey +
        ", cfgVal=" + cfgVal +
        ", remark=" + remark +
        "}";
    }
}
