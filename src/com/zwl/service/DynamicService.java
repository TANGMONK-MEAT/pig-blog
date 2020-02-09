package com.zwl.service;

import com.zwl.javaBean.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 1:10
 */
public interface DynamicService {
    /**
     * ��ȡrows����̬��Ϣ
     * @param currentPage ��ǰҳ��
     * @param rows ÿ�λ�ȡ�ļ�¼��
     * @return ��װ�ж�̬��Ϣ��PageBean����
     */
    PageBean<DynamicBean> findDynamicByPage(String currentPage, String rows) throws SQLException;

    /**
     * ��ȡ���µļ�������
     * @param start ��ʼ0
     * @param len ����
     * @return ��̬����Ϣ����
     */
    List<DynamicBean> findNewDynamicMsg(int start,int len) throws SQLException;

    /**
     * ����id����ȡָ���Ķ�̬��Ϣ
     * @param id ��̬id
     * @return ��װ�ж�̬��Ϣ��DynamicBean����
     */
    DynamicBean findDynamic(String id) throws SQLException;

    /**
     * ���ݵ���������ȡ��̬����
     * @param start ��ʼ0
     * @param len ����
     * @return ��̬����Ϣ����
     */
    List<DynamicBean> findOrderDynamicMsg(int start, int len) throws SQLException;

    /**
     * ��ȡLikeMsgBean����s
     * @param ids ��̬��id����
     * @param user_id
     * @return LikeMsgBean��list����
     */
    List<LikeMsgBean> getLikeMsgBeanList(String[] ids, String user_id) throws SQLException;

    /**
     * ������Ϣ���
     *
     * @param activeUserID
     * @param dy_id ��Ծ�û���id
     * @return true���³ɹ�������false
     */
    Boolean addLikeMsg(String activeUserID, String dy_id) throws SQLException;

    /**
     * ������Ϣɾ��
     * @param activeUserID ��Ծ�û���id
     * @param dy_id ��Ծ�û���id
     * @return rue���³ɹ�������false
     */
    Boolean delLikeMsg(String activeUserID, String dy_id) throws SQLException;

    /**
     * ���ݶ�̬id����ȡ������̬�Ĳ���������
     * @param id
     * @return
     */
    String getNameByDyID(String id) throws SQLException;

    /**
     * ���������Ϣ
     * @param dy_id ��̬id
     * @param user_id �û�id
     * @param text �����ı�
     * @param date ����ʱ��
     */
    Boolean addCommentMsg(String dy_id, String user_id, String text, Date date) throws SQLException;

    /**
     * ���ݶ�̬id����ȡ���ڸö�̬�����µļ���������Ϣ
     * @param dy_id ��̬id
     * @param  num ������
     * @return ������Ϣ��list����
     */
    List<CommentMsgBean> getCommentMsgByID(String dy_id,int num) throws SQLException;

    /**
     * ���ݶ�̬��id��ɾ����̬
     * @param dy_id ��̬��id
     * @return true����ɾ���ɹ�������false
     */
    boolean deleteDynamicByID(String dy_id) throws SQLException;

    /**
     * ��ȡ���µ�û�б��Ķ���������Ϣ
     * @return ���µ����۸���
     */
    int getNewCommentNum() throws SQLException;

    /**
     * ��ȡ���µ�û�б��Ķ�������
     * @return List���ϣ�Ԫ��CommentMsgBean
     */
    List<CommentMsgBean> getNewCommentMsgList() throws SQLException;

    /**
     * �����������������Ѷ�
     * @return true���óɹ�������false
     */
    boolean setNewCommentReaded() throws SQLException;

    /**
     * ɾ��ָ��id������
     * @param id
     * @return true����ɹ�������false
     */
    boolean deleteCommentByID(String id) throws SQLException;

}
