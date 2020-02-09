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
 * @create: 2019/12/9 21:19
 */
@WebServlet(name = "UpdatePersonPasswordServlet", urlPatterns = "/UpdatePersonPasswordServlet")
public class UpdatePersonPasswordServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String user_id = request.getParameter("user_id");
        String password = request.getParameter("password");
        String oldPassword = request.getParameter("oldPassword");
        //使用service修改密码
        UserService service = new UserServiceImplement();
        boolean bool = false;
        try {
            bool = service.updatePassword(user_id,oldPassword,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bool){
            request.setAttribute("update_msg","1");
        }
        request.getRequestDispatcher("custom_jsp/updatePassword.jsp").forward(request,response);
    }
}
