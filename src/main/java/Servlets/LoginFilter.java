package Servlets;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginFilter implements Filter {
//    private final AuthController<Customer> authController;

//    public LoginFilter(Connection conn){
//        authController = new AuthController<>(conn);
//    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (isHttp(req,resp) && isCorrectUser((HttpServletRequest) req) ) chain.doFilter(req,resp);
        HttpServletResponse response = (HttpServletResponse) resp;
        response.sendRedirect("/login");
    }

    private boolean isCorrectUser(HttpServletRequest req) {
        try{
            return Arrays.stream(req.getCookies()).anyMatch(this::checkCookie) &&
                    !req.getRequestURI().matches("(/login|/register)");
        }catch (NullPointerException e){
            return false;
        }
    }

    private boolean checkCookie(Cookie cookie) {
//        return cookie.getName().equals("id") &&
//         authController.ifUserExists(
//                 UUID.fromString(cookie.getValue())
//         );
        throw new IllegalArgumentException("not impl");
    }

    private boolean isHttp(ServletRequest req, ServletResponse resp) {
        return req instanceof HttpServletRequest && resp instanceof HttpServletResponse;
    }

    @Override
    public void destroy() {

    }
}
