package com;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by hubeini on 2016/12/25.
 */
public class InfoJsp extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res){
        //业务逻辑
        try{
            //中文乱码解决
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();

            //返回登陆页面
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>傻逼用户界面</h1>");
            pw.println("<form action=userinfo method=post>");
            pw.println("用户名:<input type=text name=username><br>");
            pw.println("<button type=submit value=hhhh>ggggggg</button>");
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
