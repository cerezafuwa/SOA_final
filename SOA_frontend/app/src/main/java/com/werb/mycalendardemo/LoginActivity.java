package com.werb.mycalendardemo;

/**
 * Created by hubeini on 2017/1/2.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.*;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.net.*;
import android.widget.TextView;
import android.widget.Toast;
import java.io.*;

public class LoginActivity extends Activity {
    Button L;
    EditText username_input;
    EditText password_input;
    TextView textView;
    String usr;

  @TargetApi(Build.VERSION_CODES.GINGERBREAD)
  @Override
  protected void onCreate(Bundle savedInstanceState) {

      super.onCreate(savedInstanceState);
      setContentView(R.layout.log_in);

      L = (Button) findViewById(R.id.login);
      username_input = (EditText) findViewById(R.id.username);
      password_input = (EditText) findViewById(R.id.password);

      L.setOnClickListener(new View.OnClickListener() {

          public void onClick(View v) {

              URL url = null;
              try {
                  String username = username_input.getText().toString();
                  String password = password_input.getText().toString();
                  String Username = new String(username.getBytes(), "iso-8859-1");
                  usr=Username;
                  String Password = new String(password.getBytes("UTF-8"), "iso-8859-1");
                  String strUrl = "http://120.77.39.186:8080/SOA/soa/login?username="+Username+"&password="+Password;
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
                  final data app = (data)getApplication();
                  if(result.equals("true")){
                      app.setCurrentUser(Username);
                      Toast.makeText(getApplicationContext(), "欢迎你哦",
                              Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent();
                      intent.setClass(LoginActivity.this, MainActivity.class);
                      intent.putExtra("Username", usr);
                      LoginActivity.this.startActivity(intent);
                  }
                  else{
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

          }
      });
  }






}