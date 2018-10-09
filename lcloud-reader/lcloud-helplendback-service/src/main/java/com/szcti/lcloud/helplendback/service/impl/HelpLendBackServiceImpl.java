package com.szcti.lcloud.helplendback.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.helplendback.entity.vo.HelpLendBackVO;
import com.szcti.lcloud.helplendback.repository.HelpLendBackDao;
import com.szcti.lcloud.helplendback.service.HelpLendBackService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class HelpLendBackServiceImpl implements HelpLendBackService {

    @Autowired
    private HelpLendBackDao helpLendBackDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancelHelpLend(@NonNull Long helpLendBackId){
        HelpLendBackVO helpLendBackVO = helpLendBackDao.get(helpLendBackId);
        if(helpLendBackVO.getStatus()==0){
            helpLendBackDao.changeStatus(helpLendBackId,4);
            return "success";
        }else{
            return "cannot";
        }

    }

}
