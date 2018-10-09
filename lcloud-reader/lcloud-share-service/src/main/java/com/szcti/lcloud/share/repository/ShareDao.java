package com.szcti.lcloud.share.repository;

import com.szcti.lcloud.share.entity.vo.CommentVO;
import com.szcti.lcloud.share.entity.vo.ReportVO;
import com.szcti.lcloud.share.entity.vo.ShareVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 阅读分享DAO
 * @Description: 处理阅读分享的操作
 * @author: fengda
 * @date: 2018/6/30 8:43
 */
@Mapper
public interface ShareDao {

    /**
     * 新增一条阅读分享
     * @param shareVO
     */
    void insertShare(ShareVO shareVO);

    /**
     * 查询阅读分享列表
     * @param shareVO
     * @return
     */
    List<ShareVO> findList(ShareVO shareVO);

    /**
     * 查询一条阅读分享的评论
     * @param commentVO
     * @return
     */
    List<CommentVO> findCommentList(CommentVO commentVO);

    /**
     * 查询一条阅读分享的举报信息
     * @param reportVO
     * @return
     */
    List<ReportVO> findReportList(ReportVO reportVO);

    /**
     * 跟住主键ID查询一条阅读分享
     * @param id
     * @param readerId
     * @return
     */
    ShareVO get(@Param("id")Long id,@Param("readerId")Long readerId);

    /**
     * 新增一条点赞数据
     * @param shareId
     * @param createTime
     * @param readerId
     */
    void insertAgree(@Param("shareId") Long shareId,@Param("readerId") Long readerId,@Param("createTime") String createTime);

    /**
     * 查询读者对某一条分享的点赞
     * @param shareId
     * @param readerId
     * @return
     */
    List<ShareVO> findAgree(@Param("shareId") Long shareId,@Param("readerId") Long readerId);

    /**
     * 查询读者对某一条分享的举报
     * @param shareId
     * @param readerId
     * @return
     */
    List<ReportVO> findReport(@Param("shareId") Long shareId,@Param("readerId") Long readerId);

    /**
     * 新增一条评论数据
     * @param comment
     */
    void insertComment(CommentVO comment);

    /**
     * 新增一条举报数据
     * @param report
     */
    void insertReport(ReportVO report);

    /**
     * 删除一条阅读分享
     * @param shareId
     */
    void deleteShare(Long shareId);

    /**
     * 删除点赞，readerId为空则表示删除该分享的所有点赞
     * @param shareId
     * @param readerId  可为null
     */
    void deleteAgree(@Param("shareId") Long shareId,@Param("readerId") Long readerId);

    /**
     * 删除评论，readerId为空则表示删除该分享的所有评论
     * @param shareId
     * @param readerId  可为null
     */
    void deleteComment(@Param("shareId") Long shareId,@Param("readerId") Long readerId);

    /**
     * 获取个人点赞数
     * @param readerId
     * @return
     */
    Integer getAgreeCount(Long readerId);


}
