package com.zwl.web.servlet;

import com.zwl.dao.DynamicDao;
import com.zwl.dao.implement.DynamicDaoImplement;
import com.zwl.javaBean.DynamicBean;
import com.zwl.util.PropertiesUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.*;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/3 20:22
 */
@MultipartConfig
@WebServlet(name = "SaveDynamicMsgServlet", urlPatterns = "/SaveDynamicMsgServlet")
public class SaveDynamicMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        //
        DynamicBean dynamicBean = new DynamicBean();//封装动态信息
        //
        String activeUserID = request.getParameter("activeUserID");//博主ID
        if(activeUserID == null){
            activeUserID = "1";
        }
        String text = request.getParameter("text");//博文
        String title = request.getParameter("title");//标题
        Collection<Part> parts = request.getParts();//图片
        String image_address = PropertiesUtil.getImage_address();//保存图片的区域image_address
        long time = System.currentTimeMillis();//辨别图片，避免重名，时间戳
        /*
        *封装
         */
        Map<String,String> imgMap = new HashMap<>();
        dynamicBean.setP_id(Integer.parseInt(activeUserID));
        dynamicBean.setText(text);
        dynamicBean.setTitle(title);
        dynamicBean.setCreateTime(new Date());
        //循环获取图片，保存到指定的区域image_address
        for (Part p : parts) {
            String submittedFileName = p.getSubmittedFileName();
            String endWith = "bmp";
            if (p.getContentType() != null && p.getContentType().contains("image/")) {
                if (submittedFileName.toLowerCase().endsWith(".jpg")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".jpg");
                    endWith = ".jpg";
                } else if (submittedFileName.toLowerCase().endsWith(".png")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".png");
                    endWith = ".png";
                } else if (submittedFileName.toLowerCase().endsWith(".bmp")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".bmp");
                } else if (submittedFileName.toLowerCase().endsWith(".gif")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".gif");
                    endWith = ".gif";
                } else if (submittedFileName.toLowerCase().endsWith(".jpeg")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".jpeg");
                    endWith = ".jpeg";
                } else if (submittedFileName.toLowerCase().endsWith(".ico")) {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".ico");
                    endWith = ".ico";
                }else {
                    p.write(image_address + "/" + time + "_" + p.getName() + ".bmp");
                }
                imgMap.put(p.getName(),"images/"+time + "_" + p.getName() + endWith);
            }
        }
        try {
            BeanUtils.populate(dynamicBean,imgMap);
            DynamicDao dynamicDao = new DynamicDaoImplement();
            dynamicDao.add(dynamicBean);
        } catch (IllegalAccessException | InvocationTargetException | SQLException e) {
            e.printStackTrace();
        }
    }
}
