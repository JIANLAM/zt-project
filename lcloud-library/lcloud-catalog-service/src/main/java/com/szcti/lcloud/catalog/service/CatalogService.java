package com.szcti.lcloud.catalog.service;

import com.szcti.lcloud.catalog.entity.Catalog;
import com.szcti.lcloud.catalog.entity.CatalogCriteria;
import com.szcti.lcloud.catalog.entity.vo.CatalogVO;
import com.szcti.lcloud.catalog.entity.vo.LendBuyBookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CatalogService {

    int deleteByPrimaryKey(Long id);
    int insert(Catalog record);
    List<Catalog> selectByExample(CatalogCriteria example);
    Catalog selectByPrimaryKey(Long id);
    int updateByPrimaryKey(Catalog record);
    List<CatalogVO> queryPage(CatalogVO CatalogVO);
    CatalogVO getCatalogInfo(CatalogVO catalogVO);
    int insertGeneralCatalog(CatalogVO vo);
    List<LendBuyBookVO> queryLendBuyPage(LendBuyBookVO vo);
    CatalogVO getMarcCatalogInfo(CatalogVO catalogVO);
    int insertMarcGeneralCatalog(CatalogVO vo);
    int insertMarcAcceptCatalog(CatalogVO catalogVO);
    /**
     * 
     * @描述：删除holding 馆藏信息
     * @作者：tianbw
     * @时间：2018年8月27日 下午2:46:03
     * @param vo
     * @return
     */
    int DeleteCatalog(CatalogVO vo);
    
    /**
     * 
     * @描述：获取编目书籍详情信息
     * @作者：tianbw
     * @时间：2018年8月25日 下午4:23:33
     * @param catalogVO
     * @return
     */
    CatalogVO queryCatalogInfo(CatalogVO catalogVO);
    
    /**
     * 
     * @描述：新增编目馆藏信息
     * @作者：tianbw
     * @时间：2018年8月27日 下午2:46:08
     * @param vo
     * @return
     */
    int insertCatalogInfo(CatalogVO vo);
    
    /**
     * 
     * @描述：修改编目馆藏信息
     * @作者：tianbw
     * @时间：2018年8月27日 下午3:37:40
     * @param vo
     * @return
     */
    int updateCatalog(CatalogVO vo);
    
}