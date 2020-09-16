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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UsersServlet extends HttpServlet {

    private Integer currUserIndex = 0;
    private final CollectionUserDao users = new CollectionUserDao() {{
        saveUser(new User("Adam", "https://upload.wikimedia.org/wikipedia/commons/a/ab/Cat_black.svg"));
        saveUser(new User("Brian", "https://upload.wikimedia.org/wikipedia/commons/thumb/6/60/Cat_silhouette.svg/1200px-Cat_silhouette.svg.png"));
        saveUser(new User("Carl", "https://upload.wikimedia.org/wikipedia/commons/2/27/Black_Cat_02812_svg_vector_nevit.svg"));
        saveUser(new User("Drake", "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Julius_the_Cat.svg/1027px-Julius_the_Cat.svg.png"));
        saveUser(new User("Earl", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Black_Cat_Vector.svg/1200px-Black_Cat_Vector.svg.png"));
        saveUser(new User("Fry", "https://i.pinimg.com/originals/ae/1e/d9/ae1ed91ec5d4db8efd9d4a9d10754fdd.png"));
    }};

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
        TemplateEngine engine = TemplateEngine.resources("/content");
        HashMap<String, Object> data = new HashMap<>();
        String userName = users.getUser(currUserIndex).map(User::getName).orElse("Unknown user");
        String userImage = users.getUser(currUserIndex).map(User::getImage).orElse("");
        data.put("name", userName);
        data.put("image", userImage);
        engine.render("user.ftl", data, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer size = users.getSize().orElse(0);
        currUserIndex = (currUserIndex + 1) % size;
        resp.sendRedirect("/users");
    }
}
