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
@TableName("t_prelend")
public class TPrelend extends Model<TPrelend> {

    private static final long serialVersionUID = 1L;

    /**
     * 预借标识
     */
    private Long id;
    /**
     * 馆藏标识
     */
    @TableField("holding_id")
    private Long holdingId;
    /**
     * 预借日期
     */
    @TableField("prelend_time")
    private Date prelendTime;
    /**
     * 到期日期
     */
    @TableField("due_lend_time")
    private Date dueLendTime;
    /**
     * 借阅状态 0预借中，1预借完成，2取消预借
     */
    @TableField("prelend_status")
    private Integer prelendStatus;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 预约地点
     */
    @TableField("lib_id")
    private Long libId;


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

    public Date getPrelendTime() {
        return prelendTime;
    }

    public void setPrelendTime(Date prelendTime) {
        this.prelendTime = prelendTime;
    }

    public Date getDueLendTime() {
        return dueLendTime;
    }

    public void setDueLendTime(Date dueLendTime) {
        this.dueLendTime = dueLendTime;
    }

    public Integer getPrelendStatus() {
        return prelendStatus;
    }

    public void setPrelendStatus(Integer prelendStatus) {
        this.prelendStatus = prelendStatus;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPrelend{" +
        ", id=" + id +
        ", holdingId=" + holdingId +
        ", prelendTime=" + prelendTime +
        ", dueLendTime=" + dueLendTime +
        ", prelendStatus=" + prelendStatus +
        ", readerId=" + readerId +
        ", libId=" + libId +
        "}";
    }
}
