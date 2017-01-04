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
@Path("/login")
public class LoginService {
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello(@QueryParam("username") String username, @QueryParam("password") String password) {

        User user = UserDAO.getInstance().findById(username, password);
        if (user==null) {
            return "false";
        } else {
            return "true";
        }
    }
}
