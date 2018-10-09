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
 * 
 * </p>
 *
 * @author dw
 * @since 2018-07-25
 */
@TableName("t_reader_credit")
public class TReaderCredit extends Model<TReaderCredit> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 读者id
     */
    @TableField("reader_id")
    private Long readerId;
    /**
     * 扣分前分数
     */
    @TableField("before_deduct_credit_value")
    private Integer beforeDeductCreditValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 扣分时间
     */
    @TableField("deduct_score_time")
    private Date deductScoreTime;
    /**
     * 扣分数量
     */
    @TableField("deduct_score")
    private Integer deductScore;
    /**
     * 扣分原因
     */
    @TableField("deduct_score_reason")
    private String deductScoreReason;
    /**
     * 扣分后分数
     */
    @TableField("after_deduct_credit_value")
    private Integer afterDeductCreditValue;
    /**
     * 状态0禁用1启用
     */
    private Integer status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Integer getBeforeDeductCreditValue() {
        return beforeDeductCreditValue;
    }

    public void setBeforeDeductCreditValue(Integer beforeDeductCreditValue) {
        this.beforeDeductCreditValue = beforeDeductCreditValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDeductScoreTime() {
        return deductScoreTime;
    }

    public void setDeductScoreTime(Date deductScoreTime) {
        this.deductScoreTime = deductScoreTime;
    }

    public Integer getDeductScore() {
        return deductScore;
    }

    public void setDeductScore(Integer deductScore) {
        this.deductScore = deductScore;
    }

    public String getDeductScoreReason() {
        return deductScoreReason;
    }

    public void setDeductScoreReason(String deductScoreReason) {
        this.deductScoreReason = deductScoreReason;
    }

    public Integer getAfterDeductCreditValue() {
        return afterDeductCreditValue;
    }

    public void setAfterDeductCreditValue(Integer afterDeductCreditValue) {
        this.afterDeductCreditValue = afterDeductCreditValue;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TReaderCredit{" +
        ", id=" + id +
        ", readerId=" + readerId +
        ", beforeDeductCreditValue=" + beforeDeductCreditValue +
        ", remark=" + remark +
        ", deductScoreTime=" + deductScoreTime +
        ", deductScore=" + deductScore +
        ", deductScoreReason=" + deductScoreReason +
        ", afterDeductCreditValue=" + afterDeductCreditValue +
        ", status=" + status +
        "}";
    }
}
