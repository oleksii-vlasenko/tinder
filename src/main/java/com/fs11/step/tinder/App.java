package com.fs11.step.tinder;

import com.fs11.step.tinder.controller.AuthController;
import com.fs11.step.tinder.controller.MessageController;
import com.fs11.step.tinder.controller.UserController;
import com.fs11.step.tinder.dao.SQLAuthDao;
import com.fs11.step.tinder.dao.SQLMessageDao;
import com.fs11.step.tinder.dao.SQLUserDao;
import com.fs11.step.tinder.filter.AuthFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.fs11.step.tinder.servlet.*;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class App {

    private static final UserController uController = new UserController(new SQLUserDao());
    private static final MessageController mController = new MessageController(new SQLMessageDao());
    private static final AuthController aController = new AuthController(new SQLAuthDao());

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(uController)), "/users");
        handler.addFilter(AuthFilter.class, "/users", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new LikedServlet(uController)), "/liked");
        handler.addFilter(AuthFilter.class, "/liked", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new MessageServlet(uController, mController)), "/message");
        handler.addFilter(AuthFilter.class, "/message", EnumSet.of(DispatcherType.REQUEST));

        handler.addServlet(new ServletHolder(new UserServlet(uController, aController)), "/user");
        handler.addServlet(new ServletHolder(new LoginServlet(aController)), "/login");

        handler.addServlet(AnyFileServlet.class, "/css/*");
        handler.addServlet(AnyFileServlet.class, "/js/*");

        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
