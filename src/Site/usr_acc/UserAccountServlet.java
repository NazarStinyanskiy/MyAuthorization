package Site.usr_acc;

import Site.cookies.CookiesInfo;
import authorization.AuthorizationHandler;
import authorization.enums.LogStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/account")
public class UserAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String[] info = CookiesInfo.getCookies(req);

        AuthorizationHandler handler = new AuthorizationHandler();
        //make info[] as MAP
        if(handler.log(info[0], info[1]) != LogStatus.SUCCESS){
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }

        PrintWriter writer = resp.getWriter();

        writer.write("This is your account, " + info[0]);
    }
}