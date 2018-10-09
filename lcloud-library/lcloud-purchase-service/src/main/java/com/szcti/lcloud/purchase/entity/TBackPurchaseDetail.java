package com.szcti.lcloud.purchase.entity;

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
 * @since 2018-08-16
 */
@TableName("t_back_purchase_detail")
public class TBackPurchaseDetail extends Model<TBackPurchaseDetail> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 退订单号
     */
    @TableField("back_purchase_id")
    private Long backPurchaseId;
    /**
     * 订购书id
     */
    @TableField("purchase_book_id")
    private Long purchaseBookId;
    private String isbn;
    /**
     * 所属图书馆id
     */
    @TableField("lib_id")
    private Long libId;
    /**
     * 退订数
     */
    @TableField("back_quantity")
    private Integer backQuantity;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBackPurchaseId() {
        return backPurchaseId;
    }

    public void setBackPurchaseId(Long backPurchaseId) {
        this.backPurchaseId = backPurchaseId;
    }

    public Long getPurchaseBookId() {
        return purchaseBookId;
    }

    public void setPurchaseBookId(Long purchaseBookId) {
        this.purchaseBookId = purchaseBookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getLibId() {
        return libId;
    }

    public void setLibId(Long libId) {
        this.libId = libId;
    }

    public Integer getBackQuantity() {
        return backQuantity;
    }

    public void setBackQuantity(Integer backQuantity) {
        this.backQuantity = backQuantity;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TBackPurchaseDetail{" +
        ", id=" + id +
        ", backPurchaseId=" + backPurchaseId +
        ", purchaseBookId=" + purchaseBookId +
        ", isbn=" + isbn +
        ", libId=" + libId +
        ", backQuantity=" + backQuantity +
        "}";
    }
}
