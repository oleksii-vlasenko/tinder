import controller.AuthController;
import controller.MessageController;
import controller.UserController;
import dao.CollectionAuthDao;
import dao.CollectionMessageDao;
import dao.CollectionUserDao;
import model.Auth;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.AuthService;
import service.MessageService;
import service.UserService;
import servlet.*;

public class App {

    private static final UserController uController = new UserController(new CollectionUserDao());
    private static final MessageController mController = new MessageController(new MessageService(new CollectionMessageDao()));
    private static final AuthController aController = new AuthController(new CollectionAuthDao());
    private static Auth auth = new Auth(0);

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UsersServlet(uController, auth)), "/users");
        handler.addServlet(IndexServlet.class, "/index");
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
