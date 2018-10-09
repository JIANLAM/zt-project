package com.szcti.lcloud.parameter.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.parameter.entity.MarcFieldEntity;
import com.szcti.lcloud.parameter.entity.MarcMouldEntity;
import com.szcti.lcloud.parameter.repository.MarcTempletDao;
import com.szcti.lcloud.parameter.service.MarcTempletService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: mrc模板 Service
 * @Description: mrc模板 的Service
 * @author: wangsiyi
 * @date: 2018/7/31 09:20
 */

@Service
public class MarcTempletServiceImpl implements MarcTempletService {

    @Autowired
    private MarcTempletDao marcTempletDao;

    /*************************************************    MARC字段       ***************************************************/

    /**
     * 根据主键删除一条 或多条 mrc字段数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMarc(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        marcTempletDao.delMarc(idArray);
    }

    /**
     * 新增   或修改 一条mrc字段 数据记录
     * @param   marcFieldEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(MarcFieldEntity marcFieldEntity){
        if(marcFieldEntity != null && marcFieldEntity.getId()!= null) {
            if(marcTempletDao.existsMarcName(marcFieldEntity.getName() , marcFieldEntity.getId(),marcFieldEntity.getLibraryId()) > 0){
                //判断是否已经存在相同的字段名称 在同一图书馆 存在返回1 不存在返回0
                return 1;
            }
            marcTempletDao.updateMarc(marcFieldEntity);
            return 0;
        }else{
            if(marcTempletDao.existsMarcName(marcFieldEntity.getName() , marcFieldEntity.getId(),marcFieldEntity.getLibraryId()) > 0){
                //判断是否已经存在相同的字段名称 在同一图书馆 存在返回1 不存在返回0
                return 1;
            }
            Long barCodeId = IdGen.randomLong();
            marcFieldEntity.setId(barCodeId);
            marcFieldEntity.setCreateDate(DateUtils.getDateTime());
            marcTempletDao.insertMarc(marcFieldEntity);
            return 0;
        }
    }

    /**
     *导出 marc字段 数据记录
     * @param   MarcFieldEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportMarcExcel(MarcFieldEntity MarcFieldEntity) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        List<MarcFieldEntity> marcList = marcTempletDao.findList(MarcFieldEntity.getLibraryId());
        return exportInfo(marcList , filePath , "export");
    }

    /**
     *导入 marc字段模板 数据记录
     * @param
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportMouldExcel() {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        Map tilte =new HashMap(16);
        tilte.put("a","字段意义*");
        tilte.put("b","字段名称*");
        tilte.put("c","指示符*");
        tilte.put("d","必选子字段*");
        tilte.put("e","可选子字段");
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    //导出数据详情
    private String exportInfo( List<MarcFieldEntity> marcList , String filePath ,String type) {
        Map tilte =new HashMap(16);
        tilte.put("a","字段意义");
        tilte.put("b","字段名称");
        tilte.put("c","指示符");
        tilte.put("d","必选子字段");
        tilte.put("e","可选子字段");
        if(marcList!=null && marcList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for(MarcFieldEntity vo:marcList){
                m = new HashMap(16);
                m.put("a",vo.getDescription());
                m.put("b",vo.getName());
                if("export".equals(type)){
                    m.put("c",vo.getDesignator());
                }else{
                    m.put("c",vo.getDesignatorTest());
                }
                m.put("d",vo.getMustValues());
                m.put("e",vo.getSelectValues());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }

    /**
     *导入 marc字段 数据记录
     * @param   maplist
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String importMarc(List<Map> maplist , Long libraryId , Long createBy) {
        //解析导入数据
        List<MarcFieldEntity> MarcFieldList= new ArrayList<>();
        for(Map m:maplist){
            MarcFieldEntity marcField = new MarcFieldEntity();
            marcField.setLibraryId(libraryId);       //图书馆ID
            marcField.setCreateBy(createBy);      //创建者
            marcField.setDescription(m.get("字段意义*").toString().trim());
            marcField.setName(m.get("字段名称*").toString().trim());
            marcField.setDesignatorTest(m.get("指示符*").toString().trim());
            marcField.setMustValues(m.get("必选子字段*").toString().trim());
            marcField.setSelectValues(m.get("可选子字段") == null ? null : m.get("可选子字段").toString().trim());
            MarcFieldList.add(marcField);
        }
        //装插入的错误数据
        List<MarcFieldEntity> errorData = new ArrayList<>();
        for (MarcFieldEntity item : MarcFieldList) {
            String description = item.getDescription();
            String name = item.getName();
            Integer designator = item.getDesignator();
            String mustValues = item.getMustValues();
            //判断不能存在相同的代码
            Integer existsMarcName = marcTempletDao.existsMarcName(item.getName() , item.getId(),item.getLibraryId());
            //带* 不能为空
            boolean flg = (description == null || "".equals(description)) || (name == null || "".equals(name)) || (designator == null || "".equals(designator)) || (mustValues == null || "".equals(mustValues));
            //字段名称 只能长度为3 的数值   指示符只能为 0 或者 1 或者 null
            boolean flgTwo = item.getName().length() != 3 || (Integer.valueOf(item.getDesignatorTest()) != 0 && Integer.valueOf(item.getDesignatorTest()) != 1 && item.getDesignatorTest() != null);
            //字段名称  只能为数字
            try{
                Integer.valueOf(item.getName());
            }catch (Exception e){
                flgTwo = true ;
            }
            if( existsMarcName > 0 || flg || flgTwo){
                //判断如果存在相同的字段名称 在同一图书馆 装到错误数据集合了
                MarcFieldEntity marcFieldEntity  = new MarcFieldEntity();
                marcFieldEntity.setDescription(item.getDescription());
                marcFieldEntity.setName(item.getName());
                marcFieldEntity.setDesignatorTest(item.getDesignatorTest());
                marcFieldEntity.setMustValues(item.getMustValues());
                marcFieldEntity.setSelectValues(item.getSelectValues());
                errorData.add(marcFieldEntity);
            }else{
                //不存在相同的则导入新增
                Long barCodeId = IdGen.randomLong();
                item.setId(barCodeId);
                item.setCreateDate(DateUtils.getDateTime());
                item.setDesignator(item.getDesignatorTest() == null ? null : Integer.valueOf(item.getDesignatorTest()));
                marcTempletDao.insertMarc(item);
            }
        }
        //如果错误数据不为空 则导出错误数据
        if(errorData != null && errorData.size() > 0){
            String fileName = IdGen.getDateUUId()+".xls";
            String filePath = new POITool().getExportPath()+fileName;
            return exportInfo(errorData , filePath , "import");
        }

        return null;
    }

    /*************************************************    MARC模版       ***************************************************/

