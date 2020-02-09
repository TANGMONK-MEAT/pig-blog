//package com.zwl.test;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * @Auther:zwl
// * @Version: 1.0
// * @create: 2019/11/13 16:52
// */
//@WebServlet("/testSensitiveWordsFilter")
//public class testSensitiveWordsFilter extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("utf-8");
//        //设置编码
//        resp.setCharacterEncoding("utf-8");
//        resp.setContentType("text/html;charset=utf-8");
////        String name = req.getParameter("name");
////        String msg = req.getParameter("msg");
////        Map<String, String[]> parameterMap = req.getParameterMap();
////            String[] names = req.getParameterValues("name");
////        String[] msgs = req.getParameterValues("msg");
//
////        System.out.println(name);
////        System.out.println(msg);
////        PrintWriter writer = resp.getWriter();
////        writer.write("<h1>"+parameterMap.get("name")[0]+"</h1><br />");
////        writer.write("<h2>"+parameterMap.get("msg")[0]+"</h2>");
////        writer.write(names[0]);
////        writer.write(msgs[0]);
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
