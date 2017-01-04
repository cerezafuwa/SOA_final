package com.werb.mycalendardemo;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionButton;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionHelper;
import com.wangjie.rapidfloatingactionbutton.RapidFloatingActionLayout;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RFACLabelItem;
import com.wangjie.rapidfloatingactionbutton.contentimpl.labellist.RapidFloatingActionContentLabelList;
import com.werb.mycalendardemo.customview.calendar.CalendarView;
import com.werb.mycalendardemo.fragment.ContentFragment;
import com.werb.mycalendardemo.models.DayItem;
import com.werb.mycalendardemo.models.user;
import com.werb.mycalendardemo.parse.userInfoReader;
import com.werb.mycalendardemo.utils.BusProvider;
import com.werb.mycalendardemo.utils.CalendarManager;
import com.werb.mycalendardemo.utils.Events;
import com.werb.mycalendardemo.utils.PrefUtils;
import com.werb.mycalendardemo.models.memo;
import com.werb.mycalendardemo.parse.memoInfoReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements RapidFloatingActionContentLabelList.OnRapidFloatingActionContentLabelListListener {

    //侧边栏布局
    @Bind(R.id.main_draw_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.left_draw)
    NavigationView mNavigationView;
    //悬浮按钮
    @Bind(R.id.fab_layout)
    RapidFloatingActionLayout fab_layout;
    @Bind(R.id.fab_button_group)
    RapidFloatingActionButton fab_button_group;
    private RapidFloatingActionHelper rfabHelper;
    //日历View
    @Bind(R.id.calendar_view)
    public CalendarView calendar_view;
    //CalendarView自定义控件的属性
    private int mCalendarHeaderColor, mCalendarDayTextColor, mCalendarPastDayTextColor, mCalendarCurrentDayColor;
    //月份
    @Bind(R.id.title_day)
    TextView title_day;
    //日程 ListView
    @Bind(R.id.main_frame)
    FrameLayout main_frame;

    //left_menu点击打开关闭侧边栏
    /*@OnClick(R.id.left_menu)
    void openLeftDrawe() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }*/

    //旋转箭头
    @Bind(R.id.title_arrow)
    ImageView title_arrow;
    //打开关闭CalendarView
    private boolean isOpen = true;//日历视图是否显示

    @OnClick(R.id.calendar_open_close)
    void openAndCloseCalendarView() {
        if (!isOpen) {
            calendar_view.setVisibility(View.VISIBLE);
            arrowRotateAnim(isOpen);
            isOpen = true;
        } else {
            calendar_view.setVisibility(View.GONE);
            arrowRotateAnim(isOpen);
            isOpen = false;
        }
    }

    TextView Show;
    Button LOGIN;
    Button SIGNUP;
    Button SHOWButton;
    ImageButton INFO;
    /*String username;*/
    private boolean isAllowAlert = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        LOGIN = (Button) findViewById(R.id.login1);
        SIGNUP = (Button) findViewById(R.id.signup1);
        INFO=(ImageButton)findViewById(R.id.info);
        Show=(TextView) findViewById(R.id.show);
        SHOWButton=(Button)findViewById(R.id.show_button) ;

        /*Intent intent=getIntent();
        username=intent.getStringExtra("Username");*/

        LOGIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SignUpActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        INFO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Toast.makeText(getApplicationContext(), username,
                        Toast.LENGTH_SHORT).show();*/
                Intent intent1 = new Intent();
                /*intent1.putExtra("username", username);*/
                intent1.setClass(MainActivity.this, InfoActivity.class);
                MainActivity.this.startActivity(intent1);
            }
        });
        SHOWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String DDD="0000-00-00";
                String ddd=calendar_view.getSelectedDay().getDate().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
                Date date = null;
                try {
                    date = sdf.parse(ddd);
                    sdf=new SimpleDateFormat("yyyy-MM-dd");
                    DDD=sdf.format(date).toString();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final data app = (data) getApplication();
                String UN = app.getCurrentUser();
                /*Toast.makeText(getApplicationContext(), UN,
                        Toast.LENGTH_SHORT).show();*/
                String strUrl = "http://120.77.39.186:8080/SOA/soa/memoinfo?user=" + UN + "&date=" + DDD;
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
                    StringBuilder sBuilder = new StringBuilder();
                    memo info1 = memoInfoReader.read_XML(result);
                    sBuilder.append(info1.get_text());
                    Show.setText("MEMO: "+sBuilder.toString());
                    sBuilder.setLength(0);

                   // Show.setText(result);
                } catch (MalformedURLException var1) {
                    var1.printStackTrace();
                } catch (IOException var2) {
                    var2.printStackTrace();
                }

                }

            });
        initCalendarInfo();
        initLayoutView();
        setMonthTitle();
        initFab();

        //弹窗权限验证
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            isAllowAlert = PrefUtils.getBoolean(this,"isAllowAlert",false);
            if(!isAllowAlert){
                showPermissionDialog();
            }
        }else {
        }

    }

    //权限申请相关方法
    private static final int REQUEST_CODE = 1;
    @TargetApi(Build.VERSION_CODES.M)
    private void requestAlertWindowPermission() {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + getPackageName()));
        startActivityForResult(intent, REQUEST_CODE);
    }

    /**
     * 权限申请弹窗
     */
    private void showPermissionDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("弹窗需要开启权限哦~")
                .setPositiveButton("开启", (dialog, which) -> {
                    requestAlertWindowPermission();
                })
                .setNegativeButton("取消", (dialog, which) -> {

                });
        builder.create().show();
    }


    /**
     * 初始化布局
     */
    private void initLayoutView() {
        //初始化 CalendarView,设置颜色
        mCalendarHeaderColor = R.color.calendar_header_day_background;
        mCalendarDayTextColor = R.color.calendar_text_day;
        mCalendarCurrentDayColor = R.color.calendar_text_current_day;
        mCalendarPastDayTextColor = R.color.calendar_text_past_day;

        calendar_view.findViewById(R.id.cal_day_names).setBackgroundColor(getResources().getColor(mCalendarHeaderColor));

        // CalendarView 初始化,完成布局的初始化，数据的绑定,日期的显示
        calendar_view.init(CalendarManager.getInstance(this), getResources().getColor(mCalendarDayTextColor), getResources().getColor(mCalendarCurrentDayColor), getResources().getColor(mCalendarPastDayTextColor));

        //填充 Fragment
        FragmentManager manager =getFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        ContentFragment fragment = new ContentFragment();

        transaction.replace(R.id.main_frame, fragment, "CONTENT_FRAGMENT");

        transaction.commit();

    }

    /**
     * title_arrow 旋转动画
     */
    private void arrowRotateAnim(Boolean isOpen) {
        RotateAnimation anim;
        if (isOpen) {
            anim = new RotateAnimation(0, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        } else {
            anim = new RotateAnimation(180f, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        anim.setDuration(150);
        anim.setFillAfter(true);
        title_arrow.startAnimation(anim);
    }

    /**
     * 设置月份
     */
    private void setMonthTitle() {
        BusProvider.getInstance().toObserverable().subscribe(event -> {
            if (event instanceof Events.CalendarMonthEvent) {
                Events.CalendarMonthEvent monthEvent = (Events.CalendarMonthEvent) event;
                title_day.setText(monthEvent.getMonth());
            }
        });
    }

    /**
     * 初始化 Fab
     */
    private void initFab() {
        RapidFloatingActionContentLabelList rfaContent = new RapidFloatingActionContentLabelList(this);
        rfaContent.setOnRapidFloatingActionContentLabelListListener(this);
        List<RFACLabelItem> items = new ArrayList<>();
        items.add(new RFACLabelItem<Integer>()
                        .setLabel("Schedule")
                        .setResId(R.drawable.ic_access_alarms_white_24dp)
                        .setIconNormalColor(0xffd84315)
                        .setIconPressedColor(0xffbf360c)
                        .setLabelSizeSp(14)
                        .setWrapper(0)
        );
        items.add(new RFACLabelItem<Integer>()
                .setLabel("Memo")
                .setResId(R.drawable.ic_memo_white_24dp)
                .setIconNormalColor(0xffd84315)
                .setIconPressedColor(0xffbf360c)
                .setLabelSizeSp(14)
                .setWrapper(1)
        );
        rfaContent
                .setItems(items)
                .setIconShadowRadius(5)
                .setIconShadowColor(0xff888888)
                .setIconShadowDy(5)
        ;
        rfabHelper = new RapidFloatingActionHelper(
                this,
                fab_layout,
                fab_button_group,
                rfaContent
        ).build();

    }

    @Override
    public void onRFACItemLabelClick(int i, RFACLabelItem rfacLabelItem) {
        Toast.makeText(this, "Click Label !"+i, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRFACItemIconClick(int i, RFACLabelItem rfacLabelItem) {
        if (i == 0) {
            Intent intent = new Intent(MainActivity.this, RecognizeActivity.class);
            intent.putExtra("type","mainToAdd");
            startActivity(intent);
            finish();
        }
        if (i == 1) {
            Intent intent = new Intent(MainActivity.this, MemoActivity.class);
            intent.putExtra("type","mainToAdd");
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("---onRestart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        support.deactivate();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (Settings.canDrawOverlays(this)) {
                Toast.makeText(this,"弹窗权限开启！",Toast.LENGTH_SHORT).show();
                PrefUtils.setBoolean(MainActivity.this, "isAllowAlert", true);
            }else {
                PrefUtils.setBoolean(MainActivity.this, "isAllowAlert", false);
            }
        }
    }


    /**
     * 初始化日历信息
     */
    private void initCalendarInfo() {
        // 设置日历显示的时间，最大为当前时间+1年，最小为当前时间-2月
        Calendar minDate = Calendar.getInstance();
        Calendar maxDate = Calendar.getInstance();

        minDate.add(Calendar.MONTH, -10);
        minDate.set(Calendar.DAY_OF_MONTH, 1);
        maxDate.add(Calendar.YEAR, 1);
        //根据你传入的开始结束值，构建生成Calendar数据（各种Item，JavaBean）
        CalendarManager.getInstance(this).buildCal(minDate, maxDate, Locale.getDefault());
    }

    /**
     * 得到侧滑菜单
     */
    public NavigationView getNavigationView(){
        return mNavigationView;
    }
    /**
     * 得到DrawerLayout
     */
    public DrawerLayout getDrawerLayout(){
        return mDrawerLayout;
    }

}
