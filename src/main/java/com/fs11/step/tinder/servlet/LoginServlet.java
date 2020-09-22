package com.fs11.step.tinder.servlet;

import com.fs11.step.tinder.controller.AuthController;
import com.fs11.step.tinder.util.TemplateEngine;
import com.fs11.step.tinder.util.TinderCookie;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final AuthController controller;

    public LoginServlet(AuthController aController) {
        this.controller = aController;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TinderCookie.removeCookie(resp);

        TemplateEngine engine = TemplateEngine.resources("/content");
        engine.render("login.ftl", new HashMap<>(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> email = Optional.ofNullable(req.getParameter("email"));
        Optional<String> password = Optional.ofNullable(req.getParameter("password"));

        Optional<Integer> authId = email.flatMap(e ->
                password
                        .flatMap(p -> this.controller.check(e, p))
        );


        Optional<Integer> id = authId.map(ai -> req.getParameter("first") == null
                ? ai == 0 ? 0 : this.controller.checkUser(ai).orElse(-1)
                : this.controller.checkEmail(email.orElse("")).orElse(false) ? 0 : -1);

        switch (id.orElse(0)) {
            case -1:
                int newAuth = authId.filter(a -> a == 0)
                        .flatMap(f ->
                                email.flatMap(e ->
                                        password.flatMap(p ->
                                                this.controller.create(e, p)))).orElse(0);
                TinderCookie.removeCookie(resp);
                resp.sendRedirect("/user?id=" + newAuth);
                break;
            case 0:
                resp.sendRedirect("/login");
                break;
            default:
                this.controller.checkUser(authId.get()).ifPresent(userId -> {
                    TinderCookie.addCookie(resp, id.get());
                });
                resp.sendRedirect("/users");
        }
    }
}
