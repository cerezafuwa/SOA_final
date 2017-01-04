package com;

import dao.ScheduleDAO;
import dao.UserDAO;
import entity.Memo;
import entity.Schedule;
import entity.User;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by hubeini on 2016/12/31.
 */
@Service
@Path("/scheduleinfo")
public class ScheduleInfoService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Schedule sayScheduleInfo(@QueryParam("user") String username, @QueryParam("date") String date) {
        User user = UserDAO.getInstance().findById(username);
        if (user == null) {
            return null;
        } else {

            List<Schedule> scheduleList = ScheduleDAO.getInstance().findByUser(user.getUsername());
            Schedule schedule = new Schedule();
            for (int i = 0; i < scheduleList.size(); i++) {
                if (scheduleList.get(i).getDate().toString().equals(date)) {
                    schedule = scheduleList.get(i);
                    return schedule;
                }
            }
            return null;
        }
    }
}
