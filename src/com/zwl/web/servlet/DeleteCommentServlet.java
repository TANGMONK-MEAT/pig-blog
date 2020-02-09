package com.zwl.web.servlet;

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
 * @create: 2019/12/10 21:45
 */
@WebServlet(name = "DeleteCommentServlet", urlPatterns = "/DeleteCommentServlet")
public class DeleteCommentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=utf-8");
        //参数
        String id = request.getParameter("id");
        //获取service
        DynamicService service = new DynamicServiceImplement();
        boolean bool = false;
        try {
            bool = service.deleteCommentByID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = null;
        if(bool){
            json = "{'isDelete':'true'}";

        }else{
            json = "{'isDelete':'false'}";
        }
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
