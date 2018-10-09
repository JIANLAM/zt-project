package com.szcti.lcloud.datacount.service.impl;

import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.datacount.entity.vo.*;
import com.szcti.lcloud.datacount.repository.CollectionCountDao;
import com.szcti.lcloud.datacount.service.CollectionCountService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * @Title: 馆藏统计Service
 * @Description: 馆藏统计的Service
 * @author: wangsiyi
 * @date: 2018/8/9 9:27
 */
@Service
public class CollectionCountServiceImpl implements CollectionCountService {

    @Autowired
    private CollectionCountDao collectionCountDao;

    //最外层KEY
    private List keyList;

    //第二层KEY
    private List keyTwoList;


    /**
     * 馆藏分类统计
     * @param circulationVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String,List> collectionTypeCount(CirculationVO circulationVO){
        //馆藏地的数组
        String [] collectionIdStr = null;
        Long []collAdsIdArray = null;
        //流通类型的数组
        String [] circulationIdStr = null;
        Long []cirIdArray = null;
        //馆藏状态的数组
        String [] collStatusIdStr = null;
        Long []collStatusIdArray = null;
        //馆藏处理统计
        if(circulationVO.getOperationType() != null && !"".equals(circulationVO.getOperationType())){
            Object[] arr = opContentList(circulationVO.getOperationType(),circulationVO.getLibraryId(),circulationVO.getStartTime(),circulationVO.getEndTime(),circulationVO.getUserId());
            if(arr != null && arr.length > 0){
                circulationVO.setBarcodeArray(arr);
            }else{
                circulationVO.setBarcodeArray(null);
            }

        }
        //获得选择的馆藏地的数组
        if (circulationVO.getCollAdsTypeIds() != null){
            collectionIdStr = circulationVO.getCollAdsTypeIds().split(",");
            collAdsIdArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        }

        //获得选择的流通类型的数组
        if (circulationVO.getCirculationTypeIds() != null){
            circulationIdStr = circulationVO.getCirculationTypeIds().split(",");
            cirIdArray = (Long[]) ConvertUtils.convert(circulationIdStr,Long.class);
        }


        //获得选择的馆藏状态的数组
        if(circulationVO.getCollStatusIds() != null){
            collStatusIdStr = circulationVO.getCollStatusIds().split(",");
            collStatusIdArray = (Long[]) ConvertUtils.convert(collStatusIdStr,Long.class);
        }

        //馆藏状态 丢到条件里
        circulationVO.setIdArray(collStatusIdArray);

        //馆藏地 已经选择最外层集合
        Map<String,List> oneListMap = new HashMap(16);
        //第二层集合
        List<Map> twoList = new ArrayList<>();
        //头部行
        List topRow = new ArrayList();
        //头部行   map
        Map topRowMap = new HashMap(16);
        //拼凑 统计一行的数据
        Map row =  null;
        //存储 统计的键
        String readerTypeName = null;
        //存储 统计的数量 map值
        Float readerTypeNub = null;
        //如果 馆藏地点是列     图书流通类型是横
        if(circulationVO.getColumnType() == 0) {
            keyList = new ArrayList();
            for (int i = 0; i < collAdsIdArray.length; i++) {
                twoList = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    keyTwoList = new ArrayList(16);
                    row = new HashMap(16);
                    for (int j = 0; j < cirIdArray.length; j++) {
                        //组合横向行
                        infeedColumn(cirIdArray[j],topRowMap,collAdsIdArray[i],circulationVO,row,k,0,keyTwoList);
                    }
                    twoList.add(row);
                }
                //最外层的Map   键
                Map collMap = collectionCountDao.cirORcollRowType(collAdsIdArray[i]);

                //所有拼凑加入到最外层
                oneListMap.put(collMap.get("label").toString(), twoList);
                keyList.add(collMap.get("label").toString());
            }

            //拼凑 合计
            twoList = new ArrayList<>();
            for (int k = 0; k < 3; k++) {
                row = new HashMap(16);
                for (int j = 0; j < cirIdArray.length; j++) {
                    //头部行 流通类型
                    Map cirMap = collectionCountDao.cirORcollRowType(cirIdArray[j]);
                    //拼凑统计行 种数 册数 总金额数
                    totalColumn(k,circulationVO,row);
                    //馆藏地
                    circulationVO.setCollIdArray(collAdsIdArray);
                    //流通类型
                    Long[] circArray = {cirIdArray[j]};
                    circulationVO.setCircIdArray(circArray);
                    //返回的统计数量   map值
                    readerTypeNub = collectionCountDao.collectionTypeCount(circulationVO);
                    //map 键
                    readerTypeName = cirMap.get("value").toString();
                    //存错 一行里的一列
                    row.put(readerTypeName, readerTypeNub);
                }
                //合计行
                twoList.add(row);
            }
        }else{
            //      如果 图书流通类型是列     馆藏地点是横
            keyList = new ArrayList();
            for (int j = 0; j < cirIdArray.length; j++) {
                twoList = new ArrayList<>();
                for (int k = 0; k < 3; k++) {
                    keyTwoList = new ArrayList(16);
                    row = new HashMap(16);
                    for (int i = 0; i < collAdsIdArray.length; i++) {
                        //组合横向行
                        infeedColumn(cirIdArray[j],topRowMap,collAdsIdArray[i],circulationVO,row,k,1,keyTwoList);
                    }
                    twoList.add(row);
                }
                //最外层的Map   键
                Map collMap = collectionCountDao.cirORcollRowType(cirIdArray[j]);
                //所有拼凑加入到最外层
                oneListMap.put(collMap.get("label").toString(), twoList);
                keyList.add(collMap.get("label").toString());
            }

            //拼凑 合计
            twoList = new ArrayList<>();
            for (int k = 0; k < 3; k++) {
                row = new HashMap(16);
                for (int i = 0; i < collAdsIdArray.length; i++) {
                    //头部行 流通类型
                    Map cirMap = collectionCountDao.cirORcollRowType(collAdsIdArray[i]);
                    //拼凑统计行  种数 册数 总金额数
                    totalColumn(k,circulationVO,row);
                    //馆藏地
                    Long[] collArray = {collAdsIdArray[i]};
                    circulationVO.setCollIdArray(collArray);
                    //流通类型
                    circulationVO.setCircIdArray(cirIdArray);
                    //返回的统计数量   map值
                    readerTypeNub = collectionCountDao.collectionTypeCount(circulationVO);
                    //map 键
                    readerTypeName = cirMap.get("value").toString();
                    //存错 一行里的一列
                    row.put(readerTypeName, readerTypeNub);
                }
                //合计行
                twoList.add(row);
            }
        }

        //用集合装头部
        topRow.add(topRowMap);
        //将头部放到最外层
        oneListMap.put("topRow",topRow);
        //将合计 放到最外层
        oneListMap.put("合计",twoList);
        //把合计 加进导出集合里
        keyList.add("合计");
        return oneListMap;
    }

    /**
     *  组合横向行
     * @param cirIdArrayJ，topRowMap，collAdsIdArrayI，circulationVO，row，k ,columnType
     * @return  Map
     */
    private Map infeedColumn(Long cirIdArrayJ,Map topRowMap,Long collAdsIdArrayI,CirculationVO circulationVO,Map row,int k,Integer columnType,List keyTwoList){
        //存储头部
        Map cirMap = null;
        if(columnType == 0){
            //头部行 流通类型
            cirMap = collectionCountDao.cirORcollRowType(cirIdArrayJ);
        }else {
            //头部行 馆藏地
            cirMap = collectionCountDao.cirORcollRowType(collAdsIdArrayI);
        }

        //拼凑头部行
        topRowMap.put(cirMap.get("value"), cirMap.get("label"));
        //获得 第二层的 所有键
        keyTwoList.add(cirMap.get("value"));
        //拼凑统计行
        totalColumn(k,circulationVO,row);
        Long[] collArray = {collAdsIdArrayI};
        //馆藏地
        circulationVO.setCollIdArray(collArray);
        //流通类型
        Long[] circArray = {cirIdArrayJ};
        circulationVO.setCircIdArray(circArray);
        //返回的统计数量   map值
        Float readerTypeNub = collectionCountDao.collectionTypeCount(circulationVO);
        //map 键
        String readerTypeName = cirMap.get("value").toString();
        //存错 一行里的一列
        row.put(readerTypeName, readerTypeNub);
        return row;
    }

