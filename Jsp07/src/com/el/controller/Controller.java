package com.el.controller;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.el.score.Score;
@WebServlet("/controller.do") //어노테이션
public class Controller extends HttpServlet {
private static final long serialVersionUID = 1L;
public Controller() {
super();
}
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String command = request.getParameter("command");
System.out.println("[command: " + command + "]");
if(command.equals("elpage")) {
response.sendRedirect("basic.jsp");
} else if(command.equals("eltest")) {
Score sc = new Score("이창진", 100, 66, 85);
request.setAttribute("score", sc);
RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
dispatch.forward(request, response);
}
}
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
doGet(request, response);
}
}