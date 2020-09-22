package com.fs11.step.tinder;

import com.fs11.step.tinder.controller.AuthController;
import com.fs11.step.tinder.controller.MessageController;
import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.dao.SQLAuthDao;
import com.fs11.step.tinder.dao.SQLMessageDao;
import com.fs11.step.tinder.dao.SQLUserDao;
import com.fs11.step.tinder.model.Auth;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.fs11.step.tinder.servlet.*;

public class App {

    private static final UserController uController = new UserController(new SQLUserDao());
    private static final MessageController mController = new MessageController(new SQLMessageDao());
    private static final AuthController aController = new AuthController(new SQLAuthDao());
    private static final Auth auth = new Auth(0);

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(uController, auth)), "/users");
        handler.addServlet(new ServletHolder(new LikedServlet(uController, auth)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(uController, mController, auth)), "/message");
        handler.addServlet(new ServletHolder(new LoginServlet(aController, auth)), "/login");
        handler.addServlet(new ServletHolder(new UserServlet(uController, aController, auth)), "/user");

        handler.addServlet(AnyFileServlet.class, "/css/*");
        handler.addServlet(AnyFileServlet.class, "/js/*");

//        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
