package com.szcti.lcloud.datacount.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.datacount.entity.vo.*;
import com.szcti.lcloud.datacount.repository.CirculationCountDao;
import com.szcti.lcloud.datacount.service.CirculationCountService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Title: 流通统计Service
 * @Description: 各种统计的Service
 * @author: wangsiyi
 * @date: 2018/7/23 2:57
 */
@Service
public class CirculationCountServiceImpl implements CirculationCountService {

    @Autowired
    private CirculationCountDao circulationCountDao;

    //行KEY      用于导出
    private List keyList;

    /**
     * 图书借阅统计
     * @param circulationVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Object> bookLendCount(CirculationVO circulationVO,Integer selectType){
        //存储选择的读者证类型
        String [] readerTypeIdStr = null;
        //结果集转换为Long    类型数组
        Long []idArray = null;
        //ids为用户选择的一个或多个读者证类型
        if(circulationVO.getReaderTypeIds() != null){
            readerTypeIdStr = circulationVO.getReaderTypeIds().split(",");
            idArray = (Long[]) ConvertUtils.convert(readerTypeIdStr,Long.class);
        }

        //存储 用户选择的下拉列表的类型
        List<Map<String , Object>> selectTypeList = new ArrayList<>();
        //获取选择的类型   *
        selectTypeList = getSelectType(selectType,circulationVO.getLibraryId());
        //存储 统计的数量 map值
        Integer readerTypeNub = null;
        //存储 哪种证件类型 map键  类型代码
        String readerTypeName = null;
        //拼凑 统计一行的数据
        Map row =  null;
        //头部行
        Map topRow = new HashMap(16);
        //返回统计的数据
        List<Object> rowList = new ArrayList<>();
        //外层循环  下拉列表    图书流通类型  或  图书分类    或    馆藏地点
        for (Map vo: selectTypeList
                ) {
            //外层循环一次反回新的一行
            row = new HashMap(16);
            //用于导出      存储导出KEY
            keyList = new ArrayList();
            //里层循环  循环  选择的读者证类型
            for (int i=0; i<idArray.length; i++){
                //根据读者证ID   查询每一个的KEY   VALUE
                Map typeMap =  circulationCountDao.basicParm(idArray[i]);
                readerTypeName = typeMap.get("value").toString();
                topRow.put(typeMap.get("value"),typeMap.get("label"));

                circulationVO.setCommReaderId(idArray[i]);
                if(selectType == 1){
                    topRow.put("0","图书流通类型");
                    circulationVO.setCommTypeId(vo.get("id").toString());
                    row.put("0",vo.get("label"));
                    readerTypeNub = circulationCountDao.cirReaderType(circulationVO);

                }else if(selectType == 2){
                    topRow.put("0","图书分类");
                    circulationVO.setCommTypeId(vo.get("classno").toString());
                    row.put("0",vo.get("description"));
                    readerTypeNub = circulationCountDao.readerType(circulationVO);

                }else if(selectType == 3){
                    topRow.put("0","馆藏地点");
                    circulationVO.setCommTypeId(vo.get("id").toString());
                    row.put("0",vo.get("label"));
                    readerTypeNub = circulationCountDao.collReaderType(circulationVO);

                }
                row.put(readerTypeName,readerTypeNub);

                keyList.add(typeMap.get("value"));
            }
            rowList.add(row);
        }
        keyList.add(0,"0");
        rowList.add(0,topRow);
        return rowList;
    }

    /**
     * 获取选择的类型的数据
     */
    private List<Map<String , Object>> getSelectType(Integer selectType , Long libraryId){
        List<Map<String , Object>> selectTypeList = new ArrayList<>();
        if(selectType == 1){
            //selectType = 1 为图书流通类型
            selectTypeList = circulationCountDao.cirType(libraryId);
        }else if(selectType == 2){
            //selectType = 2 为图书类型
            selectTypeList = circulationCountDao.bookType();
        }else if(selectType == 3){
            //selectType = 3 为馆藏地点
            selectTypeList = circulationCountDao.collAddressType(libraryId);
        }else if(selectType == 4){
            //selectType = 4 读者类型
            selectTypeList = circulationCountDao.readerTypeList(libraryId);
        }else if(selectType == 5){
            //selectType = 5 学生证年级
            selectTypeList = circulationCountDao.gradeList(libraryId);
        }else if(selectType == 6){
            //selectType = 6 学生证班级
            selectTypeList = circulationCountDao.classesList(libraryId);
        }
        return  selectTypeList;
    }

