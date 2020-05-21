package Servlets;

import Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final UserService service;

    public UsersServlet(TemplateEngine engine, SqlSession session) {
        this.engine = engine;
        service = new UserService(session);

    }
}
