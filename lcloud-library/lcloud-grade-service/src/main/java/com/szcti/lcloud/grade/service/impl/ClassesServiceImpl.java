package com.szcti.lcloud.grade.service.impl;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.grade.entity.ClassesEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;
import com.szcti.lcloud.grade.repository.ClassesDao;
import com.szcti.lcloud.grade.service.ClassesService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/7/31 8:53
 */
@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesDao classesDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull Long id){
        classesDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ClassesEntity classes){
        if(classes!=null&&classes.getId()!=null){
            Integer classesCount = classesDao.getCountByCode(classes.getCode(),classes.getId());
            if(classesCount!=null&&classesCount>0){
                return false;
            }
            classesDao.update(classes);
        }else{
            Integer classesCount = classesDao.getCountByCode(classes.getCode(),classes.getId());
            if(classesCount!=null&&classesCount>0){
                return false;
            }
            classes.setId(IdGen.randomLong());
            classesDao.insert(classes);
        }
        return true;
    }

}
