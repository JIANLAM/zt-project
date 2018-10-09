package com.szcti.lcloud.newbook.service;

import com.szcti.lcloud.newbook.entity.vo.NewBookVO;

import java.util.List;

/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
public interface NewBookService {

    List<NewBookVO> queryPage(NewBookVO newBookVO);
}

