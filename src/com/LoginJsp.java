package com;

/**
 * Created by hubeini on 2016/12/23.
 */

import javax.servlet.http.*;

import java.io.*;


public class LoginJsp extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res){
        //业务逻辑
        try{
            //中文乱码解决
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();

            //返回登陆页面
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>登陆界面</h1>");
            pw.println("<form action=login method=post>");
            pw.println("用户名:<input type=text name=username><br>");
            pw.println("密码:<input type=password name=password><br>");
            pw.println("<button type=submit value=登陆>login</button>");
            pw.println("</form>");
            pw.println("<body/>");
            pw.println("<html/>");
        }
        catch(Exception ex){
            ex.printStackTrace();
        }


    }

    public void doPost(HttpServletRequest req, HttpServletResponse res){

        this.doGet(req, res);
    }
}
