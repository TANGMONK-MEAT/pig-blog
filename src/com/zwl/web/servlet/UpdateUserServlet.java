package com.zwl.web.servlet;

import com.zwl.javaBean.User;
import com.zwl.service.UserService;
import com.zwl.service.implement.UserServiceImplement;
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
 * ���ܣ�
 * 1�����ñ���
 * 2����ȡupdate.jsp��������  map����
 * 3,��װUser����
 * 4������service����,����޸�
 * 5,��ת��UserListServlet
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/11/10 14:34
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = "/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        Map<String, String[]> parameterMap = null;
        try {
            //��ȡupdate.jsp���ύ������
            parameterMap = request.getParameterMap();
            user = new User();
            //��װUser����
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //����service��������޸�
        UserService userService = new UserServiceImplement();
        boolean bool = false;
        try {
            bool = userService.updateUserMsg(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(bool){
            //����޸ĳɹ�����ת��UserListServlet
            request.setAttribute("activeUserID",parameterMap.get("activeUserID")[0]);
            request.getRequestDispatcher("/FindUserByPageServlet").forward(request,response);
        }else{
        }
    }
}
