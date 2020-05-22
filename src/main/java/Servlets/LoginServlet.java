package Servlets;


import Services.UserService;
import org.apache.ibatis.session.SqlSession;
import utils.MessageService.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

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
        String rememberME = req.getParameter("rememberME");
        Optional<UUID> loggedInUserID = userService.authenticateUser(username, password);

        if (loggedInUserID.isEmpty()){
            messages.WARNING(resp,"Username or password is wrong!");
            resp.sendRedirect("/login/");
        }else {

            addCookie(resp,loggedInUserID.get().toString(),rememberME);

            messages.INFO(resp,String.format("Successfully registered in %s!",username));
            resp.sendRedirect("/users/");
        }
    }

    private void addCookie(HttpServletResponse resp, String userID, String rememberME) {
        Cookie cookie = new Cookie("id", userID);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        if (rememberME.equals("false")) { //FIXME RememberME problem
            throw new IllegalArgumentException("Not impl");
        }
        resp.addCookie(cookie);
    }
}
