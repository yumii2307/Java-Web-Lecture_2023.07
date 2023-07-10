package ch06_basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterMember
 */
@WebServlet("/ch06/register")
public class RegisterMember extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String html = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "	<meta charset=\"UTF-8\">"
				+ "	<title>Register Member</title>"
				+ "</head>"
				+ "<body style=\"margin: 40px;\">"
				+ "	<h1>회원 등록</h1>"
				+ "	<form action=\"/jw/ch06/register\" method=\"post\">"
				+ "		ID:		<input type=\"text\" name=\"uid\"><br><br>"
				+ "		PWD:	<input type=\"password\" name=\"pwd\"><br><br>"
				+ "		NAME:	<input type=\"text\" name=\"uname\"><br><br>"
				+ "		E-MAIL:	<input type=\"text\" name=\"email\"><br><br>"
				+ "     <input type=\"submit\" value=\"회원 가입\">"
				+ "	</form>"
				+ "</body>"
				+ "</html>";
		out.print(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uid_ = request.getParameter("uid");
		String pwd_ = request.getParameter("pwd");
		String uname_ = request.getParameter("uname");
		String email_ = request.getParameter("email");
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		String html2 = "<!DOCTYPE html>"
				+ "<html>"
				+ "<head>"
				+ "	<meta charset=\"UTF-8\">"
				+ "	<title>Register Member</title>"
				+ "</head>"
				+ "<style>"
				+ "th { width: 150px; }"
				+ "td { text-align: center; }"
				+ "</style>"
				+ "<body style=\"margin: 40px;\">"
				+ "	<h1>가입 정보 확인</h1>"
				+ "	<hr>"
				+ "	<h3>" + uid_ + " 님의 가입이 성공적으로 완료되었습니다.</h3>"
				+ "	<table border=\"1\">"
				+ "		<th>ID</th><th>PASSWORD</th><th>NAME</th><th>E-MAIL</th>"
				+ "		<tr>"
				+ "			<td>" + " "+ uid_ + " " + "</td>"
				+ "			<td>" + pwd_ + "</td>"
				+ "			<td>" + uname_ + "</td>"
				+ "			<td>" + email_ + "</td>"
				+ "		</tr>"
				+ "	</table>"
				+ "</body>"
				+ "</html>";
		out.print(html2);
	}

}
