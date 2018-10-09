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
 * @since 2018-07-23
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
     * 证件类型 0读书证
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
     * 读者类型
     */
    @TableField("reader_type")
    private Integer readerType;
    /**
     * 年级
     */
    private Long grade;
    /**
     * 班级
     */
    private Long classes;
    /**
     * 备注
     */
    private String remark;
    /**
     * 学号工号
     */
    @TableField("stuwork_number")
    private String stuworkNumber;
    /**
     * 所属图书馆id
     */
    @TableField("library_id")
    private Long libraryId;
    /**
     * 预付款
     */
    @TableField("pre_payment")
    private Double prePayment;
    /**
     * 欠款
     */
    private Double arrear;
    /**
     * 读者图片
     */
    private String pic;
    /**
     * 启用日期
     */
    @TableField("start_date")
    private Date startDate;
    /**
     * 终止日期
     */
    @TableField("end_date")
    private Date endDate;
    /**
     * 办证时间
     */
    @TableField("create_date")
    private Date createDate;


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

    public Integer getReaderType() {
        return readerType;
    }

    public void setReaderType(Integer readerType) {
        this.readerType = readerType;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Long getClasses() {
        return classes;
    }

    public void setClasses(Long classes) {
        this.classes = classes;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStuworkNumber() {
        return stuworkNumber;
    }

    public void setStuworkNumber(String stuworkNumber) {
        this.stuworkNumber = stuworkNumber;
    }

    public Long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(Long libraryId) {
        this.libraryId = libraryId;
    }

    public Double getPrePayment() {
        return prePayment;
    }

    public void setPrePayment(Double prePayment) {
        this.prePayment = prePayment;
    }

    public Double getArrear() {
        return arrear;
    }

    public void setArrear(Double arrear) {
        this.arrear = arrear;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
        ", readerType=" + readerType +
        ", grade=" + grade +
        ", classes=" + classes +
        ", remark=" + remark +
        ", stuworkNumber=" + stuworkNumber +
        ", libraryId=" + libraryId +
        ", prePayment=" + prePayment +
        ", arrear=" + arrear +
        ", pic=" + pic +
        ", startDate=" + startDate +
        ", endDate=" + endDate +
        ", createDate=" + createDate +
        "}";
    }
}
