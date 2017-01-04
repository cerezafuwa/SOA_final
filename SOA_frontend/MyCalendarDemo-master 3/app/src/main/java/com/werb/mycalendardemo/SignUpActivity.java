package com.werb.mycalendardemo;

/**
 * Created by hubeini on 2017/1/2.
 */


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class SignUpActivity extends AppCompatActivity {
    Button SAVE;
    RadioButton male;
    RadioButton female;
    EditText username_input;
    EditText password_input;
    EditText email_input;
    EditText birthday_input;
    RadioGroup gender_input;
    String Gender="0";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        SAVE = (Button) findViewById(R.id.save);
        gender_input = (RadioGroup) findViewById(R.id.gender);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        username_input = (EditText) findViewById(R.id.username);
        password_input = (EditText) findViewById(R.id.password);
        email_input = (EditText) findViewById(R.id.email);
        birthday_input = (EditText) findViewById(R.id.date);

        gender_input.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (checkedId == male.getId()) {
                    Gender="0";
                }
                if (checkedId == female.getId()) {
                    Gender="1";
                }
            }

        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL url = null;
                try {
                    String username = username_input.getText().toString();
                    String password = password_input.getText().toString();
                    String email = email_input.getText().toString();
                    String birthday = birthday_input.getText().toString();
                    String Username = new String(username.getBytes(), "iso-8859-1");
                    String Password = new String(password.getBytes("UTF-8"), "iso-8859-1");
                    String Email = new String(email.getBytes(), "iso-8859-1");
                    String Birthday = new String(birthday.getBytes("UTF-8"), "iso-8859-1");
                    String strUrl = "http://120.77.39.186:8080/SOA/soa/signup?username="+Username+"&password="+Password+"&gender="+Gender+"&birthday="+Birthday+"&email="+Email;
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
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

                    if (!result.equals("!!!!!")) {
                        Toast.makeText(getApplicationContext(), "注册成功",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.setClass(SignUpActivity.this, LoginActivity.class);
                        SignUpActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "什么玩意儿",
                                Toast.LENGTH_SHORT).show();
                    }
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }





                /*Intent intent = new Intent();
                intent.setClass(SignUpActivity.this, LoginActivity.class);
                SignUpActivity.this.startActivity(intent);*/
            }
        });
    }
}