package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.BasicParamEntity;
import com.szcti.lcloud.parameter.entity.ReaderCardEntity;
import com.szcti.lcloud.parameter.entity.vo.OutBoxVO;

/**
 * @Title: 发送邮箱设置 Service
 * @Description: 发送邮箱设置 Service
 * @author: wangsiyi
 * @date: 2018/8/16 11:15
 */
public interface OutBoxService {
    /**
     * 根据主键删除一条 或多条 发送邮箱设置记录
     * @param ids
     */
    void delOutbox(String ids);

    /**
     * 新增   或修改 发送邮箱设置 数据记录
     * @param   outBoxVO
     * @return Integer
     */
    void save(OutBoxVO outBoxVO);

}
