package com.szcti.lcloud.notice.repository;

import com.szcti.lcloud.notice.entity.NoticeReadEntity;
import com.szcti.lcloud.notice.entity.vo.NoticeVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;


/**
 * @Title: 通知信息DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface NoticeDao {
    /**
     * 按条件查找用户收到的通知信息
     * @param noticeVO
     * @return List<NoticeVO>
     */
    List<NoticeVO> findMyNotices(NoticeVO noticeVO);

    /**
     * 根据ID查询一条数据
     * @param id
     * @return
     */
    NoticeVO getNoticeById(Long id);

    /**
     * 按条件查找用户发送的通知信息
     * @param noticeVO
     * @return List<NoticeVO>
     */
    List<NoticeVO> findSendNotices(NoticeVO noticeVO);

    /**
     * 查询通知发送给的所有用户信息
     * @param noticeInfoId
     * @return
     */
    List<HashMap> findSendUsers(Long noticeInfoId);

    /**
     * 将通知信息的"未读"变更为"已读"
     * @param noticeReadId
     * @param readTime
     * @return null
     */
    void readNotice(@Param("noticeReadId") Long noticeReadId,@Param("readTime") String readTime);

    /**
     * 发送通知，变更草稿状态为发送状态
     * @param noticeInfoId
     */
    void sendNotice(@Param("noticeInfoId") Long noticeInfoId,@Param("sendTime") String sendTime);

    /**
     * 修改一条发布的通知信息
     * @param noticeVO
     */
    void updateNoticeInfo(NoticeVO noticeVO);

    /**
     * 插入一条发布的通知信息
     * @param noticeVO
     */
    void insertNoticeInfo(NoticeVO noticeVO);

    /**
     * 插入通知发送给的用户
     * @param list
     */
    void insertNoticeRead(List<NoticeReadEntity> list);

    /**
     * 根据主键删除发布的通知
     * @param id
     */
    void deleteNoticeInfo(Long id);

    /**
     * 根据主键删除接收到的通知
     * @param idArray
     */
    void deleteNoticeReadById(@Param("idArray") Long[] idArray);

    /**
     * 根据通知信息的主键删除发送出去的记录
     * @param noticeInfoId
     */
    void deleteNoticeReadByInfo(Long noticeInfoId);

    /**
     * 获取通知发送给的用户数目
     * @param noticeInfoId 通知信息的主键
     * @param readStatus 可为空
     * @return
     */
    Integer getReadCount(@Param("noticeInfoId") Long noticeInfoId,@Param("readStatus") Integer readStatus);
}
