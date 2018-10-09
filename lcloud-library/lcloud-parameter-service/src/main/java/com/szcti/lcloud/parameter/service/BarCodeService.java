package com.szcti.lcloud.parameter.service;

import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;

/**
 * @Title: 条码分区 Service
 * @Description: 条码分区参数 的Service
 * @author: wangsiyi
 * @date: 2018/7/26 11:04
 */
public interface BarCodeService {

    /**
     * 根据主键删除一条 或多条 条码分区参数数据记录
     * @param ids
     */
    void delBarCodeInfo(String ids);

    /**
     * 新增   或修改 一条条码分区参数 数据记录
     * @param   barCodeVO
     * @return  Integer
     */
    Integer save(BarCodeVO barCodeVO);

    /**
     * 新增初始化    条码区号 最大值加1 默认为1
     * @param
     * @return  Integer
     */
    Integer maxBarcodeNumber(Long libraryId);

}
