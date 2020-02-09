package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static org.apache.commons.beanutils.BeanUtils.populate;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/9 9:19
 */
@WebServlet(name = "FansRegistServlet", urlPatterns = "/FansRegistServlet")
public class FansRegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //校验验证码是否正确
        String verifycode = request.getParameter("checkCode");//获取用户填写的验证码
        HttpSession session = request.getSession();//获取session对象
        String checkCode = (String)session.getAttribute("checkCode_session");//servlet生成的随机验证码
        session.removeAttribute("checkCode_session");//保证验证码的一次性
        if(verifycode != null && verifycode.equalsIgnoreCase(checkCode)){
            //验证码正确
            //获取注册用户的信息
            Map<String, String[]> parameterMap =  request.getParameterMap();//注册用户的信息的map集合
            User registUser = new User();
            //封装用户信息
            try {
                registUser.setCreateTime(new Date());
                registUser.setUpdateTime(new Date());
                populate(registUser,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            //创建用户业务处理对象
            UserService service = new UserServiceImplement();
            boolean flag = false;
            try {
                //用户注册
                flag = service.addUser(registUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //如果用户注册成功
            if(flag){
                //重定向到login.jsp页面
                response.sendRedirect(request.getContextPath()+"/custom_jsp/login.jsp");
            }else{
                request.setAttribute("register_msg","该用户已经注册了");
                request.getRequestDispatcher("/custom_jsp/register.jsp").forward(request,response);
            }
        }else{
            //验证码错误
            //在request域中，保存用户验证码信息
            request.setAttribute("register_msg","验证码错误");
            //转发到regist.jsp页面
            request.getRequestDispatcher("/custom_jsp/register.jsp").forward(request,response);
        }
    }
}
