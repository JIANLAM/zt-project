package com.szcti.lcloud.notice.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.notice.entity.NoticeReadEntity;
import com.szcti.lcloud.notice.entity.vo.NoticeVO;
import com.szcti.lcloud.notice.repository.NoticeDao;
import com.szcti.lcloud.notice.service.NoticeService;
import lombok.NonNull;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeDao noticeDao;


    /**
     * 将通知信息的未读变更为已读
     * @param noticeReadId
     * @return null
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readNotice(@NonNull Long noticeReadId){
        noticeDao.readNotice(noticeReadId, DateUtils.getDateTime());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(NoticeVO noticeVO){
        if(noticeVO!=null&&noticeVO.getId()!=null){
            Long noticeInfoId = noticeVO.getId();
            noticeDao.deleteNoticeReadByInfo(noticeInfoId);
            List<Object> userIds = noticeVO.getUserIds();
            //保存发送记录(发送给的用户ID)
            saveNoticeRead(userIds,noticeInfoId);
            noticeDao.updateNoticeInfo(noticeVO);
        }else{
            Long noticeInfoId = IdGen.randomLong();
            noticeVO.setId(noticeInfoId);
          /*  noticeVO.setCreateBy(new Long(1));//后期改为当前操作用户ID*/
            noticeVO.setCreateTime(DateUtils.getDateTime());
            if(noticeVO.getStatus() == 1){
                noticeVO.setSendTime(DateUtils.getDateTime());
            }
            //保存发送记录(发送给的用户ID)
            List<Object> userIds = noticeVO.getUserIds();
            saveNoticeRead(userIds,noticeInfoId);
            noticeDao.insertNoticeInfo(noticeVO);
        }
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendNotice(Long noticeInfoId){
        noticeDao.sendNotice(noticeInfoId,DateUtils.getDateTime());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSendNotice(Long id){
        noticeDao.deleteNoticeInfo(id);
        noticeDao.deleteNoticeReadByInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMyNotice(String id){
        String [] collectionIdStr = id.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        noticeDao.deleteNoticeReadById(idArray);
    }

    /**
     * 保存通知发送记录
     * @param userIds
     * @param noticeInfoId
     */
    public void saveNoticeRead(List<Object> userIds,Long noticeInfoId){
        NoticeReadEntity bean;
        List<NoticeReadEntity> noticeReadList = new ArrayList<>();
        for (Object userId:userIds) {
            bean = new NoticeReadEntity();
            bean.setId(IdGen.randomLong());
            bean.setNoticeInfoId(noticeInfoId);
            bean.setReadStatus(0);
            bean.setUserId(Long.valueOf((userId.toString())));
            noticeReadList.add(bean);
        }
        noticeDao.insertNoticeRead(noticeReadList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<NoticeVO>  findSendNotices(NoticeVO noticeVO){
        List<NoticeVO> list = noticeDao.findSendNotices(noticeVO);
        if(list!=null&&list.size()>0){
            List<HashMap> users;
            for (NoticeVO bean:list) {
                users = noticeDao.findSendUsers(bean.getId());
                List<Object> userIds = new ArrayList<>();
                if(users!=null&&users.size()>0){
                    for (HashMap map:users) {
                        userIds.add(map.get("userId"));
                    }
                }
                bean.setUserIds(userIds);

            }
        }

        return list;
    }

}