    /***
     * 种数 册数 总金额数
     * @param k,circulationVO,row
     * @return  Map
     */
    private Map totalColumn(int k,CirculationVO circulationVO,Map row){
        if (k + 1 == 1) {
            //1 代表 统计种数
            circulationVO.setColumn(1);
            row.put("0", "种数");
        } else if (k + 1 == 2) {
            //1 代表 统计册数
            circulationVO.setColumn(2);
            row.put("0", "册数");
        } else if (k + 1 == 3) {
            //1 代表 统计总金额数
            circulationVO.setColumn(3);
            row.put("0", "总金额数");
        }
        return row;
    }

    /**
     * 导出统计数据
     * @param circulationVO
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exports(CirculationVO circulationVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        //拿到页面显示格式的数据
        Map<String,List> obj = collectionTypeCount(circulationVO);
        //拼成导出格式的 List
        List<Map> result = new ArrayList();
        //拼接一行
        Map row = null;
        for (Object str:keyList) {
            List<Map> list = obj.get(str.toString());

            for (Map map:list) {
                row = new HashMap(16);
                row.put(0,str.toString()+map.get("0"));
                int i = 1;
                for (Object strTwo: keyTwoList) {
                    row.put(i,map.get(strTwo));
                    ++i;
                }

                result.add(row);
            }
        }
        Map tilte =new HashMap(16);
        if(circulationVO.getColumnType() == 0){
            tilte.put("a","馆藏地点统计项");
        }else{
            tilte.put("a","图书流通类型统计项");
        }
        List<String> character = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        //获得头部
        List<Map> topList = obj.get("topRow");
        for (Map map:topList) {
            for (int i = 0; i < keyTwoList.size(); i++){

                tilte.put(character.get(i+1),map.get(keyTwoList.get(i)));

            }
        }

        //导出数据
        if(result != null && result.size() > 0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for (int j = 0; j < result.size(); j++){
                m = new HashMap(16);
                Map pp = result.get(j);

                for (int i = 0; i < pp.size(); i++) {
                    m.put(character.get(i),pp.get(i));
                }
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    /***
     * 馆藏日志     解析书的条码号
     * @param operationType
     * @return  List
     */
    private Object[] opContentList(String operationType , Long libraryId , String startTime , String endTime ,Long userId){
        List<String> operationList = collectionCountDao.opContentList(operationType , libraryId , startTime , endTime , userId);
        List<String> operation = new ArrayList();
        for (Object op : operationList) {
            String [] collectionIdStr = op.toString().split("、");

            for (String arr:collectionIdStr) {
                if(arr.indexOf("(") != -1){
                    String result = StringUtils.substringBefore(arr, "(");
                    operation.add(result);
                }
            }

        }
        Object []idArray = operation.toArray();

        return idArray;
    }

}
