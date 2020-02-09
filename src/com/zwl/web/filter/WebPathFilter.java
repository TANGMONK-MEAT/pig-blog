package com.zwl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/11 8:47
 */
@WebFilter(urlPatterns = "/*")
public class WebPathFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        /**
         * 获取项目的绝对虚拟路径
         * 例如：http://localhost:8080
         * request.getScheme()	获取网络协议的名称	（http）
         * request.getServerName()  获取服务器主机的名字，（域名或者IP）localhost
         * request.getServerPort()  获取服务器的端口号	（默认是8080）
         */
        String basePath = request.getScheme() + "://" + request.getServerName();
        int port = request.getServerPort();
        if(port != 80){//由于80端口不会显示的出现
            basePath = basePath + ":" + port;
        }
        //将项目的绝对路径储存到servletContext域中
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("basePath", basePath);
        //放行
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
