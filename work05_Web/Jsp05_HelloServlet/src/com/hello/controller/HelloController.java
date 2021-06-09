package com.hello.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HelloController")
public class HelloController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String contextP;
	private String initP;
	
	
    public HelloController() {
        System.out.println("servlet ������!!");
    }
    
    @Override
    public void init(ServletConfig config) {
    	System.out.println("servlet init(����!)");
    	
    	contextP = config.getServletContext().getInitParameter("url");
    	initP = config.getInitParameter("driver");
    	
    	System.out.println("context param: " + contextP);
    	System.out.println("init param:" + initP);
    	
    }
    
    	
    
    //�� ��Ŀ� ���� ���� �ٸ� ���� �ڵ带 ������.
    
    //get���
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get���!");
		
		doPost(request, response);
	}

	//post���
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post���!");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("�Ķ����: " + command);
		
		//java���Ͽ��� html�����ϴ¹�
		PrintWriter out = response.getWriter();
		out.print("<h1 style='background-color:skyblue;'>HelloServlet</H1>");
		out.print("<a href='home.html'>���ư���</a>");
		
		
		
		
		
	}
	
	@Override
	public void destroy() {
		System.out.println("servlet destroy(����)!");
		System.out.println("��!");
	}
	
	

}
