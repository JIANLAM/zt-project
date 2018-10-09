package com.szcti.lcloud.purchase.service.impl;

import com.szcti.lcloud.purchase.entity.Prebook;
import com.szcti.lcloud.purchase.entity.PrebookCriteria;
import com.szcti.lcloud.purchase.entity.vo.BookVO;
import com.szcti.lcloud.purchase.entity.vo.PrebookVO;
import com.szcti.lcloud.purchase.repository.BookRepository;
import com.szcti.lcloud.purchase.repository.PrebookRepository;
import com.szcti.lcloud.purchase.service.PrebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author liujunliang
 * @Description
 * @Date  2018/7/12
 **/
@Service("prebookService")
public class PrebookServiceImpl implements PrebookService {
    @Autowired
    private PrebookRepository prebookRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public Prebook selectByPrimaryKey(Long id) {
        return prebookRepository.selectByPrimaryKey(id);
    }

    @Override
    public List<Prebook> selectByExampleWithBLOBs(PrebookCriteria criteria) {
        return prebookRepository.selectByExampleWithBLOBs(criteria);
    }

    @Override
    public List<Prebook> selectByExample(PrebookCriteria criteria) {
        return prebookRepository.selectByExample(criteria);
    }

    @Override
    public int updateByPrimaryKey(Prebook prebook) {
        return  prebookRepository.updateByPrimaryKey(prebook);
    }

    @Override
    public List<PrebookVO> queryPage(PrebookVO prebookVO) {
        List<PrebookVO> list=null;
        if(prebookVO!=null&&prebookVO.getSource()!=null&&prebookVO.getSource()==1){
            list=prebookRepository.queryPage(prebookVO);
            return list;
        }
        if(prebookVO!=null&&prebookVO.getSource()!=null&&prebookVO.getSource()==2){
            BookVO bookVO= new BookVO();
            setBookVO(bookVO,prebookVO);
            List<BookVO> blist=bookRepository.queryPage(bookVO);
            if(blist!=null&&blist.size()>0){
                list=new ArrayList<PrebookVO>();
                for(BookVO b:blist){
                    PrebookVO p=new PrebookVO();
                    setPreookVO(p,b);
                    list.add(p);
                }
            }
            return list;
        }
        return list;
    }

    private void setPreookVO(PrebookVO prebookVO, BookVO bookVO) {
        prebookVO.setSource(2);
        prebookVO.setId(bookVO.getId());
        prebookVO.setAuthor(bookVO.getAuthor());
        prebookVO.setBookType(bookVO.getBookType());
        prebookVO.setIsbn(bookVO.getIsbn());
        prebookVO.setPublisher(bookVO.getPublisher());
        prebookVO.setPubdate(bookVO.getPubdate());
        prebookVO.setPrice(bookVO.getPrice());
        prebookVO.setTitle(bookVO.getTitle());
        prebookVO.setIds(bookVO.getIds());
        //bookVO.setGoodsCode(prebookVO.setGoodsCode());
        prebookVO.setSearchKey(bookVO.getSearchKey());
        prebookVO.setSearchValue(bookVO.getSearchValue());
        prebookVO.setPageNum(bookVO.getPageNum());
        prebookVO.setPageSize(bookVO.getPageSize());
        prebookVO.setLibraryId(bookVO.getLibraryId());
        prebookVO.setLibraryName(bookVO.getLibraryName());
        prebookVO.setLibraryIdList(bookVO.getLibraryIdList());
    }

    private void setBookVO(BookVO bookVO, PrebookVO prebookVO) {
        bookVO.setId(prebookVO.getId());
        bookVO.setAuthor(prebookVO.getAuthor());
        bookVO.setBookType(prebookVO.getBookType());
        bookVO.setIsbn(prebookVO.getIsbn());
        bookVO.setPublisher(bookVO.getPublisher());
        bookVO.setTitle(bookVO.getTitle());
        bookVO.setIds(prebookVO.getIds());
        //bookVO.setGoodsCode(prebookVO.setGoodsCode());
        bookVO.setSearchKey(prebookVO.getSearchKey());
        bookVO.setSearchValue(prebookVO.getSearchValue());
        bookVO.setPageNum(prebookVO.getPageNum());
        bookVO.setPageSize(prebookVO.getPageSize());
        bookVO.setLibraryId(prebookVO.getLibraryId());
        bookVO.setLibraryName(prebookVO.getLibraryName());
        bookVO.setLibraryIdList(prebookVO.getLibraryIdList());
    }

    @Override
    public int deleteBatchIds(List<String> ids) {
        System.out.println("----------------"+ids.size());
        return prebookRepository.deleteBatchIds(ids);
    }

    @Override
    public int insert(Prebook prebook) {
        //String purchaseCode=IdGen.getDateUUId();
        //prebook.setPurchaseCode(purchaseCode);
        //Prebook prebook = new Prebook();
       // getEntity(prebook,prebook);
        return prebookRepository.insert(prebook);
    }


    public static void main(String[] args) {
        //System.out.println(getDateUUId());
    }
}
