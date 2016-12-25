package com;

import dao.UserDAO;
import entity.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by hubeini on 2016/12/22.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = (String)request.getSession().getAttribute("user");
        if(username!=null){
            User user = UserDAO.getInstance().findById(username);
        }
        else{
            response.sendRedirect("/loginjsp");
        }
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 接收客户端信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String extension = request.getParameter("extension");
        User user = UserDAO.getInstance().findById(username, password);


//        if(extension!=null && extension.equals("true")){
//            response.setContentType("application/json");
//            if(user==null){
//                request.getSession().removeAttribute("user");
//                response.getWriter().append("false");
//            }
//            else{
//                HttpSession httpSession = request.getSession();
//                httpSession.setAttribute("user",user.getUsername());
//                response.getWriter().append("true");
//            }
//            return;
//        }

        if(user == null){
            request.getSession().removeAttribute("user");
            request.setAttribute("wrong", "");
            request.getRequestDispatcher("/loginjsp").forward(request,response);
//            response.sendRedirect("/error.jsp");
        }
        else{
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user",user.getUsername());
            request.getRequestDispatcher("/index.jsp").forward(request,response);

        }
    }

}