    /**
     * 读者借阅统计
     * @param circulationVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Object> readerLendCount(CirculationVO circulationVO,Integer selectType,Integer choiceCount){
        List<Map<String , Object>> readerList = null;
        if(choiceCount == 1){
            //获取所有读者证类型
            readerList = circulationCountDao.readerTypeList(circulationVO.getLibraryId());
        }else if(choiceCount == 2){
            //获取所有性别
            readerList = new ArrayList<>();
            Map sexMapOne = new HashMap(16);
            Map sexMapTwo = new HashMap(16);
            sexMapOne.put("id","1");
            sexMapTwo.put("id","2");
            readerList.add(sexMapOne);
            readerList.add(sexMapTwo);
        }else if(choiceCount == 3){
            //获取所有年级
            readerList = circulationCountDao.gradeList(circulationVO.getLibraryId());
        }else if(choiceCount == 4){
            //获取所有班级
            readerList = circulationCountDao.classesList(circulationVO.getLibraryId());
        }

        //存储 用户选择的下拉列表的类型
        List<Map<String , Object>> selectTypeList = new ArrayList<>();
        //获取选择的类型    *
        selectTypeList = getSelectType(selectType,circulationVO.getLibraryId());
        //存储 统计的数量 map值
        Integer readerTypeNub = null;
        //存储 哪种证件类型 map键  类型代码
        String readerTypeName = null;
        //拼凑 统计一行的数据
        Map row =  null;
        //头部行
        Map topRow = new HashMap(16);
        //返回统计的数据
        List<Object> rowList = new ArrayList<>();
        for (Map vo: selectTypeList
                ) {
            row = new HashMap(16);
            keyList = new ArrayList();
            for (Map list: readerList
                 ) {
                if(selectType == 1){
                    topRow.put("0","图书流通类型");

                }else if(selectType == 2){
                    topRow.put("0","图书分类");

                }else if(selectType == 3){
                    topRow.put("0","馆藏地点");

                }

                if(choiceCount == 1){
                    keyList.add(list.get("value").toString());
                    readerTypeName = list.get("value").toString();
                    topRow.put(list.get("value").toString(),list.get("label").toString());
                    circulationVO.setCommReaderId(Long.valueOf(list.get("id").toString()));
                }else if(choiceCount == 2){
                    topRow.put("one","男");
                    topRow.put("two","女");
                    if(Long.valueOf(list.get("id").toString()) == 1){
                        readerTypeName = "one";
                    }else{
                        readerTypeName = "two";
                    }
                    circulationVO.setSexStatus(Integer.valueOf(list.get("id").toString()));
                }else if(choiceCount == 3){
                    keyList.add(list.get("code").toString());
                    topRow.put(list.get("code").toString(),list.get("name").toString());
                    readerTypeName = list.get("code").toString();
                    circulationVO.setGrade(Long.valueOf(list.get("id").toString()));
                }else if(choiceCount == 4){
                    keyList.add(list.get("code").toString());
                    topRow.put(list.get("code").toString(),list.get("name").toString());
                    readerTypeName = list.get("code").toString();
                    circulationVO.setClasses(Long.valueOf(list.get("id").toString()));
                }

                if(selectType == 1){
                    circulationVO.setCommTypeId(vo.get("id").toString());
                    row.put("0",vo.get("label"));
                    readerTypeNub = circulationCountDao.cirReaderType(circulationVO);

                }else if(selectType == 2){
                    circulationVO.setCommTypeId(vo.get("classno").toString());
                    row.put("0",vo.get("description"));
                    readerTypeNub = circulationCountDao.readerType(circulationVO);

                }else if(selectType == 3){
                    circulationVO.setCommTypeId(vo.get("id").toString());
                    row.put("0",vo.get("label"));
                    readerTypeNub = circulationCountDao.collReaderType(circulationVO);

                }
                row.put(readerTypeName,readerTypeNub);

            }
            rowList.add(row);
        }
        if(choiceCount == 2){
            keyList.add("one");
            keyList.add("two");
        }
        keyList.add(0,"0");
        rowList.add(0,topRow);
        return rowList;
    }

    /**
     * 图书污损统计       图书丢失统计
     * @param circulationVO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Object> financeCount(CirculationVO circulationVO,Integer selectType){
        //获取选择的类型    *
        List<Map<String , Object>> selectTypeList = getSelectType(selectType,circulationVO.getLibraryId());
        //得到统计的结果
        Map result = null;
        //拼凑 统计一行的数据
        Map row =  null;
        //头部行
        Map topRow = new HashMap(16);

        //返回统计的数据
        List<Object> rowList = new ArrayList<>();
        keyList = new ArrayList();
            for (Map vo: selectTypeList
                    ) {
                row = new HashMap(16);
                if(selectType == 1){
                    row.put("0",vo.get("label"));
                    topRow.put("0","图书流通类型");
                    circulationVO.setActType(Long.valueOf(vo.get("id").toString()));
                }else  if(selectType == 2){
                    row.put("0",vo.get("label"));
                    topRow.put("0","图书分类");
                    circulationVO.setBookType(vo.get("classno").toString());
                    row.put("0",vo.get("description"));
                }else  if(selectType == 3){
                    row.put("0",vo.get("label"));
                    topRow.put("0","馆藏地点");
                    circulationVO.setCollAddressId(Long.valueOf(vo.get("id").toString()));
                }else  if(selectType == 4){
                    row.put("0",vo.get("label"));
                    topRow.put("0","读者证类型");
                    circulationVO.setCommReaderId(Long.valueOf(vo.get("id").toString()));
                }else  if(selectType == 5){
                    row.put("0",vo.get("name"));
                    topRow.put("0","年级");
                    circulationVO.setGrade(Long.valueOf(vo.get("id").toString()));
                }else  if(selectType == 6){
                    row.put("0",vo.get("name"));
                    topRow.put("0","班级");
                    circulationVO.setClasses(Long.valueOf(vo.get("id").toString()));
                }
                if(circulationCountDao.defileCount(circulationVO) == null){
                    result = new HashMap(16);
                }else{
                    result = circulationCountDao.defileCount(circulationVO);
                }
                if(circulationVO.getBookTypeCount() != null){
                    Object bookTypeCount = 0;
                    if(result.get("bookTypeCount") != null ){
                        bookTypeCount = result.get("bookTypeCount");
                    }
                    row.put("bookTypeCount" , bookTypeCount);
                }
                if(circulationVO.getTotalCount() != null){
                    row.put("totalCount" , result.get("totalCount"));
                }
                if(circulationVO.getTotalPrice() != null){
                    row.put("totalPrice" , result.get("totalPrice"));
                }
                rowList.add(row);
            }
        keyList.add("0");
        if(circulationVO.getBookTypeCount() != null && !"".equals(circulationVO.getBookTypeCount())){
            topRow.put("bookTypeCount","种数");
            keyList.add("bookTypeCount");
        }
        if(circulationVO.getTotalCount() != null && !"".equals(circulationVO.getTotalCount())){
            topRow.put("totalCount","册数");
            keyList.add("totalCount");
        }
        if(circulationVO.getTotalPrice() != null && !"".equals(circulationVO.getTotalPrice())){
            topRow.put("totalPrice","总价");
            keyList.add("totalPrice");
        }
        rowList.add(0,topRow);
        return rowList;
    }

    /**
     * 导出  统计数据
     * @param circulationVO
     * @return String
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String bookLendExport(CirculationVO circulationVO , Integer selectType ,Integer choiceCount) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        //拼成导出格式的 List
        List<Object> result = null;
        if(choiceCount == null && circulationVO.getType() == null){
            //图书借阅统计导出
            result = bookLendCount(circulationVO ,selectType);
        }else if(choiceCount != null){
            //读者借阅统计导出
            result = readerLendCount(circulationVO ,selectType , choiceCount);
        }else if(circulationVO.getType() == 202 || circulationVO.getType() == 203){
            //图书污损  或   丢失统计
            result = financeCount(circulationVO ,selectType);
        }

        //Excel横向排序
        List<String> character = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z");
        //搭建头部
        Map tilte =new HashMap(16);
        Map oneRow = (Map<String,Object>)result.get(0);
        for (int i = 0; i<keyList.size(); i++){
            tilte.put(character.get(i),oneRow.get(keyList.get(i)));
        }
        //导出数据
        if(result != null && result.size() > 0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for (int j = 1; j < result.size(); j++){
                m = new HashMap(16);
                Map<String,Object> pp = (Map<String,Object>)result.get(j);
                for (int i = 0; i<keyList.size(); i++){
                    m.put(character.get(i),pp.get(keyList.get(i)));
                }
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }







}
