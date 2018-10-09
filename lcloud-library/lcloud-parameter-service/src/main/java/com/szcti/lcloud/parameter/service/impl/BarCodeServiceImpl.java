package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.parameter.entity.LibdataParaEntity;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import com.szcti.lcloud.parameter.repository.BarCodeDao;
import com.szcti.lcloud.parameter.service.BarCodeService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 条码分区 Service
 * @Description: 条码分区参数 的Service
 * @author: wangsiyi
 * @date: 2018/7/26 11:04
 */

@Service
public class BarCodeServiceImpl implements BarCodeService {

    @Autowired
    private BarCodeDao barCodeDao;

    /**
     * 根据主键删除一条 或多条 条码分区参数数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delBarCodeInfo(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        barCodeDao.delBarCodeInfo(idArray);
    }

    /**
     * 新增   或修改 一条条码分区参数 数据记录
     * @param   barCodeVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(BarCodeVO barCodeVO){
        //输入的起止条码号  终止条码号   不能为负数
        boolean existMinus = barCodeVO.getStartBarcode() < 0 || barCodeVO.getStopBarcode() < 0;
        //在同一图书馆    有相同的条码前缀    输入的起止条码号 和终止条码 不能小于已经存在的
        boolean existBarcodeNub = barCodeDao.existsBarcodeNumber(barCodeVO.getPrefix(),barCodeVO.getStartBarcode(),barCodeVO.getStopBarcode(),barCodeVO.getId(),barCodeVO.getLibraryId()) > 0;
        //输入的起止条码 不能大于输入的终止条码
        boolean existGreaterThan = barCodeVO.getStartBarcode() > barCodeVO.getStopBarcode();
        //输入条码号位数  必须小于或等于条码总位数
        boolean slopOver = barCodeVO.getStopBarcode().toString().length() > barCodeVO.getTotalBit();

        if(barCodeDao.existsBarcodeClassifyNub(barCodeVO.getBarcodeNumber(),barCodeVO.getId(),barCodeVO.getLibraryId()) > 0){
            //判断是否已经存在条码分区号 存在返回1 不存在返回0
            return 1;
        }else if(existMinus || existBarcodeNub || existGreaterThan || slopOver) {
            //满足四种错误    任意一种    数据不规范   跳出方法
            return 2;
        }else{

            if(barCodeVO != null && barCodeVO.getId()!= null) {
                barCodeDao.updateBarCodeInfo(barCodeVO);
                return 0;
            }else {
                Long barCodeId = IdGen.randomLong();
                barCodeVO.setId(barCodeId);
                barCodeVO.setCreateTime(DateUtils.getDateTime());
                barCodeDao.insertBarCodeInfo(barCodeVO);
                return 0;
            }

        }
    }

    /**
     * 新增初始化    条码区号 最大值加1 默认为1
     * @param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer maxBarcodeNumber(Long libraryId){
        if(barCodeDao.maxBarcodeNumber(libraryId) == null ){
            return 1;
        }
        return Integer.valueOf(Integer.valueOf(barCodeDao.maxBarcodeNumber(libraryId))+1);
    }

}
