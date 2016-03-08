package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Антон on 08.03.2016.
 */
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        writeToFile("D:\\IdeaProjects\\HTTPSessions\\web\\users.txt",req);


        PrintWriter out = resp.getWriter();

        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head><title></title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\"> Congratulations! You've created a new account!</h1> </body></html>");


    }


    public void writeToFile(String filepath, HttpServletRequest request) throws IOException {

        File file = new File(filepath);
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
        fileWriter.write(login + " " + password + "\n");
        fileWriter.close();

    }
}
