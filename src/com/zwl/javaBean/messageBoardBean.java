package com.zwl.javaBean;

import java.util.Date;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 3:54
 */
public class messageBoardBean {
    private int id;
    private String name;
    private String text;
    private Date createTime;
    private String isRead = "0";
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }



    @Override
    public String toString() {
        return "messageBoardBean{" +
                "id=" + id +
                ", name=" + name +
                ", createTime=" + createTime +
                '}';
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setUser_id(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
