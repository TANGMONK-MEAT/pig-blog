package com.zwl.service.implement;

import com.zwl.dao.DynamicDao;
import com.zwl.dao.implement.DynamicDaoImplement;
import com.zwl.javaBean.CommentMsgBean;
import com.zwl.javaBean.DynamicBean;
import com.zwl.javaBean.LikeMsgBean;
import com.zwl.javaBean.PageBean;
import com.zwl.service.DynamicService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 14:21
 */
public class DynamicServiceImplement implements DynamicService {
    //���ݿ⹤����
    private DynamicDao dao = new DynamicDaoImplement();
    /**
     * ��ȡrows����̬��Ϣ
     *
     * @param currentPage ��ǰҳ��
     * @param rows        ÿ�λ�ȡ�ļ�¼��
     * @return ��װ�ж�̬��Ϣ��PageBean����
     */
    @Override
    public PageBean<DynamicBean> findDynamicByPage(String currentPage, String rows) throws SQLException {
        //ת��Ϊint�ͱ��ڼ���
        int _currentPage = Integer.parseInt(currentPage);
        int _rows = Integer.parseInt(rows);
        //����һ��PageBean����
        PageBean<DynamicBean> pageBean = new PageBean<DynamicBean>();
        //ʹ��dao����ȡ�ܼ�¼��
        int totalCount = dao.getTotalCount();
        //������ʼ��ѯ��
        int start = (_currentPage - 1) * _rows;
        //ʹ��dao����ѯ����ȡ��̬��list����
        List<DynamicBean> dyList = dao.findByPage(start,_rows);
        for(DynamicBean bean : dyList){
            bean.setP_name(dao.getName(bean.getP_id()));
        }
        //������ҳ��
        int totalPage = (totalCount % _rows == 0 ? totalCount / _rows : totalCount /_rows + 1);

        //����pageBean������
        pageBean.setCurrentPage(_currentPage);
        pageBean.setRows(_rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(dyList);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /**
     * ��ȡ���µļ�������
     *
     * @param start ��ʼ0
     * @param len   ����
     * @return ��̬����Ϣ
     */
    @Override
    public List<DynamicBean> findNewDynamicMsg(int start, int len) throws SQLException {
        return dao.findNew(start,len);
    }

    /**
     * ����id����ȡָ���Ķ�̬��Ϣ
     *
     * @param id ��̬id
     * @return ��װ�ж�̬��Ϣ��DynamicBean����
     */
    @Override
    public DynamicBean findDynamic(String id) throws SQLException {
        if(id == null){
            id = "-1";
        }
        int _id = Integer.parseInt(id);
        DynamicBean dynamicBean = dao.find(_id);
        dynamicBean.setP_name(dao.getNameByDyID(_id));
        return dynamicBean;
    }

    /**
     * ���ݵ���������ȡ��̬����
     *
     * @param start ��ʼ0
     * @param len   ����
     * @return ��̬����Ϣ����
     */
    @Override
    public List<DynamicBean> findOrderDynamicMsg(int start, int len) throws SQLException {
        return dao.findByLike(start,len);
    }

    /**
     * ��ȡLikeMsgBean����
     * @param ids ��̬��Ϣ��id����
     * @param user_id
     * @return LikeMsgBean��list����
     */
    @Override
    public List<LikeMsgBean> getLikeMsgBeanList(String[] ids, String user_id) throws SQLException {
        LikeMsgBean bean = null;
        List<LikeMsgBean> list = new ArrayList<LikeMsgBean>();
        for(String id : ids){
            if(id != null){
                bean = dao.getLikeMsgBeanByID(Integer.parseInt(id));
                if(user_id != null && dao.isLikeDynamic(user_id,id) != null){
                    bean.setUser_id(-2);
                }
                list.add(bean);
            }
        }
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    /**
     * ������Ϣ���
     *
     * @param activeUserID
     * @param dy_id ��Ծ�û���id
     * @return true���³ɹ�������false
     */
    @Override
    public Boolean addLikeMsg(String activeUserID, String dy_id) throws SQLException {
        int rows = dao.addLikes(activeUserID, dy_id);
        if(rows >= 0){
            return true;
        }
        return false;
    }

    /**
     * ������Ϣɾ��
     *
     * @param activeUserID ��Ծ�û���id
     * @param dy_id        ��Ծ�û���id
     * @return rue���³ɹ�������false
     */
    @Override
    public Boolean delLikeMsg(String activeUserID, String dy_id) throws SQLException {
        int rows = dao.deleteLike(activeUserID, dy_id);
        if(rows > 0){
            return true;
        }
        return false;
    }

    /**
     * ���ݶ�̬id����ȡ������̬�Ĳ���������
     *
     * @param id
     * @return
     */
    @Override
    public String getNameByDyID(String id) throws SQLException {
        return dao.getNameByDyID(Integer.parseInt(id));
    }

    /**
     * ���������Ϣ
     *  @param dy_id   ��̬id
     * @param user_id �û�id
     * @param text    �����ı�
     * @param date    ����ʱ��
     * @return
     */
    @Override
    public Boolean addCommentMsg(String dy_id, String user_id, String text, Date date) throws SQLException {
        if(dao.add(dy_id,user_id,text,date) >= 1){
            return true;
        }
        return false;
    }

    /**
     * ���ݶ�̬id����ȡ���ڸö�̬������������Ϣ
     *
     * @param dy_id ��̬id
     * @param  num ������
     * @return ������Ϣ��list����
     */
    @Override
    public List<CommentMsgBean> getCommentMsgByID(String dy_id,int num) throws SQLException {
        return dao.findCommentMsg(dy_id,num);
    }

    /**
     * ���ݶ�̬��id��ɾ����̬
     *
     * @param dy_id ��̬��id
     * @return true����ɾ���ɹ�������false
     */
    @Override
    public boolean deleteDynamicByID(String dy_id) throws SQLException {
        if(dao.delete(Integer.parseInt(dy_id))){
            return true;
        }
        return false;
    }

    /**
     * ��ȡ���µ�û�б��Ķ���������Ϣ
     *
     * @return ���µ����۸���
     */
    @Override
    public int getNewCommentNum() throws SQLException {
        return dao.getNewCommentNum();
    }

    /**
     * ��ȡ���µ�û�б��Ķ�������
     *
     * @return List���ϣ�Ԫ��CommentMsgBean
     */
    @Override
    public List<CommentMsgBean> getNewCommentMsgList() throws SQLException {
        return dao.getNewCommentList();
    }

    /**
     * �����������������Ѷ�
     *
     * @return true���óɹ�������false
     */
    @Override
    public boolean setNewCommentReaded() throws SQLException {
        if(dao.setNewCommentReaded() > 0){
            return true;
        }
        return false;
    }

    /**
     * ɾ��ָ��id������
     *
     * @param id
     * @return true����ɹ�������false
     */
    @Override
    public boolean deleteCommentByID(String id) throws SQLException {
        if(id != null && dao.deleteComment(Integer.parseInt(id)) > 0){
            return true;
        }
        return false;
    }
}
