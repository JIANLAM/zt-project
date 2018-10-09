package com.szcti.lcloud.book.service.impl;
import com.szcti.lcloud.book.entity.vo.BookVO;
import com.szcti.lcloud.book.repository.LibBookDao;
import com.szcti.lcloud.book.service.LibBookService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/30 8:53
 */
@Service
public class LibBookServiceImpl implements LibBookService {

    @Autowired
    private LibBookDao groomBuyDao;


}
