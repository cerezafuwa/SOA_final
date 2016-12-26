package com;

/**
 * Created by hubeini on 2016/12/23.
 */
import javax.servlet.http.*;

import java.io.*;


public class RegJsp extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res){
        //业务逻辑
        try{
            //中文乱码解决
            res.setContentType("text/html;charset=gbk");
            PrintWriter pw = res.getWriter();

            //返回登陆页面
            pw.println("<html>");
            pw.println("<body>");
            pw.println("<h1>注册界面</h1>");
            pw.println("<form action=register method=post>");
            pw.println("用户名:<input type=text name=username><br>");
            pw.println("密码:<input type=password name=password><br>");
            pw.println("<input type=\"radio\" name=\"gender\" id=\"inlineRadio1\" value=\"1\" onclick=\"checkForButton()\"> Male");
            pw.println("<input type=\"radio\" name=\"gender\" id=\"inlineRadio2\" value=\"0\" onclick=\"checkForButton()\"> Female<br>");
            pw.println("生日:<input type=text name=birthday><br>");
            pw.println("邮箱:<input type=text name=email><br>");
            pw.println("<button type=submit value=注册>你是智障</button>");
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
