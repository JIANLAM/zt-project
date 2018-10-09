package com.szcti.lcloud.user.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by linzj on 2018/9/10
 * 朗读亭音频类
 */
@TableName("t_audio")
public class ReadingAudio extends Model<ReadingAudio> {
    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("member_id")
    private Long memberId;
    @TableField("device_id")
    private Long deviceId;
    private String mac;
    @TableField("doc_id")
    private Long docId;
    @TableField("interest_doc_id")
    private Long interestDocId;
    @TableField("file_name")
    private String fileName;
    @TableField("server_file_name")
    private String serverFileName;
    @TableField("server_file_name_full")
    private String serverFileNameFull;
    @TableField("local_file_name")
    private String localFileName;
    @TableField("works_name")
    private String worksName;
    @TableField("reader_no")
    private String readerNo;
    @TableField("reader_name")
    private String readerName;
    @TableField("cust_name")
    private String custName;
    @TableField("activity_id")
    private Long activityId;
    @TableField("record_time")
    private String recordTime;
    @TableField("file_size")
    private Double fileSize;
    private Double duration;
    @TableField("image_file1")
    private String imageFile1;
    @TableField("image_file2")
    private String imageFile2;
    @TableField("image_file3")
    private String imageFile3;
    @TableField("make_time")
    private String makeTime;
    private Integer share;
    @TableField("is_choiceness")
    private String isChoiceness;
    @TableField("wx_is_show")
    private String wxIsShow;
    @TableField("cover_image")
    private String coverImage;
    private String message;
    private String ranking;
    @TableField("order_seq")
    private Integer orderSeq;
    @TableField("create_user")
    private String createUser;
    @TableField("create_date")
    private String createDate;
    @TableField("last_modify_date")
    private String lastModifyDate;
    @TableField("last_modify_user")
    private String lastModifyUser;
    @TableField("deleted_flag")
    private String deletedFlag;
    @TableField("audit_status")
    private String auditStatus;
    private String name;
    @TableField("member_name")
    private String memberName;
    private String headimgul;
    private String city;
    @TableField("install_address")
    private String installAddress;
    @TableField("customer_id")
    private Long customerId;
    @TableField("customer_name")
    private String customerName;
    @TableField("device_name")
    private String deviceName;
    @TableField("author")
    private String author;
    @TableField("share_count")
    private Integer shareCount;
    @TableField("comment_count")
    private Integer commentCount;
    @TableField("listen_count")
    private Integer listenCount;
    @TableField("praise_count")
    private Integer praiseCount;
    @TableField("votec_ount")
    private Integer votecOunt;
    @TableField("listen_count20")
    private Integer listenCount20;
    @TableField("praise_count20")
    private Integer praiseCount20;
    @TableField("vote_count20")
    private Integer voteCount20;
    @TableField("comment_count20")
    private Integer commentCount20;
    private Integer neworderseq;
    @TableField("raw_json")
    private String rawJson;
    @TableField("sync_date")
    private Date syncDate;

