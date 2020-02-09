package com.zwl.web.servlet;

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

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/7 14:56
 */
@WebServlet(name = "ShowOrderDynamicMsgServlet", urlPatterns = "/ShowOrderDynamicMsgServlet")
public class ShowOrderDynamicMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");//���ñ���
        String id = request.getParameter("id");//��ȡ��̬id
        //ʵ����service
        DynamicService service = new DynamicServiceImplement();
        //ʹ��service����ȡ��̬��Ϣ
        DynamicBean dynamic = null;
        try {
            dynamic = service.findDynamic(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(dynamic != null){
            //����̬��Ϣ���浽request����
            request.setAttribute("dynamicBean",dynamic);;
            //ת��
            request.getRequestDispatcher("/custom_jsp/showAllText_iframe.jsp").forward(request,response);
        }
    }
}
