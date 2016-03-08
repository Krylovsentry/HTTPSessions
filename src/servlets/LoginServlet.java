package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class LoginServlet extends HttpServlet {

    private boolean isTrue;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        isTrue = false;

        checkCoockie(req,resp);

        validation(req,resp);

        if (!isTrue) {
            resp.sendRedirect("/register.html");
        }

    }


    public void checkCoockie(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Cookie [] cookies = request.getCookies();

        if (login.equalsIgnoreCase("")&&password.equalsIgnoreCase("")) {
            if (cookies != null) {

                isTrue = true;
                response.sendRedirect("/WelcomeServlet?login=" + cookies[0].getName());
            }
        }

    }




    public void validation(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Scanner in = new Scanner(new File("D:\\IdeaProjects\\HTTPSessions\\web\\users.txt"));
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        while (in.hasNextLine()){


            String [] array = in.nextLine().split(" ");

            if (array[0].equalsIgnoreCase(login)){

                if (array[1].equalsIgnoreCase(password)) {


                    if (request.getParameter("ano").equalsIgnoreCase("on")){

                        Cookie cookie = new Cookie(login,password);
                        response.addCookie(cookie);


                    }

                    isTrue = true;
                    response.sendRedirect("/WelcomeServlet?login=" + login);

                }
            }

        }

        in.close();

    }





}
