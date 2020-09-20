import controller.MessageController;
import controller.UserController;
import dao.CollectionMessageDao;
import dao.CollectionUserDao;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.MessageService;
import service.UserService;
import servlet.*;

public class App {

    private static final UserController uController = new UserController(new UserService(new CollectionUserDao()));
    private static final MessageController mController = new MessageController(new MessageService(new CollectionMessageDao()));

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(uController, 1)), "/users");
        handler.addServlet(IndexServlet.class, "/index");
        handler.addServlet(new ServletHolder(new LikedServlet(uController, 1)), "/liked");
        handler.addServlet(new ServletHolder(new MessageServlet(uController, mController, 1)), "/message");

        handler.addServlet(AnyFileServlet.class, "/css/*");
        handler.addServlet(AnyFileServlet.class, "/js/*");

//        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
