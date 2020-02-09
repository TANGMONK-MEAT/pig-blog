package com.zwl.dao.implement;

import com.zwl.dao.DynamicDao;
import com.zwl.javaBean.CommentMsgBean;
import com.zwl.javaBean.DynamicBean;
import com.zwl.javaBean.LikeMsgBean;
import com.zwl.javaBean.messageBoardBean;
import com.zwl.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 0:16
 */
public class DynamicDaoImplement implements DynamicDao {
    QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());

    /**
     * 获取总记录数
     *
     * @return 记录数
     */
    @Override
    public int getTotalCount() throws SQLException {
        String sql = "select count(*) from dynamic_publication";
        Connection connection = C3P0Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int count = 0;
        if (resultSet.next()) {
            count = resultSet.getInt(1);
        }
        C3P0Util.closeAll(resultSet, statement, connection);
        return count;
    }

    /**
     * 根据id，返回动态信息对象
     *
     * @param id 动态
     * @return 动态信息对象
     */
    @Override
    public DynamicBean find(int id) throws SQLException {
        String sql = "select * from dynamic_publication where id=?";
        List<DynamicBean> list = qRunner.query(sql, new BeanListHandler<DynamicBean>(DynamicBean.class), id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 添加一条动态信息
     *
     * @param dynamicBean 封装有动态信息的对象
     * @return true代表添加成功，否则失败
     */
    @Override
    public boolean add(DynamicBean dynamicBean) throws SQLException {
        String sql = "insert into dynamic_publication values(?,?,?,?,?,?,?,?,?,?,?)";
        dynamicBean.setTitle(dynamicBean.getTitle().replaceAll("(\\r\\n|\\n|\\n\\r)",""));
        dynamicBean.setText(dynamicBean.getText().replaceAll("(\\r\\n|\\n|\\n\\r)","<br/>"));
        int rows = qRunner.update(sql, null, dynamicBean.getImg_0(), dynamicBean.getImg_1()
                , dynamicBean.getImg_2(), dynamicBean.getImg_3(), dynamicBean.getImg_4(),
                dynamicBean.getImg_5(), dynamicBean.getText(), dynamicBean.getCreateTime(), dynamicBean.getP_id(), dynamicBean.getTitle());
        if (rows == 1) {
            return true;
        }
        return false;
    }

    /**
     * 根据id，删除一条动态信息
     *
     * @param id 动态id
     * @return true代表添加成功，否则失败
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String downKey = "SET FOREIGN_KEY_CHECKS = 0;";
        String sql = "delete from dynamic_publication where id=?";
        String upKey = "SET FOREIGN_KEY_CHECKS = 1";
        qRunner.update(downKey);
        int rows = qRunner.update(sql, id);
        qRunner.update(upKey);
        if (rows == 1) {
            return true;
        }
        return false;
    }

    /**
     * 获取部分动态信息
     *
     * @param start 起始点
     * @param rows  查询的行数
     * @return 返回保存有这些动态信息的list集合
     */
    @Override
    public List<DynamicBean> findByPage(int start, int rows) throws SQLException {
        //sql语句
        String sql = "select * from dynamic_publication order by createTime desc limit ?,?";
        List<DynamicBean> dyList = qRunner.query(sql, new BeanListHandler<DynamicBean>(DynamicBean.class), start, rows);
        return dyList;
    }

    /**
     * 根据用户id获取用户名字
     *
     * @param id 编号
     * @return 姓名
     */
    @Override
    public String getName(int id) throws SQLException {
        String sql = "select name from poweruser where id=?";
        return getString(id, sql);
    }

    /**
     * 获取最新的几条动态信息
     *
     * @param start 起始 0
     * @param len   长度
     * @return 动态的信息list集合
     */
    @Override
    public List<DynamicBean> findNew(int start, int len) throws SQLException {
        if (start < 0 || len <= 0) {
            return null;
        }
        String sql = "select * from dynamic_publication ORDER BY createTime DESC LIMIT ?,?";
        List<DynamicBean> dyList = qRunner.query(sql, new BeanListHandler<DynamicBean>(DynamicBean.class), start, len);
        return dyList;
    }

    /**
     * 根据点赞数，获取动态数据集合
     *
     * @param start 起始0
     * @param len   长度
     * @return 动态的信息集合
     */
    @Override
    public List<DynamicBean> findByLike(int start, int len) throws SQLException {
        if (start < 0 || len <= 0) {
            return null;
        }
        //sql
        String sql = "SELECT d.id,d.img_0,d.img_1,d.img_2,d.img_3,d.img_4,d.img_5,d.text,d.createTime,d.p_id,d.title FROM dynamic_publication AS d,(SELECT dy_id,COUNT(*) AS like_num FROM likes_msg GROUP BY dy_id) AS l WHERE d.id=l.dy_id ORDER BY like_num DESC LIMIT ?,?;";
        //查询
        List<DynamicBean> dyList = qRunner.query(sql, new BeanListHandler<DynamicBean>(DynamicBean.class), start, len);
        if (dyList == null || dyList.isEmpty()) {
            return null;
        }
        return dyList;
    }

    /**
     * 根据动态的id，获取该动态的点赞信息
     *
     * @param id 动态id
     * @return 该动态的点赞个数
     */
    @Override
    public LikeMsgBean getLikeMsgBeanByID(int id) throws SQLException {
        List<LikeMsgBean> list = null;
        //sql
        String sql = "SELECT dy_id,like_num FROM (SELECT dy_id,user_id,COUNT(*) AS like_num FROM likes_msg GROUP BY dy_id) AS dc WHERE dc.dy_id=?";
        //查询
        list = qRunner.query(sql, new BeanListHandler<LikeMsgBean>(LikeMsgBean.class), id);
        if (list == null || list.isEmpty()) {//如果该动态没有点赞信息
            LikeMsgBean bean = new LikeMsgBean();
            bean.setDy_id(id);
            bean.setLike_num(0);
            bean.setUser_id(0);
            return bean;
        }
        return list.get(0);
    }

    /**
     * 根据用户名，动态id，查询该用户对该动态点过赞数
     *
     * @param user_id 用户id
     * @param dy_id   动态id
     * @return LikeMsgBean对象
     */
    public LikeMsgBean isLikeDynamic(String user_id, String dy_id) throws SQLException {
        //sql
        String sql = "SELECT dy_id,user_id,COUNT(*) FROM likes_msg WHERE dy_id=? AND user_id=? GROUP BY dy_id,user_id";
        List<LikeMsgBean> list = null;
        list = qRunner.query(sql, new BeanListHandler<LikeMsgBean>(LikeMsgBean.class), dy_id, user_id);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取最新的没有被阅读的评论信息
     *
     * @return 最新的评论个数
     */
    @Override
    public int getNewCommentNum() throws SQLException {
        String sql = "SELECT COUNT(*) FROM `comment` WHERE isRead=0";
        Connection connection = C3P0Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        return num;
    }

    /**
     * 获取最新的没有被阅读的评论
     *
     * @return List集合，元素CommentMsgBean
     */
    @Override
    public List<CommentMsgBean> getNewCommentList() throws SQLException {
        String sql = "SELECT A.id,`user`.`name`,A.text,A.createTime FROM `user`,(\tSELECT * FROM `comment` WHERE isRead=0) AS A WHERE A.user_id=`user`.id ORDER BY A.createTime DESC";
        List<CommentMsgBean> list = qRunner.query(sql, new BeanListHandler<CommentMsgBean>(CommentMsgBean.class));
        if(list == null || list.isEmpty()){
            return null;
        }
        return list;
    }

    /**
     * 设置所有最新评论已读
     *
     * @return true设置成功；否则false
     */
    @Override
    public int setNewCommentReaded() throws SQLException {
        String sql = "UPDATE `comment` SET isRead=1 WHERE isRead=0;";
        return qRunner.update(sql);
    }

    /**
     * 删除指定id的评论
     *
     * @param id 评论的id
     * @return 影响行数，1成功；0失败
     */
    @Override
    public int deleteComment(int id) throws SQLException {
        String sql = "DELETE FROM `comment` WHERE id=?";
        return qRunner.update(sql,id);
    }



    /**
     * 判断动态是否被指定id的用户点赞过
     *
     * @param user_id 用户的id
     * @return true代表点赞过
     */
    @Override
    public boolean findLikeMsgByID(int dy_id, int user_id) throws SQLException {
        String sql = "SELECT * FROM likes_msg WHERE dy_id=? AND user_id=?";
        Connection connection = C3P0Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, dy_id);
        preparedStatement.setInt(2, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            C3P0Util.closeAll(resultSet, preparedStatement, connection);
            return true;
        }
        C3P0Util.closeAll(resultSet, preparedStatement, connection);
        return false;
    }

    /**
     * 根据用户id，动态id，更新点赞信息
     *
     * @param user_id 用户id
     * @param dy_id   动态id
     * @return 更新影响的行数
     */
    @Override
    public int addLikes(String user_id, String dy_id) throws SQLException {
        //sql
        String sql = "insert into likes_msg values(?,?,?,?)";
        //插入
        return qRunner.update(sql, null, dy_id, user_id, new Date());
    }

    /**
     * 根据用户id，动态id，更新点赞信息
     *
     * @param user_id 用户id
     * @param dy_id   动态id
     * @return 更新影响的行数
     */
    @Override
    public int deleteLike(String user_id, String dy_id) throws SQLException {
        //sql
        String sql = null;
        sql = "delete from likes_msg where dy_id=? and user_id=?";
        return qRunner.update(sql, dy_id, user_id);
}

    /**
     * 根据动态id，获取发布动态的博主的名字
     *
     * @param id
     * @return 博主的名字
     */
    @Override
    public String getNameByDyID(int id) throws SQLException {
        //sql
        String sql = "SELECT `name` FROM poweruser WHERE poweruser.id=(SELECT p_id FROM dynamic_publication WHERE id=?)";
        return getString(id, sql);
    }

    /**
     * 添加评论信息
     *
     * @param dy_id   动态id
     * @param user_id 用户id
     * @param text    评论文本
     * @param date    评论时间
     * @return 影响行数
     */
    @Override
    public int add(String dy_id, String user_id, String text, Date date) throws SQLException {
        //sql
        String sql = "INSERT INTO comment VALUES(?,?,?,?,?,?)";
        if (user_id != null && (new UserDaoImplement()).findByID(Integer.parseInt(user_id)) == null) {
            user_id = "-1";
        }
        return qRunner.update(sql, null, dy_id, user_id, text, date,0);
    }

    /**
     * 根据动态id，获取关于该动态的最新的几条评论信息
     *
     * @param dy_id 动态id
     * @param num   评论数
     * @return 评论信息的list集合
     */
    @Override
    public List<CommentMsgBean> findCommentMsg(String dy_id, int num) throws SQLException {
        //sql
        String sql = "SELECT B.id,`name`,createTime,text FROM " +
                "(SELECT id,`name` FROM `user`WHERE id IN(SELECT user_id FROM `comment` WHERE dy_id=?)) AS A," +
                "(SELECT * FROM `comment` WHERE dy_id=?) AS B " +
                "WHERE A.id=B.user_id ORDER BY createTime DESC LIMIT 0,?";
        //查询
        List<CommentMsgBean> comList = qRunner.query(sql, new BeanListHandler<CommentMsgBean>(CommentMsgBean.class), dy_id, dy_id, num);
        if (comList != null && !comList.isEmpty()) {//如果查询到结果
            return comList;
        }
        return null;//没有查询到结果
    }

    /**
     * 通用id查询
     *
     * @param id
     * @param sql
     * @return
     * @throws SQLException
     */
    private String getString(int id, String sql) throws SQLException {
        Connection connection = C3P0Util.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        String name = null;
        while (resultSet.next()) {
            name = resultSet.getString(1);
        }
        C3P0Util.closeAll(resultSet, preparedStatement, connection);
        return name;
    }
}
