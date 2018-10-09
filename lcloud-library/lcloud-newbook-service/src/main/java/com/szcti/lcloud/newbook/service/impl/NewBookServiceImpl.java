package com.szcti.lcloud.newbook.service.impl;


import com.szcti.lcloud.newbook.entity.vo.NewBookVO;
import com.szcti.lcloud.newbook.repository.NewBookRepository;
import com.szcti.lcloud.newbook.service.NewBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("prebookService")
public class NewBookServiceImpl implements NewBookService {
    @Autowired
    private NewBookRepository newBookRepository;

    @Override
    public List<NewBookVO> queryPage(NewBookVO newBookVO) {

        return newBookRepository.queryPage(newBookVO);
    }
}
