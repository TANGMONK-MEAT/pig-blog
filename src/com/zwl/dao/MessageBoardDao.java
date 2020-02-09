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
     * �������԰����Ϣ
     * @param user_id �û�id
     * @param text ����
     */
    void add(String user_id,String text) throws SQLException;

    /**
     * ��ȡ���£�û�б��Ķ���������Ϣ�ĸ���
     * @return �������Ը���
     */
    int getNewMsgNum() throws SQLException;

    /**
     * ��ȡ���£�û�б��Ķ���������Ϣ����
     * @return ������Ϣ����
     */
    List<messageBoardBean> getNewMessageBoard() throws SQLException;
}
