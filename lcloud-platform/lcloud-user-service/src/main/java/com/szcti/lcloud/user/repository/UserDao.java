package com.szcti.lcloud.user.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.szcti.lcloud.user.entity.vo.ReaderVO;
import com.szcti.lcloud.user.entity.vo.UserInfoVO;


/**
 * @Title: 用户DAO
 * @Description: 描述
 * @author: fengda
 * @date: 2018/5/16 8:43
 */
@Mapper
//@CacheNamespace(implementation = com.szcti.lcloud.common.utils.RedisCache.class) 
public interface UserDao{
    /**
     * 根据用户ID查找读者
     * @param peopleId
     * @return ReaderVO
     */
    ReaderVO getReader(Long peopleId);



    /**
     * 根据用户ID查找用户
     * @param userId
     * @return UserInfoVO
     */
    UserInfoVO getUserInfo(Long userId);
    
    
    List<UserInfoVO> getUserList(@Param("username") String username,@Param("login_name") String login_name,@Param("create_account") String create_account,@Param("type") Integer type,@Param("orgid") Long orgid);

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


    
    void deleteUser(@Param("userId") Long userId);
}
