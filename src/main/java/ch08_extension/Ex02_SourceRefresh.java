package ch08_extension;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ex02_SourceRefresh
 */
@WebServlet("/ch08/src2")
public class Ex02_SourceRefresh extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/jw/ch08/src2 doGet()");
		
		String msg = "SendRedirect, 한글 메세지";
		msg = URLEncoder.encode(msg, "utf-8");
		System.out.println(msg);
		
		response.addHeader("Refresh", "5; url=/jw/ch08/dst2?msg=" + msg);
	}

}
