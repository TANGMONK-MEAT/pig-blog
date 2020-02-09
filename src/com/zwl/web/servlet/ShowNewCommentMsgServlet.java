package com.zwl.web.servlet;

import com.zwl.javaBean.CommentMsgBean;
import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/10 20:29
 */
@WebServlet(name = "ShowNewCommentMsgServlet", urlPatterns = "/ShowNewCommentMsgServlet")
public class ShowNewCommentMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置response的编码
        response.setContentType("text/html;charset=utf-8");
        //获取service
        DynamicService service = new DynamicServiceImplement();
        List<CommentMsgBean> list = new ArrayList<CommentMsgBean>();
        try {
            list = service.getNewCommentMsgList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //设置所有评论已读
        try {
            service.setNewCommentReaded();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("NewCommentList",list);
        request.getRequestDispatcher("/jsp/NewCommentMsg.jsp").forward(request,response);
    }
}
