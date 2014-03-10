package com.projetweb;
import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Projet_Web_2014Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Helloooo, world");
	}
}