    /**
     * 根据主键删除一条 或多条 mrc模板数据记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delMarcMould(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        marcTempletDao.delMarcMould(idArray);
    }

    /**
     * 新增   或修改 一条mrc模板 数据记录
     * @param   marcMouldEntity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer saveMould(MarcMouldEntity marcMouldEntity){
        if(marcTempletDao.existsMarcValue(marcMouldEntity.getValue() , marcMouldEntity.getId(),marcMouldEntity.getLibraryId()) > 0){
            //判断是否已经存在相同的代码在同一图书馆 存在返回1 不存在返回0
            return 1;
        }else if(ValidateUtils.numberORLetter(marcMouldEntity.getValue())){
            return 2;
        }else{
            if(marcMouldEntity != null && marcMouldEntity.getId()!= null) {
                marcTempletDao.updateMarcMould(marcMouldEntity);
                return 0;
            }else{
                Long marcMouldId = IdGen.randomLong();
                marcMouldEntity.setStatus(0);
                marcMouldEntity.setId(marcMouldId);
                marcMouldEntity.setCreateDate(DateUtils.getDateTime());
                marcTempletDao.insertMarcMould(marcMouldEntity);
                return 0;
            }
        }
    }

    /**
     * MARC模板     启用一个模版 设为默认
     * @param id , libraryId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void openMould(Long id ,  Long libraryId){
        //先禁用其他的
        marcTempletDao.colseAll(libraryId);
        //再启用
        marcTempletDao.openMould(id);
    }


}
