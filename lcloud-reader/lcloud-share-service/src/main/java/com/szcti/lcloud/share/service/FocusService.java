package com.szcti.lcloud.share.service;


/**
 * @Title: 粉丝关注Service
 * @Description: 处理粉丝关注的Service
 * @author: fengda
 * @date: 2018/6/29 8:50
 */
public interface FocusService {

    /**
     * 关注
     * @param readerId
     * @param currentReaderId
     * @return
     */
    boolean save(Long currentReaderId, Long readerId);


}
