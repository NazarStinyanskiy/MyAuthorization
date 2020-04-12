package authorization.errors.loginErrors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login/wrong-login")
public class WrongLogError extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.write("<html>" +
                "<head>" +
                "<title>Log in</title>" +
                "</head>" +
                "<body>" +
                "<h3>Your login is not correct. Please, try log in again.</h3>" +
                "</body>" +
                "</html>");
    }
}
