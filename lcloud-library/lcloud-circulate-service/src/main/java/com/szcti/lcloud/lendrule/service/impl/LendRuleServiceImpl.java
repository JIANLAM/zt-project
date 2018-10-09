package com.szcti.lcloud.lendrule.service.impl;

import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.common.utils.ValidateUtils;
import com.szcti.lcloud.lendrule.entity.vo.LendRuleVo;
import com.szcti.lcloud.lendrule.repository.LendRuleDao;
import com.szcti.lcloud.lendrule.service.LendRuleService;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: 借阅规则 Service
 * @Description: 借阅规则 的Service
 * @date: 2018/8/1 15:48
 */

@Service
public class LendRuleServiceImpl implements LendRuleService {

    @Autowired
    private LendRuleDao lendRuleDao;

    /**
     * 查询一行借阅规则
     * @param ruleId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LendRuleVo get(Long ruleId ){
        LendRuleVo lendRuleVo = lendRuleDao.get(ruleId , new Long[]{null});
        String [] collectionIdStr = lendRuleVo.getLiteratureType().split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        return lendRuleDao.get(ruleId , idArray);
    }


    /**
     * 根据主键删除一条 或多条 借阅规则记录
     * @param ids
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delLendRule(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        lendRuleDao.delLendRule(idArray);
    }

    /**
     * 新增   或修改 一条借阅规则  数据记录
     * @param   lendRuleVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer save(LendRuleVo lendRuleVo){
        if(lendRuleDao.existsRuleCode(lendRuleVo.getRuleCode(),lendRuleVo.getRuleType(),lendRuleVo.getId(),lendRuleVo.getLibraryId()) > 0){
            //判断是否  借阅规则  同一图书馆 不能存在相同的规则代码  存在返回1
            return 1;
        }else if(ValidateUtils.numberORLetter(lendRuleVo.getRuleCode())){
            return 2;
        }else {
            if(lendRuleVo != null && lendRuleVo.getId()!= null) {
                lendRuleVo.setUpdateTime(DateUtils.getDateTime());
                lendRuleDao.updateLendRule(lendRuleVo);
                return 0;
            }else{
                Long barCodeId = IdGen.randomLong();
                lendRuleVo.setId(barCodeId);
                lendRuleVo.setCreateTime(DateUtils.getDateTime());
                lendRuleDao.insertLendRule(lendRuleVo);
                return 0;
            }
        }

    }

    /**
     * 启用 一条 或多条规则
     * @param   ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ruleOpen(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        lendRuleDao.ruleOpen(idArray);
    }

    /**
     * 禁用 一条 或多条规则
     * @param   ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void ruleClose(String ids){
        String [] collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        lendRuleDao.ruleClose(idArray);
    }

    /**
     *导出 借阅规则 数据记录
     * @param   lendRuleVo
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportRuleExcel(LendRuleVo lendRuleVo) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        List<LendRuleVo> lendRuleVoList = lendRuleDao.findList(lendRuleVo);
        return exportInfo(lendRuleVoList , filePath , fileName);
    }

    /**
     * 借阅图书时 校验规则
     * @param   readerId ， bookId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LendRuleVo checkLendRule(Long readerId , Long holdingId){
        //根据读者ID 获取 相关信息
        HashMap usermap = lendRuleDao.getReaderInfo(readerId);
        //获取图书馆ID
        Object libraryId = usermap.get("libraryId");
        //获取读者类型
        Object readerType = usermap.get("readerType");
        //获取文献类型  和所属馆
        Map info = lendRuleDao.bookArticleType(holdingId);
        //获取所属馆
        Long ownlib = Long.valueOf(info.get("ownlib").toString());
        //如果书的所属馆和办理读者证的馆是同一个   则校验馆内规则
        if(libraryId == ownlib){
            //获取这个图书馆 这种读者类型 的 馆内借阅规则
            LendRuleVo MuseumRule = lendRuleDao.readerMuseumRule(Long.valueOf(readerType.toString()));
            return checkLendRuleInfo(MuseumRule , readerId , info);
        }else{
            //获取这个图书馆 这种读者类型 的 馆际借阅规则
            LendRuleVo InterRule = lendRuleDao.readerInterRule(Long.valueOf(readerType.toString()));
            return checkLendRuleInfo(InterRule , readerId , info);
        }
    }

    /***      封装借阅规则校验 细节      ***/
    public LendRuleVo checkLendRuleInfo(LendRuleVo obj , Long readerId , Map info ){
        if(obj != null){
            //获取此读者已经借阅了几本书
            Integer lendQty = lendRuleDao.readerCount(readerId);
            //获取 读者当前信用分
            Integer credit = lendRuleDao.creditNumber(readerId);
            //获取文献类型
            Long literatureType = Long.valueOf(info.get("actType").toString());
            if(obj.getLendQty() != null && !"".equals(obj.getLendQty())  && (lendQty+1) > obj.getLendQty()){
                //借阅册数为空 则不限制 不为空 读者已经借阅的图书册数加上当前借阅数量 大于 规定册数 失败
                return null;
            }else if(obj.getCredit() != null && !"".equals(obj.getCredit())  && credit < obj.getCredit()){
                //信用分为空 则不限制 不为空 读者当前信用分小于规则 信用分 失败
                return null;
            }else if(obj.getLiteratureType() != null && !"".equals(obj.getLiteratureType())){
                //文献类型为空 则不限制 不为空 判断此读者 读者证类型是否能够借阅此文献类型的图书

                String [] collectionIdStr = obj.getLiteratureType().split(",");
                Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
                boolean flg = false;
                for (int i =0 ; i < idArray.length ; i++){
                    if(literatureType.equals(idArray[i])){
                        flg = true;
                    }
                }
                if(!flg){
                    return null;
                }
            }
            return obj;
        }else {
            return null;
        }
    }

