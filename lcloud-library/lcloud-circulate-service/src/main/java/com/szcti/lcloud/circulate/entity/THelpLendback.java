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
 * @since 2018-07-31
 */
@TableName("t_help_lendback")
public class THelpLendback extends Model<THelpLendback> {

    private static final long serialVersionUID = 1L;

    /**
     * 代借标识
     */
    private Long id;
    /**
     * 馆藏标识
     */
    @TableField("holding_id")
    private Long holdingId;
    /**
     * 读者标识
     */
    @TableField("lend_id")
    private Long lendId;
    /**
     * 借阅标识
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 微柜标识
     */
    @TableField("box_id")
    private Long boxId;
    /**
     * 取书期限
     */
    @TableField("due_time")
    private Date dueTime;
    /**
     * 状态 0待处理，1待领取，2已领取，3已还书，4已取消
     */
    private Integer status;
    /**
     * 0代借，1代还
     */
    private Integer type;
    /**
     * 馆藏所属图书馆
     */
    @TableField("holding_lib_id")
    private Long holdingLibId;
    /**
     * 读者所属图书馆
     */
    @TableField("reader_lib_id")
    private Long readerLibId;


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

    public Long getLendId() {
        return lendId;
    }

    public void setLendId(Long lendId) {
        this.lendId = lendId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getHoldingLibId() {
        return holdingLibId;
    }

    public void setHoldingLibId(Long holdingLibId) {
        this.holdingLibId = holdingLibId;
    }

    public Long getReaderLibId() {
        return readerLibId;
    }

    public void setReaderLibId(Long readerLibId) {
        this.readerLibId = readerLibId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "THelpLendback{" +
        ", id=" + id +
        ", holdingId=" + holdingId +
        ", lendId=" + lendId +
        ", readerId=" + readerId +
        ", boxId=" + boxId +
        ", dueTime=" + dueTime +
        ", status=" + status +
        ", type=" + type +
        ", holdingLibId=" + holdingLibId +
        ", readerLibId=" + readerLibId +
        "}";
    }
}
