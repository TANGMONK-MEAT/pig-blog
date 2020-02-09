package com.zwl.web.servlet;

import com.zwl.javaBean.CommentMsgBean;
import com.zwl.javaBean.DynamicBean;
import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/8 20:56
 */
@WebServlet(name = "ShowCommentMsgServlet", urlPatterns = "/ShowCommentMsgServlet")
public class ShowCommentMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡservice
        DynamicService service = new DynamicServiceImplement();
        //��ȡ��̬����Ϣ����
        String dy_id = request.getParameter("dynamic");//��̬id
        String currentPage = request.getParameter("currentPage");
        String rows = request.getParameter("rows");
        String who = request.getParameter("who");
        //��ѯ����װ
        List<CommentMsgBean> comList = null;
        DynamicBean dynamicBean = null;
        try {
            dynamicBean = service.findDynamic(dy_id);
            comList = service.getCommentMsgByID(dy_id, 5);//5����������
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //������Ӧͷ��Ϣ
        response.setContentType("text/html;charset=utf-8");//���ñ���
        request.setAttribute("comlist", comList);
        request.setAttribute("dynamicBean", dynamicBean);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("rows", rows);
        if(who != null && who.equals("comment")){
            request.getRequestDispatcher("/jsp/showAllText_iframe.jsp").forward(request,response);
        }else{
            request.getRequestDispatcher("/custom_jsp/showAllText_iframe.jsp").forward(request, response);
        }
    }
}
