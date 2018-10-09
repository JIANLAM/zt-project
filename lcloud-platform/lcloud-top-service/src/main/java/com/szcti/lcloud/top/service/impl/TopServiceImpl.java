package com.szcti.lcloud.top.service.impl;

import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.common.utils.POITool;
import com.szcti.lcloud.top.entity.vo.TopBookVO;
import com.szcti.lcloud.top.repository.TopRespository;
import com.szcti.lcloud.top.service.TopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("topService")
public class TopServiceImpl implements TopService {

    @Autowired
    private TopRespository topRepository;

    @Override
    public List<TopBookVO> topLendBook(TopBookVO topBookVO) {
        return topRepository.topLendBook(topBookVO);
    }

    @Override
    public List<TopBookVO> topRecommBook(TopBookVO topBookVO) {
        List<TopBookVO> list=topRepository.topRecommBook(topBookVO);
        for(TopBookVO vo:list){
            TopBookVO b=getBookByIsbn(vo.getIsbn());
//            vo.setBookFrom(b.getBookFrom());
//            vo.setBookId(b.getId());
            vo.setBookType(b.getBookType());
//            vo.setTitle(b.getTitle());
//            vo.setAuthor(b.getAuthor());
//            vo.setPublisher(b.getPublisher());
//            vo.setSummary(b.getSummary());
//            vo.setPubdate(b.getPubdate());
//            vo.setPrice(b.getPrice());
//            vo.setPic(b.getPic());
        }
        return  list;
    }

    @Override
    public List<TopBookVO> topRecommBuyBook(TopBookVO topBookVO) {
        List<TopBookVO> list=topRepository.topRecommBuyBook(topBookVO);
        for(TopBookVO vo:list){
            List<TopBookVO> prelist=topRepository.getPrebook(topBookVO.getIsbn());
            if(prelist!=null&&prelist.size()>0){
            TopBookVO b=prelist.get(0);
            vo.setBookFrom(b.getBookFrom());
            vo.setBookId(b.getId());
            vo.setBookType(b.getBookType());
            vo.setTitle(b.getTitle());
            vo.setAuthor(b.getAuthor());
            vo.setPublisher(b.getPublisher());
            vo.setSummary(b.getSummary());
            vo.setPubdate(b.getPubdate());
            vo.setPrice(b.getPrice());
            vo.setPic(b.getPic());
            }
        }
        return list;
    }

    @Override
    public List<TopBookVO> topLendReader(TopBookVO topBookVO) {
        return topRepository.topLendReader(topBookVO);
    }

    @Override
    public List<TopBookVO> topRecommBuyReader(TopBookVO topBookVO) {
        return topRepository.topRecommBuyReader(topBookVO);
    }

    @Override
    public TopBookVO getBookByIsbn(String isbn) {
        List<TopBookVO> booklist=topRepository.getBook(isbn);
        if(booklist!=null&&booklist.size()>0){
            return booklist.get(0);
        }else{
            List<TopBookVO> prelist=topRepository.getPrebook(isbn);
            if(prelist!=null&&prelist.size()>0){
                return prelist.get(0);
            }
        }
        return null;
    }

    @Override
    public void setBookInfo(List<TopBookVO> topBookVOList) {
        if(topBookVOList!=null&&topBookVOList.size()>0) {
            for (TopBookVO vo : topBookVOList) {
                if (vo.getIsbn() != null && !vo.getIsbn().equals("")) {
                    TopBookVO v = getBookByIsbn(vo.getIsbn());
                    vo.setBookId(v.getId());
                    vo.setPic(v.getPic());
                    vo.setTitle(v.getTitle());
                    vo.setAuthor(v.getAuthor());
                    vo.setPrice(v.getPrice());
                    vo.setPublisher(v.getPublisher());
                    vo.setPubdate(v.getPubdate());
                    vo.setSummary(v.getSummary());
                    vo.setBookFrom(v.getBookFrom());
                    vo.setBookType(v.getBookType());
                }
            }
        }
    }
    @Override
    public List<TopBookVO> topLendBuyBook(TopBookVO topBookVO) {
        List<TopBookVO> list=topRepository.topLendBuyBook(topBookVO);
        for(TopBookVO vo:list){
            List<TopBookVO> prelist=topRepository.getPrebook(topBookVO.getIsbn());
            if(prelist!=null&&prelist.size()>0){
                TopBookVO b=prelist.get(0);
                vo.setBookFrom(b.getBookFrom());
                vo.setBookId(b.getId());
                vo.setBookType(b.getBookType());
                vo.setTitle(b.getTitle());
                vo.setAuthor(b.getAuthor());
                vo.setPublisher(b.getPublisher());
                vo.setSummary(b.getSummary());
                vo.setPubdate(b.getPubdate());
                vo.setPrice(b.getPrice());
                vo.setPic(b.getPic());
            }
        }
        return list;
    }

