package authorization;

import authorization.enums.LogStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/html/login.html");
        rd.forward(req,resp);

        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String log = req.getParameter("log");
        String pass = req.getParameter("pass");

        AuthorizationHandler handler = new AuthorizationHandler();
        LogStatus logStatus = handler.log(log, pass);

        switch (logStatus){
            case SUCCESS:
                Cookie loginCookie = new Cookie("login", log);
                Cookie passwordCookie = new Cookie("password", pass);
                resp.addCookie(loginCookie);
                resp.addCookie(passwordCookie);

                resp.sendRedirect("/account");
                break;
            case WRONG_PASSWORD:
                req.getRequestDispatcher("/login/wrong-password").forward(req, resp);
                break;
            case UN_CORRECT_LOGIN:
                req.getRequestDispatcher("/login/wrong-login").forward(req, resp);
                break;
        }
    }
}
