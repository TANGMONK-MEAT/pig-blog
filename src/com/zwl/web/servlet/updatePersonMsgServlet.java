package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;
import com.zwl.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 用户自己修改个人信息
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/9 14:54
 */
@WebServlet(name = "updatePersonMsgServlet", urlPatterns = "/updatePersonMsgServlet")
public class updatePersonMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        Map<String, String[]> map = request.getParameterMap();
        //封装用户信息
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //使用service修改
        UserService service = new UserServiceImplement();
        boolean bool = false;
        try {
             bool = service.updateUserMsg(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //传递消息
        if(bool) {
            request.setAttribute("update_msg", "1");
        }
        //转发
        request.getRequestDispatcher("/custom_jsp/updatePersonMsg.jsp").forward(request,response);
    }
}
