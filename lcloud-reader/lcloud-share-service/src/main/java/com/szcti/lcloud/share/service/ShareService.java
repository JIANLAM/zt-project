package com.szcti.lcloud.share.service;


import com.szcti.lcloud.share.entity.vo.ReportVO;
import com.szcti.lcloud.share.entity.vo.ShareVO;

/**
 * @Title: 阅读分享Service
 * @Description: 处理阅读分享的Service
 * @author: fengda
 * @date: 2018/6/29 8:50
 */
public interface ShareService {


    /**
     * 删除一条阅读分享
     * @param id
     */
    void delete(Long id);

    /**
     * 点赞
     * @param shareId
     * @param readerId
     * @return Integer
     */
    Integer saveAgree(Long shareId,Long readerId);

    /**
     * 取消点赞
     * @param shareId
     * @param readerId
     * @return Integer
     */
    Integer deleteAgree(Long shareId,Long readerId);

    /**
     * 举报
     * @param report
     * @return
     */
    boolean report(ReportVO report);


}
