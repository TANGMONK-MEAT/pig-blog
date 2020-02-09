package com.zwl.service;

import com.zwl.javaBean.PowerUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:54
 */
public interface PowerUserService {
    /**
     * 登入方法
     * @param loginUser 封装有登录用户的信息的PowerUser对象
     * @reutrn  封装有登录用户，在数据库的数据的PowerUser对象
     */
    PowerUser login(PowerUser loginUser) throws SQLException;

    /**
     * 根据id，在数据库查询对应用户
     * @return
     */
    PowerUser findMe() throws SQLException;

    /**
     * 获取所有图片的虚拟路径
     * @return
     */
    List<String> getImagesContext() throws SQLException;

    /**
     * 修改博主信息
     * @param user 博主
     * @return true代表修该成功；否则false
     */
    boolean setPowerUserMsg(PowerUser user) throws SQLException;

    /**
     * 获取指定id的博主信息
     * @return PowerUser对象
     */
    PowerUser getPowerUserByID(int id) throws SQLException;
}
