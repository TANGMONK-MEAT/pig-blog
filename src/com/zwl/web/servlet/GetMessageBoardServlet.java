package com.zwl.web.servlet;

import com.zwl.dao.MessageBoardDao;
import com.zwl.dao.implement.MessageBoardDaoImplement;
import com.zwl.javaBean.messageBoardBean;
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
 * @create: 2019/12/11 2:32
 */
@WebServlet(name = "GetMessageBoardServlet", urlPatterns = "/GetMessageBoardServlet")
public class GetMessageBoardServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置响应编码
        response.setContentType("text/html;charset=utf-8");
        //使用service
        MessageBoardDao dao = new MessageBoardDaoImplement();
        List<messageBoardBean> list = new ArrayList<messageBoardBean>();
        try {
            list = dao.getNewMessageBoard();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list != null && !list.isEmpty()){
            request.setAttribute("NewMessagetList",list);
        }
        request.getRequestDispatcher("/jsp/messageBoard.jsp").forward(request,response);
    }
}
