package authorization.errors.registrationErrors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration/user-exist")
public class UserExistError extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("text/html;charset=UTF-8");

            PrintWriter writer = resp.getWriter();
            writer.write("<html>" +
                    "<head>" +
                    "<title>Log in</title>" +
                    "</head>" +
                    "<body>" +
                    "<h3>User with this name already exist. Please, change your login and try again.</h3>" +
                    "</body>" +
                    "</html>");
        }

}
