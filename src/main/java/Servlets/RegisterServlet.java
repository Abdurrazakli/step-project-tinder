package Servlets;

import Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.http.HttpServlet;

public class RegisterServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final UserService service;
    public RegisterServlet(TemplateEngine engine, SqlSession session) {
        this.engine=engine;
        this.service = new UserService(session);
    }
}
