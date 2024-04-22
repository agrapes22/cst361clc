package util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import beans.User;

public class LoginFilter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	    System.out.println("Entered intop Login Filter");
	    HttpServletRequest req = (HttpServletRequest) request;
	    User login = (User) req.getSession().getAttribute("user");
	    String path = req.getRequestURI().substring(req.getContextPath().length());
	    System.out.println("path:" + path);

//	    if (path.contains("/Admin/") || path.contains("/Employee/")) {
//	        if (login != null) {
//	            if (login.getUsername() != null && !login.getUsername().equals("")) {
//	                chain.doFilter(request, response);
//	            } else {
//	                HttpServletResponse res = (HttpServletResponse) response;
//	                res.sendRedirect("/EMS2/faces/Html/Common/Login.xhtml");
//	            }
//	        } else {
//	            HttpServletResponse res = (HttpServletResponse) response;
//	            res.sendRedirect("/EMS2/faces/Html/Common/Login.xhtml");
//	        }
//
//
//	    } else {
//	        chain.doFilter(request, response);
//	    }
	}
	
}
