package com.szcti.lcloud.share.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.share.entity.vo.ReportVO;
import com.szcti.lcloud.share.entity.vo.ShareVO;
import com.szcti.lcloud.share.repository.ShareDao;
import com.szcti.lcloud.share.service.ShareService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 阅读分享处理
 * @Description: 阅读分享处理
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class ShareServiceImpl implements ShareService {

    @Autowired
    private ShareDao shareDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull Long id){
        shareDao.deleteShare(id);
        shareDao.deleteAgree(id,null);
        shareDao.deleteComment(id,null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveAgree(@NonNull Long shareId,@NonNull Long readerId){
        //已点赞过的不能重复点赞
        List<ShareVO> list = shareDao.findAgree(shareId,readerId);
        if(list!=null&&list.size()>0){
            return -1;
        }else{
            shareDao.insertAgree(shareId,readerId, DateUtils.getDateTime());
            List<ShareVO> list2 = shareDao.findAgree(shareId,null);
            return list2.size();
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer deleteAgree(@NonNull Long shareId,@NonNull Long readerId){
        shareDao.deleteAgree(shareId,readerId);
        List<ShareVO> list2 = shareDao.findAgree(shareId,null);
        if(list2!=null&&list2.size()>0){
            return list2.size();
        }else{
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean report(ReportVO report){
        //已点举报的不能重复举报
        List<ReportVO> list = shareDao.findReport(report.getShareId(),report.getReaderId());
        if(list!=null&&list.size()>0){
            return false;
        }else{
            report.setId(IdGen.randomLong());
            report.setCreateTime(DateUtils.getDateTime());
            shareDao.insertReport(report);
            return true;
        }
    }

}
