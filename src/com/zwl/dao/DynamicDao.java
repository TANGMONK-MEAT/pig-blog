package com.zwl.dao;

import com.zwl.javaBean.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 0:10
 */
public interface DynamicDao {


    /**
     * ��ȡ�ܼ�¼��
     * @return ��¼��
     */
    public int getTotalCount() throws SQLException;
    /**
     * ����id�����ض�̬��Ϣ����
     * @param id ��̬
     * @return ��̬��Ϣ����
     */
    public DynamicBean find(int id) throws SQLException;
    /**
     * ���һ����̬��Ϣ
     * @param dynamicBean ��װ�ж�̬��Ϣ�Ķ���
     * @return 1������ӳɹ�������ʧ��
     */
    public boolean add(DynamicBean dynamicBean) throws SQLException;

    /**
     * ����id��ɾ��һ����̬��Ϣ
     * @param id ��̬id
     * @return 1����ɾ���ɹ�������ʧ��
     */
    public boolean delete(int id) throws SQLException;

    /**
     * ��ȡ���ֶ�̬��Ϣ
     * @param start ��ʼ��
     * @param rows ��ѯ������
     * @return ���ر�������Щ��̬��Ϣ��list����
     */
    List<DynamicBean> findByPage(int start, int rows) throws SQLException;

    /**
     * ����id��ȡ����
     * @param id ���
     * @return ����
     */
    String getName(int id) throws SQLException;

    /**
     * ��ȡ���µļ�������
     * @param start ��ʼ0
     * @param len ����
     * @return ��̬����Ϣ
     */
    List<DynamicBean> findNew(int start,int len) throws SQLException;

    /**
     * ���ݵ���������ȡ��̬���ݼ���
     *
     * @param start ��ʼ0
     * @param len   ����
     * @return ��̬����Ϣ����
     */
    List<DynamicBean> findByLike(int start,int len) throws SQLException;

    /**
     * ���ݶ�̬��id����ȡ�ö�̬�ĵ�����Ϣ
     * @param id ��̬id
     * @return �ö�̬�ĵ�����Ϣ
     */
    LikeMsgBean getLikeMsgBeanByID(int id) throws SQLException;

    /**
     * �ж϶�̬�Ƿ�ָ��id���û����޹�
     * @param user_id �û���id
     * @return true������޹�
     */
    boolean findLikeMsgByID(int dy_id,int user_id) throws SQLException;

    /**
     * �����û�id����̬id�����µ�����Ϣ
     * @param user_id �û�id
     * @param dy_id ��̬id
     * @return ����Ӱ�������
     */
    int addLikes(String user_id, String dy_id) throws SQLException;

    /**
     * �����û�id����̬id�����µ�����Ϣ
     * @param user_id �û�id
     * @param dy_id ��̬id
     * @return ����Ӱ�������
     */
    int deleteLike(String user_id, String dy_id) throws SQLException;

    /**
     * ���ݶ�̬id����ȡ������̬�Ĳ���������
     * @param id
     * @return ����������
     */
    String getNameByDyID(int id) throws SQLException;

    /**
     * ���������Ϣ
     * @param dy_id ��̬id
     * @param user_id �û�id
     * @param text �����ı�
     * @param date ����ʱ��
     */
    int add(String dy_id, String user_id, String text, Date date) throws SQLException;

    /**
     * ���ݶ�̬id����ȡ���ڸö�̬�����µļ���������Ϣ
     * @param dy_id ��̬id
     * @param  num ������
     * @return ������Ϣ��list����
     */
    List<CommentMsgBean> findCommentMsg(String dy_id,int num) throws SQLException;

    /**
     * �����û�������̬id����ѯ���û��Ըö�̬�������
     * @param user_id �û�id
     * @param dy_id ��̬id
     * @return LikeMsgBean����
     */
    LikeMsgBean isLikeDynamic(String user_id,String dy_id) throws SQLException;

    /**
     * ��ȡ���µ�û�б��Ķ���������Ϣ
     * @return ���µ����۸���
     */
    int getNewCommentNum() throws SQLException;

    /**
     * ��ȡ���µ�û�б��Ķ�������
     * @return List���ϣ�Ԫ��CommentMsgBean
     */
    List<CommentMsgBean> getNewCommentList() throws SQLException;

    /**
     * �����������������Ѷ�
     *
     * @return true���óɹ�������false
     */
    int setNewCommentReaded() throws SQLException;

    /**
     * ɾ��ָ��id������
     * @param id ���۵�id
     * @return Ӱ��������1�ɹ���0ʧ��
     */
    int deleteComment(int id) throws SQLException;


}
