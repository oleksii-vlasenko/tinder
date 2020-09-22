package servlet;

;

import controller.UserController;
import model.Auth;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

public class UsersServlet extends HttpServlet {

    private final UserController controller;
    private final Auth auth;

    public UsersServlet(UserController userController, Auth auth) {
        this.controller = userController;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.getActual(this.auth.getUser_id())
                .filter(s -> s.size() > 0)
                .map(r -> {
                    TemplateEngine engine = TemplateEngine.resources("/content");
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("name", r.get(0).getName());
                    data.put("image", r.get(0).getImage());
                    data.put("id", r.get(0).getId());
                    System.out.println(r.get(0));
                    engine.render("user.ftl", data, resp);
                    return r;
                })
                .orElseGet(() -> {
                    try{
                        resp.sendRedirect("/liked");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    return new ArrayList<>();
                });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(req.getParameter("like")).ifPresent(s -> {
            controller.addLike(this.auth.getUser_id(), Integer.parseInt(s));
        });
        Optional.ofNullable(req.getParameter("dislike")).ifPresent(s -> {
            controller.addDislike(this.auth.getUser_id(), Integer.parseInt(s));
        });
        resp.sendRedirect("/users");
    }
}
