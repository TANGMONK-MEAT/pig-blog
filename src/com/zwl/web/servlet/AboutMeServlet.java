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
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 14:31
 */
@WebServlet(name = "AboutMeServlet", urlPatterns = "/AboutMeServlet")
public class AboutMeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取service
        PowerUserService powerUserService = new PowerUserServiceImplement();
        //查询
        PowerUser me = null;
        try {
            me = powerUserService.findMe();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String flag = request.getParameter("me");
        if (me != null) {
            if (flag.equals("0")) {
                response.setContentType("application/json; charset=utf-8");
                String json = "{'name':'" + me.getName() + "','job':'" + me.getJob() + "','head_img':'" + me.getHead_img() + "','introduction':'" + me.getIntroduction() + "','motto':'"+me.getMotto() +"'}";
                PrintWriter out = response.getWriter();
                out.write(json);
                out.flush();
                out.close();
            } else if (flag.equals("1")) {
                response.setContentType("text/html;charset=utf-8");
                //将博主信息，储存到request域中
                request.setAttribute("me", me);
                //转发到主界面
                request.getRequestDispatcher("/custom_jsp/user_iframe.jsp").forward(request, response);
            }
        }
    }
}
