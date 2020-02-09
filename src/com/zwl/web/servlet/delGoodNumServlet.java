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

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 2:26
 */
@WebServlet(name = "delGoodNumServlet", urlPatterns = "/delGoodNumServlet")
public class delGoodNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取service
        DynamicService service = new DynamicServiceImplement();
        //获取参数
        String activeUserID = request.getParameter("activeUserID");
        String dy_id = request.getParameter("dy_id");
        try {
            service.delLikeMsg(activeUserID,dy_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
