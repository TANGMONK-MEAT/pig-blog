package com.zwl.web.servlet;

import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 16:32
 */
@WebServlet(name = "SaveCommentServlet", urlPatterns = "/SaveCommentServlet")
public class SaveCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取service
        DynamicService service = new DynamicServiceImplement();
        //获取参数
        String user_id = request.getParameter("user_id");
        String dy_id = request.getParameter("dy_id");
        String text = request.getParameter("text");
        try {
            service.addCommentMsg(dy_id,user_id,text,new Date());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
