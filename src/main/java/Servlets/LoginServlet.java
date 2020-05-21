package Servlets;

import DAO.UserMapper;
import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final UserMapper userDao;
    private final TemplateEngine engine;
    public LoginServlet(TemplateEngine engine, SqlSession session) {
        this.userDao = session.getMapper(UserMapper.class);
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // sent form
        throw new IllegalArgumentException("not impl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check?=>HomeServlet,set cookie to => '/' : LoginServlet (error message);
        throw new IllegalArgumentException("not impl");
    }
}
