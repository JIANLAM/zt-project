package com.szcti.lcloud.holding.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.holding.entity.vo.BookCopyVO;
import com.szcti.lcloud.holding.entity.vo.OpLogVO;
import com.szcti.lcloud.holding.repository.HoldingDao;
import com.szcti.lcloud.holding.service.HoldingService;
import com.szcti.lcloud.holding.service.OpLogService;
import lombok.NonNull;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/7/26 8:53
 */
@Service
public class HoldingServiceImpl implements HoldingService {

    @Autowired
    private HoldingDao holdingDao;

    @Autowired
    private OpLogService opLogService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer delete(@NonNull String ids,@NonNull Long userId,@NonNull Long libId,@NonNull String ip){
        String []collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        //操作日志存入数据库
        OpLogVO opLogVO = new OpLogVO();
        opLogVO.setUserId(userId);
        opLogVO.setLibraryId(libId);
        opLogVO.setIp(ip);
        opLogService.opDelete(idArray,opLogVO);

        return holdingDao.delete(idArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer update(BookCopyVO bookCopyVO, @NonNull String ids,@NonNull Long userId,@NonNull Long libId,@NonNull String ip){
        String []collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);

        //操作日志存入数据库
        OpLogVO opLogVO = new OpLogVO();
        opLogVO.setUserId(userId);
        opLogVO.setLibraryId(libId);
        opLogVO.setIp(ip);
        opLogService.opUpdate(idArray,opLogVO);
        if(bookCopyVO==null){
            return 0;
        }else{
            if(StringUtils.isNotEmpty(bookCopyVO.getReaderCardNumber())||StringUtils.isNotEmpty(bookCopyVO.getLendTime())||StringUtils.isNotEmpty(bookCopyVO.getDueBackTime())){
                BookCopyVO lendInfo = holdingDao.getLendInfo(idArray[0]);
                HashMap<String,Long> map = holdingDao.getReaderIdByCard(bookCopyVO.getReaderCardNumber());
                if(map==null){
                    return 0;
                }
                if(bookCopyVO.getStatus()!=1){
                    Long readerId = map.get("readerId");
                    holdingDao.deleteLend(readerId,idArray);
                }else if(lendInfo!=null){
                    holdingDao.updateLend(lendInfo,idArray);
                }else{
                    bookCopyVO.setId(idArray[0]);
                    bookCopyVO.setLendId(IdGen.randomLong());
                    bookCopyVO.setReaderId(map.get("readerId"));
                    bookCopyVO.setUserId(map.get("userId"));
                    holdingDao.insertLend(bookCopyVO);
                }
            }
            return holdingDao.update(bookCopyVO,idArray);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BookCopyVO get(Long id){
        BookCopyVO bookCopyVO = holdingDao.get(id);
        //1为在借中的书籍
        if(bookCopyVO.getStatus() == 1){
            BookCopyVO lendInfo = holdingDao.getLendInfo(id);
            if(lendInfo!=null){
                bookCopyVO.setUserName(lendInfo.getUserName());
                bookCopyVO.setLendTime(lendInfo.getLendTime());
                bookCopyVO.setDueBackTime(lendInfo.getDueBackTime());
                bookCopyVO.setReaderCardNumber(lendInfo.getReaderCardNumber());
            }
        }
        return bookCopyVO;
    }

}
