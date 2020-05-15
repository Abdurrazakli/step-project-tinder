import DBServer.PostgreServer;
import Servlets.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.net.URI;
import java.sql.Connection;
import java.util.EnumSet;

public class ServerApp {
    private final static PostgreServer dbserver = new PostgreServer();
    private final static String URL = "";
    private final static String NAME = "";
    private final static String PASSWORD = "";


    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);


        ServletContextHandler handler = new ServletContextHandler();
        Connection conn = dbserver.createConnection(URL,NAME,PASSWORD);

        handler.addServlet(new ServletHolder(new LoginServlet()),"/login/");


        server.setHandler(handler);
        server.start();
        server.join();
    }
}
