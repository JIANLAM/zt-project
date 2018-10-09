package com.szcti.lcloud.lendbuy.service;


import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyOrderVO;

import java.util.List;

/**
 * @Title: 借购Service
 * @Description: 处理借购的Service
 * @author: fengda
 * @date: 2018/5/16 8:50
 */
public interface LendBuyService {


    /**
     * 生成订单，并把选中的借购书车中的书籍加入到订单中
     * @param ids 借购书车中的书籍主键，用","隔开的字符串
     * @param online
     * @param userId
     * @param readerId
     * @param libId
     * @return
     */
    List<Long> saveOrder(String ids, Integer online,Long userId,Long readerId,Long libId);

    /**
     * 取消订单
     * @param lendBuyOrderId
     * @return String
     */
    String cancelOrder(Long lendBuyOrderId);

    /**
     * 批量删除借购数车里的书籍
     * @param ids
     */
    void deleteBooks(String ids);

    /**
     * 批量订单里的书籍,同时变更订单金额
     * @param lendBuyOrderId 订单主键ID
     * @param ids 订单中书籍的主键ID，用","隔开的字符串
     */
    void deleteBooksInOrder(Long lendBuyOrderId,String ids);

    /**
     * 添加书籍到借购数车
     * @param lendBuyBookVO
     * @return
     */
    boolean saveBooks(LendBuyBookVO lendBuyBookVO);


    /**
     * 通过ISBN编号检查借购的书是否存在
     * @param isbn
     * @param readerId
     * @return
     */
    boolean checkBook(String isbn,Long readerId);

    /**
     * 导出借购单书籍
     * @param lendBuyOrderVO
     * @return
     */
    String exportOrderExcel(LendBuyOrderVO lendBuyOrderVO);

    /**
     * 导出借购书籍书籍
     * @param lendBuyBookVO
     * @return
     */
    String exportBookExcel(LendBuyBookVO lendBuyBookVO);

    /**
     * 完成订单
     * @param lendBuyOrderId
     * @param libId    所属图书馆id
     * @param staffId    操作员工的id
     */
    void finishOrder(Long lendBuyOrderId,Long libId,Long staffId);

}
