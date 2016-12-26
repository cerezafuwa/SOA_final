package com;

import dao.UserDAO;
import entity.User;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by hubeini on 2016/12/25.
 */

@WebServlet(name = "UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentUsername = (String) request.getSession().getAttribute("user");
        String targetUsername = request.getParameter("username");
        JSONObject userinfo = new JSONObject();

        if(targetUsername==null){
            userinfo.put("user",currentUsername==null?null: UserDAO.getInstance().findById(currentUsername).JSONInfo());
        }
        else{
            User targetUser = UserDAO.getInstance().findById(targetUsername);
            if(targetUser==null){
                userinfo.put("user",null);
            }
            else{
                JSONObject targetUserInfo = targetUser.JSONInfo();
                if(currentUsername==null || !currentUsername.equals(targetUser.getUsername())){
                    targetUserInfo.remove("email");
                }
                userinfo.put("user",targetUserInfo);
            }
        }
        response.setContentType("application/json");
        response.getWriter().append(userinfo.toJSONString());
    }
}

