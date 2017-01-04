package com;

import dao.MemoDAO;
import dao.UserDAO;
import entity.Memo;
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
@Path("/memoinfo")
public class MemoInfoService {
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Memo sayMemoInfo(@QueryParam("user") String username, @QueryParam("date") String date) {
        User user = UserDAO.getInstance().findById(username);
        if (user == null) {
            return null;
        } else {

            List<Memo> memoList = MemoDAO.getInstance().findByUser(user.getUsername());
            Memo memo = new Memo();
            for (int i = 0; i < memoList.size(); i++) {
                if (memoList.get(i).getDate().toString().equals(date)) {
                    memo = memoList.get(i);
                    return memo;
                }
            }
            return null;
        }
    }
}
