package com.zwl.web.listener;

import com.zwl.util.SessionUtil;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.HashMap;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/21 8:18
 * session监听器
 * 范围：整个web项目的所有session
 * 作用：
 * 1、监听session的创建和销毁
 * 2、监听session添加和移除数据
 */
@WebListener()
public class Session_Listener implements HttpSessionListener, HttpSessionAttributeListener {

    private int activeUserCount = 0;//在线用户数
    /**
     * 构造方法，服务器启动后，就被调用
     */
    public Session_Listener() {

    }


    /**
     * session被创建后，调用
     * @param se
     */
    public void sessionCreated(HttpSessionEvent se) {
        SessionUtil.AddSession(se.getSession());
        // 设置session的最大生存时间，20分钟
        // 如果用户在20分钟内，没有访问服务器，服务器会自动销毁session
        se.getSession().setMaxInactiveInterval(60 * 30);
        activeUserCount++;
        System.out.println("session_create");
    }

    /**
     * session被销毁后，调用
     * 功能：
     * 在session销毁时，把servletContext域中保存的活跃用户的信息，清除掉
     * @param se
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionUtil.DelSession(se.getSession());
        //获取session域对象
        HttpSession session = se.getSession();
        //获取session中保存的活跃用户的ID
        Integer activeUserID = (Integer) session.getAttribute("activeUserID");
        if(activeUserID != null){//如果活跃用户ID存在
            /*
           1，移除activeUserMap集合中，指定ID的活跃用户的信息
           2，重设activeUserMap集合
           3，重新添加到servletContext域中
             */
            ServletContext servletContext = session.getServletContext();//获取servletContext域对象
            HashMap<Integer,String> activeUserMap = (HashMap<Integer,String>)servletContext.getAttribute("activeUserMap");//获取保存有活跃用户的hash集合
            activeUserMap.remove(activeUserID);//移除指定ID的活跃用户信息
            servletContext.setAttribute("activeUserMap",activeUserMap);//重新添加到ServletContext域中
            activeUserCount--;
            System.out.println("session_destroy");
        }
    }


    /**
     * session添加键值对后，调用
     * @param sbe
     */
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    /**
     * session移除键值对后，调用
     * @param sbe
     */
    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    /**
     * 改变session中键对应的值后，调用
     * @param sbe
     */
    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}
