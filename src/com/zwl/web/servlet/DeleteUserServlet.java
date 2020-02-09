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

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/10 13:07
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取id
        String id = request.getParameter("id");
        boolean bool = false;
        if(id != null){
            //调用service对象地deleteUser方法删除
            UserService userService = new UserServiceImplement();
            try {
                bool = userService.deleteUser(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //判断是否删除成功
            if(bool){
                //转发到UserListServlet
                request.getRequestDispatcher("/FindUserByPageServlet").forward(request,response);
            }
        }
    }
}
