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
 * 借书表
 * </p>
 *
 * @author dw
 * @since 2018-08-28
 */
@TableName("t_lend")
public class TLend extends Model<TLend> {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅标识
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 借阅状态0在借，1已还，2续借，3逾期
     */
    @TableField("lend_status")
    private Integer lendStatus;
    /**
     * 是否续借过，0 否；1 是
     */
    private Integer renew;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 用户id，操作员
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 借书图书管id
     */
    @TableField("lend_lib_id")
    private Long lendLibId;
    /**
     * 还书图书管id
     */
    @TableField("back_lib_id")
    private Long backLibId;


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

    public Integer getRenew() {
        return renew;
    }

    public void setRenew(Integer renew) {
        this.renew = renew;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLendLibId() {
        return lendLibId;
    }

    public void setLendLibId(Long lendLibId) {
        this.lendLibId = lendLibId;
    }

    public Long getBackLibId() {
        return backLibId;
    }

    public void setBackLibId(Long backLibId) {
        this.backLibId = backLibId;
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
        ", renew=" + renew +
        ", readerId=" + readerId +
        ", userId=" + userId +
        ", lendLibId=" + lendLibId +
        ", backLibId=" + backLibId +
        "}";
    }
}
