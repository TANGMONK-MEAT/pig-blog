package com.zwl.dao;

import com.zwl.javaBean.messageBoardBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 3:56
 */
public interface MessageBoardDao {
    /**
     * 保存留言板的信息
     * @param user_id 用户id
     * @param text 留言
     */
    void add(String user_id,String text) throws SQLException;

    /**
     * 获取最新，没有被阅读的留言信息的个数
     * @return 最新留言个数
     */
    int getNewMsgNum() throws SQLException;

    /**
     * 获取最新，没有被阅读的留言信息集合
     * @return 留言信息集合
     */
    List<messageBoardBean> getNewMessageBoard() throws SQLException;
}
