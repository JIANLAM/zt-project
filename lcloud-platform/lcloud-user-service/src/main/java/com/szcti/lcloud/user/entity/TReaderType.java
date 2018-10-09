package com.szcti.lcloud.user.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 读者类型
 * </p>
 *
 * @author dw（linzj修改）
 * @since 2018-08-07（2018-09-05 update ）
 */
@TableName("t_readercard")
public class TReaderType extends Model<TReaderType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 字段代码 对应code
     */
    @TableField("value")
    private String value;
    /**
     * 字段值 对应name名称
     */
    @TableField("label")
    private String label;
    /**
     *图书馆外键
     */
    @TableField("library_id")
    private  Long  libraryId;
    /**
     * 本馆借阅规则
     */
    @TableField("museum_rule")
    private Long museumRule;
    /**
     * 馆际借阅规则
     */
    @TableField("interlibrary_rule")
    private Long interlibraryRule;

    @TableField("create_date")
    private Date createDate;

    @TableField("create_by")
    private Long createBy;

    @Override
    public String toString() {
        return "TReaderType{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", label='" + label + '\'' +
                ", libraryId=" + libraryId +
                ", museumRule=" + museumRule +
                ", interlibraryRule=" + interlibraryRule +
                ", createDate=" + createDate +
                ", createBy=" + createBy +
                ", remarks='" + remarks + '\'' +
                '}';
    }

    @TableField("remarks")
    private String remarks;
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Long getMuseumRule() {
        return museumRule;
    }

    public void setMuseumRule(Long museumRule) {
        this.museumRule = museumRule;
    }

    public Long getInterlibraryRule() {
        return interlibraryRule;
    }

    public void setInterlibraryRule(Long interlibraryRule) {
        this.interlibraryRule = interlibraryRule;
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
}
