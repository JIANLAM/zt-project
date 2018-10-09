package com.szcti.lcloud.holding.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.holding.entity.vo.BookCopyVO;
import com.szcti.lcloud.holding.entity.vo.OpLogVO;
import com.szcti.lcloud.holding.repository.HoldingDao;
import com.szcti.lcloud.holding.repository.OpLogDao;
import com.szcti.lcloud.holding.service.OpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/7/26 8:53
 */
@Service
public class OpLogServiceImpl implements OpLogService {

    @Autowired
    private OpLogDao opLogDao;

    @Autowired
    private HoldingDao holdingDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void opDelete(Long[] idArray,OpLogVO opLogVO){
        List<BookCopyVO> list = holdingDao.findCopysByIds(idArray);
        String opContent = "";
        for (BookCopyVO bookCopy:list) {
            opContent += bookCopy.getBarCode()+"("+bookCopy.getTitle()+")、";
        }
        opContent = opContent+"共"+list.size()+"本馆藏副本被批量删除";
        opLogVO.setOpContent(opContent);
        opLogVO.setOperationType("删除");
        save(opLogVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void opUpdate(Long[] idArray,OpLogVO opLogVO){
        List<BookCopyVO> list = holdingDao.findCopysByIds(idArray);
        String opContent = "";
        for (BookCopyVO bookCopy:list) {
            opContent += bookCopy.getBarCode()+"("+bookCopy.getTitle()+")、";
        }
        opContent = opContent+"共"+list.size()+"本馆藏副本被批量修改";
        opLogVO.setOpContent(opContent);
        opLogVO.setOperationType("修改");
        save(opLogVO);
    }

    @Transactional(rollbackFor = Exception.class)
    public void save(OpLogVO opLogVO){
        opLogVO.setId(IdGen.randomLong());
        opLogVO.setModuleId(8);
        opLogVO.setModuleName("馆藏管理");
        opLogDao.insert(opLogVO);
    }

}
