package com.zwl.dao;

import com.zwl.javaBean.User;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/8 22:37
 */
public interface UserDao {
    /**
     * 查询所有用户信息
     * @reutrn 所有用户组成的一个list集合
     */
    public List<User> findAll() throws SQLException;

    /**
     * 查询组内的所有用户
     * @param group 组编号
     * @return 所有用户组成的一个list集合
     */
    public List<User> findAllByGroup(int group) throws SQLException;
    /**
     * 根据用户名和密码，查询
     * @param user user对象
     * @return user对象
     */
    public User find(User user) throws SQLException;

    /**
     * 根据用户名id，查询
     * @param userID user对象
     * @return user对象
     */
    public User findByID(int userID) throws SQLException;

    /**
     * 将用户信息保存到数据库的user表中
     * @param user 封装有用户信息的user对象
     * @return 成功插入数据到数据库，返回值>=1;否则=0
     */
    public int add(User user) throws SQLException;

    /**
     * 根据用户地编号，删除
     * @param id
     * @return 删除影响的行数，=0代表删除失败，>0代表删除成功
     */
    public int delete(int id) throws SQLException;

    /**
     * 修改用户的信息
     * @param user 存有修改的样式信息的User对象
     * @return
     */
    public int update(User user) throws SQLException;

    /**
     * 查询总记录数
     * @return 返回查询的总记录数
     * @param condition
     */
    public int findTotalCount(Map<String, String[]> condition,int group) throws SQLException;

    /**
     * 分页查询
     * @param start 查询的起始点
     * @param rows 查询的行数
     * @param condition
     * @return 保存有User对象的list集合
     */
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition,int activeUserID) throws SQLException;

    /**
     * 根据id，修改用户的密码
     * @param oldPassword 旧密码
     * @param user_id 用户id
     * @param password 新密码
     * @return true代表修改成功；否则false
     */
    int updatePassword(int user_id,String oldPassword,String password) throws SQLException;
}
