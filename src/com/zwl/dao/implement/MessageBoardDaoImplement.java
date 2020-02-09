package com.zwl.dao.implement;

import com.zwl.dao.MessageBoardDao;
import com.zwl.javaBean.messageBoardBean;
import com.zwl.util.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 3:59
 */
public class MessageBoardDaoImplement implements MessageBoardDao {
    QueryRunner qRunner = new QueryRunner(C3P0Util.getDataSource());
    /**
     * 保存留言板的信息
     *
     * @param user_id 用户id
     * @param text    留言
     */
    @Override
    public void add(String user_id, String text) throws SQLException {
        //sql
        String sql = "insert into messageboard values(?,?,?,?,?)";
        //插入
        qRunner.update(sql, null, user_id, text, new Date(),0);
    }

    /**
     * 获取最新，没有被阅读的留言信息的个数
     *
     * @return 最新留言个数
     */
    @Override
    public int getNewMsgNum() throws SQLException {
        String sql = "SELECT count(*) FROM messageboard WHERE isRead=0 ORDER BY createTime DESC";
        Connection connection = C3P0Util.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int num = 0;
        while(resultSet.next()){
            num = resultSet.getInt(1);
        }
        C3P0Util.closeAll(resultSet,statement,connection);
        return num;
    }

    /**
     * 获取最新，没有被阅读的留言信息集合
     *
     * @return 留言信息集合
     */
    @Override
    public List<messageBoardBean> getNewMessageBoard() throws SQLException {
        String sql = "SELECT * FROM `user`,(SELECT * FROM messageboard WHERE isRead=0 ORDER BY createTime DESC) AS A WHERE  isRead=0 AND user_id=`user`.id ORDER BY A.createTime DESC";
        List<messageBoardBean> list = qRunner.query(sql,new BeanListHandler<messageBoardBean>(messageBoardBean.class));
        if(list != null && !list.isEmpty()){
            return list;
        }
        return null;
    }
}
