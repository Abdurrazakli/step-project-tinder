import DAO.UserMapper;
import DBServer.DBSetup;
import DBServer.PostgresServer;
import Models.Gender;
import Models.User;
import Servlets.*;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.awt.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ServerApp {
    private final static PostgresServer dbserver = new PostgresServer();
//
//    private final static String URL = "jdbc:mysql://localhost:3306/tinder";
//    private final static String NAME = "root";
//    private final static String PASSWORD = "efqan1999";


    private final static String URL = "jdbc:postgresql://ec2-54-195-247-108.eu-west-1.compute.amazonaws.com:5432/d6953kdjrm3v1c";
    private final static String NAME = "kgdebzmkjzrjyg";
    private final static String PASSWORD = "55230058b15e4f8e18a9b543cd053afe105413d85b996e6872458bf2030ae99f";
    private final static TemplateEngine engine = new TemplateEngine("./src/main/java/templates/");

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);
        ServletContextHandler handler = new ServletContextHandler();
        DBSetup.migrate(URL,NAME,PASSWORD,true);
        SqlSession session = dbserver.createConnection(URL, NAME, PASSWORD);

//        Testing the connection+
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> likedUser = mapper.getLikedUser("2ce6981a-b438-4baa-b879-17203fa59210");
        System.out.println(likedUser.toString());
        session.commit();
//        testing ends



        handler.addServlet(new ServletHolder(new LikedUserServlet(engine,session)),"/users/liked/");
        handler.addServlet(new ServletHolder(new UsersServlet(engine,session)),"/users/");
        handler.addServlet(new ServletHolder(new RegisterServlet(engine,session)),"/register/");
        handler.addServlet(new ServletHolder(new LoginServlet(engine,session)),"/login/");
        handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/users",ft);

        server.setHandler(handler);
        server.start();
        server.join();
    }

}
