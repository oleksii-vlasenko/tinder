package servlet;

import controller.MessageController;
import controller.UserController;
import model.Auth;
import model.Message;
import util.TemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class MessageServlet extends HttpServlet {

    private final UserController uController;
    private final MessageController mController;
    private final Auth auth;

    public MessageServlet(UserController uc, MessageController mc, Auth auth) {
        this.uController = uc;
        this.mController = mc;
        this.auth = auth;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngine.resources("/content");
        Optional.ofNullable(req.getParameter("id")).ifPresent(s -> {
            uController.get(Integer.parseInt(s)).ifPresent(u -> {
                mController.getUserMessages(this.auth.getUser_id(), u.getId()).ifPresent(l -> {
                    HashMap<String, Object> data = new HashMap<>();
                    data.put("id", this.auth.getUser_id());
                    data.put("user", u);
                    data.put("messages", l);
                    engine.render("chat.ftl", data, resp);
                });
            });
        });
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional.ofNullable(req.getParameter("text"))
                .ifPresent(t -> {
                    Optional.ofNullable(req.getParameter("id"))
                            .map(Integer::parseInt)
                            .ifPresent(i -> {
                                mController.save(new Message(-1, this.auth.getUser_id(), i, t, new Date().getTime()));
                            });
                });
        resp.sendRedirect(req.getRequestURI() + "?id=" + req.getParameter("id"));
    }
}
