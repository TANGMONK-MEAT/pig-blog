package com.zwl.web.servlet;

import com.zwl.javaBean.PowerUser;
import com.zwl.service.PowerUserService;
import com.zwl.service.implement.PowerUserServiceImplement;

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
 * @create: 2019/12/11 12:46
 */
@WebServlet(name = "ShowPowerUserMsgServlet", urlPatterns = "/ShowPowerUserMsgServlet")
public class ShowPowerUserMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //…Ë÷√±‡¬Î
        response.setContentType("text/html;charset=utf-8");
        //
        String activeUserID = request.getParameter("activeUserID");
        //≤È—Ø
        PowerUserService service = new PowerUserServiceImplement();
        PowerUser user = null;
        try {
            if(activeUserID != null){
                user = service.getPowerUserByID(Integer.parseInt(activeUserID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(activeUserID != null){
            try {
                user = service.getPowerUserByID(Integer.parseInt(activeUserID));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("user",user);
        }else{
            request.setAttribute("user","null");
        }
        request.getRequestDispatcher("/jsp/updatePowerUserMsg.jsp").forward(request,response);
    }
}
