package com.zwl.web.servlet;

import com.zwl.service.PowerUserService;
import com.zwl.service.implement.PowerUserServiceImplement;

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
 * @create: 2019/12/6 20:22
 */
@WebServlet(name = "PhotosServlet", urlPatterns = "/PhotosServlet")
public class PhotosServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        PowerUserService service = new PowerUserServiceImplement();
        List<String> contextList = null;
        try {
            contextList = service.getImagesContext();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("imgList",contextList);
        request.getRequestDispatcher("/custom_jsp/photos_iframe.jsp").forward(request,response);
    }
}
