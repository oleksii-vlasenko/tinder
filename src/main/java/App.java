import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlet.*;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(UsersServlet.class, "/users");
        handler.addServlet(IndexServlet.class, "/index");
        handler.addServlet(LikedServlet.class, "/liked");
        handler.addServlet(MessageServlet.class, "/message/*");

        handler.addServlet(AnyFileServlet.class, "/css/*");
        handler.addServlet(AnyFileServlet.class, "/js/*");

        handler.addServlet(RedirectServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
