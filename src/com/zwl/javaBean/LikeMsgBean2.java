package com.zwl.javaBean;

import java.util.Date;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 2:14
 */
public class LikeMsgBean2 {
    private int id;
    private int dy_id;
    private int user_id;
    private Date createTime;

    @Override
    public String toString() {
        return "LikeMsgBean2{" +
                "id=" + id +
                ", dy_id=" + dy_id +
                ", user_id=" + user_id +
                ", createTime=" + createTime +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
