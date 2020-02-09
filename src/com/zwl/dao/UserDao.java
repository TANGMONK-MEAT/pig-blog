package com.zwl.dao;

import com.zwl.javaBean.User;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/8 22:37
 */
public interface UserDao {
    /**
     * ��ѯ�����û���Ϣ
     * @reutrn �����û���ɵ�һ��list����
     */
    public List<User> findAll() throws SQLException;

    /**
     * ��ѯ���ڵ������û�
     * @param group ����
     * @return �����û���ɵ�һ��list����
     */
    public List<User> findAllByGroup(int group) throws SQLException;
    /**
     * �����û��������룬��ѯ
     * @param user user����
     * @return user����
     */
    public User find(User user) throws SQLException;

    /**
     * �����û���id����ѯ
     * @param userID user����
     * @return user����
     */
    public User findByID(int userID) throws SQLException;

    /**
     * ���û���Ϣ���浽���ݿ��user����
     * @param user ��װ���û���Ϣ��user����
     * @return �ɹ��������ݵ����ݿ⣬����ֵ>=1;����=0
     */
    public int add(User user) throws SQLException;

    /**
     * �����û��ر�ţ�ɾ��
     * @param id
     * @return ɾ��Ӱ���������=0����ɾ��ʧ�ܣ�>0����ɾ���ɹ�
     */
    public int delete(int id) throws SQLException;

    /**
     * �޸��û�����Ϣ
     * @param user �����޸ĵ���ʽ��Ϣ��User����
     * @return
     */
    public int update(User user) throws SQLException;

    /**
     * ��ѯ�ܼ�¼��
     * @return ���ز�ѯ���ܼ�¼��
     * @param condition
     */
    public int findTotalCount(Map<String, String[]> condition,int group) throws SQLException;

    /**
     * ��ҳ��ѯ
     * @param start ��ѯ����ʼ��
     * @param rows ��ѯ������
     * @param condition
     * @return ������User�����list����
     */
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition,int activeUserID) throws SQLException;

    /**
     * ����id���޸��û�������
     * @param oldPassword ������
     * @param user_id �û�id
     * @param password ������
     * @return true�����޸ĳɹ�������false
     */
    int updatePassword(int user_id,String oldPassword,String password) throws SQLException;
}