    /***      封装续借规则校验 细节      ***/
    public LendRuleVo checkRelendRuleInfo(LendRuleVo obj , Long readerId , Map info ,Long holdingId){
        if(obj != null){
            //获取此读者已经借阅了几本书
            Integer lendQty = lendRuleDao.readerCount(readerId);
            //获取 读者当前信用分
            Integer credit = lendRuleDao.creditNumber(readerId);
            //获取文献类型
            Long literatureType = Long.valueOf(info.get("actType").toString());
            //获取此读者已经续借了几次
            Integer reLendQty = lendRuleDao.bookRelendCount(holdingId ,readerId);
            if(obj.getLendQty() != null && !"".equals(obj.getLendQty())  && lendQty > obj.getLendQty()){
                //借阅册数为空 则不限制 不为空 读者已经借阅的图书册数加上当前借阅数量 大于 规定册数 失败
                return null;
            }else if(obj.getCredit() != null && !"".equals(obj.getCredit())  && credit < obj.getCredit()){
                //信用分为空 则不限制 不为空 读者当前信用分小于规则 信用分 失败
                return null;
            }else if(obj.getLiteratureType() != null && !"".equals(obj.getLiteratureType())){
                //文献类型为空 则不限制 不为空 判断此读者 读者证类型是否能够借阅此文献类型的图书

                String [] collectionIdStr = obj.getLiteratureType().split(",");
                Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
                boolean flg = false;
                for (int i =0 ; i < idArray.length ; i++){
                    if(literatureType.equals(idArray[i])){
                        flg = true;
                    }
                }
                if(!flg){
                    return null;
                }
            }else if(obj.getRenewQty() != null && !"".equals(obj.getRenewQty()) && (reLendQty+1) > obj.getRenewQty()){
                //续借次数为空 则不限制 不为空 读者已经续借的图书的次数加上当前续借一次数量 大于 规定续借次数 失败
                return null;
            }
            return obj;
        }else {
            return null;
        }
    }

    /**
     * 续借图书时 校验规则
     * @param   readerId ， bookId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public LendRuleVo checkRelendRule(Long readerId , Long holdingId){
        //根据读者ID 获取 相关信息
        HashMap usermap = lendRuleDao.getReaderInfo(readerId);
        //获取图书馆ID
        Object libraryId = usermap.get("libraryId");
        //获取读者类型
        Object readerType = usermap.get("readerType");
        //获取文献类型  和所属馆
        Map info = lendRuleDao.bookArticleType(holdingId);
        //获取所属馆
        Long ownlib = Long.valueOf(info.get("ownlib").toString());
        //如果书的所属馆和办理读者证的馆是同一个   则校验馆内规则
        if(libraryId == ownlib){
            //获取这个图书馆 这种读者类型 的 馆内借阅规则
            LendRuleVo MuseumRule = lendRuleDao.readerMuseumRule(Long.valueOf(readerType.toString()));
            return checkRelendRuleInfo(MuseumRule , readerId , info , holdingId);
        }else{
            //获取这个图书馆 这种读者类型 的 馆际借阅规则
            LendRuleVo InterRule = lendRuleDao.readerInterRule(Long.valueOf(readerType.toString()));
            return checkRelendRuleInfo(InterRule , readerId , info , holdingId);
        }
    }

    /**
     *导入 借阅规则模版 数据记录
     * @param
     * @return
     */
    /*@Override
    @Transactional(rollbackFor = Exception.class)*/
   /* public String exportMouldExcel() {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        Map tilte =new HashMap(16);
        tilte.put("a*","规则代码*");
        tilte.put("b*","规则名称*");
        tilte.put("c","规则类型*");
        tilte.put("d","借阅册数");
        tilte.put("e","借阅天数");
        tilte.put("f","续借次数");
        tilte.put("g","续借天数");
        tilte.put("h","逾期罚款率");
        tilte.put("i","丢失罚款率");
        tilte.put("j","污损罚款率");
        tilte.put("k","信用值限制");
        tilte.put("l","可借文献类型");
        tilte.put("m","是否启用");
        *//*tilte.put("n","创建者");
        tilte.put("o","创建时间");
        tilte.put("p","修改者");
        tilte.put("q","修改时间");*//*
        return POITool.ExportData(tilte,new ArrayList<>(),filePath);
    }*/

