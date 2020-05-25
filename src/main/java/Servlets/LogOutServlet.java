package Servlets;

import Services.CookieService;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final CookieService cookieService;

    public LogOutServlet(TemplateEngine engine) {
        this.engine=engine;
        cookieService = new CookieService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookieService.logoutUser(req,resp);
        engine.render(resp,"logout.ftl");
    }
}
