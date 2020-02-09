package com.zwl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 作用：
 *      设置浏览器请求的资源的编码格式
 *      以及服务器响应浏览器的编码格式
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/13 7:23
 */
@WebFilter(urlPatterns = "/*",dispatcherTypes = DispatcherType.REQUEST)
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        //设置编码
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //通知浏览器，不要缓存
        resp.setHeader("pragma","no-cache");
        resp.setHeader("cache-control","no-cache");
        resp.setHeader("expires","0");
        //放行
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
