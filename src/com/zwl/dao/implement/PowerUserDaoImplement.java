package com.zwl.dao.implement;

import com.zwl.dao.PowerUserDao;
import com.zwl.javaBean.PowerUser;
import com.zwl.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:53
 */
public class PowerUserDaoImplement implements PowerUserDao {
    QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    /**
     * 根据用户名，在数据库查询对应的用户
     *
     * @param loginUser 登录的PowerUser对象
     * @return 数据库查询到的PowerUser对象,；没有查到返回null
     */
    @Override
    public PowerUser find(PowerUser loginUser) throws SQLException {
        //sql语句
        String sql = "select * from poweruser where username=?";
        //查询
        List<PowerUser> userList = qRunner.query(sql,new BeanListHandler<PowerUser>(PowerUser.class),loginUser.getUsername());
        if(userList.isEmpty()){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 根据id，在数据库查询对应用户
     *
     * @param id
     * @return
     */
    @Override
    public PowerUser findByID(int id) throws SQLException {
        String sql = "select * from poweruser where id=?";
        List<PowerUser> userList = qRunner.query(sql,new BeanListHandler<PowerUser>(PowerUser.class),id);
        if(userList.isEmpty()){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 获取所有动态的虚拟路径
     *
     * @return
     */
    @Override
    public List<String> findImages() throws SQLException {
        List<String> imgList = new ArrayList<String>();
        String sql = "select img_0,img_1,img_2,img_3,img_4,img_5 from dynamic_publication";
        Connection connection = C3P0Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String img_0 = resultSet.getString("img_0");
            String img_1 = resultSet.getString("img_1");
            String img_2 = resultSet.getString("img_2");
            String img_3 = resultSet.getString("img_3");
            String img_4 = resultSet.getString("img_4");
            String img_5 = resultSet.getString("img_5");
            if(img_0 != null){
                imgList.add(img_0);
            }
            if(img_1 != null){
                imgList.add(img_1);
            }
            if(img_2 != null){
                imgList.add(img_2);
            }
            if(img_3 != null){
                imgList.add(img_3);
            }
            if(img_4 != null){
                imgList.add(img_4);
            }
            if(img_5 != null){
                imgList.add(img_5);
            }
        }
        C3P0Util.closeAll(resultSet,statement,connection);
        return imgList;
    }

    /**
     * 修改博主的简介，头像和座右铭
     */
    @Override
    public int setPowerUserMsg(PowerUser user) throws SQLException {
        String sql = "update poweruser set name=?,sex=?,age=?,phone=?,email=?,username=?,password=?,introduction=?,head_img=?,job=?,motto=? where id=?";
        user.setIntroduction(user.getIntroduction().replaceAll("(\\r\\n|\\n|\\n\\r)","<br/>"));
        user.setMotto(user.getMotto().replaceAll("(\\r\\n|\\n|\\n\\r)","<br/>"));
        return qRunner.update(sql, user.getName(),user.getSex(),user.getAge(),user.getPhone(),user.getEmail(),user.getUsername(),user.getPassword(),user.getIntroduction(),user.getHead_img(),user.getJob(),user.getMotto(),user.getId());
    }
}