    @Override
    public List<TopBookVO> topLendBuyReader(TopBookVO topBookVO) {
        return topRepository.topLendBuyReader(topBookVO);
    }

    @Override
    public String topLendBookExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","图书名称");
        tilte.put("b","isbn");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版时间");
        tilte.put("g","借阅次数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getTitle());
                m.put("b",vo.getIsbn());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getBookType());
                m.put("e",vo.getPublisher());
                m.put("f",vo.getPubdate());
                m.put("g",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topRecommBookExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","图书名称");
        tilte.put("b","isbn");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版时间");
        tilte.put("g","推荐次数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getTitle());
                m.put("b",vo.getIsbn());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getBookType());
                m.put("e",vo.getPublisher());
                m.put("f",vo.getPubdate());
                m.put("g",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topRecommBuyBookExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","图书名称");
        tilte.put("b","isbn");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版时间");
        tilte.put("g","荐购次数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getTitle());
                m.put("b",vo.getIsbn());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getBookType());
                m.put("e",vo.getPublisher());
                m.put("f",vo.getPubdate());
                m.put("g",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topLendReaderExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","读者证号");
        tilte.put("b","读者姓名");
        tilte.put("c","读者性别");
        tilte.put("d","证件类型");
        tilte.put("e","借阅图书总数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getReaderCardNumber());
                m.put("b",vo.getUserName());
                m.put("c",vo.getSex().equals("1")?"男":"女");
                m.put("d",vo.getReaderCardType().equals("0")?"读书证":"其它");
                m.put("e",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topRecommBuyReaderExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","读者证号");
        tilte.put("b","读者姓名");
        tilte.put("c","读者性别");
        tilte.put("d","证件类型");
        tilte.put("e","荐购图书总数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getReaderCardNumber());
                m.put("b",vo.getUserName());
                m.put("c",vo.getSex().equals("1")?"男":"女");
                m.put("d",vo.getReaderCardType().equals("0")?"读书证":"其它");
                m.put("e",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topLendBuyBookExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","图书名称");
        tilte.put("b","isbn");
        tilte.put("c","作者");
        tilte.put("d","分类号");
        tilte.put("e","出版社");
        tilte.put("f","出版时间");
        tilte.put("g","借购次数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getTitle());
                m.put("b",vo.getIsbn());
                m.put("c",vo.getAuthor());
                m.put("d",vo.getBookType());
                m.put("e",vo.getPublisher());
                m.put("f",vo.getPubdate());
                m.put("g",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

    @Override
    public String topLendBuyReaderExport(List<TopBookVO> topBookVOList) {
        String fileName=IdGen.getDateUUId()+".xls";
        String filePath=new POITool().getExportPath()+fileName;
        List<TopBookVO> querylist=topBookVOList;
        Map tilte =new HashMap();
        tilte.put("a","读者证号");
        tilte.put("b","读者姓名");
        tilte.put("c","读者性别");
        tilte.put("d","证件类型");
        tilte.put("e","借购图书总数");
        if(querylist!=null&&querylist.size()>0){
            List<Object> exportList=new ArrayList<Object>();
            for(TopBookVO vo:querylist){
                Map m =new HashMap();
                m.put("a",vo.getReaderCardNumber());
                m.put("b",vo.getUserName());
                m.put("c",vo.getSex().equals("1")?"男":"女");
                m.put("d",vo.getReaderCardType().equals("0")?"读书证":"其它");
                m.put("e",vo.getRecount());
                exportList.add(m);
            }
            return POITool.ExportData(tilte,exportList,filePath);
        }
        return fileName;
    }

}
