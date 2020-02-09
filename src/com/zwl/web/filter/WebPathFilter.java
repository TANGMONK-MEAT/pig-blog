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
         * ��ȡ��Ŀ�ľ�������·��
         * ���磺http://localhost:8080
         * request.getScheme()	��ȡ����Э�������	��http��
         * request.getServerName()  ��ȡ���������������֣�����������IP��localhost
         * request.getServerPort()  ��ȡ�������Ķ˿ں�	��Ĭ����8080��
         */
        String basePath = request.getScheme() + "://" + request.getServerName();
        int port = request.getServerPort();
        if(port != 80){//����80�˿ڲ�����ʾ�ĳ���
            basePath = basePath + ":" + port;
        }
        //����Ŀ�ľ���·�����浽servletContext����
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("basePath", basePath);
        //����
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
