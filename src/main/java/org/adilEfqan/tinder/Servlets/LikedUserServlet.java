package org.adilEfqan.tinder.Servlets;

import org.adilEfqan.tinder.Models.User;
import org.adilEfqan.tinder.Services.LikedUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


public class LikedUserServlet extends HttpServlet {
    private final TemplateEngine engin;
    private final LikedUserService service;
    private static final Logger log = LogManager.getLogger(LikedUserService.class);


    public LikedUserServlet(TemplateEngine engine, SqlSession session) {
        this.engin=engine;
        this.service = new LikedUserService(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Get method of Liked User Servlet");
        HashMap<String,Object> data = new HashMap<>();

        Optional<Cookie> user = Arrays.stream(req.getCookies())
                .filter(this::checkCookie)
                .findFirst();
        if (user.equals(Optional.empty())){
            System.out.println("User not exists");
            log.warn("User not exists");
            resp.sendRedirect("/login/");
        }else {
            List<User> allLikedUsers = service.getAllLikedUsers(user.get().getValue());
            log.info(allLikedUsers.toString());
            data.put("likedUsers",allLikedUsers);
            engin.render(resp,"people-list.ftl",data);
        }
    }

    private boolean checkCookie(Cookie cookie) {
        //id = dfsdfsdf
        //message = fdsfsdfsdfds
        return cookie.getName().equals("id");
    }
}
