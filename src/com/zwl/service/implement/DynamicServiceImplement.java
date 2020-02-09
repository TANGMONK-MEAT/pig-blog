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
    //数据库工具类
    private DynamicDao dao = new DynamicDaoImplement();
    /**
     * 获取rows条动态信息
     *
     * @param currentPage 当前页码
     * @param rows        每次获取的记录数
     * @return 封装有动态信息的PageBean对象
     */
    @Override
    public PageBean<DynamicBean> findDynamicByPage(String currentPage, String rows) throws SQLException {
        //转换为int型便于计算
        int _currentPage = Integer.parseInt(currentPage);
        int _rows = Integer.parseInt(rows);
        //声明一个PageBean对象
        PageBean<DynamicBean> pageBean = new PageBean<DynamicBean>();
        //使用dao，获取总记录数
        int totalCount = dao.getTotalCount();
        //计算起始查询点
        int start = (_currentPage - 1) * _rows;
        //使用dao，查询，获取动态的list集合
        List<DynamicBean> dyList = dao.findByPage(start,_rows);
        for(DynamicBean bean : dyList){
            bean.setP_name(dao.getName(bean.getP_id()));
        }
        //计算总页码
        int totalPage = (totalCount % _rows == 0 ? totalCount / _rows : totalCount /_rows + 1);

        //设置pageBean的属性
        pageBean.setCurrentPage(_currentPage);
        pageBean.setRows(_rows);
        pageBean.setTotalCount(totalCount);
        pageBean.setList(dyList);
        pageBean.setTotalPage(totalPage);
        return pageBean;
    }

    /**
     * 获取最新的几条数据
     *
     * @param start 起始0
     * @param len   长度
     * @return 动态的信息
     */
    @Override
    public List<DynamicBean> findNewDynamicMsg(int start, int len) throws SQLException {
        return dao.findNew(start,len);
    }

    /**
     * 根据id，获取指定的动态信息
     *
     * @param id 动态id
     * @return 封装有动态信息的DynamicBean对象
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
     * 根据点赞数，获取动态数据
     *
     * @param start 起始0
     * @param len   长度
     * @return 动态的信息集合
     */
    @Override
    public List<DynamicBean> findOrderDynamicMsg(int start, int len) throws SQLException {
        return dao.findByLike(start,len);
    }

    /**
     * 获取LikeMsgBean集合
     * @param ids 动态信息的id数组
     * @param user_id
     * @return LikeMsgBean的list集合
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
     * 点赞信息添加
     *
     * @param activeUserID
     * @param dy_id 活跃用户的id
     * @return true更新成功，否则false
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
     * 点赞信息删除
     *
     * @param activeUserID 活跃用户的id
     * @param dy_id        活跃用户的id
     * @return rue更新成功，否则false
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
     * 根据动态id，获取发布动态的博主的名字
     *
     * @param id
     * @return
     */
    @Override
    public String getNameByDyID(String id) throws SQLException {
        return dao.getNameByDyID(Integer.parseInt(id));
    }

    /**
     * 添加评论信息
     *  @param dy_id   动态id
     * @param user_id 用户id
     * @param text    评论文本
     * @param date    评论时间
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
     * 根据动态id，获取关于该动态的所有评论信息
     *
     * @param dy_id 动态id
     * @param  num 评论数
     * @return 评论信息的list集合
     */
    @Override
    public List<CommentMsgBean> getCommentMsgByID(String dy_id,int num) throws SQLException {
        return dao.findCommentMsg(dy_id,num);
    }

    /**
     * 根据动态的id，删除动态
     *
     * @param dy_id 动态的id
     * @return true代表删除成功；否则false
     */
    @Override
    public boolean deleteDynamicByID(String dy_id) throws SQLException {
        if(dao.delete(Integer.parseInt(dy_id))){
            return true;
        }
        return false;
    }

    /**
     * 获取最新的没有被阅读的评论信息
     *
     * @return 最新的评论个数
     */
    @Override
    public int getNewCommentNum() throws SQLException {
        return dao.getNewCommentNum();
    }

    /**
     * 获取最新的没有被阅读的评论
     *
     * @return List集合，元素CommentMsgBean
     */
    @Override
    public List<CommentMsgBean> getNewCommentMsgList() throws SQLException {
        return dao.getNewCommentList();
    }

    /**
     * 设置所有最新评论已读
     *
     * @return true设置成功；否则false
     */
    @Override
    public boolean setNewCommentReaded() throws SQLException {
        if(dao.setNewCommentReaded() > 0){
            return true;
        }
        return false;
    }

    /**
     * 删除指定id的评论
     *
     * @param id
     * @return true代表成功；否则false
     */
    @Override
    public boolean deleteCommentByID(String id) throws SQLException {
        if(id != null && dao.deleteComment(Integer.parseInt(id)) > 0){
            return true;
        }
        return false;
    }
}
