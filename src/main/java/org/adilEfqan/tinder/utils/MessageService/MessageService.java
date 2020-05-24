package org.adilEfqan.tinder.utils.MessageService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;


public class MessageService {
    private static final String INFO = "info";
    private static final String WARNING = "warning";
    private static final String ERROR = "error";


    public void INFO(HttpServletResponse resp, String message){
        System.out.println(message);

        Cookie messages = new Cookie("messages", URLEncoder.encode(message));
        Cookie tag = new Cookie("tag",INFO);
        setup(resp,messages,tag);
    }

    public void WARNING(HttpServletResponse resp, String message){
        System.out.println(message);
        System.out.println(URLEncoder.encode(message));
        Cookie messages = new Cookie("messages", URLEncoder.encode(message));
        Cookie  tag = new Cookie("tag",WARNING);
        setup(resp,messages,tag);
    }

    public void ERROR(HttpServletResponse resp, String message){

        Cookie messages = new Cookie("messages", message);// My cookie
        Cookie tag = new Cookie("tag",ERROR); //<div class=" alert type="Warr"">message</div>
        setup(resp,messages,tag);
    }

    private void setup(HttpServletResponse response, Cookie messages, Cookie tag){
        messages.setHttpOnly(true);
        messages.setPath("/");
        messages.setVersion(1);
        tag.setVersion(1);
        tag.setHttpOnly(true);
        tag.setPath("/");
        response.addCookie(messages);
        response.addCookie(tag);
    }
}

