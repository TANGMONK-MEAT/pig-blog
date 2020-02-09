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
 * �������ɶ�̬��֤��
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
		// 1,����һ���������ڴ��д���һ��ͼƬ����֤��ͼƬ����
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		// 2������ͼƬ
		// 2.1,��䱳��ɫ
		Graphics g = image.getGraphics();// ��ȡͼƬ����Ļ���
		g.setColor(Color.GRAY);// ���û�����ɫ
		g.fillRect(0, 0, width, height);// ���ͼƬ
		// 2.2,���߿�
		g.setColor(Color.yellow);
		g.drawRect(0, 0, width - 1, height - 1);
		// 2.3,д��֤��
		String str = "ABCDEFGHIJKLMOPQESTUVWXYZabcdefghijklmopqrstuvwxya0123456789";//
		Random random = new Random();// �������
		g.setFont(new Font("����", Font.BOLD, 25));
		char ch = ' ';
		StringBuilder strBuilder = new StringBuilder();
		for (int i = 1; i <= 4; i++) {
			ch = str.charAt(random.nextInt(str.length()));// ����ַ�
			g.drawString(ch + "", width / 5 * i, height / 2);
			strBuilder.append(ch);
		}
		//��λ�ַ�����֤��
		String checkCode = strBuilder.toString();
		//��ȡSession
		request.getSession().setAttribute("checkCode_session", checkCode);
		// 2.4,���Ƹ�����
		g.setColor(Color.green);
		// ������������
		for (int i = 0; i < 10; i++) {
			int x1 = random.nextInt(width);
			int y1 = random.nextInt(height);
			int x2 = random.nextInt(width);
			int y2 = random.nextInt(height);
			g.drawLine(x1, y1, x2, y2);
		}
		// 3����ͼƬ�����ҳ��չʾ
		ImageIO.write(image, "jpg", response.getOutputStream());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
