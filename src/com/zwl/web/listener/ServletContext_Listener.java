package com.zwl.web.listener; /**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/21 14:37
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ServletContext_Listener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ServletContext_Listener() {
    }

    // -------------------------------------------------------
    // com.zwl.web.listener.ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
