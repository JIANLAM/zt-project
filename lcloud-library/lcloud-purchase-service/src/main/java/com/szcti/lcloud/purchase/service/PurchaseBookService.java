package com.szcti.lcloud.purchase.service;

import com.szcti.lcloud.purchase.entity.PurchaseBook;
import com.szcti.lcloud.purchase.entity.PurchaseBookCriteria;
import com.szcti.lcloud.purchase.entity.PurchaseRule;
import com.szcti.lcloud.purchase.entity.vo.BookInfo;
import com.szcti.lcloud.purchase.entity.vo.PurchaseBookVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author liujunliang
 * @email ${email}
 * @date 2018-05-17 14:25:42
 */
public interface PurchaseBookService {

    int countByExample(PurchaseBookCriteria example);

    int deleteByExample(PurchaseBookCriteria example);

    int insert(PurchaseBook record);

    int insertSelective(PurchaseBook record);

    List<PurchaseBook> selectByExample(PurchaseBookCriteria example);

    int updateByExampleSelective(@Param("record") PurchaseBook record, @Param("example") PurchaseBookCriteria example);

    int updateByExample(@Param("record") PurchaseBook record, @Param("example") PurchaseBookCriteria example);

    int updateByPrimaryKeySelective(PurchaseBook record);
    PurchaseBook selectByPrimaryKey(Long orderBuyBookId);
    int deleteByPrimaryKey(Long orderBuyBookId);
    int updateByPrimaryKey(PurchaseBook purchaseBook);

    //
    int importExcel(List<PurchaseBook> list,Long orderId);
    String  exportExcel(Long orderId,List ids);
    List<PurchaseBookVO> queryPage(PurchaseBookVO purchaseBookVO);
    List<Long> getBooks(BookInfo b);
    int setBookQt(String[] array, Long orderBuyId,Map<String, Object> params);
    List<Long> getDupBooks(BookInfo b);
    Map dupCheckBookRes(Long id);
    Map dupCheckPrebookRes(Long id,Long libraryId,Long orderId);
    Boolean autoCheck(long l, Map m);
    Boolean checkRule(PurchaseBookVO vo, PurchaseRule r, Map m);
    int delete(String[] array,Long orderId);
    Map dupCheckBookRes(PurchaseBookVO vo, PurchaseRule pr);
    PurchaseBookVO getPurchaseBookVO(Long purchaseBookId);
    List<PurchaseBookVO> getAcceptBook(PurchaseBookVO purchaseBookVO);
    String exportLack(long id);
    
    /**
     * 
     * @描述：获取分页中央库来源订单数据
     * @作者：tianbw
     * @时间：2018年9月6日 下午2:59:21
     * @param purchaseBookVO
     * @return
     */
    List<PurchaseBookVO> getPageBook(PurchaseBookVO purchaseBookVO);
    
    /**
     * 
     * @描述：获取分页采访库来源订单数据
     * @作者：tianbw
     * @时间：2018年9月6日 下午2:59:21
     * @param purchaseBookVO
     * @return
     */
    List<PurchaseBookVO> queryPagePreBook(PurchaseBookVO purchaseBookVO);
    
    /**
     * 
     * @描述：获取新华库来源订单数据
     * @作者：tianbw
     * @时间：2018年9月6日 下午2:59:59
     * @param purchaseBookVO
     * @return
     */
    List<PurchaseBookVO> queryPageBookXH(PurchaseBookVO purchaseBookVO);
    
    
}

