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
    private final static String URL = "jdbc:postgresql://ec2-54-195-247-108.eu-west-1.compute.amazonaws.com:5432/d6953kdjrm3v1c";
    private final static String NAME = "kgdebzmkjzrjyg";
    private final static String PASSWORD = "55230058b15e4f8e18a9b543cd053afe105413d85b996e6872458bf2030ae99f";


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
