package com.szcti.lcloud.lendbuy.repository;

import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyOrderVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 借购信息DAO
 * @Description: 处理借购操作
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface LendBuyDao {
    /**
     * 根据条件查找用户的借购书籍信息
     * @param lendBuyBookVO
     * @return List<LendBuyBookVO>
     */
    List<LendBuyBookVO> findBooksInOrder(LendBuyBookVO lendBuyBookVO);

    /**
     * 根据条件查找用户的购物书车
     * @param lendBuyBookVO
     * @return List<LendBuyBookVO>
     */
    List<LendBuyBookVO> findBooks(LendBuyBookVO lendBuyBookVO);

    /**
     * 根据条件查找用户的借购订单信息
     * @param lendBuyOrderVO
     * @return List<LendBuyOrderVO>
     */
    List<LendBuyOrderVO> findOrders(LendBuyOrderVO lendBuyOrderVO);

    /**
     * 更新订单状态
     * @param lendBuyOrderId
     * @param status
     * @param staffId   操作员   为空则值不变
     * @param sendTime 发货时间  为空则值不变
     * @param takedTime 收货时间  为空则值不变
     */
    void updateOrderStatus(@Param("lendBuyOrderId") Long lendBuyOrderId,
                           @Param("status") Integer status,
                           @Param("staffId") Long staffId,
                           @Param("sendTime") String sendTime,
                           @Param("takedTime") String takedTime);

    /**
     * 更新订单金额
     * @param lendBuyOrderId
     * @param totalMoney
     */
    void updateTotalMoney(@Param("lendBuyOrderId") Long lendBuyOrderId,@Param("totalMoney") Float totalMoney);

    /**
     * 借购书车中的书籍加入但订单，或从订单张回到借购书车
     * @param idArray
     * @param isSubmit
     * @param lendBuyOrderId
     */
    void book2Order(@Param("idArray") Long[] idArray,@Param("isSubmit") Integer isSubmit,@Param("lendBuyOrderId") Long lendBuyOrderId);

    /**
     * 设置被成功借购的书籍的应还日期
     * @param idArray
     * @param dueBackTime
     */
    void setDueBackTime(@Param("idArray") Long[] idArray,@Param("dueBackTime") String dueBackTime);

    /**
     * 取消订单后，订单中的书籍也被取消借购状态，可再次被同一读者借购
     * @param idArray 借购书籍的主键
     */
    void cancelBook(@Param("idArray") Long[] idArray);

    /**
     * 查询单个订单
     * @param lendBuyOrderId
     * @return LendBuyOrderVO
     */
    LendBuyOrderVO getOrder(Long lendBuyOrderId);

    /**
     * 新增一条借购订单
     * @param lendBuyOrderVO
     */
    void insertOrder(LendBuyOrderVO lendBuyOrderVO);

    /**
     * 根据图书主键和读者主键查询单个借购书籍
     * @param preBookId
     * @param readerId
     * @return
     */
    List<LendBuyBookVO> findRepeatBooks(@Param("preBookId") Long preBookId,@Param("readerId")Long readerId);

    /**
     * 根据借购图书的主键ID查询单个借购书籍
     * @param id
     * @return
     */
    LendBuyBookVO getBookById(@Param("id") Long id);

    /**
     * 根据多本借购书籍的ID查询借购的书籍
     * @param idArray
     * @return
     */
    List<LendBuyBookVO> findBooksByIds(@Param("idArray") Long[] idArray);

    /**
     * 根据ISBN编号查询被借购的书
     * @param isbn
     * @param readerId
     * @return
     */
    List<LendBuyBookVO> findBooksByISBN(@Param("isbn") String isbn,@Param("readerId") Long readerId);

    /**
     * 根据ISBN编号查询已被提交借购单的书
     * @param isbn
     * @param libId
     * @return
     */
    List<LendBuyBookVO> findReadyBooks(@Param("isbn") String isbn,@Param("libId") Long libId);

    /**
     * 新增一条借购书籍
     * @param lendBuyBookVO
     */
    void insertBook(LendBuyBookVO lendBuyBookVO);

    /**
     * 批量删除荐购书车里的书籍
     * @param idArray
     */
    void deleteBooks(@Param("idArray") Long[] idArray);

    /**
     * 获取订单中所有书籍的总价
     * @param idArray
     * @return Float
     */
    Float getOrderPrice(@Param("idArray") Long[] idArray);

    /**
     * 根据读者id获取读者类型
     * @param id
     * @return Integer
     */
    Integer getReaderTypeById(Long id);

}
