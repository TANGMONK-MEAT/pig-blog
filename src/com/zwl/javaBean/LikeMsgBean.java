package com.zwl.javaBean;

import java.util.Date;

/**
 * ������Ϣ��װ��
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/7 20:39
 */
public class LikeMsgBean {
    private int dy_id;//��̬id
    private int user_id = 0;//��˿�û�id  0,-2(-2�����û���¼��)
    private long like_num;//��̬�����޵ĸ���

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
