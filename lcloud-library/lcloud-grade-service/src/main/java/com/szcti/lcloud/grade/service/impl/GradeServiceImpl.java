package com.szcti.lcloud.grade.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.grade.entity.DictionaryEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;
import com.szcti.lcloud.grade.repository.DictionaryDao;
import com.szcti.lcloud.grade.repository.GradeDao;
import com.szcti.lcloud.grade.service.GradeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/7/30 8:53
 */
@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    @Autowired
    private DictionaryDao dictionaryDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull Long id){
        gradeDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(GradeEntity grade){
        if(grade!=null&&grade.getId()!=null){
            Integer count = gradeDao.getCountByCode(grade.getCode(),grade.getId());
            if(count!=null&&count>0){
                return false;
            }
            gradeDao.update(grade);
        }else{
            Integer count = gradeDao.getCountByCode(grade.getCode(),grade.getId());
            if(count!=null&&count>0){
                return false;
            }
            grade.setId(IdGen.randomLong());
            gradeDao.insert(grade);
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String upgrade(@NonNull Long id){
        GradeEntity grade = gradeDao.get(id);
        Integer next = gradeDao.hasNext(grade.getType(),grade.getLevel(),grade.getLibId());
        if(next>0){
            //需要先升级高年级
            return "higherFirst";
        }else{
            DictionaryEntity dic = dictionaryDao.getNext(grade.getType(),grade.getLevel());
            if(dic!=null){
                grade.setName(dic.getLabel());
                grade.setLevel(dic.getSort());
                gradeDao.update(grade);
                return "ok";
            }else{
                //没有更高年级
                return "noHigher";
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean graduate(@NonNull Long id){
        GradeEntity grade = gradeDao.get(id);
        DictionaryEntity dic = dictionaryDao.getNext(grade.getType(),grade.getLevel());
        if(dic!=null){
            //最高年级才能毕业
            return false;
        }else{
            grade.setGraduateTime(DateUtils.getDateTime());
            //graduate=1表示毕业
            grade.setGraduate(1);

            gradeDao.update(grade);
            return true;
        }
    }

}
