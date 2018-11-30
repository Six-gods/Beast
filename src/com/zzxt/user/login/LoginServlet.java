package com.zzxt.user.login;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzxt.user.User;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailorphone=request.getParameter("emailorphone");
		String password=request.getParameter("password");
		String email=null;
		String phone=null;
		if(emailorphone.indexOf("@")==-1) {
			phone=emailorphone;
		}else {
			email=emailorphone;	
		}
		System.out.println(email);
		LoginDao ld=new LoginDao();
		if(ld.login(email, phone, password)) {
			response.sendRedirect("/SecondGroup-2/jsp/person-address.jsp");
		}else {
			response.sendRedirect("/SecondGroup-2/jsp/login.jsp");
		}
	}
	
}
