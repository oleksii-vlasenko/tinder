package servlet;

import controller.UserController;
import dao.CollectionUserDao;
import model.Auth;
import model.User;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LikedServlet extends HttpServlet {

    private final UserController controller;
    private final Auth auth;

    public LikedServlet(UserController controller, Auth auth) {
        this.controller = controller;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.resources("/content");
        HashMap<String, Object> data = new HashMap<>();
        List<User> likedList = new ArrayList<>();
        controller.getLiked(this.auth.getUser_id()).ifPresent(likedList::addAll);
        data.put("users", likedList);
        engine.render("like-page.ftl", data, resp);
    }
}
