package com.zwl.web.servlet;

import com.zwl.javaBean.PowerUser;
import com.zwl.service.PowerUserService;
import com.zwl.service.implement.PowerUserServiceImplement;
import com.zwl.util.BeanUtil;
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
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/11 9:13
 */
@MultipartConfig
@WebServlet(name = "SetPowerUserMsgServlet", urlPatterns = "/SetPowerUserMsgServlet")
public class SetPowerUserMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        //service
        PowerUserService service = new PowerUserServiceImplement();
        Map<String, String[]> map = request.getParameterMap();
        Collection<Part> parts = request.getParts();//头像集合
        String path = null;//头像虚拟路径
        String image_address = PropertiesUtil.getImage_address() + "/head_img";//保存头像的区域image_address
        //循环获取图片，保存到指定的区域image_address
        long time = System.currentTimeMillis();//辨别图片，避免重名，时间戳
        String submittedFileName = null;
        String endWith = ".bmp";
        for (Part p : parts) {
            submittedFileName = p.getSubmittedFileName();
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
                path = "images/head_img/"+time + "_" + p.getName() + endWith;//组装头像的虚拟路径
            }
        }
        //封装
        PowerUser user = new PowerUser();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        user.setHead_img(path);//头像虚拟路径添加到user
        //修改
        boolean bool = false;
        try {
            bool = service.setPowerUserMsg(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String json = "{'isUpdate':'false'}";
        if(bool){
            json = "{'isUpdate':'true'}";
        }
        PrintWriter out = response.getWriter();
        out.write(json);
        out.flush();
        out.close();
    }
}
