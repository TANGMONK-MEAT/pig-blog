package com.zwl.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 铭感词汇过滤器
 *
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/13 9:16
 */
@WebFilter(urlPatterns = "/*")
public class SensitiveWordsFilter implements Filter {

    private List<String> list = new ArrayList<>();//敏感词汇集合

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        //1,创建代理对象,增强request对象的getParameter方法
        ServletRequest proxy_request = (ServletRequest) Proxy.newProxyInstance(request.getClass().getClassLoader(), request.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强getParameter方法
                //判断是否是getParameter方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String)method.invoke(request, args);
                    if(value != null){
                        //遍历list集合
                        for(String str : list){
                            if(value.contains(str)){//如果request的请求参数中，包含有敏感词汇
                                //用***替换参数中的敏感词汇
                                value = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return value;
                }
                //判断是否是getParameterMap方法
                if(method.getName().equals("getParameterMap")){
                    //获取返回值
                    Map<String,String[]> paraMap = (Map<String,String[]>)method.invoke(request,args);
                    Map<String,String[]> paraMap_2 = new HashMap<>();
                    if(!paraMap.isEmpty()){
                        for(String key : paraMap.keySet()){
                            String[] values = paraMap.get(key);
                            int i = 0;
                            for(String str : values){
                                for(String li : list){
                                    if(str.contains(li)){
                                        str = str.replaceAll(li,"***");
                                    }
                                }
                                values[i] = str;//替换敏感字符
                                i++;
                            }
                            paraMap_2.put(key,values);//将替换玩的字符串，重新加到map集合中
                        }
                        return paraMap_2;
                    }
                }
                //判断是否是getParameterValues方法
                if(method.getName().equals("getParameterValues")){
                    //获取返回值
                    String[] paraValues = (String[])method.invoke(request, args);
                    int i = 0;
                    for(String value : paraValues){
                        for(String str : list){
                            if(value.contains(str)){
                                paraValues[i] = value.replaceAll(str,"***");
                            }
                        }
                    }
                    return paraValues;
                }
                return method.invoke(request,args);//如果不是getParameter方法，原样执行
            }
        });
        //放行,传递代理对象
        chain.doFilter(proxy_request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        //1，加载文件，获取文件的真实路径
        ServletContext servletContext = config.getServletContext();
        String realPath = servletContext.getRealPath("/WEB-INF/classes/SensitiveWords.txt");
        //2,读取文件
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(realPath));
            //3，将文件的每一行数据设置添加到list集合中
            String line = null;
            while((line = bufferedReader.readLine()) != null){//循环获取文件的每一行数据
                list.add(line);//将敏感词汇添加到list集合
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(bufferedReader != null) bufferedReader.close();//释放资源
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
