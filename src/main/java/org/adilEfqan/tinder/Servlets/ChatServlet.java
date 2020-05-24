package org.adilEfqan.tinder.Servlets;

import org.adilEfqan.tinder.Models.*;
import org.adilEfqan.tinder.Services.*;
import org.adilEfqan.tinder.Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ChatServlet extends HttpServlet {
    private final TemplateEngine engine;
    private final ChatService chatService;
    private final UserService userService;
    private final CookieService cookieService = new CookieService();
    private static final Logger log = LogManager.getLogger(ChatServlet.class);

    public ChatServlet(TemplateEngine engine, SqlSession session) {
        this.engine=engine;
        this.chatService = new ChatService(session);
        this.userService = new UserService(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, Object> data = new HashMap<>();
        Optional<Cookie> loggedUserCookie = Arrays.stream(req.getCookies())
                .filter(this::checkCookie)
                .findFirst();

        if (loggedUserCookie.isPresent()){
            String loggedUser = loggedUserCookie.get().getValue();
            String chatFriendID = req.getParameter("messageTo");
            List<Message> messages = chatService.getMessages(loggedUser, chatFriendID);
            Optional<User> chatFriendOpt = userService.getUserByID(chatFriendID);
            if (chatFriendOpt.isPresent()){
                User chatFriend = chatFriendOpt.get();
                log.warn(loggedUser+" "+chatFriend);
                log.info(messages.toString());

                data.put("messages",messages);
                data.put("loggedUser",loggedUser);
                data.put("chatFriend",chatFriend);

                engine.render(resp,"chat.ftl", data);
            }else {
                resp.sendRedirect("/users/liked/");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sendTo = req.getParameter("sendTo");
        String message = req.getParameter("message");
        String loggedUser = cookieService.fetchUserId(req.getCookies());

        Message newMessage = new Message(loggedUser, sendTo, message);

        chatService.insertMessage(newMessage);
        resp.sendRedirect(String.format("/chat/?messageTo=%s",sendTo));


    }



    private boolean checkCookie(Cookie cookie) {
        return cookie.getName().equals("id");
    }

}
