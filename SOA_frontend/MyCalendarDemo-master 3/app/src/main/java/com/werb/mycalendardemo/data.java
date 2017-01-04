package com.werb.mycalendardemo;

import android.app.Application;

/**
 * Created by fengyuqing on 2017/1/3.
 */

public class data extends Application {
    private String currentUser;
    public String getCurrentUser(){
        return this.currentUser;
    }
    public void setCurrentUser(String c){
        this.currentUser= c;
    }
    @Override
    public void onCreate(){
        currentUser = "";
        super.onCreate();
    }

}
