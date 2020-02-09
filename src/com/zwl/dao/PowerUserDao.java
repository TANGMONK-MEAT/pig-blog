package com.zwl.dao;

import com.zwl.javaBean.DynamicBean;
import com.zwl.javaBean.PowerUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:52
 */
public interface PowerUserDao {

    /**
     * 根据用户名，在数据库查询对应的用户
     *
     * @param loginUser 登录的PowerUser对象
     * @return 数据库查询到的PowerUser对象,；没有查到返回null
     */
    PowerUser find(PowerUser loginUser) throws SQLException;

    /**
     * 根据id，在数据库查询对应用户
     * @param id
     * @return
     */
    PowerUser findByID(int id) throws SQLException;

    /**
     * 获取所有动态的虚拟路径
     * @return
     */
    List<String> findImages() throws SQLException;


    /**
     * 设置博主的简介，头像和座右铭
     * @param user 用户对象
     * @return 影响行数
     */
    int setPowerUserMsg(PowerUser user) throws SQLException;
}
