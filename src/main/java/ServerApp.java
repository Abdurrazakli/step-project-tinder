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
    private final static String URL = "jdbc:postgresql://ec2-54-228-251-117.eu-west-1.compute.amazonaws.com:5432/d63vvi0hqlhno4";
    private final static String NAME = "kvgevlmdsbfkhp";
    private final static String PASSWORD = "84e5e80296109d5da5a7dee02c518f38b008533ac9bee837ed1880421e61c909";


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
