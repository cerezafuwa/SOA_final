package com.werb.mycalendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.werb.mycalendardemo.models.user;
import com.werb.mycalendardemo.parse.userInfoReader;

/**
 * Created by hubeini on 2016/12/31.
 */
public class InfoActivity extends AppCompatActivity {
    TextView username_output;
    TextView gender_output;
    TextView email_output;
    TextView birthday_output;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        username_output=(TextView) findViewById(R.id.u);
        gender_output=(TextView) findViewById(R.id.g);
        email_output=(TextView) findViewById(R.id.e);
        birthday_output=(TextView) findViewById(R.id.b);
        /*Intent intent=getIntent();
        String UN=intent.getStringExtra("username");*/
        final data app = (data)getApplication();
        String UN=app.getCurrentUser();

        String strUrl = "http://120.77.39.186:8080/SOA/soa/userinfo?username="+UN;
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        URL url = null;
        try {
            url = new URL(strUrl);
            System.out.println(url.getPort());
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            InputStreamReader in = new InputStreamReader(urlConn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String result = "";
            String readerLine = null;
            while ((readerLine = br.readLine()) != null) {
                result += readerLine;
            }
            in.close();
            urlConn.disconnect();
            StringBuilder sBuilder=new StringBuilder();
            user info1=userInfoReader.read_XML(result);
            sBuilder.append(info1.get_birthday());
            birthday_output.setText(sBuilder.toString());
            sBuilder.setLength(0);
            sBuilder.append(info1.get_email());
            email_output.setText(sBuilder.toString());
            sBuilder.setLength(0);
            sBuilder.append(info1.get_gender());
            gender_output.setText(sBuilder.toString());
            sBuilder.setLength(0);
            sBuilder.append(info1.get_username());
            username_output.setText(sBuilder.toString());
            sBuilder.setLength(0);

           // username_output.setText(result);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
