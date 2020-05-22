import DAO.UserMapper;
import DBServer.DBSetup;
import DBServer.PostgresServer;
import Models.Gender;
import Models.User;
import Servlets.*;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerApp {
    private final static PostgresServer dbserver = new PostgresServer();
    private final static String URL = "jdbc:postgresql://ec2-54-195-247-108.eu-west-1.compute.amazonaws.com:5432/d6953kdjrm3v1c";
    private final static String NAME = "kgdebzmkjzrjyg";
    private final static String PASSWORD = "55230058b15e4f8e18a9b543cd053afe105413d85b996e6872458bf2030ae99f";
    private final static TemplateEngine engine = new TemplateEngine("./src/main/java/templates/");

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        EnumSet<DispatcherType> ft = EnumSet.of(DispatcherType.REQUEST);
        ServletContextHandler handler = new ServletContextHandler();
        DBSetup.migrate(URL,NAME,PASSWORD,true);                                   //Might be a problem. Two connection for migration and myBatis
        SqlSession session = dbserver.createConnection(URL, NAME, PASSWORD);

//        Testing the connection+
//        User user = new User(UUID.randomUUID(), "test1", "test", Gender.F, "path");
//        UserMapper mapper = session.getMapper(UserMapper.class);
//        mapper.insert(user);
//        session.commit();
//        session.close();
//        testing ends

        handler.addServlet(new ServletHolder(new UsersServlet(engine,session)),"/users/");
        handler.addServlet(new ServletHolder(new RegisterServlet(engine,session)),"/register/");
        handler.addServlet(new ServletHolder(new LoginServlet(engine,session)),"/login/");
        handler.addFilter(new FilterHolder(new AuthenticationFilter(session)),"/",ft);


        server.setHandler(handler);
        server.start();
        server.join();
    }

}
