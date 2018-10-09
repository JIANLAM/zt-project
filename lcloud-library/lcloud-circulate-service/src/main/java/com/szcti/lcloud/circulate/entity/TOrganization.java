package com.szcti.lcloud.circulate.entity;

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
@TableName("t_organization")
public class TOrganization extends Model<TOrganization> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 上级机构id
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 所有上级id
     */
    @TableField("parent_ids")
    private String parentIds;
    /**
     * 图书馆名称
     */
    private String name;
    /**
     * 图书馆编码
     */
    private String code;
    /**
     * 联系人电话
     */
    @TableField("linkman_phone")
    private String linkmanPhone;
    /**
     * 联系人姓名
     */
    @TableField("linkman_name")
    private String linkmanName;
    private String city;
    private Integer type;
    private String remark;
    /**
     * 销售类型
     */
    @TableField("sale_type")
    private Integer saleType;
    /**
     * 是否能借购
     */
    @TableField("is_lendbuy")
    private Integer isLendbuy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public Integer getIsLendbuy() {
        return isLendbuy;
    }

    public void setIsLendbuy(Integer isLendbuy) {
        this.isLendbuy = isLendbuy;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TOrganization{" +
        ", id=" + id +
        ", parentId=" + parentId +
        ", parentIds=" + parentIds +
        ", name=" + name +
        ", code=" + code +
        ", linkmanPhone=" + linkmanPhone +
        ", linkmanName=" + linkmanName +
        ", city=" + city +
        ", type=" + type +
        ", remark=" + remark +
        ", saleType=" + saleType +
        ", isLendbuy=" + isLendbuy +
        "}";
    }
}
