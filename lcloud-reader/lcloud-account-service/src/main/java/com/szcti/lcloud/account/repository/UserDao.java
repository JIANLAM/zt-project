package com.szcti.lcloud.account.repository;

import com.szcti.lcloud.account.entity.RoleEntity;
import com.szcti.lcloud.account.entity.vo.ReaderVO;
import com.szcti.lcloud.account.entity.vo.StaffVO;
import com.szcti.lcloud.account.entity.vo.UserInfoVO;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Title: 用户DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface UserDao {
    /**
     * 根据用户ID查找读者
     * @param peopleId userId
     * @return ReaderVO
     */
    ReaderVO getReader(Long peopleId);

    /**
     * 根据用户id查找用户角色
     * @param userId
     * @return RoleEntity
     */
    List<RoleEntity> getRoleInfo(Long userId);

    /**
     * 根据用户ID查找职工
     * @param peopleId
     * @return ReaderVO
     */
    StaffVO getStaff(Long peopleId);

    /**
     * 根据用户ID查找用户
     * @param userId
     * @return UserInfoVO
     */
    UserInfoVO getUserInfo(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return void
     */
    void modifyPwd(@Param("userId") Long userId,@Param("newPassword") String newPassword);

    /**
     * 修改用户(t_user)信息
     * @param user
     */
    void updateUser(UserInfoVO user);

    /**
     * 新增用户(t_user)信息
     * @param user
     */
    void insertUser(UserInfoVO user);

    /**
     * 修改用户(t_people)信息
     * @param user
     */
    void updatePeople(UserInfoVO user);

    /**
     * 新增用户(t_people)信息
     * @param user
     */
    void insertPeople(UserInfoVO user);

    /**
     * 修改读者(t_reader)信息
     * @param reader
     */
    void updateReader(ReaderVO reader);

    /**
     * 新增读者(t_reader)信息
     * @param reader
     */
    void insertReader(ReaderVO reader);


    /**
     * 修改职员(t_staff)信息
     * @param staff
     */
    void updateStaff(StaffVO staff);

    /**
     * 新增职员(t_staff)信息
     * @param staff
     */
    void insertStaff(StaffVO staff);

    /************************      修改读者资料         读者微信端     ******************************/
    /**
     * 修改读者资料
     * @param userVO
     */
    void updateReadInfo(UserInfoVO userVO);
}
