package com.zwl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/10 8:02
 */
@WebFilter(urlPatterns = "/jsp/*",dispatcherTypes = DispatcherType.REQUEST)
public class PowerUserLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1，判断请求路径中是否包含登录相关的资源路径
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;//向下转型
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String requestURI = httpServletRequest.getRequestURI();//获取请求头中的请求路径
        if(requestURI.contains("/login.jsp") || requestURI.contains("/LoginServlet") || requestURI.contains("/CheckCodeServlet") || requestURI.contains("/register.jsp") || requestURI.contains("/forgotPassword.jsp")
                || requestURI.contains("/css/") || requestURI.contains("/js/") || requestURI.contains("/fonts/") || requestURI.contains("/images/") || requestURI.contains("/RegistServlet")){//login.jsp/loginServlet/CheckCodeServlet
            //包含，用户要登入
            chain.doFilter(request, response);//放行
        }else{
            //不包含，需要验证用户是否已经登录
            //获取session域中的user对象
            HttpSession session = httpServletRequest.getSession(true);
            Integer activeUserID = (Integer) session.getAttribute("activeUserID");
            if(activeUserID != null){
                //用户已经登录了
                chain.doFilter(request, response);//放行
            }else{
                //转发到login.jsp,存放提示信息到request域中
                request.setAttribute("login_msg","请先登录");
                request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
