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
 * ���ܣ�
 * 1����ȡ�û���id
 * 2������id��ѯ�û���Ϣ User
 * 3����User����浽request
 * 4��ת����update.jspҳ��
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
        //��ȡ�û���id
        String id = request.getParameter("id");
        //����id��ѯ�û���Ϣ User
        UserService userService = new UserServiceImplement();
        String activeUserID = request.getParameter("activeUserID");
        User user = null;
        try {
            user = userService.findUser(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //�ж��Ƿ��ҵ�User
        if(user != null){
            //���û���Ϣ���� User ���浽request����
            request.setAttribute("user",user);
            request.setAttribute("activeUserID",activeUserID);
            //ת����update.jsp
            request.getRequestDispatcher("/jsp/update.jsp").forward(request,response);
        }
    }
}
