package com.fs11.step.tinder.servlet;

import com.fs11.step.tinder.controller.MessageController;
import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.model.Message;
import com.fs11.step.tinder.util.TemplateEngine;
import com.fs11.step.tinder.util.TinderCookie;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class MessageServlet extends HttpServlet {

    private final UserController uController;
    private final MessageController mController;

    public MessageServlet(UserController uc, MessageController mc) {
        this.uController = uc;
        this.mController = mc;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TinderCookie.getCookie(req).ifPresent(i -> {
            TemplateEngine engine = TemplateEngine.resources("/content");
            Optional.ofNullable(req.getParameter("id"))
                    .flatMap(s -> uController.get(Integer.parseInt(s)))
                    .ifPresent(u -> {
                        mController.getUserMessages(i, u.getId()).ifPresent(l -> {
                            HashMap<String, Object> data = new HashMap<>();
                            data.put("id", i);
                            data.put("user", u);
                            data.put("messages", l);
                            engine.render("chat.ftl", data, resp);
                        });
                    });
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TinderCookie.getCookie(req).ifPresent(ui -> {
            Optional.ofNullable(req.getParameter("text"))
                    .ifPresent(t -> {
                        Optional.ofNullable(req.getParameter("id"))
                                .map(Integer::parseInt)
                                .ifPresent(i -> {
                                    mController.save(new Message(-1, ui, i, t, new Date().getTime()));
                                });
                    });
        });
        resp.sendRedirect(req.getRequestURI() + "?id=" + req.getParameter("id"));

    }
}