    //导出数据详情
    private String exportInfo( List<LendRuleVo> lendRuleVoList , String filePath ,String fileName) {
        Map tilte =new HashMap(16);
        tilte.put("a","规则代码");
        tilte.put("b","规则名称");
        tilte.put("c","规则类型");
        tilte.put("d","借阅册数");
        tilte.put("e","借阅天数");
        tilte.put("f","续借次数");
        tilte.put("g","续借天数");
        tilte.put("h","逾期罚款率");
        tilte.put("i","丢失罚款率");
        tilte.put("j","污损罚款率");
        tilte.put("k","信用值限制");
        tilte.put("l","可借文献类型");
        tilte.put("m","是否启用");
        tilte.put("n","创建者");
        tilte.put("o","创建时间");
        tilte.put("p","修改者");
        tilte.put("q","修改时间");
        if(lendRuleVoList!=null && lendRuleVoList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map m;
            for(LendRuleVo vo:lendRuleVoList){
                m = new HashMap(16);
                m.put("a",vo.getRuleCode());
                m.put("b",vo.getRuleName());
                if(vo.getRuleType() == 0){
                    m.put("c","馆内规则");
                }else{
                    m.put("c","馆际规则");
                }
                m.put("d",vo.getLendQty());
                m.put("e",vo.getLendDays());
                m.put("f",vo.getRenewQty());
                m.put("g",vo.getRenewDays());
                m.put("h",vo.getOverduePayrate());
                m.put("i",vo.getLostPayrate());
                m.put("j",vo.getBrokenPayrate());
                m.put("k",vo.getCredit());
                m.put("l",vo.getLiteratureType());
                if(vo.getStatus() == 1){
                    m.put("m","启用");
                }
                if(vo.getStatus() == 0){
                    m.put("m","禁用");
                }
                m.put("n",vo.getCreateName());
                m.put("o",vo.getCreateTime());
                m.put("p",vo.getUpdaterName());
                m.put("q",vo.getUpdateTime());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    /**
     *批量导入 借阅规则 数据记录
     * @param   maplist
     * @return
     */
    /*@Override
    @Transactional(rollbackFor = Exception.class)
    public String importLendRule(List<Map> maplist , Long libraryId , Long createBy) {
        List<LendRuleVo> lendRuleVoList= new ArrayList<>();
        for(Map m:maplist){
            LendRuleVo lendRuleVo = new LendRuleVo();
            lendRuleVo.setLibraryId(libraryId);       //图书馆ID
            lendRuleVo.setCreateBy(createBy);      //创建者

            lendRuleVo.setRuleCode(m.get("规则代码").toString());
            lendRuleVo.setRuleName(m.get("规则名称").toString());

            if(m.get("借阅册数").toString() == null && "".equals(m.get("借阅册数").toString())){
                lendRuleVo.setLendQty(0);
            }else {
                lendRuleVo.setLendQty(Integer.valueOf(m.get("借阅册数").toString()));
            }
            if(m.get("借阅天数").toString() == null && "".equals(m.get("借阅天数").toString())){
                lendRuleVo.setLendDays(0);
            }else {
                lendRuleVo.setLendDays(Integer.valueOf(m.get("借阅天数").toString()));
            }
            if(m.get("续借次数").toString() == null && "".equals(m.get("续借次数").toString())){
                lendRuleVo.setRenewQty(0);
            }else {
                lendRuleVo.setRenewQty(Integer.valueOf(m.get("续借次数").toString()));
            }
            if(m.get("续借天数").toString() == null && "".equals(m.get("续借天数").toString())){
                lendRuleVo.setRenewDays(0);
            }else {
                lendRuleVo.setRenewDays(Integer.valueOf(m.get("续借天数").toString()));
            }
            if(m.get("逾期罚款率").toString() == null && "".equals(m.get("逾期罚款率").toString())){
                lendRuleVo.setOverduePayrate(0);
            }else {
                lendRuleVo.setOverduePayrate(Float.valueOf(m.get("逾期罚款率").toString()));
            }
            if(m.get("丢失罚款率").toString() == null && "".equals(m.get("丢失罚款率").toString())){
                lendRuleVo.setLostPayrate(0);
            }else {
                lendRuleVo.setLostPayrate(Float.valueOf(m.get("丢失罚款率").toString()));
            }
            if(m.get("污损罚款率").toString() == null && "".equals(m.get("污损罚款率").toString())){
                lendRuleVo.setBrokenPayrate(0);
            }else {
                lendRuleVo.setBrokenPayrate(Float.valueOf(m.get("污损罚款率").toString()));
            }
            lendRuleVo.setRuleTypeTest(m.get("规则类型").toString());
            if(m.get("要求信用分").toString() == null && "".equals(m.get("要求信用分").toString())){
                lendRuleVo.setCredit(0);
            }else {
                lendRuleVo.setCredit(Integer.valueOf(m.get("要求信用分").toString()));
            }
            lendRuleVo.setLiteratureType(m.get("可借文献类型").toString());
            if(m.get("是否启用").toString() == null && "".equals(m.get("是否启用").toString())){
                if("禁用".equals(m.get("是否启用").toString())){
                    lendRuleVo.setStatus(0);
                }else if("启用".equals(m.get("是否启用").toString())){
                    lendRuleVo.setStatus(1);
                }

            }else {
                lendRuleVo.setStatus(Integer.valueOf(m.get("是否启用").toString()));
            }
            lendRuleVoList.add(lendRuleVo);
        }

        //装插入的错误数据
        List<LendRuleVo> errorData = new ArrayList<>();
        for (LendRuleVo item : lendRuleVoList) {
            //同一个图书馆 是否存在 相同的规则代码  0 不存在  >0 存在
            int lendRuleRepeat = lendRuleDao.existsRuleCode(item.getRuleType(),item.getId(),item.getLibraryId());
            //规则类型 输入 馆内借阅 = 0 馆际借阅 = 1  其他 = 2（无此规则类型）
            int ruleType;
            String literatureType = null;
            if("馆内借阅".equals(item.getRuleTypeTest())){
                ruleType = 0;
            }else if("馆际借阅".equals(item.getRuleTypeTest())){
                ruleType = 1;
            }else{
                ruleType = 2;
            }
           *//* if()*//*
           //获取文献类型 根据，截图成字符串
            String [] collectionIdStr = item.getLiteratureType().split(",");
            for (int i = 0; i < collectionIdStr.length; i++){
                //一个一个判断 是否存在此文献类型 selectResult 不为空 则输入正确   否则 无此文献类型
                String selectResult = lendRuleDao.existsLiteratureType(collectionIdStr[i] , item.getLibraryId());
                if(selectResult != null && !"".equals(selectResult)){
                    //文献类型 存在 则拼成String 字符串格式保存
                    literatureType += selectResult+",";
                }else{
                    //有一种文献类型 不存在则输入错误 不符合 跳出循环
                    literatureType = null;
                    break;
                }
            }

            //存在相同代码  错误  规则类型不符合 错误 文献类型一种不符合错误
            if(lendRuleRepeat > 0 || ruleType == 2 || (literatureType != null && !"".equals(literatureType))){
                //判断是否  借阅规则  同一图书馆 不能存在相同的规则代码  存在返回1
                LendRuleVo lendRuleVo  = new LendRuleVo();
                lendRuleVo.setRuleCode(item.getRuleCode());
                lendRuleVo.setRuleName(item.getRuleName());
                lendRuleVo.setLendQty(item.getLendQty());
                lendRuleVo.setLendDays(item.getLendDays());
                lendRuleVo.setRenewQty(item.getRenewQty());
                lendRuleVo.setRenewDays(item.getRenewDays());
                lendRuleVo.setOverduePayrate(item.getOverduePayrate());
                lendRuleVo.setLostPayrate(item.getLostPayrate());
                lendRuleVo.setBrokenPayrate(item.getBrokenPayrate());
                lendRuleVo.setRuleType(ruleType);
                lendRuleVo.setCredit(item.getCredit());
                lendRuleVo.setLiteratureType(literatureType);
                lendRuleVo.setStatus(lendRuleVo.getStatus());
                *//*lendRuleVo.setCreateTime(DateUtils.getDateTime());
                lendRuleVo.setCreateBy(createBy);
                lendRuleVo.setLibraryId(libraryId);     *//*
                errorData.add(lendRuleVo);
            }else{
                //不存在相同的则导入新增
                Long barCodeId = IdGen.randomLong();
                item.setId(barCodeId);
                item.setCreateTime(DateUtils.getDateTime());
                lendRuleDao.insertLendRule(item);
            }
        }
        //如果错误数据不为空 则导出错误数据
        if(errorData != null && errorData.size() > 0){
            String fileName = IdGen.getDateUUId()+".xls";
            String filePath = new POITool().getExportPath()+fileName;
            return exportInfo(errorData , filePath , fileName);
        }

        return null;
    }*/

}
