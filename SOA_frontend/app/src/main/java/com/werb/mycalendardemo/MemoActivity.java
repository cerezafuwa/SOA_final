package com.werb.mycalendardemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by hubeini on 2016/12/31.
 */
public class MemoActivity extends AppCompatActivity {
    Button SAVEME;
    EditText date_input;
    EditText text_input;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.memo);

        date_input = (EditText) findViewById(R.id.memodate);
        text_input = (EditText) findViewById(R.id.memotext);
        SAVEME = (Button) findViewById(R.id.saveme);
        SAVEME.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final data app = (data)getApplication();
                String username=app.getCurrentUser();
                Toast.makeText(getApplicationContext(), username,
                        Toast.LENGTH_SHORT).show();*/
                URL url = null;
                try {
                    final data app = (data)getApplication();
                    String username=app.getCurrentUser();
                    String date = date_input.getText().toString();
                    String text = text_input.getText().toString();
                    String Date= new String(date.getBytes(), "iso-8859-1");
                    String Text = new String(text.getBytes("UTF-8"), "iso-8859-1");
                    String strUrl = "http://120.77.39.186:8080/SOA/soa/addmemo?user="+username+"&date="+Date+"&text="+Text;
                    /*String strUrl = "http://120.77.39.186:8080/SOA/soa/addmemo?user=fyq&date=2016-07-08&text=hi";*/
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

                    if (result.equals("true")) {
                        Toast.makeText(getApplicationContext(), "保存成功",
                                Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent();
                       intent.setClass(MemoActivity.this, MainActivity.class);
                       MemoActivity.this.startActivity(intent);
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

            }
        });

    }
}
