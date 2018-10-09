package com.szcti.lcloud.exchange.entity;

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
 * @since 2018-07-10
 */
@TableName("t_reader")
public class TReader extends Model<TReader> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 个人信息id
     */
    @TableField("people_id")
    private Long peopleId;
    /**
     * 证件类型
     */
    @TableField("reader_card_type")
    private Integer readerCardType;
    /**
     * 证件号码
     */
    @TableField("reader_card_number")
    private String readerCardNumber;
    /**
     * 状态0禁用1正常2注销3挂失
     */
    private Integer status;
    /**
     * 班级
     */
    private Integer grade;
    /**
     * 备注
     */
    private String remark;
    /**
     * 所属图书馆id
     */
    @TableField("library_id")
    private Long libraryId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(Long peopleId) {
        this.peopleId = peopleId;
    }

    public Integer getReaderCardType() {
        return readerCardType;
    }

    public void setReaderCardType(Integer readerCardType) {
        this.readerCardType = readerCardType;
    }

    public String getReaderCardNumber() {
        return readerCardNumber;
    }

    public void setReaderCardNumber(String readerCardNumber) {
        this.readerCardNumber = readerCardNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TReader{" +
        ", id=" + id +
        ", peopleId=" + peopleId +
        ", readerCardType=" + readerCardType +
        ", readerCardNumber=" + readerCardNumber +
        ", status=" + status +
        ", grade=" + grade +
        ", remark=" + remark +
        ", libraryId=" + libraryId +
        "}";
    }
}
