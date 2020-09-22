package com.fs11.step.tinder.servlet;

import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.model.User;
import com.fs11.step.tinder.util.TemplateEngine;
import com.fs11.step.tinder.util.TinderCookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LikedServlet extends HttpServlet {

    private final UserController controller;

    public LikedServlet(UserController controller) {
        this.controller = controller;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TinderCookie.getCookie(req).ifPresent(i -> {
            TemplateEngine engine = TemplateEngine.resources("/content");
            HashMap<String, Object> data = new HashMap<>();
            data.put("users", controller.getLiked(i));
            engine.render("like-page.ftl", data, resp);
        });

    }
}
