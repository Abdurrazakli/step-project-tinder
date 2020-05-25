package org.adilEfqan.tinder;

import org.adilEfqan.tinder.DBServer.PostgresServer;
import org.adilEfqan.tinder.Servlets.*;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApp {
    private final static String pathToStaticFiles = "templates/";
    private final static PostgresServer dbserver = new PostgresServer();
    private final static TemplateEngine engine = TemplateEngine.resources(pathToStaticFiles);

//
//    private final static String URL = "jdbc:mysql://localhost:3306/tinder";
//    private final static String NAME = "root";
//    private final static String PASSWORD = "efqan1999";

    private final static String URL = HerokuENV.JDBC_URL();
    private final static String NAME = HerokuENV.JDBC_USERNAME();
    private final static String PASSWORD = HerokuENV.JDBC_PASSWORD();
    private final static int PORT = HerokuENV.PORT();

   /* private final static String URL = "jdbc:postgresql://ec2-54-195-247-108.eu-west-1.compute.amazonaws.com:5432/d6953kdjrm3v1c";
    private final static String NAME = "kgdebzmkjzrjyg";
    private final static String PASSWORD = "55230058b15e4f8e18a9b543cd053afe105413d85b996e6872458bf2030ae99f";
    private final static int PORT = 8080;*/

    public static void main(String[] args) throws Exception {
        Server server = new Server(PORT);
        EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);
        ServletContextHandler handler = new ServletContextHandler();
//        DBSetup.migrate(URL,NAME,PASSWORD);
        SqlSession session = dbserver.createConnection(URL, NAME, PASSWORD);


        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/login/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/users/liked/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/chat/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/users/css/*");
        handler.addServlet(new ServletHolder(new StaticServlet("css")), "/logout/css/*");
        handler.addServlet(new ServletHolder(new ChatServlet(engine,session)),"/chat/");
        handler.addServlet(new ServletHolder(new LogOutServlet(engine)),"/logout/");
        handler.addServlet(new ServletHolder(new LikedUserServlet(engine,session)),"/users/liked/");
        handler.addServlet(new ServletHolder(new UsersServlet(engine,session)),"/users/");
        handler.addServlet(new ServletHolder(new RegisterServlet(engine,session)),"/register/");
        handler.addServlet(new ServletHolder(new LoginServlet(engine,session)),"/login/");
        handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/",ft);
        handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/users/*",ft);
        handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/chat/*",ft);


        //handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/users/",ft);

        server.setHandler(handler);
        server.start();
        server.join();
    }

}
