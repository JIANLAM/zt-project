package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.Holding;
import com.szcti.lcloud.catalog.entity.HoldingCriteria;
import com.szcti.lcloud.catalog.entity.vo.HoldingVO;
import com.szcti.lcloud.catalog.entity.vo.LableVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HoldingService {
    int deleteByPrimaryKey(Long id);
    int insert(Holding record);
    List<Holding> selectByExample(HoldingCriteria example);
    Holding selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Holding record);
    List<HoldingVO> queryPage(HoldingVO vo);
    LableVO getMaxBarcode(LableVO lableVO);
    List<HoldingVO> getPrintLabel(LableVO lableVO);
    LableVO queryCurrBarcode(LableVO lableVO);
    /**
     * 
     * @描述：查询指定条码和图书馆id馆藏信息
     * @作者：tianbw
     * @时间：2018年8月25日 下午2:47:41
     * @param vo
     * @return
     */
    List<HoldingVO> queryHolding(HoldingVO vo);
}