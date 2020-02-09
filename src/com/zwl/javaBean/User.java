package com.zwl.javaBean;

import java.util.Date;

/**
 * User类
 * 用于封装粉丝信息的类
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/7 17:33
 */
public class User {
    private int id;
    private String name;
    private String sex;
    private int age;
    private String address;
    private String phone;
    private String email;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private int group;
//    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:ss");

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 返回对象创建的时间
     * @return 时间自字符串，或者null
     */
//    public String getCreateTime() {
//        if(this.createTime != null){
//            return simpleDateFormat.format(createTime);
//        }
//        return null;
//    }

    public Date getCreateTime(){
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 返回对象修改的时间
     * @return 时间自字符串,或者null
     */
//    public String getUpdateTime() {
//        if(this.updateTime != null){
//            return simpleDateFormat.format(this.updateTime);
//        }
//        return null;
//    }
    public Date getUpdateTime(){
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }


    /**
     * 返回user对象的所有字段的值
     * @return user对象的所有字段的值
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", qq='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +'\'' +
                ", updateTime=" + updateTime +'\'' +
                '}';
    }

    /**
     * 判断两个user对象是否相等
     * 依据：密码和账号
     * @param user
     * @return 两个user对象符合条件返回true;否则返回false
     */
    @Override
    public boolean equals(Object user) {
        if(user instanceof User){
            User loginUser = (User)user;
            if(loginUser.getUsername().equals(this.username) && loginUser.getPassword().equals(this.password)){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}
