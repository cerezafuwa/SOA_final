package com.werb.mycalendardemo.models;

/**
 * Created by fengyuqing on 2017/1/3.
 */

public class user {
    String username;
    String gender;
    String email;
    String birthday;

    public void set_username(String result){
        this.username=result;
    }
    public String get_username(){
        return username;
    }

    public void set_gender(String result){
        this.gender =result;
    }
    public String get_gender(){
        return gender;
    }
    public void set_email(String result){
        this.email =result;
    }
    public String get_email(){
        return email;
    }
    public void set_birthday(String result){
        this.birthday =result;
    }
    public String get_birthday(){
        return birthday;
    }
}
