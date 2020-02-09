package com.zwl.javaBean;

import java.util.Date;

/**封装动态信息的javaBean
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 0:03
 */
public class DynamicBean {
    private int id;
    private String img_0;//图片的虚拟地址
    private String img_1;
    private String img_2;
    private String img_3;
    private String img_4;
    private String img_5;
    private String text;//文本
    private Date createTime;//创建时间
    private int p_id;//属于博主
    private String p_name;
    private String title;


    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg_0() {
        return img_0;
    }

    public void setImg_0(String img_0) {
        this.img_0 = img_0;
    }

    public String getImg_1() {
        return img_1;
    }

    public void setImg_1(String img_1) {
        this.img_1 = img_1;
    }

    public String getImg_2() {
        return img_2;
    }

    public void setImg_2(String img_2) {
        this.img_2 = img_2;
    }

    public String getImg_3() {
        return img_3;
    }

    public void setImg_3(String img_3) {
        this.img_3 = img_3;
    }

    public String getImg_4() {
        return img_4;
    }

    public void setImg_4(String img_4) {
        this.img_4 = img_4;
    }

    public String getImg_5() {
        return img_5;
    }

    public void setImg_5(String img_5) {
        this.img_5 = img_5;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DynamicBean{" +
                "id='" + id + '\'' +
                ", img_0='" + img_0 + '\'' +
                ", img_1='" + img_1 + '\'' +
                ", img_2='" + img_2 + '\'' +
                ", img_3='" + img_3 + '\'' +
                ", img_4='" + img_4 + '\'' +
                ", img_5='" + img_5 + '\'' +
                ", text='" + text + '\'' +
                ", creatTime=" + createTime +
                '}';
    }
}
