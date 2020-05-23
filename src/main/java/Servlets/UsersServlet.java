package Servlets;

import Models.User;
import Services.CookieService;
import Services.LikeDislikeService;
import Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Optional;

public class UsersServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final UserService service;
    private final CookieService cookieService;
    private final LikeDislikeService likeDislikeService;
    private final String pageTemplate = "like-page.ftl";
    public UsersServlet(TemplateEngine engine, SqlSession session) {
        this.engine = engine;
        service = new UserService(session);
        cookieService = new CookieService();
        likeDislikeService = new LikeDislikeService(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String user_id = cookieService.fetchUserId(req.getCookies());

        User potentialLover = service.getANewLove(user_id);
        HashMap<String, Object> data = new HashMap<>();
        data.put("loverUser",potentialLover);
        engine.render(resp,pageTemplate,data);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String loverId = req.getParameter("id");
        String action = req.getParameter("action");
        String userId = cookieService.fetchUserId(req.getCookies());
        likeDislikeService.actBetween(userId,loverId,action);
        resp.sendRedirect("/users/");
    }
}
