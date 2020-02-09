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
     * 获取rows条动态信息
     * @param currentPage 当前页码
     * @param rows 每次获取的记录数
     * @return 封装有动态信息的PageBean对象
     */
    PageBean<DynamicBean> findDynamicByPage(String currentPage, String rows) throws SQLException;

    /**
     * 获取最新的几条数据
     * @param start 起始0
     * @param len 长度
     * @return 动态的信息集合
     */
    List<DynamicBean> findNewDynamicMsg(int start,int len) throws SQLException;

    /**
     * 根据id，获取指定的动态信息
     * @param id 动态id
     * @return 封装有动态信息的DynamicBean对象
     */
    DynamicBean findDynamic(String id) throws SQLException;

    /**
     * 根据点赞数，获取动态数据
     * @param start 起始0
     * @param len 长度
     * @return 动态的信息集合
     */
    List<DynamicBean> findOrderDynamicMsg(int start, int len) throws SQLException;

    /**
     * 获取LikeMsgBean集合s
     * @param ids 动态的id数组
     * @param user_id
     * @return LikeMsgBean的list集合
     */
    List<LikeMsgBean> getLikeMsgBeanList(String[] ids, String user_id) throws SQLException;

    /**
     * 点赞信息添加
     *
     * @param activeUserID
     * @param dy_id 活跃用户的id
     * @return true更新成功，否则false
     */
    Boolean addLikeMsg(String activeUserID, String dy_id) throws SQLException;

    /**
     * 点赞信息删除
     * @param activeUserID 活跃用户的id
     * @param dy_id 活跃用户的id
     * @return rue更新成功，否则false
     */
    Boolean delLikeMsg(String activeUserID, String dy_id) throws SQLException;

    /**
     * 根据动态id，获取发布动态的博主的名字
     * @param id
     * @return
     */
    String getNameByDyID(String id) throws SQLException;

    /**
     * 添加评论信息
     * @param dy_id 动态id
     * @param user_id 用户id
     * @param text 评论文本
     * @param date 评论时间
     */
    Boolean addCommentMsg(String dy_id, String user_id, String text, Date date) throws SQLException;

    /**
     * 根据动态id，获取关于该动态的最新的几条评论信息
     * @param dy_id 动态id
     * @param  num 评论数
     * @return 评论信息的list集合
     */
    List<CommentMsgBean> getCommentMsgByID(String dy_id,int num) throws SQLException;

    /**
     * 根据动态的id，删除动态
     * @param dy_id 动态的id
     * @return true代表删除成功；否则false
     */
    boolean deleteDynamicByID(String dy_id) throws SQLException;

    /**
     * 获取最新的没有被阅读的评论信息
     * @return 最新的评论个数
     */
    int getNewCommentNum() throws SQLException;

    /**
     * 获取最新的没有被阅读的评论
     * @return List集合，元素CommentMsgBean
     */
    List<CommentMsgBean> getNewCommentMsgList() throws SQLException;

    /**
     * 设置所有最新评论已读
     * @return true设置成功；否则false
     */
    boolean setNewCommentReaded() throws SQLException;

    /**
     * 删除指定id的评论
     * @param id
     * @return true代表成功；否则false
     */
    boolean deleteCommentByID(String id) throws SQLException;

}
