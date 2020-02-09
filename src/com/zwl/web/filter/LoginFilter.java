package com.zwl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 作用：
 * 		1，浏览器访问资项目的资源，验证其是否登录(判断session中是否有user)
 * 	            首先，要排除登录相关的资源	，如，css/js/图片/验证码等资源
 * 	                * 判断用户请求的资源是否为login.jsp/loginServlet/CheckCodeServlet
 * 	                    * 是，直接放行
 * 	                    * 否，判断是否登录
 * 	                        * 判断session域中的user对象是否为空
 * 		2，如果登录了，则直接放行
 * 		3，如果没有登录，则跳转到登录页面（login.jsp），提示“你尚未登录，请先登录”
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/13 7:21
 */
@WebFilter(urlPatterns = "/custom_jsp/*",dispatcherTypes = DispatcherType.REQUEST)//
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1，判断请求路径中是否包含登录相关的资源路径
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;//向下转型
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        String requestURI = httpServletRequest.getRequestURI();//获取请求头中的请求路径
        if(requestURI.contains("/custom_jsp/") || requestURI.contains("/login.jsp") || requestURI.contains("/FansUserLoginServlet") || requestURI.contains("/CheckCodeServlet") || requestURI.contains("/register.jsp") || requestURI.contains("/forgotPassword.jsp")
                || requestURI.contains("/css/") || requestURI.contains("/js/") || requestURI.contains("/fonts/") || requestURI.contains("/images/") || requestURI.contains("/FansRegistServlet")){//login.jsp/loginServlet/CheckCodeServlet
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
                request.setAttribute("login_msg","账号异常，请重新登录");
                request.getRequestDispatcher("/index.jsp").forward(request,response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
