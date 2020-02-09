package com.zwl.web.servlet;

import com.zwl.dao.MessageBoardDao;
import com.zwl.dao.implement.MessageBoardDaoImplement;
import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

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
 * @create: 2019/12/11 2:16
 */
@WebServlet(name = "GetNewMessageNumServlet", urlPatterns = "/GetNewMessageNumServlet")
public class GetNewMessageNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // π”√service
        MessageBoardDao dao = new MessageBoardDaoImplement();
        int num = 0;
        try {
            num = dao.getNewMsgNum();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json; charset=utf-8");
        String json = "{'num':'"+ num +"'}";
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
