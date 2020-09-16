package servlet;

import dao.CollectionUserDao;
import model.User;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LikedServlet extends HttpServlet {

    private final CollectionUserDao users = new CollectionUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.resources("/content");
        HashMap<String, Object> data = new HashMap<>();
        List<User> likedList = users.getUsers().stream()
                .filter(u -> u.getLikes().contains(3))
                .collect(Collectors.toList());
        data.put("users", likedList);

        engine.render("like-page.ftl", data, resp);
    }
}
