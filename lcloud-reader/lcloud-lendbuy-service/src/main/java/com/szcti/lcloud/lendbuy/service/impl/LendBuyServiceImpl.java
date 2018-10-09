package com.szcti.lcloud.lendbuy.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyBookVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyOrderVO;
import com.szcti.lcloud.lendbuy.entity.vo.LendBuyRuleVO;
import com.szcti.lcloud.lendbuy.repository.LendBuyDao;
import com.szcti.lcloud.lendbuy.repository.RuleDao;
import com.szcti.lcloud.lendbuy.service.LendBuyService;
import com.szcti.lcloud.lendbuy.service.RuleService;
import lombok.NonNull;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:53
 */
@Service
public class LendBuyServiceImpl implements LendBuyService {

    @Autowired
    private LendBuyDao lendBuyDao;

    @Autowired
    private RuleDao ruleDao;

    @Autowired
    private RuleService ruleService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String cancelOrder(@NonNull Long lendBuyOrderId){
        LendBuyOrderVO lendBuyOrderVO = lendBuyDao.getOrder(lendBuyOrderId);
        if(lendBuyOrderVO.getStatus()==0){
            //取消成功,改变订单状态
            lendBuyDao.updateOrderStatus(lendBuyOrderId,3,null,null,null);
            //改变书籍状态
            LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
            lendBuyBookVO.setLendBuyOrderId(lendBuyOrderId);
            List<LendBuyBookVO> bookList = lendBuyDao.findBooksInOrder(lendBuyBookVO);
            if(bookList.size()>0){
                Long [] ids = new Long[bookList.size()];
                for (int i=0;i<bookList.size();i++){
                    ids[i] = bookList.get(i).getId();
                }
                lendBuyDao.cancelBook(ids);
            }
            return "success";
        }else{
            //不允许取消
            return "cannot";
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBooks(@NonNull String ids){
        String []collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        lendBuyDao.deleteBooks(idArray);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBooksInOrder(@NonNull Long lendBuyOrderId,@NonNull String ids){
        LendBuyBookVO lendBuyBookVO = new LendBuyBookVO();
        lendBuyBookVO.setLendBuyOrderId(lendBuyOrderId);
        List<LendBuyBookVO> bookList = lendBuyDao.findBooksInOrder(lendBuyBookVO);
        Long id;
        Long [] idArray = new Long[bookList.size()];
        for (int i = 0;i<bookList.size();i++) {
            id = bookList.get(i).getId();
            if(!ids.contains(id.toString())){
                idArray[i] = id;
            }
        }
        lendBuyDao.updateTotalMoney(lendBuyOrderId,lendBuyDao.getOrderPrice(idArray));
        deleteBooks(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void finishOrder(@NonNull Long lendBuyOrderId,Long libId,Long staffId){
        lendBuyDao.updateOrderStatus(lendBuyOrderId,2,staffId,null,DateUtils.getDateTime());
        //默认借购天数为30
        Integer days = 30;
        LendBuyOrderVO order = lendBuyDao.getOrder(lendBuyOrderId);
        Integer readerType = lendBuyDao.getReaderTypeById(order.getReaderId());
        LendBuyRuleVO rule = ruleDao.getByType(libId,readerType,1);
        if(rule!=null){
            days = rule.getLendBuyDays();
        }
        //获取借购单中的书籍并修改应还书日期
        LendBuyBookVO book = new LendBuyBookVO();
        book.setLendBuyOrderId(lendBuyOrderId);
        List<LendBuyBookVO> bookList = lendBuyDao.findBooksInOrder(book);
        Long [] idArray = new Long[bookList.size()];
        for (int i = 0;i<bookList.size();i++) {
            idArray[i] = bookList.get(i).getId();
        }
        String dueBackTime = DateUtils.formatDateTime(DateUtils.getDateToN(new Date(),days));
        lendBuyDao.setDueBackTime(idArray,dueBackTime);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Long> saveOrder(@NonNull String ids,Integer online,Long userId,Long readerId,Long libId){
        String []collectionIdStr = ids.split(",");
        Long []idArray = (Long[]) ConvertUtils.convert(collectionIdStr,Long.class);
        LendBuyOrderVO lendBuyOrderVO = new LendBuyOrderVO();
        Long id = IdGen.randomLong();
        lendBuyOrderVO.setId(id);
        lendBuyOrderVO.setOrderNo(IdGen.getDateUUId());
        lendBuyOrderVO.setCreateTime(DateUtils.getDateTime());
        lendBuyOrderVO.setStatus(0);
        lendBuyOrderVO.setOnline(online);
        lendBuyOrderVO.setReaderId(readerId);
        lendBuyOrderVO.setTotalMoney(lendBuyDao.getOrderPrice(idArray));
        List<LendBuyBookVO> books = lendBuyDao.findBooksByIds(idArray);
        boolean flag = true;
        List<Long> idList = new ArrayList<>();
        for (LendBuyBookVO book:books) {
            //规则校验全部通过才能提交结构单
            Integer readerType = lendBuyDao.getReaderTypeById(readerId);
            if(!ruleService.autoCheck(book.getId(),new HashMap(16),userId,readerType,libId)){
                idList.add(book.getId());
                flag = false;
            }
        }
        if(flag){
            lendBuyDao.insertOrder(lendBuyOrderVO);
            lendBuyDao.book2Order(idArray,1,id);
            return null;
        }else{
            return idList;
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveBooks(LendBuyBookVO lendBuyBookVO){
        //判断同一本书是否被同一个读者借购过
        List<LendBuyBookVO> books = lendBuyDao.findRepeatBooks(lendBuyBookVO.getPreBookId(),lendBuyBookVO.getReaderId());
        if(books==null||books.size()==0){
            lendBuyBookVO.setId(IdGen.randomLong());
            lendBuyBookVO.setIsSubmit(0);
            lendBuyDao.insertBook(lendBuyBookVO);
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public boolean checkBook(@NonNull String isbn,Long readerId){
        List<LendBuyBookVO> books = lendBuyDao.findBooksByISBN(isbn,readerId);
        if(books!=null&&books.size()>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportOrderExcel(LendBuyOrderVO lendBuyOrderVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;
        
        List<LendBuyOrderVO> orderList = lendBuyDao.findOrders(lendBuyOrderVO);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","订单号");
        title.put("b","快递号");
        title.put("c","读者证号");
        title.put("d","读者姓名");
        title.put("e","图书总数");
        title.put("f","总价");
        title.put("g","收货信息");
        title.put("h","订单状态");
        title.put("i","备注");
        if(orderList!=null&&orderList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(LendBuyOrderVO vo:orderList){
                m = new HashMap<>(16);
                m.put("a",vo.getOrderNo());
                m.put("b",vo.getExpressNo());
                m.put("c",vo.getReaderCardNumber());
                m.put("d",vo.getUserName());
                m.put("e",vo.getBookCount());
                m.put("f",new DecimalFormat("##0.00").format(vo.getTotalMoney()));
                m.put("g",vo.getSendeeInfo());
                switch(vo.getStatus()+""){
                    case "0":
                        m.put("h","未处理");
                        break;
                    case "1":
                        m.put("h","已发货");
                        break;
                    case "2":
                        m.put("h","已完成");
                        break;
                    case "3":
                        m.put("h","已取消");
                        break;
                    default:m.put("h","");
                }
                m.put("i",vo.getRemark());
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String exportBookExcel(LendBuyBookVO lendBuyBookVO) {
        String fileName = IdGen.getDateUUId()+".xls";
        String filePath = new POITool().getExportPath()+fileName;

        List<LendBuyBookVO> bookList = lendBuyDao.findBooksInOrder(lendBuyBookVO);
        Map<String,Object> title =new HashMap<>(16);
        title.put("a","所属订单");
        title.put("b","图书名称");
        title.put("c","ISBN");
        title.put("d","作者");
        title.put("e","分类号");
        title.put("f","出版社");
        title.put("g","出版时间");
        title.put("h","单价");
        if(bookList!=null&&bookList.size()>0){
            List<Object> exportList = new ArrayList<>();
            Map<String,Object> m;
            for(LendBuyBookVO vo:bookList){
                m = new HashMap<>(16);
                m.put("a",vo.getOrderNo());
                m.put("b",vo.getTitle());
                m.put("c",vo.getISBN());
                m.put("d",vo.getAuthor());
                m.put("e",vo.getBookType());
                m.put("f",vo.getPublisher());
                m.put("g",vo.getPubDate());
                m.put("h",new DecimalFormat("##0.00").format(vo.getPrice()));
                exportList.add(m);
            }
            return POITool.ExportData(title,exportList,filePath);
        }
        return fileName;
    }

}
