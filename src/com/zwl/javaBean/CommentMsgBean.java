package com.zwl.javaBean;

import java.util.Date;

/**
 * �������۵���Ϣ
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

    private int id;//���۵�id
    private String name;//�����ߵ�����
    private Date createTime;//���۵�ʱ��
    private String text;//����

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
