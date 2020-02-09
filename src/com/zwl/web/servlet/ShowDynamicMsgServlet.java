package com.zwl.web.servlet;

import com.zwl.javaBean.CommentMsgBean;
import com.zwl.javaBean.DynamicBean;
import com.zwl.javaBean.PageBean;
import com.zwl.service.DynamicService;
import com.zwl.service.implement.DynamicServiceImplement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 查询动态信息列表，转发到index_iframe.jsp
 *
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2019/12/4 1:56
 */
@WebServlet(name = "ShowDynamicMsgServlet", urlPatterns = "/ShowDynamicMsgServlet")
public class ShowDynamicMsgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        response.setContentType("text/html;charset=utf-8");
        //获取参数
        String currentPage = request.getParameter("currentPage");
        String dynamicNum = request.getParameter("dynamic");
        String rows = request.getParameter("rows");
        String who = request.getParameter("who");
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if (rows == null || "".equals(rows)) {
            rows = "4";
        }
        //用于封装分页的信息的对象
        PageBean<DynamicBean> dynamicPage = null;
        //调用service查询，获取结果集
        DynamicService dynamicService = new DynamicServiceImplement();
        if (dynamicNum != null && Integer.parseInt(dynamicNum) >= 0) {
            if (dynamicNum != null && dynamicNum.equals("0")) {
                try {
                    dynamicPage = dynamicService.findDynamicByPage(currentPage, rows);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //将动态信息储存到request域中
                request.setAttribute("dynamicPage", dynamicPage);
                if (who != null && who.equals("God")) {
                    //转发到DynamicDelete.jsp
                    request.getRequestDispatcher("/jsp/DynamicDelete.jsp").forward(request, response);
                }
                else if (who != null && who.equals("comment")) {
                    request.getRequestDispatcher("/jsp/Comment.jsp").forward(request, response);
                }
                else {
                    //转发到index_iframe.jsp
                    request.getRequestDispatcher("/custom_jsp/index_iframe.jsp").forward(request, response);
                }
            } else if (Integer.parseInt(dynamicNum) >= 1) {
                List<CommentMsgBean> comList = null;
                DynamicBean dynamicBean = null;
                try {
                    comList = dynamicService.getCommentMsgByID(dynamicNum, 5);//5条最新评论
                    dynamicBean = dynamicService.findDynamic(dynamicNum);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                request.setAttribute("comList", comList);
                request.setAttribute("dynamicBean", dynamicBean);
                request.setAttribute("currentPage", currentPage);
                request.setAttribute("rows", rows);
                if (who != null && who.equals("God")) {
                    request.getRequestDispatcher("/jsp/showAllText_iframe.jsp").forward(request, response);
                }
                else if(who != null && who.equals("comment")){
                    request.getRequestDispatcher("/jsp/showAllText_iframe.jsp").forward(request, response);
                }
                else {
                    request.getRequestDispatcher("/custom_jsp/showAllText_iframe.jsp").forward(request, response);
                }
            }
        } else {
            try {
                dynamicPage = dynamicService.findDynamicByPage(currentPage, rows);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            //将动态信息储存到request域中
            request.setAttribute("dynamicPage", dynamicPage);
            if (who != null && who.equals("God")) {
                //转发到DynamicDelete.jsp
                request.getRequestDispatcher("/jsp/DynamicDelete.jsp").forward(request, response);
            }
            else if (who != null && who.equals("comment")) {
                request.getRequestDispatcher("/jsp/Comment.jsp").forward(request, response);
            }
            else {
                //转发到index_iframe.jsp
                request.getRequestDispatcher("/custom_jsp/index_iframe.jsp").forward(request, response);
            }
        }
    }
}
