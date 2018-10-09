package com.szcti.lcloud.lendrule.service;

import com.szcti.lcloud.lendrule.entity.vo.LendRuleVo;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * @Title: 借阅规则 Service
 * @Description: 借阅规则 的Service
 * @author: wangsiyi
 * @date: 2018/8/1 15:48
 */
public interface LendRuleService {

    /**
     * 查询一行借阅规则
     * @param id
     */
    LendRuleVo get(Long id );

    /**
     * 根据主键删除一条 或多条 借阅规则记录
     * @param ids
     */
    void delLendRule(String ids);

    /**
     * 新增   或修改 借阅规则 数据记录
     * @param   lendRuleVo
     * @return
     */
    Integer save(LendRuleVo lendRuleVo);

    /**
     *导出 借阅规则 数据记录
     * @param   lendRuleVo
     * @return
     */
    String exportRuleExcel(LendRuleVo lendRuleVo);

    /**
     * 启用 一条或多条规则
     * @param   ids
     * @return
     */
    void ruleOpen(String ids);

    /**
     * 禁用 一条或多条规则
     * @param   ids
     * @return
     */
    void ruleClose(String ids);

    /**
     * 借阅图书时 校验规则
     * @param   readerId , bookId
     * @param bookId
     * @return  LendRuleVo
     */
    LendRuleVo checkLendRule(Long readerId , Long bookId);

    /**
     * 续借图书时 校验规则
     * @param   readerId , bookId
     * @param bookId
     * @return  LendRuleVo
     */
    LendRuleVo checkRelendRule(Long readerId , Long bookId);

   /* *//**
     *导出 借阅规则模版 数据记录
     * @param
     * @return
     *//*
    String exportMouldExcel();

    *//**
     *批量导入 借阅规则 数据记录
     * @param   lendRuleVo
     * @return
     *//*
    String importLendRule(List<Map>  lendRuleVo , Long libraryId , Long createBy);*/

}
