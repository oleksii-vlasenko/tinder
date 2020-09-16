package servlet;

;

import dao.CollectionUserDao;
import model.User;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class UsersServlet extends HttpServlet {

    private Integer currUserIndex = 0;
    private final CollectionUserDao users = new CollectionUserDao();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        try (OutputStream os = resp.getOutputStream()) {
//            URI uri = this.getClass().getClassLoader().getResource("index.html").toURI();
//            Path path = Paths.get(uri);
//            Files.copy(path, os);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (currUserIndex + 1 == users.getSize()) {
            resp.sendRedirect("/liked");
        } else {
            TemplateEngine engine = TemplateEngine.resources("/content");
            HashMap<String, Object> data = new HashMap<>();

            String userName = users.getUser(currUserIndex)
                    .map(User::getName)
                    .orElse("Unknown user");
            String userImage = users.getUser(currUserIndex)
                    .map(User::getImage)
                    .orElse("");
            data.put("name", userName);
            data.put("image", userImage);
            engine.render("user.ftl", data, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> like = Optional.of(req.getParameter("like"));
        users.getUser(currUserIndex)
                .filter(u -> like
                        .filter(l -> l.equals("yes"))
                        .isPresent()
                )
                .ifPresent(r -> r.addLike(3));
        currUserIndex = currUserIndex == users.getSize() - 1 ? currUserIndex : currUserIndex + 1;
        resp.sendRedirect("/users");
    }
}
