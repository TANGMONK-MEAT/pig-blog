package com.zwl.web.servlet;

import com.zwl.javaBean.DynamicBean;
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
 * @create: 2019/12/7 12:18
 */
@WebServlet(name = "FindOrderDynamicMsgServlet", urlPatterns = "/FindOrderDynamicMsgServlet")
public class FindOrderDynamicMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取service
        DynamicService service = new DynamicServiceImplement();
        //查询
        List<DynamicBean> dynamicBeanList = null;
        try {
            dynamicBeanList = service.findOrderDynamicMsg(0,6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(dynamicBeanList != null && !dynamicBeanList.isEmpty()){
            response.setContentType("application/json; charset=utf-8");
            String json = null;
            StringBuilder _json = new StringBuilder();
            String title = null;
            String id = null;
            if(dynamicBeanList.size() < 6){
                for(int i = 0;i < dynamicBeanList.size();i++){
                    title = "'title_"+ i + "':'" + dynamicBeanList.get(i).getTitle() +"',";
                    id = "'id_"+ i + "':'" + dynamicBeanList.get(i).getId() +"',";
                    _json.append(title+id);
                }
                json = "{" + _json.toString() + "'size':'"+dynamicBeanList.size()+ "'}";
            }else{
                json = "{'title_0':'"+dynamicBeanList.get(0).getTitle()+"','title_1':'"+dynamicBeanList.get(1).getTitle() +"','title_2':'"+dynamicBeanList.get(2).getTitle()
                        +"','title_3':'"+dynamicBeanList.get(3).getTitle() +"','title_4':'"+dynamicBeanList.get(4).getTitle() +"','title_5':'"+dynamicBeanList.get(5).getTitle() +
                        "','id_0':'"+dynamicBeanList.get(0).getId()+"','id_1':'"+dynamicBeanList.get(1).getId()+"','id_2':'"+dynamicBeanList.get(2).getId()+
                        "','id_3':'"+dynamicBeanList.get(3).getId()+"','id_4':'"+dynamicBeanList.get(4).getId()+"','id_5':'"+dynamicBeanList.get(5).getId()+"','size':'"+dynamicBeanList.size()+"'}";

            }
            PrintWriter out = response.getWriter();
            out.write(json);
            out.flush();
            out.close();
        }
    }
}
