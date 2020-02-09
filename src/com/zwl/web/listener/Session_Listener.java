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
 * session������
 * ��Χ������web��Ŀ������session
 * ���ã�
 * 1������session�Ĵ���������
 * 2������session��Ӻ��Ƴ�����
 */
@WebListener()
public class Session_Listener implements HttpSessionListener, HttpSessionAttributeListener {

    private int activeUserCount = 0;//�����û���
    /**
     * ���췽���������������󣬾ͱ�����
     */
    public Session_Listener() {

    }


    /**
     * session�������󣬵���
     * @param se
     */
    public void sessionCreated(HttpSessionEvent se) {
        SessionUtil.AddSession(se.getSession());
        // ����session���������ʱ�䣬20����
        // ����û���20�����ڣ�û�з��ʷ����������������Զ�����session
        se.getSession().setMaxInactiveInterval(60 * 30);
        activeUserCount++;
        System.out.println("session_create");
    }

    /**
     * session�����ٺ󣬵���
     * ���ܣ�
     * ��session����ʱ����servletContext���б���Ļ�Ծ�û�����Ϣ�������
     * @param se
     */
    public void sessionDestroyed(HttpSessionEvent se) {
        SessionUtil.DelSession(se.getSession());
        //��ȡsession�����
        HttpSession session = se.getSession();
        //��ȡsession�б���Ļ�Ծ�û���ID
        Integer activeUserID = (Integer) session.getAttribute("activeUserID");
        if(activeUserID != null){//�����Ծ�û�ID����
            /*
           1���Ƴ�activeUserMap�����У�ָ��ID�Ļ�Ծ�û�����Ϣ
           2������activeUserMap����
           3��������ӵ�servletContext����
             */
            ServletContext servletContext = session.getServletContext();//��ȡservletContext�����
            HashMap<Integer,String> activeUserMap = (HashMap<Integer,String>)servletContext.getAttribute("activeUserMap");//��ȡ�����л�Ծ�û���hash����
            activeUserMap.remove(activeUserID);//�Ƴ�ָ��ID�Ļ�Ծ�û���Ϣ
            servletContext.setAttribute("activeUserMap",activeUserMap);//������ӵ�ServletContext����
            activeUserCount--;
            System.out.println("session_destroy");
        }
    }


    /**
     * session��Ӽ�ֵ�Ժ󣬵���
     * @param sbe
     */
    public void attributeAdded(HttpSessionBindingEvent sbe) {

    }

    /**
     * session�Ƴ���ֵ�Ժ󣬵���
     * @param sbe
     */
    public void attributeRemoved(HttpSessionBindingEvent sbe) {

    }

    /**
     * �ı�session�м���Ӧ��ֵ�󣬵���
     * @param sbe
     */
    public void attributeReplaced(HttpSessionBindingEvent sbe) {

    }
}
