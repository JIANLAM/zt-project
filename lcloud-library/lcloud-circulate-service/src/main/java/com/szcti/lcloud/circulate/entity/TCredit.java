package com.szcti.lcloud.circulate.entity;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("t_credit")
public class TCredit extends Model<TCredit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 默认值
     */
    @TableField("default_value")
    private Integer defaultValue;
    /**
     * 剩余值
     */
    @TableField("own_value")
    private Integer ownValue;
    /**
     * 状态0禁用1启用
     */
    private Integer status;
    /**
     * 阅读卡状态0禁用1启用
     */
    @TableField("card_status")
    private Integer cardStatus;
    /**
     * 借购启用0禁用1启用
     */
    @TableField("islend_buy")
    private Integer islendBuy;
    /**
     * 备注
     */
    private String summary;


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

    public Integer getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Integer defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Integer getOwnValue() {
        return ownValue;
    }

    public void setOwnValue(Integer ownValue) {
        this.ownValue = ownValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(Integer cardStatus) {
        this.cardStatus = cardStatus;
    }

    public Integer getIslendBuy() {
        return islendBuy;
    }

    public void setIslendBuy(Integer islendBuy) {
        this.islendBuy = islendBuy;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TCredit{" +
        ", id=" + id +
        ", userId=" + userId +
        ", defaultValue=" + defaultValue +
        ", ownValue=" + ownValue +
        ", status=" + status +
        ", cardStatus=" + cardStatus +
        ", islendBuy=" + islendBuy +
        ", summary=" + summary +
        "}";
    }
}
