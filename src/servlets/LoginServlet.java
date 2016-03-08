package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class LoginServlet extends HttpServlet {

    private boolean isTrue;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Scanner in = new Scanner(new File("D:\\IdeaProjects\\HTTPSessions\\web\\users.txt"));

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        isTrue = false;


        Cookie [] cookies = req.getCookies();

        if (login.equalsIgnoreCase("")&&password.equalsIgnoreCase("")) {
            if (cookies != null) {


                isTrue = true;
                resp.sendRedirect("/WelcomeServlet?login=" + cookies[0].getName());
            }
        }


        while (in.hasNextLine()){


            String [] array = in.nextLine().split(" ");

            if (array[0].equalsIgnoreCase(login)){

                if (array[1].equalsIgnoreCase(password)) {


                    if (req.getParameter("ano").equalsIgnoreCase("on")){

                        Cookie cookie = new Cookie(login,password);
                        resp.addCookie(cookie);


                    }

                    isTrue = true;
                    resp.sendRedirect("/WelcomeServlet?login=" + login);

                }
            }

        }

        if (!isTrue) {
            resp.sendRedirect("/register.html");
        }



        in.close();

    }
}
