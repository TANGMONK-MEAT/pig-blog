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

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/9 15:03
 */
@WebServlet(name = "showPersonMsgServlet", urlPatterns = "/showPersonMsgServlet")
public class showPersonMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户id
        String id = request.getParameter("id");
        //使用service修改
        UserService service = new UserServiceImplement();
        //封装用户信息
        User user = null;
        try {
            user = service.findUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(user != null){
            request.setAttribute("user",user);
            request.getRequestDispatcher("/custom_jsp/updatePersonMsg.jsp").forward(request,response);
        }
    }
}
