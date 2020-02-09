package com.zwl.javaBean;

import java.util.Date;

/**
 * 关于评论的信息
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 20:51
 */
public class CommentMsgBean {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;//评论的id
    private String name;//评论者的姓名
    private Date createTime;//评论的时间
    private String text;//评论

    @Override
    public String toString() {
        return "CommentMsgBean{" +
                "com_name='" + name + '\'' +
                ", com_time=" + createTime +
                ", text='" + text + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
