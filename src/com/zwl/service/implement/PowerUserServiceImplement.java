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
     * 登入方法
     * @param loginUser 封装有登录用户的信息的PowerUser对象
     * @reutrn  封装有登录用户，在数据库的数据的PowerUser对象
     */
    @Override
    public PowerUser login(PowerUser loginUser) throws SQLException {
        return dao.find(loginUser);
    }

    /**
     * 在数据库查询博主的信息
     *
     * @return
     */
    @Override
    public PowerUser findMe() throws SQLException {
        return dao.findByID(1);
    }

    /**
     * 获取所有图片的虚拟路径
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
     * 修改博主信息
     * @param user 博主
     * @return true代表修该成功；否则false
     */
    @Override
    public boolean setPowerUserMsg(PowerUser user) throws SQLException {
        if(dao.setPowerUserMsg(user) > 0){
            return true;
        }
        return false;
    }

    /**
     * 获取指定id的博主信息
     *
     * @param id
     * @return PowerUser对象
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
