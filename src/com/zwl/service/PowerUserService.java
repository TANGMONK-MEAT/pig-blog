package com.zwl.service;

import com.zwl.javaBean.PowerUser;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:54
 */
public interface PowerUserService {
    /**
     * ���뷽��
     * @param loginUser ��װ�е�¼�û�����Ϣ��PowerUser����
     * @reutrn  ��װ�е�¼�û��������ݿ�����ݵ�PowerUser����
     */
    PowerUser login(PowerUser loginUser) throws SQLException;

    /**
     * ����id�������ݿ��ѯ��Ӧ�û�
     * @return
     */
    PowerUser findMe() throws SQLException;

    /**
     * ��ȡ����ͼƬ������·��
     * @return
     */
    List<String> getImagesContext() throws SQLException;

    /**
     * �޸Ĳ�����Ϣ
     * @param user ����
     * @return true�����޸óɹ�������false
     */
    boolean setPowerUserMsg(PowerUser user) throws SQLException;

    /**
     * ��ȡָ��id�Ĳ�����Ϣ
     * @return PowerUser����
     */
    PowerUser getPowerUserByID(int id) throws SQLException;
}
