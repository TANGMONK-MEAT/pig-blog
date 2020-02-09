package com.zwl.service;

import com.zwl.javaBean.PageBean;
import com.zwl.javaBean.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 用户数据管理的业务接口，声明一些对用户信息操作的抽象方法
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/7 18:00
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @reutrn 所有用户组成的一个list集合
     */
    public List<User> getAllUser() throws SQLException;

    /**
     * 获取组内的所有成员
     * @param group 组编号
     * @return 所有用户组成的一个list集合
     */
    public List<User> getGroupUsers(int group) throws SQLException;
    /**
     * 登入方法
     * @param loginUser 封装有登录用户的信息的User对象
     * @reutrn  封装有登录用户，在数据库的数据的User对象
     */
    public User login(User loginUser) throws SQLException;

    /**
     * 保存User对象
     * @param user 封装有用户信息的User对象
     * @return 用户注册成功返回true;否则返回false
     */
    public boolean addUser(User user) throws SQLException;

    /**
     * 根据id删除User
     * @param id User对象的编号（id）
     */
    public boolean deleteUser(String id) throws SQLException;

    /**
     * 根据id查询用户(User)
     * @param id User对象的编号（id）
     */
    public User findUser(String id) throws SQLException;

    /**
     * 修改用户信息
     * @param user 封装有用户信息的User对象
     */
    public boolean updateUserMsg(User user) throws SQLException;

    /**
     * 批量删除用户
     * @param ids 需要删除的用户的id（编号）
     */
    public void deleteSelectedUser(String[] ids) throws SQLException;

    /**
     * 批量查询用户信息
     * @param currentPage 查询起始点
     * @param rows  查询的记录数
     * @return  PageBea
     */
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String,String[]> condition,int group) throws SQLException;


    /**
     * 根据id，修改用户的密码
     *
     * @param oldPassword 旧密码
     * @param user_id 用户id
     * @param password 新密码
     * @return true代表修改成功；否则false
     */
    public boolean updatePassword(String user_id, String oldPassword, String password) throws SQLException;
}
