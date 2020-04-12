package authorization;

import authorization.enums.RegStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/html/registration.html");
        System.out.println(rd);
        rd.forward(req, resp);

        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String log = req.getParameter("log");
        String pass = req.getParameter("pass");

        AuthorizationHandler handler = new AuthorizationHandler();
        RegStatus regStatus = handler.reg(log, pass);

        switch(regStatus){
            case SUCCESS:
                Cookie loginCookie = new Cookie("login", log);
                Cookie passwordCookie = new Cookie("password", pass);
                resp.addCookie(loginCookie);
                resp.addCookie(passwordCookie);

                resp.sendRedirect("/account");
                break;
            case BAD_LOGIN_CHARACTER:
                req.getRequestDispatcher("/registration/bad-login-character").forward(req, resp);
                break;
            case BAD_PASSWORD_CHARACTER:
                req.getRequestDispatcher("/registration/bad-password-character").forward(req, resp);
                break;
            case BAD_PASSWORD_LENGTH:
                req.getRequestDispatcher("/registration/bad-password-length").forward(req, resp);
                break;
            case USER_EXIST:
                req.getRequestDispatcher("/registration/user-exist").forward(req, resp);
                break;


        }
    }
}