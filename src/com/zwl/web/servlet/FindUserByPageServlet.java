package com.zwl.web.servlet;

import com.zwl.javaBean.PageBean;
import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * 作用：
 * 1，接收请求参数currentPage(当前页码)，rows（显示的记录数）
 * 2，调用service查询数据，返回一个PageBean对象
 * 3，将PageBean存入request域中
 * 4，转发到list.jsp展示
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/14 20:24
 */
@WebServlet(name = "FindUserByPageServlet", urlPatterns = "/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示的条数
        String activeUserID = request.getParameter("activeUserID");
        if(currentPage == null || "".equals(currentPage)){
            currentPage = "1";
        }
        if(rows == null || "".equals(rows)){
            rows = "9";
        }
        //调用service查询
        //获取条件查询的参数
        Map<String, String[]> condition = request.getParameterMap();
        //调用service查询
        UserService userService = new UserServiceImplement();
        PageBean<User> userPage = null;
        try {
            if(activeUserID != null && !activeUserID.equals("")){
                userPage = userService.findUserByPage(currentPage, rows,condition,Integer.parseInt(activeUserID));
            }else{
                //转发重复登录信息到login.jsp
                request.setAttribute("login_msg", "检测到用户异常登录，请退出后再登录");
                //转发到登录页面
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        /*
//        测试：
//        输出pageBean
//        调用pageBean的toString()
//         */
//        System.out.println(userPage);

        //将pageBean存入request域中
        request.setAttribute("userPage",userPage);
        request.setAttribute("condition",condition);
        //转发到list.jsp页面
        request.getRequestDispatcher("/jsp/list.jsp").forward(request,response);
    }
}
