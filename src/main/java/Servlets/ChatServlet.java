package Servlets;

import Models.Message;
import Services.ChatService;
import Services.LikedUserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
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
    private final ChatService service;
    private static final Logger log = LogManager.getLogger(LikedUserService.class);


    public ChatServlet(TemplateEngine engine, SqlSession session) {
        this.engine=engine;
        this.service = new ChatService(session);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Chat servlet running...");

        HashMap<String, Object> data = new HashMap<>();
        Optional<Cookie> loggedUserCookie = Arrays.stream(req.getCookies())
                .filter(this::checkCookie)
                .findFirst();

        if (loggedUserCookie.isPresent()){
            String loggedUser = loggedUserCookie.get().getValue();
            String chatFriend = req.getParameter("messageTo");
            List<Message> messages = service.getMessages(loggedUser, chatFriend);
            log.info(messages.toString());
            data.put("messages",messages);
            data.put("loggedUser",loggedUser);
            data.put("chatFriend",chatFriend);

            engine.render(resp,"chat.html", data);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private boolean checkCookie(Cookie cookie) {
        return cookie.getName().equals("id");
    }
}
