package com.company.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "JSPFileFilter")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;
            HttpSession session = ((HttpServletRequest) request).getSession();

            if (session == null || session.getAttribute("loggedInUser") == null) {
                res.sendRedirect("home"); // No logged-in user found, so redirect to login page.
            } else {
                chain.doFilter(req, res); // Logged-in user found, so just continue request.
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
