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
import java.util.Map;

/**
 * 功能：
 * 1，设置编码
 * 2，获取update.jsp表单的数据  map集合
 * 3,封装User对象
 * 4，调用service方法,完成修改
 * 5,跳转到UserListServlet
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/10 14:34
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        Map<String, String[]> parameterMap = null;
        try {
            //获取update.jsp表单提交的数据
            parameterMap = request.getParameterMap();
            user = new User();
            //封装User对象
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service方法完成修改
        UserService userService = new UserServiceImplement();
        boolean bool = false;
        try {
            bool = userService.updateUserMsg(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bool){
            //如果修改成功，跳转到UserListServlet
            request.setAttribute("activeUserID",parameterMap.get("activeUserID")[0]);
            request.getRequestDispatcher("/FindUserByPageServlet").forward(request,response);
        }else{
        }
    }
}
