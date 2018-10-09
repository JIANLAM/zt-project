package com.szcti.lcloud.helplendback.service;


import com.szcti.lcloud.helplendback.entity.vo.HelpLendBackVO;

import java.util.List;

/**
 * @Title: 代借还Service
 * @Description: 处理代借还信息的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface HelpLendBackService {

    /**
     * 取消代借
     * @param helpLendBackId
     */
    String cancelHelpLend(Long helpLendBackId);


}
