package com;

/**
 * Created by hubeini on 2016/12/28.
 */
import dao.UserDAO;
import entity.User;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Service
@Path("/userinfo")
public class UserInfoService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public User sayUserInfo(@QueryParam("username") String username){
        User targetUser = UserDAO.getInstance().findById(username);
        if (targetUser == null){
            return null;
        }
        else {
            return targetUser;
        }
    }

}
