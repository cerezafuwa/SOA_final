package com.werb.mycalendardemo.parse;



import com.werb.mycalendardemo.models.user;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * Created by fengyuqing on 2017/1/3.
 */

public class userInfoReader {
    public static user read_XML(String string){
        //List<Phone_info> address_infos = new ArrayList<Phone_info>();
        user user_info = new user();
        DocumentBuilderFactory factory =null;
        DocumentBuilder builder =null;
        Document document=null;
        InputStream is=null;

        try {

            factory =DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            is=new ByteArrayInputStream(string.getBytes("utf-8"));
            document=builder.parse(is);
            Element root =document.getDocumentElement();

            Element birthdaynode=(Element)root.getElementsByTagName("birthday").item(0);
            user_info.set_birthday(birthdaynode.getFirstChild().getNodeValue());

            Element emailnode=(Element)root.getElementsByTagName("email").item(0);
            user_info.set_email(emailnode.getFirstChild().getNodeValue());

            Element gendernode=(Element)root.getElementsByTagName("gender").item(0);
            if(gendernode.getFirstChild().getNodeValue().equals(true)){user_info.set_gender("female");}else {user_info.set_gender("male");}

            Element usernamenode=(Element)root.getElementsByTagName("username").item(0);
            user_info.set_username(usernamenode.getFirstChild().getNodeValue());



        } catch (Exception e) {

            e.printStackTrace();

        }
        return user_info;
    }
}
