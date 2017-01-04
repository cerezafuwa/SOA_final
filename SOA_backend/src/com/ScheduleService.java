package com;

import dao.ScheduleDAO;
import dao.UserDAO;
import entity.Schedule;
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
 * Created by hubeini on 2016/12/31.
 */
@Service
@Path("/addschedule")
public class ScheduleService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String addSchedule(@QueryParam("user") String username, @QueryParam("date") String date, @QueryParam("description") String description) {
        User user = UserDAO.getInstance().findById(username);
        if (user == null) {
            return "false";
        } else {

            Date str_date = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                str_date = sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.sql.Date str_date1;
            str_date1 = new java.sql.Date(str_date.getTime());

            Schedule schedule = new Schedule(str_date1, description, user.getUsername());
            ScheduleDAO.getInstance().save(schedule);
            return "true";
        }
    }
}
