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
 * @since 2018-07-23
 */
@TableName("t_lend")
public class TLend extends Model<TLend> {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅标识
     */
    private Long id;
    /**
     * 馆藏标识
     */
    @TableField("holding_id")
    private Long holdingId;
    /**
     * 借阅日期
     */
    @TableField("lend_time")
    private Date lendTime;
    /**
     * 归还日期
     */
    @TableField("back_time")
    private Date backTime;
    /**
     * 应还书日期
     */
    @TableField("due_back_time")
    private Date dueBackTime;
    /**
     * 借阅状态0在借，1已还，2续借
     */
    @TableField("lend_status")
    private Integer lendStatus;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHoldingId() {
        return holdingId;
    }

    public void setHoldingId(Long holdingId) {
        this.holdingId = holdingId;
    }

    public Date getLendTime() {
        return lendTime;
    }

    public void setLendTime(Date lendTime) {
        this.lendTime = lendTime;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Date getDueBackTime() {
        return dueBackTime;
    }

    public void setDueBackTime(Date dueBackTime) {
        this.dueBackTime = dueBackTime;
    }

    public Integer getLendStatus() {
        return lendStatus;
    }

    public void setLendStatus(Integer lendStatus) {
        this.lendStatus = lendStatus;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TLend{" +
        ", id=" + id +
        ", holdingId=" + holdingId +
        ", lendTime=" + lendTime +
        ", backTime=" + backTime +
        ", dueBackTime=" + dueBackTime +
        ", lendStatus=" + lendStatus +
        ", readerId=" + readerId +
        "}";
    }
}
