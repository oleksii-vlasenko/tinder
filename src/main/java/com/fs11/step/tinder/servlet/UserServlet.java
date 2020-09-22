package com.fs11.step.tinder.servlet;

import com.fs11.step.tinder.controller.AuthController;
import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.model.User;
import com.fs11.step.tinder.util.TemplateEngine;
import com.fs11.step.tinder.util.TinderCookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

public class UserServlet extends HttpServlet {

    private final String COOKIE_NAME = "user";

    private final UserController userController;
    private final AuthController authController;

    public UserServlet(UserController uController, AuthController aController) {
        this.authController = aController;
        this.userController = uController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (TinderCookie.getCookie(req).isPresent()) resp.sendRedirect("users");

        Integer userId = Optional.ofNullable(req.getParameter("id")).map(Integer::parseInt).orElse(0);

        HashMap<String, Object> data = new HashMap<>();
        data.put("id", userId);

        TemplateEngine engine = TemplateEngine.resources("/content");
        engine.render("create-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Integer> authId = Optional.ofNullable(req.getParameter("id")).map(r -> {
            System.out.println(r);
            return Integer.parseInt(r);
        });

        Optional.ofNullable(req.getParameter("name"))
                .flatMap(n -> Optional.ofNullable(req.getParameter("image"))
                        .flatMap(i -> this.userController.save(new User(n, i))))
                .ifPresent(ir ->
                        this.authController.combine(authId.get(), ir).map(r -> {
                            TinderCookie.addCookie(resp, ir);
                            return r;
                        }));

        resp.sendRedirect("/users");
    }
}
