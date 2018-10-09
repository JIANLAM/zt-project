package com.szcti.lcloud.circulate.entity;

import java.math.BigDecimal;
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
 * @since 2018-08-02
 */
@TableName("t_lendbuy_book")
public class TLendbuyBook extends Model<TLendbuyBook> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单标识
     */
    @TableField("lendbuy_order_id")
    private Long lendbuyOrderId;
    /**
     * 采访书库标识
     */
    @TableField("prebook_id")
    private Long prebookId;
    /**
     * 读者标识
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 单价
     */
    private BigDecimal price;
    /**
     * 还书日期
     */
    @TableField("back_time")
    private Date backTime;
    /**
     * 线上或线下处理1线上，0线下
     */
    private Integer online;
    /**
     * 0未到馆，1已到馆
     */
    private Integer status;
    /**
     * 是否生成到订单0未提交，1已提交
     */
    @TableField("is_submit")
    private Integer isSubmit;
    /**
     * 应还日期
     */
    @TableField("due_back_time")
    private Date dueBackTime;
    /**
     * 借购日期
     */
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLendbuyOrderId() {
        return lendbuyOrderId;
    }

    public void setLendbuyOrderId(Long lendbuyOrderId) {
        this.lendbuyOrderId = lendbuyOrderId;
    }

    public Long getPrebookId() {
        return prebookId;
    }

    public void setPrebookId(Long prebookId) {
        this.prebookId = prebookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSubmit() {
        return isSubmit;
    }

    public void setIsSubmit(Integer isSubmit) {
        this.isSubmit = isSubmit;
    }

    public Date getDueBackTime() {
        return dueBackTime;
    }

    public void setDueBackTime(Date dueBackTime) {
        this.dueBackTime = dueBackTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TLendbuyBook{" +
        ", id=" + id +
        ", lendbuyOrderId=" + lendbuyOrderId +
        ", prebookId=" + prebookId +
        ", readerId=" + readerId +
        ", price=" + price +
        ", backTime=" + backTime +
        ", online=" + online +
        ", status=" + status +
        ", isSubmit=" + isSubmit +
        ", dueBackTime=" + dueBackTime +
        ", createTime=" + createTime +
        "}";
    }
}
