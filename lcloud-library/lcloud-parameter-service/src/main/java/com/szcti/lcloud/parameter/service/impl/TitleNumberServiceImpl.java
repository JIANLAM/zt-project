package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.TitleNumberEntity;
import com.szcti.lcloud.parameter.repository.TitleNumberDao;
import com.szcti.lcloud.parameter.service.TitleNumberService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 种次号维护 Service
 * @Description: 种次号维护 的Service
 * @author: wangsiyi
 * @date: 2018/7/26 19:58
 */

@Service
public class TitleNumberServiceImpl implements TitleNumberService {

    @Autowired
    private TitleNumberDao titleNumberDao;

    /**
     * 根据主键删除一条 或多条 种次号维护数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delTitleNumber(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        titleNumberDao.delTitleNumber(idArray);
    }

    /**
     * 新增   或修改 一条种次号维护 数据记录
     * @param   titleNumberEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(TitleNumberEntity titleNumberEntity){
        if(titleNumberEntity != null && titleNumberEntity.getId()!= null) {

            if(titleNumberDao.existsTitlenumber(titleNumberEntity.getClassNumber(),titleNumberEntity.getId(),titleNumberEntity.getLibraryId()) > 0){
                //判断是否已经存在分类号 存在返回1 不存在返回0
                return 1;
            }else {
                titleNumberDao.updateTitleNumber(titleNumberEntity);
            }
            return 0;

        }else{

            if(titleNumberDao.existsTitlenumber(titleNumberEntity.getClassNumber(),titleNumberEntity.getId(),titleNumberEntity.getLibraryId()) > 0){
                //判断是否已经存在分类号 存在返回1 不存在返回0
                return 1;
            }else {
                Long barCodeId = IdGen.randomLong();
                titleNumberEntity.setId(barCodeId);
                titleNumberEntity.setCreateTime(DateUtils.getDateTime());
                titleNumberDao.insertTitleNumber(titleNumberEntity);
            }
            return 0;

        }
    }

    /**
     * 新增初始化    分类号 最大值加1 默认为1
     * @param
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map maxCurrSeednumber(Long libraryId , String classNumber){
        // KEY = maxNub 代表在大值+1      KEY = id    不为null则修改 为null 则新增
        Map resultMap = titleNumberDao.maxCurrSeednumber(libraryId , classNumber);
        if( resultMap == null ){
            resultMap = new HashMap(16);
            resultMap.put("id" , null);
            resultMap.put("maxNub" , 1);
            return resultMap;
        }
        resultMap.put("id" , resultMap.get("id"));
        resultMap.put("maxNub" , resultMap.get("maxNub") );
        return resultMap;
    }

    /**
     *导出 种次号维护 数据记录
     * @param   titleNumberEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportExcel(TitleNumberEntity titleNumberEntity) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        List<TitleNumberEntity> marcList = titleNumberDao.findTitleNumberList(titleNumberEntity);
        return exportInfo(marcList , filePath , fileName , "export");
    }

    //导出数据详情
    private String exportInfo( List<TitleNumberEntity> titleNumberList , String filePath ,String fileName , String type) {
        Map tilte =new HashMap(16);
        tilte.put("a","分类号");
        tilte.put("b","当前种号");
        tilte.put("c","备注");
        if(titleNumberList!=null && titleNumberList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for(TitleNumberEntity vo:titleNumberList){
                m = new HashMap(16);
                m.put("a",vo.getClassNumber());
                if("export".equals(type)){
                    m.put("b",vo.getCurrSeednumber());
                }else {
                    //vo.setCurrSeednumberTest(vo.getCurrSeednumber().toString());
                    m.put("b",vo.getCurrSeednumberTest());
                }
                m.put("c",vo.getRemark());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     *下载  导入 种次号维护模版 数据记录
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportMouldExcel() {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        Map tilte =new HashMap(16);
        tilte.put("a","分类号*");
        tilte.put("b","当前种号*");
        tilte.put("c","备注");
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     *批量导入 种次号维护 数据记录
     * @param   maplist
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importTitleNumber(List<Map> maplist , Long libraryId , Long createBy) {

            //解析导入数据
            List<TitleNumberEntity> titleNumberEntityList= new ArrayList<>();
            for(Map m:maplist){
                TitleNumberEntity titleNumber = new TitleNumberEntity();
                titleNumber.setLibraryId(libraryId);       //图书馆ID
                titleNumber.setUserId(createBy);      //创建者
                titleNumber.setClassNumber(m.get("分类号*").toString().trim());
                titleNumber.setCurrSeednumberTest(m.get("当前种号*").toString().trim());
                titleNumber.setRemark(m.get("备注") == null ? null : m.get("备注").toString().trim());
                titleNumberEntityList.add(titleNumber);
            }
            //装插入的错误数据
            List<TitleNumberEntity> errorData = new ArrayList<>();
            for (TitleNumberEntity item : titleNumberEntityList) {
                String classNumber = item.getClassNumber();
                String currSeednumberTest = item.getCurrSeednumberTest();
                boolean flg = false;
                //判断是否已经存在分类号 存在返回1 不存在返回0
                Integer existsClassNumber =titleNumberDao.existsTitlenumber(item.getClassNumber(),item.getId(),item.getLibraryId());
                try {
                    Integer.valueOf(item.getCurrSeednumberTest().toString());
                }catch (Exception e){
                    flg = true;
                }
                if(existsClassNumber > 0 || (classNumber == null || "".equals(classNumber)) || (currSeednumberTest == null || "".equals(currSeednumberTest)) || flg){
                    //判断如果存在相同的字段名称 在同一图书馆 装到错误数据集合了
                    TitleNumberEntity titleNumberEntity  = new TitleNumberEntity();
                    titleNumberEntity.setClassNumber(item.getClassNumber());
                    titleNumberEntity.setCurrSeednumberTest(item.getCurrSeednumberTest().toString());
                    titleNumberEntity.setRemark(item.getRemark());

                    errorData.add(titleNumberEntity);
                }else{
                    //不存在相同的则导入新增
                    Long barCodeId = IdGen.randomLong();
                    item.setId(barCodeId);
                    item.setCreateTime(DateUtils.getDateTime());
                    item.setCurrSeednumber(Integer.valueOf(item.getCurrSeednumberTest().toString()));
                    titleNumberDao.insertTitleNumber(item);
                }
            }
            //如果错误数据不为空 则导出错误数据
            if(errorData != null && errorData.size() > 0){
                String fileName = IdGen.getDateUUId()+".xls";
                String filePath = new POITool().getExportPath()+fileName;
                return exportInfo(errorData , filePath , fileName ,"import");
            }

        return null;

    }
}
