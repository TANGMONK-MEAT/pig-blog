package com.zwl.web.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于生成动态验证码
 */
@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckCodeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int width = 130;
		int height = 45;
		// 1,创建一个对象，在内存中代表一个图片（验证码图片对象）
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		// 2，美化图片
		// 2.1,填充背景色
		Graphics g = image.getGraphics();// 获取图片对象的画笔
		g.setColor(Color.GRAY);// 设置画笔颜色
		g.fillRect(0, 0, width, height);// 填充图片
		// 2.2,画边框
		g.setColor(Color.yellow);
		g.drawRect(0, 0, width - 1, height - 1);
		// 2.3,写验证码
		String str = "ABCDEFGHIJKLMOPQESTUVWXYZabcdefghijklmopqrstuvwxya0123456789";//
		Random random = new Random();// 随机对象
		g.setFont(new Font("黑体", Font.BOLD, 25));
		char ch = ' ';
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 1; i <= 4; i++) {
			ch = str.charAt(random.nextInt(str.length()));// 随机字符
			g.drawString(ch + "", width / 5 * i, height / 2);
			strBuilder.append(ch);
		}
		//四位字符的验证码
		String checkCode = strBuilder.toString();
		//获取Session
		request.getSession().setAttribute("checkCode_session", checkCode);
		// 2.4,绘制干扰线
		g.setColor(Color.green);
		// 随机生成坐标点
		for (int i = 0; i < 10; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		// 3，将图片输出到页面展示
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
