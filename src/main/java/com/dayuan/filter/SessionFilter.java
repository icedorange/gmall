package com.dayuan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "LoginStatusFilter", urlPatterns = { "/*" })
public class SessionFilter implements Filter {

	public SessionFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(true);
		String url = req.getRequestURL().toString();
		if (url.contains("/orderItem") || url.contains("/orderInfo") || url.contains("/showCart")
				|| url.contains("/addCart") || url.contains("/updateCart") || url.contains("/deleteCart")) {
			if (session.getAttribute("user") == null) {
				resp.setHeader("sessionstatus", "0");
				return;
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}
