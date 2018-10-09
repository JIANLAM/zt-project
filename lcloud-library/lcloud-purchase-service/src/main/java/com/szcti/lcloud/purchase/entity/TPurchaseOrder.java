package com.szcti.lcloud.purchase.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订购单
 * </p>
 *
 * @author dw
 * @since 2018-08-16
 */
@TableName("t_purchase_order")
public class TPurchaseOrder extends Model<TPurchaseOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单编码
     */
    @TableField("order_code")
    private String orderCode;
    /**
     * 快递号码
     */
    @TableField("express_code")
    private String expressCode;
    /**
     * 下订单时间
     */
    @TableField("order_time")
    private Date orderTime;
    /**
     * 收货人
     */
    @TableField("sendee_id")
    private Long sendeeId;
    /**
     * 书本总数量
     */
    @TableField("total_quantity")
    private Integer totalQuantity;
    /**
     * 备注
     */
    private String summary;
    /**
     * 订单提交后状态：0申请中2已审批3已发货4部分验收5补发货6已完成7退回
     */
    @TableField("order_status")
    private Integer orderStatus;
    /**
     * 图书馆id
     */
    @TableField("library_id")
    private Long libraryId;
    /**
     * 订购号
     */
    @TableField("purchase_code")
    private String purchaseCode;
    /**
     * 创建者id
     */
    private Long creator;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 书种类数
     */
    @TableField("book_type_qty")
    private Integer bookTypeQty;
    /**
     * 订购单的审核总状态：0待审核1通过2不通过3无需审核
     */
    @TableField("check_status")
    private Integer checkStatus;
    /**
     * 审核者id
     */
    private Long checker;
    /**
     * 审核时间
     */
    @TableField("check_time")
    private Date checkTime;
    /**
     * 预算额度
     */
    @TableField("budget_amount")
    private Float budgetAmount;
    /**
     * 订单总价
     */
    @TableField("total_price")
    private Float totalPrice;
    /**
     * 预算id
     */
    @TableField("budget_id")
    private Long budgetId;
    /**
     * 规则id
     */
    @TableField("rule_id")
    private Long ruleId;
    /**
     * 提交人id
     */
    private Long committer;
    /**
     * 地址
     */
    private String address;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getExpressCode() {
        return expressCode;
    }

    public void setExpressCode(String expressCode) {
        this.expressCode = expressCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Long getSendeeId() {
        return sendeeId;
    }

    public void setSendeeId(Long sendeeId) {
        this.sendeeId = sendeeId;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getBookTypeQty() {
        return bookTypeQty;
    }

    public void setBookTypeQty(Integer bookTypeQty) {
        this.bookTypeQty = bookTypeQty;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Long getChecker() {
        return checker;
    }

    public void setChecker(Long checker) {
        this.checker = checker;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public Float getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Float budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getCommitter() {
        return committer;
    }

    public void setCommitter(Long committer) {
        this.committer = committer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TPurchaseOrder{" +
        ", id=" + id +
        ", orderCode=" + orderCode +
        ", expressCode=" + expressCode +
        ", orderTime=" + orderTime +
        ", sendeeId=" + sendeeId +
        ", totalQuantity=" + totalQuantity +
        ", summary=" + summary +
        ", orderStatus=" + orderStatus +
        ", libraryId=" + libraryId +
        ", purchaseCode=" + purchaseCode +
        ", creator=" + creator +
        ", createTime=" + createTime +
        ", bookTypeQty=" + bookTypeQty +
        ", checkStatus=" + checkStatus +
        ", checker=" + checker +
        ", checkTime=" + checkTime +
        ", budgetAmount=" + budgetAmount +
        ", totalPrice=" + totalPrice +
        ", budgetId=" + budgetId +
        ", ruleId=" + ruleId +
        ", committer=" + committer +
        ", address=" + address +
        "}";
    }
}
