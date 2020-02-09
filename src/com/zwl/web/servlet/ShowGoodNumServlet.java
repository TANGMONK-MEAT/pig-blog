package com.zwl.web.servlet;

import com.zwl.javaBean.LikeMsgBean;
import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/7 15:36
 */
@WebServlet(name = "ShowGoodNumServlet", urlPatterns = "/ShowGoodNumServlet")
public class ShowGoodNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //��ȡservice
        DynamicService service = new DynamicServiceImplement();
        //��ȡ��̬id
        String dy_0 = request.getParameter("dy_0");
        String dy_1 = request.getParameter("dy_1");
        String dy_2 = request.getParameter("dy_2");
        String dy_3 = request.getParameter("dy_3");
        String[] ids = {dy_0,dy_1,dy_2,dy_3};
        String user_id = request.getParameter("user_id");
        //��ѯ������Ϣ
        List<LikeMsgBean> list = null;
        try {
            list = service.getLikeMsgBeanList(ids,user_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list != null && !list.isEmpty()){
            //������Ӧͷ��Ϣ
            response.setContentType("application/json; charset=utf-8");
            String json = "{";
            String str = null;
            //ѭ��ƴװjson��ʽ������
            for(int i = 0;i < list.size();i++){
                if(i == list.size()-1){
                    str = "'user_" + (i+1) +"':'"+list.get(i).getUser_id()+"','num_" + (i+1) +"':'"+list.get(i).getLike_num() +"'}";
                }else{
                    str = "'user_" + (i+1) +"':'"+list.get(i).getUser_id()+"','num_" + (i+1) +"':'"+list.get(i).getLike_num() + "',";
                }
                json = json + str;
            }
            //��ȡ��
            PrintWriter out = response.getWriter();
            //д��
            out.write(json);
            //ˢ��
            out.flush();
            //�ر���
            out.close();
        }
    }
}
