package com.szcti.lcloud.circulate.entity;

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
 * @since 2018-07-25
 */
@TableName("t_relend")
public class TRelend extends Model<TRelend> {

    private static final long serialVersionUID = 1L;

    /**
     * 续借标识
     */
    private Long id;
    /**
     * 借阅标识
     */
    @TableField("lend_id")
    private Long lendId;
    /**
     * 续借日期
     */
    @TableField("relend_time")
    private Date relendTime;
    /**
     * 续借归还日期
     */
    @TableField("relend_back_time")
    private Date relendBackTime;
    /**
     * 原应还书日期
     */
    @TableField("primary_back_time")
    private Date primaryBackTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLendId() {
        return lendId;
    }

    public void setLendId(Long lendId) {
        this.lendId = lendId;
    }

    public Date getRelendTime() {
        return relendTime;
    }

    public void setRelendTime(Date relendTime) {
        this.relendTime = relendTime;
    }

    public Date getRelendBackTime() {
        return relendBackTime;
    }

    public void setRelendBackTime(Date relendBackTime) {
        this.relendBackTime = relendBackTime;
    }

    public Date getPrimaryBackTime() {
        return primaryBackTime;
    }

    public void setPrimaryBackTime(Date primaryBackTime) {
        this.primaryBackTime = primaryBackTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TRelend{" +
        ", id=" + id +
        ", lendId=" + lendId +
        ", relendTime=" + relendTime +
        ", relendBackTime=" + relendBackTime +
        ", primaryBackTime=" + primaryBackTime +
        "}";
    }
}
