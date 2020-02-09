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
 * @create: 2019/12/10 14:35
 */
@WebServlet(name = "DeleteDynamicServlet", urlPatterns = "/DeleteDynamicServlet")
public class DeleteDynamicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取动态id
        String dy_id = request.getParameter("dy_id");
        //使用service
        DynamicService service = new DynamicServiceImplement();
        boolean b = false;
        //删除
        try {
            b = service.deleteDynamicByID(dy_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/ShowDynamicMsgServlet?dynamic=0&who=God").forward(request,response);
    }
}
