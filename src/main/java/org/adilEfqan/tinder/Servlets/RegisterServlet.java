package org.adilEfqan.tinder.Servlets;

import org.adilEfqan.tinder.Models.User;
import org.adilEfqan.tinder.Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.adilEfqan.tinder.utils.MessageService.MessageService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final UserService service;
    private final MessageService messages = new MessageService();

    public RegisterServlet(TemplateEngine engine, SqlSession session) {
        this.engine=engine;
        this.service = new UserService(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        engine.render(resp,"signUp.ftl");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender = req.getParameter("gender");
        String imageURL = req.getParameter("imageURL");
        User newUser = imageURL != null ? new User(username, password, gender, imageURL) : new User(username, password, gender);
        System.out.println(newUser.toString());
        if (service.authenticateAndRegisterUser(newUser)){
            resp.sendRedirect("/login/");
        } else {
            messages.WARNING(resp,"Username was taken. Please choose another!");
            resp.sendRedirect("/register/");
        }
    }

}
