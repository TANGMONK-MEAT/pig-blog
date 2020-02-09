package com.zwl.web.servlet;

import com.zwl.dao.MessageBoardDao;
import com.zwl.dao.implement.MessageBoardDaoImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 3:52
 */
@WebServlet(name = "saveMessageBoardServlet", urlPatterns = "/saveMessageBoardServlet")
public class saveMessageBoardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String text = request.getParameter("text");
        String user_id = request.getParameter("activeUserID");
        MessageBoardDao dao = new MessageBoardDaoImplement();
        //插入数据
        try {
            dao.add(user_id,text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("save_msg","留言成功");
        request.getRequestDispatcher("/custom_jsp/messageBoard_iframe.jsp").forward(request,response);
    }
}
