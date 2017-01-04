package com;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.jvnet.hk2.annotations.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hubeini on 2017/1/3.
 */
@Service
@Path("/allinfo")
public class MultiplyService {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayAllInfo(@QueryParam("user") String username, @QueryParam("date") String date,@QueryParam("choice") int choice) throws Exception {
        String str1;
        String str2;
        Map dataMap = new HashMap();
        dataMap.put("user", username);
        dataMap.put("date", date);
        str1 = new HttpRequestor().doGet("http://120.77.39.186:8080/SOA/soa/memoinfo?", dataMap);
        str2 = new HttpRequestor().doGet("http://120.77.39.186:8080/SOA/soa/scheduleinfo?", dataMap);
        if (choice == 1){
            return str1;
        }
        if (choice == 2){
            return str2;
        }
        if (choice == 0) {
            return str1 + str2;
        }
    return null;
    }
}
