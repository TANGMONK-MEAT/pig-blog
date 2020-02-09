package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;
import com.zwl.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

/**
 * �û��Լ��޸ĸ�����Ϣ
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/9 14:54
 */
@WebServlet(name = "updatePersonMsgServlet", urlPatterns = "/updatePersonMsgServlet")
public class updatePersonMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡ����
        Map<String, String[]> map = request.getParameterMap();
        //��װ�û���Ϣ
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //ʹ��service�޸�
        UserService service = new UserServiceImplement();
        boolean bool = false;
        try {
             bool = service.updateUserMsg(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //������Ϣ
        if(bool) {
            request.setAttribute("update_msg", "1");
        }
        //ת��
        request.getRequestDispatcher("/custom_jsp/updatePersonMsg.jsp").forward(request,response);
    }
}
