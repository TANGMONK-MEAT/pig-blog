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
     * �������԰����Ϣ
     *
     * @param user_id �û�id
     * @param text    ����
     */
    @Override
    public void add(String user_id, String text) throws SQLException {
        //sql
        String sql = "insert into messageboard values(?,?,?,?,?)";
        //����
        qRunner.update(sql, null, user_id, text, new Date(),0);
    }

    /**
     * ��ȡ���£�û�б��Ķ���������Ϣ�ĸ���
     *
     * @return �������Ը���
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
     * ��ȡ���£�û�б��Ķ���������Ϣ����
     *
     * @return ������Ϣ����
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
