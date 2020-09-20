package servlet;

;

import controller.UserController;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;

public class UsersServlet extends HttpServlet {

    private final UserController controller;
    private final int id;

    public UsersServlet(UserController userController, int id) {
        this.controller = userController;
        this.id = id;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        controller.getActual(this.id)
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
            controller.addLike(this.id, Integer.parseInt(s));
        });
        Optional.ofNullable(req.getParameter("dislike")).ifPresent(s -> {
            controller.addDislike(this.id, Integer.parseInt(s));
        });
        resp.sendRedirect("/users");
    }
}
