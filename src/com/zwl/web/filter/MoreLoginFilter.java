package com.zwl.web.filter;

import com.zwl.util.SessionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/5 16:28
 */
@WebFilter(urlPatterns = "/*",dispatcherTypes = DispatcherType.REQUEST)
public class MoreLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession(false);//如id的存在，返回session；否则返回null
       HttpServletRequest _request = (HttpServletRequest)request;
        if (session == null){
//            _request.getRequestDispatcher("/index.jsp").forward(request,response);
//            ((HttpServletResponse)response).sendRedirect(((HttpServletRequest) request).getContextPath()+"/index.jsp");
        }else{
            ServletContext servletContext = request.getServletContext();
            if(servletContext.getAttribute("activeUserMap") != null){
                HashMap userMap = (HashMap<Integer, String>) servletContext.getAttribute("activeUserMap");
                String username = request.getParameter("username");
                    /*
                    如果重复登录
                    将解绑Session绑定的所有数据，强制下线
                    */
                if (userMap.containsValue(username)) {
                    String session_id = (String) userMap.get(username);
                    HttpSession _session = SessionUtil.getSession(session_id);
                    _session.invalidate();//解绑该Session绑定的所有数据
                }
            }
        }
        //放行
        chain.doFilter(_request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
