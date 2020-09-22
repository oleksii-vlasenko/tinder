package servlet;

import controller.AuthController;
import model.Auth;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final AuthController controller;
    private Auth auth;

    public LoginServlet(AuthController aController, Auth auth) {
        this.controller = aController;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.resources("/content");
        HashMap<String, Object> data = new HashMap<>();

        engine.render("login.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> email = Optional.ofNullable(req.getParameter("email"));
        Optional<String> password = Optional.ofNullable(req.getParameter("password"));

        email.flatMap(e ->
                password
                        .flatMap(p -> this.controller.check(e, p))
        ).ifPresent(auth::setId);

        int id = req.getParameter("first") == null
                ? this.auth.getId() == 0 ? 0 : this.controller.checkUser(this.auth.getId()).orElse(-1)
                : this.controller.checkEmail(email.orElse("")).orElse(false) ? 0 : -1;

        switch (id) {
            case -1:
                Optional.of(this.auth.getId()).filter(a -> a == 0)
                        .flatMap(f ->
                                email.flatMap(e ->
                                        password.flatMap(p ->
                                                this.controller.create(e, p))))
                        .ifPresent(i -> this.auth.setId(i));
                resp.sendRedirect("/user");
                break;
            case 0:
                resp.sendRedirect("/login");
                break;
            default:
                this.controller.checkUser(this.auth.getId()).ifPresent(i -> {
                    this.auth.setUser_id(i);
                });
                resp.sendRedirect("/users");
        }
    }
}
