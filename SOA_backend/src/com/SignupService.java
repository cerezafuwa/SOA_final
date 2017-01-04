package com;

import dao.UserDAO;
import entity.User;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hubeini on 2016/12/28.
 */

@Service
@Path("/signup")
public class SignupService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String register(@QueryParam("username") String username, @QueryParam("password") String password, @QueryParam("gender") String gender, @QueryParam("email") String email, @QueryParam("birthday") String birthday) {
        User user = UserDAO.getInstance().findById(username);
        if (user != null){
            return "false";
        }else {
            boolean gender_bool;
            if (Integer.parseInt(gender) == 1)
                gender_bool = true;
            else gender_bool = false;

            Date birthday_date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthday_date = sdf.parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date birthday_date1;
            birthday_date1 = new java.sql.Date(birthday_date.getTime());
            User user1 = new User(username, password, gender_bool, birthday_date1, email);
            UserDAO.getInstance().save(user1);
            return "true";
        }
    }
}

