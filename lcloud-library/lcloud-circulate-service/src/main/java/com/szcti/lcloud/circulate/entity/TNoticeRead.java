package com.szcti.lcloud.circulate.entity;

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
 * @since 2018-07-30
 */
@TableName("t_notice_read")
public class TNoticeRead extends Model<TNoticeRead> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 通知信息
     */
    @TableField("notice_info_id")
    private Long noticeInfoId;
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 读者状态
     */
    @TableField("read_status")
    private Integer readStatus;
    /**
     * 阅读时间
     */
    @TableField("read_time")
    private Date readTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNoticeInfoId() {
        return noticeInfoId;
    }

    public void setNoticeInfoId(Long noticeInfoId) {
        this.noticeInfoId = noticeInfoId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }

    public Date getReadTime() {
        return readTime;
    }

    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TNoticeRead{" +
        ", id=" + id +
        ", noticeInfoId=" + noticeInfoId +
        ", userId=" + userId +
        ", readStatus=" + readStatus +
        ", readTime=" + readTime +
        "}";
    }
}
