package com;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * Created by hubeini on 2016/12/25.
 */
@WebServlet(name = "RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");

        User user = UserDAO.getInstance().findById(username);
        if (user != null) {
            response.sendRedirect("/index.jsp");
        } else {

            boolean gender_bool;
            if (Integer.parseInt(gender) == 1)
                gender_bool = true;
            else gender_bool = false;

            Date birthday_date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthday_date = (Date) sdf.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User user1 = new User(username, password, gender_bool, birthday_date, email);
            UserDAO.getInstance().save(user1);

            if (UserDAO.getInstance().findById(username) == null)
                response.sendRedirect("/index.jsp");
            else {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user1.getUsername());
                Cookie cookie = new Cookie("sessionId", httpSession.getId());
                response.addCookie(cookie);
                request.getRequestDispatcher("/login").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
