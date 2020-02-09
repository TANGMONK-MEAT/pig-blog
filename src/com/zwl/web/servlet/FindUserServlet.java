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

/**
 * 功能：
 * 1，获取用户的id
 * 2，根据id查询用户信息 User
 * 3，将User对象存到request
 * 4，转发到update.jsp页面
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/10 15:05
 */
@WebServlet(name = "FindUserServlet", urlPatterns = "/FindUserServlet")
public class FindUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户的id
        String id = request.getParameter("id");
        //根据id查询用户信息 User
        UserService userService = new UserServiceImplement();
        String activeUserID = request.getParameter("activeUserID");
        User user = null;
        try {
            user = userService.findUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //判断是否找到User
        if(user != null){
            //将用户信息对象 User 保存到request域中
            request.setAttribute("user",user);
            request.setAttribute("activeUserID",activeUserID);
            //转发到update.jsp
            request.getRequestDispatcher("/jsp/update.jsp").forward(request,response);
        }
    }
}
