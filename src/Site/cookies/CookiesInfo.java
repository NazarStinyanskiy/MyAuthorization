package Site.cookies;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


public class CookiesInfo {

    public static String[] getCookies(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();

        String[] info = {"",""};
        if(cookies == null){
            return info;
        }
        for(Cookie c : cookies){
            if(c.getName().equals("login")){
                info[0] = c.getValue();
            }

            if(c.getName().equals("password")){
                info[1] = c.getValue();
            }
        }

        return info;
    }
}
