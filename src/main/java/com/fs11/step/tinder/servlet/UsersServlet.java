package com.fs11.step.tinder.servlet;

import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.model.Auth;
import com.fs11.step.tinder.util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

public class UsersServlet extends HttpServlet {

    private final String COOKIE_NAME = "user";

    private final UserController controller;
    private final Auth auth;

    public UsersServlet(UserController userController, Auth auth) {
        this.controller = userController;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<Integer> id = Optional.ofNullable(req.getCookies()).flatMap(l ->
                Arrays.stream(l)
                        .filter(c -> COOKIE_NAME.equals(c.getName()))
                        .findFirst())
                .map(r -> Integer.parseInt(r.getValue()));

        id.ifPresent(i -> controller.getActual(i)
                .filter(s -> s.size() > 0)
                .map(r -> {
                    TemplateEngine engine = TemplateEngine.resources("/content");
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", r.get(0).getName());
                    data.put("image", r.get(0).getImage());
                    data.put("id", r.get(0).getId());
                    engine.render("user.ftl", data, resp);
                    return r;
                })
                .orElseGet(() -> {
                    try {
                        resp.sendRedirect("/liked");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return new ArrayList<>();
                })
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Integer> id = Optional.ofNullable(req.getCookies()).flatMap(l ->
                Arrays.stream(l)
                        .filter(c -> COOKIE_NAME.equals(c.getName()))
                        .findFirst())
                .map(r -> Integer.parseInt(r.getValue()));

        Optional.ofNullable(req.getParameter("like")).ifPresent(s -> {
            id.ifPresent(i -> {
                controller.addLike(i, Integer.parseInt(s));
            });
        });
        Optional.ofNullable(req.getParameter("dislike")).ifPresent(s -> {
            id.ifPresent(i -> {
                controller.addDislike(i, Integer.parseInt(s));
            });
        });

        //        Optional.ofNullable(req.getParameter("like")).ifPresent(s -> {
//            controller.addLike(this.auth.getUser_id(), Integer.parseInt(s));
//        });
//        Optional.ofNullable(req.getParameter("dislike")).ifPresent(s -> {
//            controller.addDislike(this.auth.getUser_id(), Integer.parseInt(s));
//        });
        resp.sendRedirect("/users");
    }
}


