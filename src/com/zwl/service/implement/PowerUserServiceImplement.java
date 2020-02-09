package com.zwl.service.implement;

import com.zwl.dao.PowerUserDao;
import com.zwl.dao.implement.PowerUserDaoImplement;
import com.zwl.javaBean.PowerUser;
import com.zwl.service.PowerUserService;

import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:54
 */
public class PowerUserServiceImplement implements PowerUserService {
    private PowerUserDao dao = new PowerUserDaoImplement();
    /**
     * ���뷽��
     * @param loginUser ��װ�е�¼�û�����Ϣ��PowerUser����
     * @reutrn  ��װ�е�¼�û��������ݿ�����ݵ�PowerUser����
     */
    @Override
    public PowerUser login(PowerUser loginUser) throws SQLException {
        return dao.find(loginUser);
    }

    /**
     * �����ݿ��ѯ��������Ϣ
     *
     * @return
     */
    @Override
    public PowerUser findMe() throws SQLException {
        return dao.findByID(1);
    }

    /**
     * ��ȡ����ͼƬ������·��
     *
     * @return
     */
    @Override
    public List<String> getImagesContext() throws SQLException {
        List<String> images = dao.findImages();
        if(images.isEmpty()){
            return null;
        }
        return images;
    }

    /**
     * �޸Ĳ�����Ϣ
     * @param user ����
     * @return true�����޸óɹ�������false
     */
    @Override
    public boolean setPowerUserMsg(PowerUser user) throws SQLException {
        if(dao.setPowerUserMsg(user) > 0){
            return true;
        }
        return false;
    }

    /**
     * ��ȡָ��id�Ĳ�����Ϣ
     *
     * @param id
     * @return PowerUser����
     */
    @Override
    public PowerUser getPowerUserByID(int id) throws SQLException {
        PowerUser user = dao.findByID(id);
        if(user != null){
            return user;
        }
        return null;
    }
}
