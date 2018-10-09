package com.szcti.lcloud.datacount.service;


import com.szcti.lcloud.datacount.entity.vo.CirculationVO;

import java.util.List;
import java.util.Map;

/**
 * @Title: 馆藏统计Service
 * @Description: 馆藏统计的Service
 * @author: wangsiyi
 * @date: 2018/8/9 9:27
 */
public interface CollectionCountService {

    /**
     * 馆藏分类统计
     * @param circulationVO
     * @return String
     */
    Map<String,List> collectionTypeCount(CirculationVO circulationVO);

    /**
     * 导出统计数据
     * @param circulationVO
     * @return String
     */
    String exports(CirculationVO circulationVO);


}
