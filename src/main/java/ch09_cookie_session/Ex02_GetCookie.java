package ch09_cookie_session;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex02_GetCookie
 */
@WebServlet("/ch09/getCookie")
public class Ex02_GetCookie extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		for (Cookie c: cookies) {
			System.out.println(c.getName() + ": " + c.getValue());
		}
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<h1>Cookie를 잘 읽었음.</h1>");
		
		for (Cookie c: cookies) {
			if (c.getName().equals("hangul-test")) {
				String kValue = URLDecoder.decode(c.getValue(), "utf-8");
				System.out.println(c.getName() + ": " + kValue);
				out.print("<h3>" + c.getName() + ": " + kValue);
			} else {
				System.out.println(c.getName() + ": " + c.getValue());
				out.print("<h3>" + c.getName() + ": " + c.getValue());
			}
		}
	}

}