    @Override
    public String toString() {
        return "ReadingAudio{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", deviceId=" + deviceId +
                ", mac='" + mac + '\'' +
                ", docId=" + docId +
                ", interestDocId=" + interestDocId +
                ", fileName='" + fileName + '\'' +
                ", serverFileName='" + serverFileName + '\'' +
                ", serverFileNameFull='" + serverFileNameFull + '\'' +
                ", localFileName='" + localFileName + '\'' +
                ", worksName='" + worksName + '\'' +
                ", readerNo='" + readerNo + '\'' +
                ", readerName='" + readerName + '\'' +
                ", custName='" + custName + '\'' +
                ", activityId=" + activityId +
                ", recordTime='" + recordTime + '\'' +
                ", fileSize=" + fileSize +
                ", duration=" + duration +
                ", imageFile1='" + imageFile1 + '\'' +
                ", imageFile2='" + imageFile2 + '\'' +
                ", imageFile3='" + imageFile3 + '\'' +
                ", makeTime='" + makeTime + '\'' +
                ", share=" + share +
                ", isChoiceness='" + isChoiceness + '\'' +
                ", wxIsShow='" + wxIsShow + '\'' +
                ", coverImage='" + coverImage + '\'' +
                ", message='" + message + '\'' +
                ", ranking='" + ranking + '\'' +
                ", orderSeq=" + orderSeq +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                ", lastModifyDate='" + lastModifyDate + '\'' +
                ", lastModifyUser='" + lastModifyUser + '\'' +
                ", deletedFlag='" + deletedFlag + '\'' +
                ", auditStatus='" + auditStatus + '\'' +
                ", name='" + name + '\'' +
                ", memberName='" + memberName + '\'' +
                ", headimgul='" + headimgul + '\'' +
                ", city='" + city + '\'' +
                ", installAddress='" + installAddress + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", author='" + author + '\'' +
                ", shareCount=" + shareCount +
                ", commentCount=" + commentCount +
                ", listenCount=" + listenCount +
                ", praiseCount=" + praiseCount +
                ", votecOunt=" + votecOunt +
                ", listenCount20=" + listenCount20 +
                ", praiseCount20=" + praiseCount20 +
                ", voteCount20=" + voteCount20 +
                ", commentCount20=" + commentCount20 +
                ", neworderseq=" + neworderseq +
                ", rawJson='" + rawJson + '\'' +
                ", syncDate=" + syncDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public Long getInterestDocId() {
        return interestDocId;
    }

    public void setInterestDocId(Long interestDocId) {
        this.interestDocId = interestDocId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getServerFileName() {
        return serverFileName;
    }

    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName;
    }

    public String getServerFileNameFull() {
        return serverFileNameFull;
    }

    public void setServerFileNameFull(String serverFileNameFull) {
        this.serverFileNameFull = serverFileNameFull;
    }

    public String getLocalFileName() {
        return localFileName;
    }

    public void setLocalFileName(String localFileName) {
        this.localFileName = localFileName;
    }

    public String getWorksName() {
        return worksName;
    }

    public void setWorksName(String worksName) {
        this.worksName = worksName;
    }

    public String getReaderNo() {
        return readerNo;
    }

    public void setReaderNo(String readerNo) {
        this.readerNo = readerNo;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(String recordTime) {
        this.recordTime = recordTime;
    }

    public Double getFileSize() {
        return fileSize;
    }

    public void setFileSize(Double fileSize) {
        this.fileSize = fileSize;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getImageFile1() {
        return imageFile1;
    }

    public void setImageFile1(String imageFile1) {
        this.imageFile1 = imageFile1;
    }

    public String getImageFile2() {
        return imageFile2;
    }

    public void setImageFile2(String imageFile2) {
        this.imageFile2 = imageFile2;
    }

    public String getImageFile3() {
        return imageFile3;
    }

    public void setImageFile3(String imageFile3) {
        this.imageFile3 = imageFile3;
    }

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public String getIsChoiceness() {
        return isChoiceness;
    }

    public void setIsChoiceness(String isChoiceness) {
        this.isChoiceness = isChoiceness;
    }

    public String getWxIsShow() {
        return wxIsShow;
    }

    public void setWxIsShow(String wxIsShow) {
        this.wxIsShow = wxIsShow;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public Integer getOrderSeq() {
        return orderSeq;
    }

    public void setOrderSeq(Integer orderSeq) {
        this.orderSeq = orderSeq;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getLastModifyUser() {
        return lastModifyUser;
    }

    public void setLastModifyUser(String lastModifyUser) {
        this.lastModifyUser = lastModifyUser;
    }

    public String getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(String deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getHeadimgul() {
        return headimgul;
    }

    public void setHeadimgul(String headimgul) {
        this.headimgul = headimgul;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getInstallAddress() {
        return installAddress;
    }

    public void setInstallAddress(String installAddress) {
        this.installAddress = installAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getListenCount() {
        return listenCount;
    }

    public void setListenCount(Integer listenCount) {
        this.listenCount = listenCount;
    }

    public Integer getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(Integer praiseCount) {
        this.praiseCount = praiseCount;
    }

    public Integer getVotecOunt() {
        return votecOunt;
    }

    public void setVotecOunt(Integer votecOunt) {
        this.votecOunt = votecOunt;
    }

    public Integer getListenCount20() {
        return listenCount20;
    }

    public void setListenCount20(Integer listenCount20) {
        this.listenCount20 = listenCount20;
    }

    public Integer getPraiseCount20() {
        return praiseCount20;
    }

    public void setPraiseCount20(Integer praiseCount20) {
        this.praiseCount20 = praiseCount20;
    }

    public Integer getVoteCount20() {
        return voteCount20;
    }

    public void setVoteCount20(Integer voteCount20) {
        this.voteCount20 = voteCount20;
    }

    public Integer getCommentCount20() {
        return commentCount20;
    }

    public void setCommentCount20(Integer commentCount20) {
        this.commentCount20 = commentCount20;
    }

    public Integer getNeworderseq() {
        return neworderseq;
    }

    public void setNeworderseq(Integer neworderseq) {
        this.neworderseq = neworderseq;
    }

    public String getRawJson() {
        return rawJson;
    }

    public void setRawJson(String rawJson) {
        this.rawJson = rawJson;
    }

    public Date getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(Date syncDate) {
        this.syncDate = syncDate;
    }


    @Override
    protected Serializable pkVal() {
            return this.id;
    }
}
