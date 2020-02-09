package com.zwl.web.servlet;

import com.zwl.dao.PowerUserDao;
import com.zwl.dao.implement.PowerUserDaoImplement;
import com.zwl.service.PowerUserService;
import com.zwl.service.UserService;
import com.zwl.service.implement.PowerUserServiceImplement;
import com.zwl.service.implement.UserServiceImplement;
import com.zwl.util.SessionUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/9 17:13
 */
@WebServlet(name = "QuitLoginServlet", urlPatterns = "/QuitLoginServlet")
public class QuitLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户参数
        String id = request.getParameter("id");
        String who = request.getParameter("who");
        //查询username
        String username = null;
        if(who != null && id != null && who.equals("God")){
            PowerUserDao powerUserDao = new PowerUserDaoImplement();
            try {
                username = powerUserDao.findByID(Integer.parseInt(id)).getName();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            UserService service = new UserServiceImplement();
            try {
                username = service.findUser(id).getUsername();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //获取全局域对象
        ServletContext servletContext = request.getServletContext();
        HashMap userMap = (HashMap<Integer, String>) servletContext.getAttribute("activeUserMap");
        if (userMap.containsValue(username)) {
            String session_id = (String) userMap.get(username);
            HttpSession _session = SessionUtil.getSession(session_id);
            _session.invalidate();//解绑该Session绑定的所有数据
        }
        if(who != null && id != null && who.equals("God")){
            request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
        }else{
            request.setAttribute("quit_msg","system_quit");
            request.getRequestDispatcher("/custom_jsp/index.jsp").forward(request,response);
        }
    }
}
