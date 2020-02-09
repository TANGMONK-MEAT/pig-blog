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
     * 获取总记录数
     * @return 记录数
     */
    public int getTotalCount() throws SQLException;
    /**
     * 根据id，返回动态信息对象
     * @param id 动态
     * @return 动态信息对象
     */
    public DynamicBean find(int id) throws SQLException;
    /**
     * 添加一条动态信息
     * @param dynamicBean 封装有动态信息的对象
     * @return 1代表添加成功，否则失败
     */
    public boolean add(DynamicBean dynamicBean) throws SQLException;

    /**
     * 根据id，删除一条动态信息
     * @param id 动态id
     * @return 1代表删除成功，否则失败
     */
    public boolean delete(int id) throws SQLException;

    /**
     * 获取部分动态信息
     * @param start 起始点
     * @param rows 查询的行数
     * @return 返回保存有这些动态信息的list集合
     */
    List<DynamicBean> findByPage(int start, int rows) throws SQLException;

    /**
     * 根据id获取名字
     * @param id 编号
     * @return 姓名
     */
    String getName(int id) throws SQLException;

    /**
     * 获取最新的几条数据
     * @param start 起始0
     * @param len 长度
     * @return 动态的信息
     */
    List<DynamicBean> findNew(int start,int len) throws SQLException;

    /**
     * 根据点赞数，获取动态数据集合
     *
     * @param start 起始0
     * @param len   长度
     * @return 动态的信息集合
     */
    List<DynamicBean> findByLike(int start,int len) throws SQLException;

    /**
     * 根据动态的id，获取该动态的点赞信息
     * @param id 动态id
     * @return 该动态的点赞信息
     */
    LikeMsgBean getLikeMsgBeanByID(int id) throws SQLException;

    /**
     * 判断动态是否被指定id的用户点赞过
     * @param user_id 用户的id
     * @return true代表点赞过
     */
    boolean findLikeMsgByID(int dy_id,int user_id) throws SQLException;

    /**
     * 根据用户id，动态id，更新点赞信息
     * @param user_id 用户id
     * @param dy_id 动态id
     * @return 更新影响的行数
     */
    int addLikes(String user_id, String dy_id) throws SQLException;

    /**
     * 根据用户id，动态id，更新点赞信息
     * @param user_id 用户id
     * @param dy_id 动态id
     * @return 更新影响的行数
     */
    int deleteLike(String user_id, String dy_id) throws SQLException;

    /**
     * 根据动态id，获取发布动态的博主的名字
     * @param id
     * @return 博主的名字
     */
    String getNameByDyID(int id) throws SQLException;

    /**
     * 添加评论信息
     * @param dy_id 动态id
     * @param user_id 用户id
     * @param text 评论文本
     * @param date 评论时间
     */
    int add(String dy_id, String user_id, String text, Date date) throws SQLException;

    /**
     * 根据动态id，获取关于该动态的最新的几条评论信息
     * @param dy_id 动态id
     * @param  num 评论数
     * @return 评论信息的list集合
     */
    List<CommentMsgBean> findCommentMsg(String dy_id,int num) throws SQLException;

    /**
     * 根据用户名，动态id，查询该用户对该动态点过赞数
     * @param user_id 用户id
     * @param dy_id 动态id
     * @return LikeMsgBean对象
     */
    LikeMsgBean isLikeDynamic(String user_id,String dy_id) throws SQLException;

    /**
     * 获取最新的没有被阅读的评论信息
     * @return 最新的评论个数
     */
    int getNewCommentNum() throws SQLException;

    /**
     * 获取最新的没有被阅读的评论
     * @return List集合，元素CommentMsgBean
     */
    List<CommentMsgBean> getNewCommentList() throws SQLException;

    /**
     * 设置所有最新评论已读
     *
     * @return true设置成功；否则false
     */
    int setNewCommentReaded() throws SQLException;

    /**
     * 删除指定id的评论
     * @param id 评论的id
     * @return 影响行数，1成功；0失败
     */
    int deleteComment(int id) throws SQLException;


}
