package com.zwl.dao;

import com.zwl.javaBean.DynamicBean;
import com.zwl.javaBean.PowerUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:52
 */
public interface PowerUserDao {

    /**
     * �����û����������ݿ��ѯ��Ӧ���û�
     *
     * @param loginUser ��¼��PowerUser����
     * @return ���ݿ��ѯ����PowerUser����,��û�в鵽����null
     */
    PowerUser find(PowerUser loginUser) throws SQLException;

    /**
     * ����id�������ݿ��ѯ��Ӧ�û�
     * @param id
     * @return
     */
    PowerUser findByID(int id) throws SQLException;

    /**
     * ��ȡ���ж�̬������·��
     * @return
     */
    List<String> findImages() throws SQLException;


    /**
     * ���ò����ļ�飬ͷ���������
     * @param user �û�����
     * @return Ӱ������
     */
    int setPowerUserMsg(PowerUser user) throws SQLException;
}
