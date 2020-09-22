package com.fs11.step.tinder.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AnyFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream outputStream = resp.getOutputStream()) {
            String rqName =req.getRequestURI();
            Path fileName = Paths.get(rqName);
            Path rs = Paths.get(this.getClass().getClassLoader().getResource(fileName.toString()).toURI());
            Files.copy(rs, outputStream);
        } catch (URISyntaxException e) {
            resp.setStatus(400);
        }
    }
}
