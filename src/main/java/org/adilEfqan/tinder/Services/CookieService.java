package org.adilEfqan.tinder.Services;

import javax.servlet.http.Cookie;
import java.util.Arrays;

public class CookieService {

    public String fetchUserId(Cookie[] cookies) {
        return Arrays.stream(cookies).filter(c->c.getName().equals("id")).findFirst()
                .orElseThrow(()->new RuntimeException("It is not possible but user logged in without user id")).getValue();
    }
}
