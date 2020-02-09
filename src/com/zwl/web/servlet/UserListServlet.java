package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * 功能：将数据库的，user表的数据，展示到list.jsp页面
 * 步骤：
 * 	1,调用service层的getAllUser()方法，返回list集合，List<User>
 * 	2,将list集合,存入request域中，共享信息
 * 	3，转发到list.jsp页面展示
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/8 22:36
 */
@WebServlet(name = "UserListServlet", urlPatterns = "/UserListServlet")
public class UserListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,调用UserService的getAllUser方法,将所有用户对象，保存到list集合中
        UserService userService = new UserServiceImplement();
        List<User> users = null;
        try {
            users = userService.getAllUser();
        } catch (SQLException e) {
            e.printStackTrace();//输出异常信息到控制台
        }
        //将list集合保存到request域中
        request.setAttribute("users",users);
        //妆发到list.jsp页面
        request.getRequestDispatcher("/jsp/list.jsp").forward(request,response);
    }
}
