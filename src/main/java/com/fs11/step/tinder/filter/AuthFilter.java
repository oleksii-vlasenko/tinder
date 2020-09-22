package com.fs11.step.tinder.filter;

import com.fs11.step.tinder.util.TinderCookie;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthFilter implements HttpFilter {

    @Override
    public void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain chain) throws IOException, ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest &&
                resp instanceof HttpServletResponse) {
            HttpServletRequest rq = (HttpServletRequest) req;
            HttpServletResponse rs = (HttpServletResponse) resp;
            Boolean isValid = TinderCookie.getCookie(rq).map(id -> id >= -1).orElse(false);
            if (!isValid) {
                rs.sendRedirect("/login");
            }
            doHttpFilter(rq, rs, chain);
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}
