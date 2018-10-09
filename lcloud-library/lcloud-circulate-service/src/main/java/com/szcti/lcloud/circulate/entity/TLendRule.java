package com.szcti.lcloud.circulate.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 借阅规则表
 * </p>
 *
 * @author dw
 * @since 2018-08-22
 */
@TableName("t_lend_rule")
public class TLendRule extends Model<TLendRule> {

    private static final long serialVersionUID = 1L;

    /**
     * 借阅规则标识
     */
    private Long id;
    /**
     * 规则代码 
     */
    @TableField("rule_code")
    private String ruleCode;
    /**
     * 规则名称
     */
    @TableField("rule_name")
    private String ruleName;
    /**
     * 0 管内借阅；1 馆际借阅
     */
    @TableField("rule_type")
    private Integer ruleType;
    /**
     * 借阅册数
     */
    @TableField("lend_qty")
    private Integer lendQty;
    /**
     * 允许借阅天数
     */
    @TableField("lend_days")
    private Integer lendDays;
    /**
     * 续借次数
     */
    @TableField("renew_qty")
    private Integer renewQty;
    /**
     * 续借天数
     */
    @TableField("renew_days")
    private Integer renewDays;
    /**
     * 逾期罚款率
     */
    @TableField("overdue_payrate")
    private Float overduePayrate;
    /**
     * 丢失罚款率
     */
    @TableField("lost_payrate")
    private Float lostPayrate;
    /**
     * 污损罚款率
     */
    @TableField("broken_payrate")
    private Float brokenPayrate;
    /**
     * 要求信用分
     */
    private Integer credit;
    /**
     * 可借文献类型
     */
    @TableField("literature_type")
    private String literatureType;
    /**
     * 是否启用 1启用   0不启用
     */
    private Integer status;
    /**
     * 图书馆标识 外键 
     */
    @TableField("library_id")
    private Long libraryId;
    /**
     * 创建者 外键，t_user的主键
     */
    @TableField("create_by")
    private Long createBy;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改者 外键，t_user的主键
     */
    @TableField("update_by")
    private Long updateBy;
    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;
    /**
     * 备注
     */
    private String remarks;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Integer getRuleType() {
        return ruleType;
    }

    public void setRuleType(Integer ruleType) {
        this.ruleType = ruleType;
    }

    public Integer getLendQty() {
        return lendQty;
    }

    public void setLendQty(Integer lendQty) {
        this.lendQty = lendQty;
    }

    public Integer getLendDays() {
        return lendDays;
    }

    public void setLendDays(Integer lendDays) {
        this.lendDays = lendDays;
    }

    public Integer getRenewQty() {
        return renewQty;
    }

    public void setRenewQty(Integer renewQty) {
        this.renewQty = renewQty;
    }

    public Integer getRenewDays() {
        return renewDays;
    }

    public void setRenewDays(Integer renewDays) {
        this.renewDays = renewDays;
    }

    public Float getOverduePayrate() {
        return overduePayrate;
    }

    public void setOverduePayrate(Float overduePayrate) {
        this.overduePayrate = overduePayrate;
    }

    public Float getLostPayrate() {
        return lostPayrate;
    }

    public void setLostPayrate(Float lostPayrate) {
        this.lostPayrate = lostPayrate;
    }

    public Float getBrokenPayrate() {
        return brokenPayrate;
    }

    public void setBrokenPayrate(Float brokenPayrate) {
        this.brokenPayrate = brokenPayrate;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getLiteratureType() {
        return literatureType;
    }

    public void setLiteratureType(String literatureType) {
        this.literatureType = literatureType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TLendRule{" +
        ", id=" + id +
        ", ruleCode=" + ruleCode +
        ", ruleName=" + ruleName +
        ", ruleType=" + ruleType +
        ", lendQty=" + lendQty +
        ", lendDays=" + lendDays +
        ", renewQty=" + renewQty +
        ", renewDays=" + renewDays +
        ", overduePayrate=" + overduePayrate +
        ", lostPayrate=" + lostPayrate +
        ", brokenPayrate=" + brokenPayrate +
        ", credit=" + credit +
        ", literatureType=" + literatureType +
        ", status=" + status +
        ", libraryId=" + libraryId +
        ", createBy=" + createBy +
        ", createTime=" + createTime +
        ", updateBy=" + updateBy +
        ", updateTime=" + updateTime +
        ", remarks=" + remarks +
        "}";
    }
}
