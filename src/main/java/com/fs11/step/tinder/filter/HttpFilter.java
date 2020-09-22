package com.fs11.step.tinder.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface HttpFilter extends Filter {
    @Override
    default void init(FilterConfig filterConfig) throws ServletException {

    }

    void doHttpFilter(HttpServletRequest rq, HttpServletResponse rs, FilterChain chain) throws IOException, ServletException;

    @Override
    default void doFilter(
            ServletRequest req,
            ServletResponse resp,
            FilterChain chain
    ) throws IOException, ServletException {

    }

    @Override
    default void destroy() {

    }
}
