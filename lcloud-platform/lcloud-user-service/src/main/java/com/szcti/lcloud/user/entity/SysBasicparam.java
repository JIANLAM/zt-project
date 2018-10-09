package com.szcti.lcloud.user.entity;

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
 * @since 2018-08-07
 */
@TableName("sys_basicparam")
public class SysBasicparam extends Model<SysBasicparam> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 字段代码
     */
    private String value;
    /**
     * 字段值
     */
    private String label;
    /**
     * 类型 
     */
    private String type;
    /**
     * 自定义字段描述
     */
    private String description;
    /**
     * 外键 图书馆
     */
    @TableField("library_id")
    private Long libraryId;
    /**
     * 创建时间
     */
    @TableField("create_date")
    private Date createDate;
    /**
     * 创建者
     */
    @TableField("create_by")
    private Long createBy;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
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
        return "SysBasicparam{" +
        ", id=" + id +
        ", value=" + value +
        ", label=" + label +
        ", type=" + type +
        ", description=" + description +
        ", libraryId=" + libraryId +
        ", createDate=" + createDate +
        ", createBy=" + createBy +
        ", remarks=" + remarks +
        "}";
    }
}
