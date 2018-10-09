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
 * 读者类型
 * </p>
 *
 * @author dw
 * @since 2018-08-07
 */
@TableName("t_reader_type")
public class TReaderType extends Model<TReaderType> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 类型名称
     */
    private String name;
    /**
     * 类型代码
     */
    private String code;
    /**
     * 注释
     */
    private String notes;
    /**
     * 本馆借阅规则
     */
    @TableField("in_lib_lendrule")
    private Long inLibLendrule;
    /**
     * 馆际借阅规则
     */
    @TableField("out_lib_lendrule")
    private Long outLibLendrule;
    @TableField("create_time")
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getInLibLendrule() {
        return inLibLendrule;
    }

    public void setInLibLendrule(Long inLibLendrule) {
        this.inLibLendrule = inLibLendrule;
    }

    public Long getOutLibLendrule() {
        return outLibLendrule;
    }

    public void setOutLibLendrule(Long outLibLendrule) {
        this.outLibLendrule = outLibLendrule;
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
        return "TReaderType{" +
        ", id=" + id +
        ", name=" + name +
        ", code=" + code +
        ", notes=" + notes +
        ", inLibLendrule=" + inLibLendrule +
        ", outLibLendrule=" + outLibLendrule +
        ", createTime=" + createTime +
        "}";
    }
}
