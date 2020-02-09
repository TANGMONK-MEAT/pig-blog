package com.zwl.dao.implement;

import com.zwl.dao.UserDao;
import com.zwl.javaBean.User;
import com.zwl.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/8 22:37
 */
public class UserDaoImplement implements UserDao {
    //获取session对象
    //封装连接池,提供数据源（连接池），DBUtils底层自动维护连接connection
    QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    /**
     * 查询所有用户信息
     * @reutrn 所有用户组成的一个list集合
     */
    @Override
    public List<User> findAll() throws SQLException {
        //SQL语句,查询数据库，user表中的所有记录
        String sql = "select * from user";
        //将结果集中的每一条记录封装到javaBean中，再将javaBean保存到list集合中
        List<User> userList = qRunner.query(sql, new BeanListHandler<User>(User.class));
        return userList;
    }

    /**
     * 查询组内的所有用户
     *
     * @param group 组编号
     * @return 所有用户组成的一个list集合
     */
    @Override
    public List<User> findAllByGroup(int group) throws SQLException {
        //sql语句
        String sql = "select * from user where group=?";
        List<User> userList = qRunner.query(sql,new BeanListHandler<User>(User.class),group);
        return userList;
    }

    /**
     * 根据用户名和密码，在数据库查询对应的用户
     *
     * @param user 登录的user对象
     * @return 数据库查询到的user对象,；没有查到返回null
     */
    @Override
    public User find(User user) throws SQLException {
        //sql语句
        String sql = "select * from user where username=?";
        //查询
        List<User> userList = qRunner.query(sql,new BeanListHandler<User>(User.class),user.getUsername());
        if(userList.isEmpty()){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 根据用户名id，查询
     *
     * @param userID user对象
     * @return user对象
     */
    @Override
    public User findByID(int userID) throws SQLException {
        //sql语句
        String sql = "select * from user where id=?";
        //查询
        List<User> userList = qRunner.query(sql,new BeanListHandler<User>(User.class),userID);
        if(userList.isEmpty()){
            return null;
        }
        return userList.get(0);
    }

    /**
     * 将用户信息保存到数据库的user表中
     *
     * @param user 封装有用户信息的user对象
     * @return 成功插入数据到数据库，返回值>=1;否则=0
     */
    @Override
    public int add(User user) throws SQLException {
        //sql语句
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?,?,?)";
        //插入数据到数据库
        int rows = qRunner.update(sql,null,user.getName(),user.getSex(),user.getAge(),user.getAddress(),user.getPhone(),user.getEmail(),user.getUsername(),user.getPassword(),user.getCreateTime(),
                user.getUpdateTime(),1);
        return rows;//返回影响的行数
    }

    /**
     * 根据用户地编号，删除
     *
     * @param id
     */
    @Override
    public int delete(int id) throws SQLException {
        //sql语句
        String sql = "delete from user where id=?";
        int rows = qRunner.update(sql,id);
        return rows;
    }

    /**
     * 修改1用户的信息
     *
     * @param user 存有修改的样式信息的User对象
     * @return
     */
    @Override
    public int update(User user) throws SQLException {
        //sql语句
        String sql = "update user set name=?,sex=?,age=?,address=?,phone=?,email=? where id=?";
        //执行修改
        int rows = qRunner.update(sql, user.getName(), user.getSex(), user.getAge(), user.getAddress(), user.getPhone(), user.getEmail(), user.getId());
        return rows;
    }

    /**
     * 查询总记录数
     *
     * @return 返回查询的总记录数
     * @param condition 存储查询信息的map集合
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition,int activeUserID) throws SQLException {
        int group = activeUserID;
        //模板sql语句
        String sql = "select count(*) from `user` where 1=1 and `group`="+group+" ";
        //实例化可变字符串对象，用于拼接sql语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        //用于存储（条件map集合）的值
        String str = null;
        //用于记录通配符数
        int i = 0;
        //保存值
        String[] values = new String[3];
        //获取连接对象
        Connection connection = C3P0Util.getConnection();
        //获取执行者对象
        PreparedStatement statement;
        if(condition.get("find") != null){
            //遍历map集合condition
            for(String key : condition.keySet()){
                if(key.equals("currentPage") || key.equals("rows") || key.equals("find") || key.equals("activeUserID")){//排除
                    continue;
                }
                //获取value值
                str = condition.get(key)[0];
                //判断value是否有值
                if(str != null && !str.equals("")){//value有值
                    //重构模板sql
                    stringBuilder.append(" and " + key + " like " + "?");
                    //保存value值
                    values[i] = str;
                    //索引自增
                    i++;
                }
            }
        }
        stringBuilder.append(" GROUP BY `group`");
        statement = connection.prepareStatement(stringBuilder.toString());
        System.out.println(stringBuilder.toString());
        //设置通配符的值
        for(int j = 1;j <= i;j++){
            statement.setObject(j,"%"+values[j-1]+"%");
        }
        //获取查询结果集
        ResultSet resultSet = statement.executeQuery();
        //用于存储查询结果（记录数）
        int count = 0;
        while(resultSet.next()){
            count = resultSet.getInt(1);
        }
        //关闭资源
        C3P0Util.closeAll(resultSet,statement,connection);
        return count;//返回查询到的记录数
    }
    /**
     * 分页查询记录
     *
     * @param start 查询起始点
     * @param rows 查询的行数
     * @param condition 条件参数的map集合
     * @return 保存有User对象的list集合
     */
    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition,int activeUserID) throws SQLException {
        int group = activeUserID;
        //模板sql语句
        String sql = "select * from `user` where 1=1 and `group`="+group+" ";
        //实例化可变字符串对象，用于拼接sql语句
        StringBuilder stringBuilder = new StringBuilder(sql);
        //用于存储（条件map集合）的值
        String str = null;
        //用于记录通配符数
        int i = 0;
        //保存值
        List<Object> values = new ArrayList<Object>();
        if(condition.get("find") != null){
            //遍历map集合condition
            for(String key : condition.keySet()){
                if(key.equals("currentPage") || key.equals("rows") || key.equals("find") || key.equals("activeUserID")){//排除
                    continue;
                }
                //获取value值
                str = condition.get(key)[0];
                //判断value是否有值
                if(str != null && !str.equals("")){//value有值
                    //重构模板sql
                    stringBuilder.append(" and " + key + " like " + "?");
                    //保存value值
                    values.add("%"+str+"%");
                    //索引自增
                    i++;
                }
            }
        }
        //添加分页查询
        stringBuilder.append(" limit ?,?");
        //添加分页查询参数值
        values.add(start);
        values.add(rows);

        sql = stringBuilder.toString();
        System.out.println(sql);
        List<User> userList = qRunner.query(sql, new BeanListHandler<User>(User.class),values.toArray());
        return userList;
    }

    /**
     * 根据id，修改用户的密码
     * @param oldPassword 旧密码
     * @param user_id 用户id
     * @param password 新密码
     * @return true代表修改成功；否则false
     */
    @Override
    public int updatePassword(int user_id,String oldPassword,String password) throws SQLException {
        //sql
        String sql = "UPDATE `user` SET `password`=? WHERE id=? AND `password`=?";
        int rows = qRunner.update(sql, password, user_id,oldPassword);
        return rows;
    }
}
