package com.zwl.web.servlet;

import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/11 23:56
 */
@WebServlet(name = "DeleteSelectedServlet", urlPatterns = "/DeleteSelectedServlet")
public class DeleteSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取选中的id的数组
        String[] ids = request.getParameterValues("ids");
        //调用service方法批量删除，指定id的用户
        UserService userService = new UserServiceImplement();
        String activeUserID = request.getParameter("activeUserID");
        try {
            userService.deleteSelectedUser(ids);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //重定向到查询所有用户的UserListServlet
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet?activeUserID="+activeUserID);
    }
}
