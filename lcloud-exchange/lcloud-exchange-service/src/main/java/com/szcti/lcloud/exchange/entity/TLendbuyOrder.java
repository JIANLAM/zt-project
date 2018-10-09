package com.szcti.lcloud.exchange.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 借购单
 * </p>
 *
 * @author dw
 * @since 2018-08-09
 */
@TableName("t_lendbuy_order")
public class TLendbuyOrder extends Model<TLendbuyOrder> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单编号
     */
    @TableField("order_no")
    private String orderNo;
    /**
     * 快递公司
     */
    @TableField("express_company")
    private String expressCompany;
    /**
     * 快递单号
     */
    @TableField("express_no")
    private String expressNo;
    /**
     * 预算金额
     */
    @TableField("limit_money")
    private Float limitMoney;
    /**
     * 总金额
     */
    @TableField("total_money")
    private Float totalMoney;
    /**
     * 职工id
     */
    @TableField("staff_id")
    private Long staffId;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 读者证号
     */
    @TableField("reader_card_number")
    private String readerCardNumber;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 发货时间
     */
    @TableField("send_time")
    private Date sendTime;
    /**
     * 收货时间
     */
    @TableField("taked_time")
    private Date takedTime;
    /**
     * 0待处理，1已发货，2已收货，3已取消,4已完成
     */
    private Integer status;
    /**
     * 线上或线下处理
     */
    private Integer online;
    /**
     * 备注
     */
    private String remark;
    /**
     * 收件人
     */
    private String addressee;
    /**
     * 收件地址
     */
    private String address;
    /**
     * 联系电话
     */
    private String phone;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getExpressCompany() {
        return expressCompany;
    }

    public void setExpressCompany(String expressCompany) {
        this.expressCompany = expressCompany;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Float getLimitMoney() {
        return limitMoney;
    }

    public void setLimitMoney(Float limitMoney) {
        this.limitMoney = limitMoney;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public String getReaderCardNumber() {
        return readerCardNumber;
    }

    public void setReaderCardNumber(String readerCardNumber) {
        this.readerCardNumber = readerCardNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getTakedTime() {
        return takedTime;
    }

    public void setTakedTime(Date takedTime) {
        this.takedTime = takedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TLendbuyOrder{" +
        ", id=" + id +
        ", orderNo=" + orderNo +
        ", expressCompany=" + expressCompany +
        ", expressNo=" + expressNo +
        ", limitMoney=" + limitMoney +
        ", totalMoney=" + totalMoney +
        ", staffId=" + staffId +
        ", readerId=" + readerId +
        ", readerCardNumber=" + readerCardNumber +
        ", createTime=" + createTime +
        ", sendTime=" + sendTime +
        ", takedTime=" + takedTime +
        ", status=" + status +
        ", online=" + online +
        ", remark=" + remark +
        ", addressee=" + addressee +
        ", address=" + address +
        ", phone=" + phone +
        "}";
    }
}
