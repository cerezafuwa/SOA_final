package com.werb.mycalendardemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.werb.mycalendardemo.utils.ColorUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by acer-pc on 2016/4/19.
 */
public class ScheduleDetailActivity extends AppCompatActivity {

    //detail
    @Bind(R.id.detail_alarm_title)
    TextView detail_alarm_title;
    @Bind(R.id.detail_alarm_date)
    TextView detail_alarm_date;
    @Bind(R.id.detail_alarm_start_end_time)
    TextView detail_alarm_start_end_time;
    @Bind(R.id.detail_alarm_remind)
    TextView detail_alarm_remind;
    @Bind(R.id.detail_alarm_local)
    TextView detail_alarm_local;
    @Bind(R.id.detail_alarm_description)
    TextView detail_alarm_description;
    @Bind(R.id.detail_layout)
    RelativeLayout detail_layout;


    @Bind(R.id.update_fab)
    FloatingActionButton update_fab;

    //更新修改
    @OnClick(R.id.update_fab)
    void updateData() {

        Intent intent = new Intent(this, RecognizeActivity.class);
        intent.putExtra("type", "DetailToAdd");
        startActivity(intent);
        finish();

    }

    @OnClick(R.id.left_alarm_back)
    void back() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


    private int id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_detail);

        ButterKnife.bind(this);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        System.out.println("---id=" + id);


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  EE");
        //匹配数据
        detail_alarm_date.setText(df.format(calendar.getTime()) + "  本月第" + calendar.get(Calendar.WEEK_OF_MONTH) + "周");


        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        }

    }


    /**
     * 构建开始结束时间
     */
    private void buildStartEndTime(int startHour, int startMinute, int endHour, int endMinute) {
        Calendar startTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, startHour);
        startTime.set(Calendar.MINUTE, startMinute);

        Calendar endTime = Calendar.getInstance();
        endTime.set(Calendar.HOUR_OF_DAY, endHour);
        endTime.set(Calendar.MINUTE, endMinute);

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String strStartTime = df.format(startTime.getTime());
        String strEndTime = df.format(endTime.getTime());

        detail_alarm_start_end_time.setText(strStartTime + "-" + strEndTime);
    }
}


