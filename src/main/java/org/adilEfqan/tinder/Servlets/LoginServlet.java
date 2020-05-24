package org.adilEfqan.tinder.Servlets;


import org.adilEfqan.tinder.Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.adilEfqan.tinder.utils.MessageService.MessageService;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoginServlet extends HttpServlet {
    private final UserService userService;
    private final TemplateEngine engine;
    private final MessageService messages = new MessageService();

    public LoginServlet(TemplateEngine engine, SqlSession session) {
        this.userService = new UserService(session);
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        engine.render(resp,"login.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // check?=>HomeServlet,set cookie to => '/' : LoginServlet (error message);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username+" "+password);
        Optional<String> loggedInUserID = userService.authenticateUser(username, password);
        System.out.println(loggedInUserID.toString());
        if (loggedInUserID.get().equals(Optional.empty())){
            messages.WARNING(resp,"\"Username or password wrong!\"");
            resp.sendRedirect("/login/");
        }else {

            addCookie(resp,loggedInUserID.get().toString());

            messages.INFO(resp,"Success");
            resp.sendRedirect("/users/");
        }
    }

    private void addCookie(HttpServletResponse resp, String userID) {
        Cookie cookie = new Cookie("id", userID);
        cookie.setHttpOnly(true);
        cookie.setPath("/");

        resp.addCookie(cookie);
    }
}
