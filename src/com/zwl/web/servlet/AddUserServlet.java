package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/10 17:01
 */
@WebServlet(name = "AddUserServlet", urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User addUser = null;
        Map<String, String[]> parameterMap = null;
        try {
            //获取提交来的所有数据
            parameterMap = request.getParameterMap();
            //封装数据到User对象中
            addUser = new User();
            addUser.setCreateTime(new Date());
            addUser.setUpdateTime(new Date());
            addUser.setGroup(Integer.parseInt(parameterMap.get("activeUserID")[0]));//设置分组
            BeanUtils.populate(addUser,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service对象的addUser方法，完成数据保存
        UserService userService = new UserServiceImplement();
        boolean bool = false;//用户数据保存成功的标志位
        try {
            bool = userService.addUser(addUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bool){
            //如果用户数据保存成功
            //跳转到FindUserByPageServlet，再次查询所有用户，展示
            request.getRequestDispatcher("/FindUserByPageServlet?activeUseID="+parameterMap.get("activeUserID")[0]).forward(request,response);
        }else{
            //如果用户数据保存失败
            //保存提示信息，到request域中
            request.setAttribute("add_msg","此用户已经存在");
            request.setAttribute("fail_values",parameterMap);
            //转发到add.jsp页面
            request.getRequestDispatcher("/jsp/add.jsp").forward(request,response);
        }
    }
}
