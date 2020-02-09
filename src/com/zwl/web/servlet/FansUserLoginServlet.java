package com.zwl.web.servlet;

import com.zwl.javaBean.PowerUser;
import com.zwl.javaBean.User;
import com.zwl.service.PowerUserService;
import com.zwl.service.UserService;
import com.zwl.service.implement.PowerUserServiceImplement;
import com.zwl.service.implement.UserServiceImplement;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/6 10:37
 */
@WebServlet(name = "FansUserLoginServlet", urlPatterns = "/FansUserLoginServlet")
public class FansUserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String verifycode = request.getParameter("verifycode");//获取用户填写的验证码
        HttpSession session = request.getSession(true);//如果会话不存在，就创建一个session
        String checkCodeString = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");//保证验证码的一次性
        ServletContext servletContext = this.getServletContext();//获取项目对象（管理员）
        if (verifycode != null && verifycode.equalsIgnoreCase(checkCodeString)) {//忽略大小写，比较
            //验证码正确
            //获取数据
            Map<String, String[]> parameterMap = request.getParameterMap();
            User loginUser = new User();
            //封装信息
            try {
                BeanUtils.populate(loginUser, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //调用service的查询
            UserService powerUserService = new UserServiceImplement();
            User User = null;
            //判断登录用户是否是，已经注册过的
            try {
                User = powerUserService.login(loginUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (User != null) { //判断是否是注册用户
                //将用户对象，保存到ServletContext域中的hashMap集合中:id--->username
                HashMap powerUserMap = null;
                if (servletContext.getAttribute("activeUserMap") != null) {
                    powerUserMap = (HashMap<Integer, String>) servletContext.getAttribute("activeUserMap");
                } else {
                    powerUserMap = new HashMap();
                }
                //添加用户信息到hashMap,session_id
                powerUserMap.put(User.getId(),User.getUsername());
                powerUserMap.put(User.getUsername(),session.getId());
                //添加活跃用户信息到servletContext域中
                servletContext.setAttribute("activeUserMap", powerUserMap);
                //添加用户信息到session中
                session.setAttribute("activeUserID", User.getId());
                session.setAttribute("activeUserName", User.getUsername());
                //重定向到index.jsp页面
                response.sendRedirect(request.getContextPath() + "/custom_jsp/index.jsp");
            }else{
                //登录失败
                //将密码或者用户名错误的信息，保存到request域中
                request.setAttribute("login_msg", "密码或者账号错误");
                //转发到登录页面
                request.getRequestDispatcher("/custom_jsp/login.jsp").forward(request, response);
            }
        }else{
            //验证码错误
            //将验证码提示信息，存入request域中
            request.setAttribute("login_msg", "验证码错误");
            //转发到login.jsp页面
            request.getRequestDispatcher("/custom_jsp/login.jsp").forward(request, response);
        }
    }
}
