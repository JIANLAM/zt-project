package com.szcti.lcloud.grade.service.impl;
import com.szcti.lcloud.common.utils.DateUtils;
import com.szcti.lcloud.common.utils.IdGen;
import com.szcti.lcloud.grade.entity.DeptEntity;
import com.szcti.lcloud.grade.entity.DictionaryEntity;
import com.szcti.lcloud.grade.entity.GradeEntity;
import com.szcti.lcloud.grade.repository.DeptDao;
import com.szcti.lcloud.grade.repository.DictionaryDao;
import com.szcti.lcloud.grade.repository.GradeDao;
import com.szcti.lcloud.grade.service.DeptService;
import com.szcti.lcloud.grade.service.GradeService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Title: 标题
 * @Description: 描述
 * @author: fengda
 * @date: 2018/8/29 8:53
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptDao deptDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(@NonNull Long id){
        deptDao.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(DeptEntity dept){
        if(dept!=null&&dept.getId()!=null){
            Integer count = deptDao.getCountByCode(dept.getCode(),dept.getId());
            if(count!=null&&count>0){
                return false;
            }
            deptDao.update(dept);
        }else{
            Integer count = deptDao.getCountByCode(dept.getCode(),dept.getId());
            if(count!=null&&count>0){
                return false;
            }
            dept.setId(IdGen.randomLong());
            deptDao.insert(dept);
        }
        return true;
    }

}
