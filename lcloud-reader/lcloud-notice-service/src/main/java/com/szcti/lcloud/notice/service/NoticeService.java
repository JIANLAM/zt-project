package com.szcti.lcloud.notice.service;


import com.szcti.lcloud.notice.entity.vo.NoticeVO;

import java.util.List;


/**
 * @Title: 信息通知Service
 * @Description: 处理通知信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface NoticeService {


    /**
     * 将通知信息的未读变更为已读
     * @param noticeReadId
     * @return null
     */
    void readNotice(Long noticeReadId);

    /**
     * 新增并发送通知
     * @param noticeVO
     */
    void save(NoticeVO noticeVO);

    /**
     * 发送通知，变更草稿状态为发送状态
     * @param noticeInfoId,sendTime
     */
    void sendNotice(Long noticeInfoId);

    /**
     * 删除通知信息以及发送记录
     * @param id
     */
    void deleteSendNotice(Long id);

    /**
     * 删除收到的通知
     * @param id
     */
    void deleteMyNotice(String id);

    /**
     * 按条件查找用户发送的通知信息
     * @param noticeVO
     * @return List<NoticeVO>
     */
    List<NoticeVO>  findSendNotices(NoticeVO noticeVO);



}
