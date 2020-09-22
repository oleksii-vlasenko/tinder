package servlet;

import controller.AuthController;
import controller.UserController;
import model.Auth;
import model.User;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class UserServlet extends HttpServlet {

    private final UserController userController;
    private final AuthController authController;
    private final Auth auth;

    public UserServlet(UserController uController, AuthController aController, Auth auth) {
        this.authController = aController;
        this.userController = uController;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.resources("/content");
        HashMap<String, Object> data = new HashMap<>();

        engine.render("create-page.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(req.getParameter("name")).ifPresent(n -> {
                Optional.ofNullable(req.getParameter("image")).ifPresent(i -> {
                    this.userController.save(new User(n, i)).ifPresent(id -> this.auth.setUser_id(id));
                });
        });
        resp.sendRedirect("/users");
    }
}
