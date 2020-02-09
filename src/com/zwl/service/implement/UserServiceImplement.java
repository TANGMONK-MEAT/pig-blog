package com.zwl.service.implement;

import com.zwl.dao.UserDao;
import com.zwl.dao.implement.UserDaoImplement;
import com.zwl.javaBean.PageBean;
import com.zwl.javaBean.User;
import com.zwl.service.UserService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 实现UserService接口中的所有方法
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/8 22:33
 */
public class UserServiceImplement implements UserService {
    private UserDao dao = new UserDaoImplement();
    /**
     * 查询所有用户信息
     *
     * @reutrn 所有用户组成的一个list集合
     */
    @Override
    public List<User> getAllUser() throws SQLException {
        return dao.findAll();
    }

    /**
     * 获取组内的所有成员
     *
     * @param group 组编号
     * @return 所有用户组成的一个list集合
     */
    @Override
    public List<User> getGroupUsers(int group) throws SQLException {
        //调用dao的方法,找到组内的所有成员
        List<User> userList = dao.findAllByGroup(group);
        if(userList == null){
            return null;
        }
        return userList;
    }

    /**
     * 登入方法
     *
     * @param loginUser 封装有登录用户的信息的User对象
     * @reutrn 封装有登录用户，在数据库的数据的User对象
     */
    @Override
    public User login(User loginUser) throws SQLException {
        return dao.find(loginUser);
    }


    /**
     * 根据id删除User
     *
     * @param id User对象的编号（id）
     */
    @Override
    public boolean deleteUser(String id) throws SQLException {
        int rows = dao.delete(Integer.parseInt(id));
        if(rows>0){
            return true;
        }
        return false;
    }

    /**
     * 根据id查询用户(User)
     *
     * @param id User对象的编号（id）
     */
    @Override
    public User findUser(String id) throws SQLException {
        User user = dao.findByID(Integer.parseInt(id));
        return user;
    }

    /**
     * 修改用户信息
     *
     * @param user 封装有用户信息的User对象
     */
    @Override
    public boolean updateUserMsg(User user) throws SQLException {
        user.setUpdateTime(new Date());//设置最后一次修改的时间
        int i = dao.update(user);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的用户的id（编号）
     */
    @Override
    public void deleteSelectedUser(String[] ids) throws SQLException {
        if(ids != null && ids.length != 0){
            //循环获取id,删除指定id的用户
            for (String id : ids){
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    /**
     * 批量查询用户信息
     *
     * @param currentPage 当前页面属性
     * @param rows        要查询的记录数
     * @return PageBean的对象
     */
    @Override
    public PageBean<User> findUserByPage(String currentPage, String rows, Map<String,String[]> condition,int activeUserID) throws SQLException {
        //转换为int型数据，便于计算
        int _currentPage = Integer.parseInt(currentPage);
        int _rows = Integer.parseInt(rows);
        //创建一个空的PageBean对象
        PageBean<User> pageBean = new PageBean<User>();
        //调用dao的totalCount获取总记录数
        int totalCount = dao.findTotalCount(condition,activeUserID);
        //start = （currentPage - 1）* rows;计算查询起始点
        int start = (_currentPage - 1) * _rows;
        //调用dao查询,获取list集合
        List<User> userList = dao.findByPage(start, _rows,condition,activeUserID);
        //计算总页码
        int totalPage = (totalCount % _rows == 0 ? totalCount / _rows : totalCount / _rows + 1);

        //设置pageBean的属性
        pageBean.setGroup(activeUserID);
        pageBean.setCurrentPage(_currentPage);  //当前页码
        pageBean.setRows(_rows);                //显示的记录数
        pageBean.setTotalCount(totalCount);     //总记录数
        pageBean.setList(userList);             //每页的user集合
        pageBean.setTotalPage(totalPage);       //总页码数
        return pageBean;
    }


    /**
     * 根据id，修改用户的密码
     * @param oldPassword 旧密码
     * @param user_id 用户id
     * @param password 新密码
     * @return true代表修改成功；否则false
     */
    @Override
    public boolean updatePassword(String user_id, String oldPassword, String password) throws SQLException {
        if(dao.updatePassword(Integer.parseInt(user_id),oldPassword,password) > 0){
            return true;
        }
        return false;
    }

    /**
     * 注册方法
     *
     * @param user 封装有用户信息的User对象
     * @return 用户注册成功返回true;否则返回false
     */
    @Override
    public boolean addUser(User user) throws SQLException {
        User _user = dao.find(user);
        if(_user == null){
            int flag = dao.add(user);
            if(flag>0){
                return true;
            }
        }
        return false;
    }
}
