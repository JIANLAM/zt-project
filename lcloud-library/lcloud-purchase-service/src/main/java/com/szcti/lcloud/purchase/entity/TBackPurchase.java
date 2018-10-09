package com.szcti.lcloud.purchase.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 退订单
 * </p>
 *
 * @author dw
 * @since 2018-08-16
 */
@TableName("t_back_purchase")
public class TBackPurchase extends Model<TBackPurchase> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订购单号
     */
    @TableField("purchase_code")
    private String purchaseCode;
    /**
     * 退订单号
     */
    @TableField("back_code")
    private String backCode;
    /**
     * 操作员id  user_id
     */
    @TableField("operator_id")
    private Long operatorId;
    @TableField("create_time")
    private Date createTime;
    /**
     * 所属图书馆id
     */
    @TableField("lib_id")
    private Long libId;
    /**
     * 总价
     */
    @TableField("total_price")
    private Double totalPrice;
    /**
     * 备注
     */
    private String remark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(String purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

    public String getBackCode() {
        return backCode;
    }

    public void setBackCode(String backCode) {
        this.backCode = backCode;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TBackPurchase{" +
        ", id=" + id +
        ", purchaseCode=" + purchaseCode +
        ", backCode=" + backCode +
        ", operatorId=" + operatorId +
        ", createTime=" + createTime +
        ", libId=" + libId +
        ", totalPrice=" + totalPrice +
        ", remark=" + remark +
        "}";
    }
}
