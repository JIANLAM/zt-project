package com.szcti.lcloud.parameter.repository;
import com.szcti.lcloud.parameter.entity.vo.BarCodeVO;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @Title: 条码分区 Dao
 * @Description: 条码分区参数 Dao
 * @author: wangsiyi
 * @date: 2018/7/26 11:04
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface BarCodeDao {

    /**
     * 查询条码分区参数List集合
     * @param
     * @return List<BarCodeVO>
     */
    List<BarCodeVO> findBarCodeList(@Param("libraryId") Long libraryId , @Param("currUser") Long currUser);

    /**
     * 根据ID 查询一条条码分区参数记录
     * @param   id
     * @return LibdataParaEntity
     */
    BarCodeVO getBarCode(@Param("id") Long id);

    /**
     * 根据主键删除一条 或多条 条码分区参数数据记录
     * @param idArray
     */
    void delBarCodeInfo(@Param("idArray") Long[] idArray);

    /**
     * 新增一条条码分区参数 数据记录
     * @param   barCodeVO
     * @return
     */
    void insertBarCodeInfo(BarCodeVO barCodeVO);

    /**
     * 修改一条条码分区参数 数据记录
     * @param   barCodeVO
     * @return
     */
    void updateBarCodeInfo(BarCodeVO barCodeVO);

    /**
     * 新增初始化    条码区号 最大值加1 默认为1
     * @param
     * @return String
     */
    String maxBarcodeNumber(Long libraryId);

    /**
     * 新增或修改 保存 判断是否已经存在条码分区号 存在返回1 不存在返回0
     * @param
     * @return Integer
     */
    Integer existsBarcodeClassifyNub(@Param("barcodeNumber") Long barcodeNumber,@Param("id") Long id , @Param("libraryId") Long libraryId);

    /**
     * 新增或修改 保存 判断是否已经存在条码号 存在返回1 不存在返回0
     * @param
     * @return Integer
     */
    Integer existsBarcodeNumber(@Param("prefix") String prefix,@Param("startBarcode") Long startBarcode,@Param("stopBarcode") Long stopBarcode,@Param("id") Long id, @Param("libraryId") Long libraryId);

    /**
     * 当前登录的图书馆 的用户
     * @param libraryId
     * @return Map
     */
    List<Map> cuurLibraryUser(@Param("libraryId") Long libraryId);

}
