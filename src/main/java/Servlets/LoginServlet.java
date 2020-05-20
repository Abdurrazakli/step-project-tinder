package Servlets;

import DAO.UserDao;
import DAO.UserMapper;
import org.apache.ibatis.session.SqlSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private final UserDao userDao;
    private final TemplateEngine engine;
    public LoginServlet(TemplateEngine engine, SqlSession session) {
        this.userDao = new UserDao(session.getMapper(UserMapper.class));
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
