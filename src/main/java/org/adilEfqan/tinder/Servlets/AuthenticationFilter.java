package org.adilEfqan.tinder.Servlets;


import org.adilEfqan.tinder.Services.UserService;
import org.apache.ibatis.session.SqlSession;
import org.adilEfqan.tinder.utils.MessageService.MessageService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class AuthenticationFilter implements Filter {
    private final UserService service;
    private final MessageService messages = new MessageService();

    public AuthenticationFilter(SqlSession session) {
        service = new UserService(session);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter worked!!!");
        if (isHttp(req,resp) && isCorrectUser((HttpServletRequest) req) ) chain.doFilter(req,resp);
        else{
            HttpServletResponse response = (HttpServletResponse) resp;
            messages.WARNING(response,"Please Log In to continue!");
            response.sendRedirect("/login/");
        }


    }

    private boolean isCorrectUser(HttpServletRequest req) {
        try{
            System.out.println(req.getRequestURI().matches("(/login/|/register/)"));
            return Arrays.stream(req.getCookies()).anyMatch(this::checkCookie) &&
                    !req.getRequestURI().matches("(/login/|/register/)");
        }catch (NullPointerException e){
            return false;
        }
    }

    private boolean checkCookie(Cookie cookie) {
        return cookie.getName().equals("id") &&
         service.getUserByID(
                 cookie.getValue()
         ).isPresent();
    }

    private boolean isHttp(ServletRequest req, ServletResponse resp) {
        return req instanceof HttpServletRequest && resp instanceof HttpServletResponse;
    }

    @Override
    public void destroy() {

    }
}
