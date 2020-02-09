package com.zwl.javaBean;

import java.util.Date;

/**
 * 点赞信息封装类
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/7 20:39
 */
public class LikeMsgBean {
    private int dy_id;//动态id
    private int user_id = 0;//粉丝用户id  0,-2(-2代表用户登录了)
    private long like_num;//动态被点赞的个数

    public int getDy_id() {
        return dy_id;
    }

    public void setDy_id(int dy_id) {
        this.dy_id = dy_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getLike_num() {
        return like_num;
    }

    public void setLike_num(long num) {
        this.like_num = num;
    }
}
