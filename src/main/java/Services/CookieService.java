package Services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

public class CookieService {

    public String fetchUserId(Cookie[] cookies) {
        return Arrays.stream(cookies).filter(c->c.getName().equals("id")).findFirst()
                .orElseThrow(()->new RuntimeException("It is not possible but user logged in without user id")).getValue();
    }


    public void logoutUser(HttpServletRequest req, HttpServletResponse resp) {
        for (Cookie c :
                req.getCookies()) {
            c.setMaxAge(0);
            resp.addCookie(c);
        }
    }
}
