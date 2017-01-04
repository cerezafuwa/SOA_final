//package com.werb.mycalendardemo;
//
//import android.app.DatePickerDialog;
//import android.app.TimePickerDialog;
//import android.content.Intent;
//import android.media.RingtoneManager;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Vibrator;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.view.View;
//import android.view.Window;
//import android.widget.DatePicker;
//import android.widget.TimePicker;
//import android.widget.Toast;
//
//
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//
///**
// * 添加活动时间界面
// */
//public class AddScheduleActivity extends AppCompatActivity {
//
//    private DatePickerDialog mDataPicker;
//    private TimePickerDialog mStartTimePicker, mEndTimePicker;
//    private boolean isAllDay = false;
//    private boolean isVibrate = false;
//    private int id;
//
//
////    /**
////     * 获取日期选择器
////     */
////    private void getDatePickerDialog() {
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTimeInMillis(System.currentTimeMillis());
////        mDataPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
////            @Override
////            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
////
////                Calendar calendar = Calendar.getInstance();
////                calendar.set(year, monthOfYear, dayOfMonth);
////                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日  EE");
////
////
////            }
////        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
////    }
//
////    /**
////     * 获取开始时间选择器
////     */
////    private void getStartTimePickerDialog() {
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTimeInMillis(System.currentTimeMillis());
////        mStartTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
////            @Override
////            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
////
////                Calendar calendar = Calendar.getInstance();
////                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
////                calendar.set(Calendar.MINUTE, minute);
////                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
////
////
////
////            }
////        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
////    }
//
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_schedule);
//    }
//
//    private static final int REQUEST_TAKE_PHOTO = 0;
//    private static final int REQUEST_SELECT_IMAGE_IN_ALBUM = 1;
//    private Uri mUriPhotoTaken;
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putParcelable("ImageUri", mUriPhotoTaken);
//    }
//
//    // Recover the saved state when the activity is recreated.
//    @Override
//    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        mUriPhotoTaken = savedInstanceState.getParcelable("ImageUri");
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch (requestCode)
//        {
//            case REQUEST_TAKE_PHOTO:
//            case REQUEST_SELECT_IMAGE_IN_ALBUM:
//                if (resultCode == RESULT_OK) {
//                    Uri imageUri;
//                    if (data == null || data.getData() == null) {
//                        imageUri = mUriPhotoTaken;
//                    } else {
//                        imageUri = data.getData();
//                    }
//                    Intent intent = new Intent();
//                    intent.setData(imageUri);
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    public void selectImageInAlbum(View view) {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
//        }
//    }
//
//
//
//    @Override
//    public void onBackPressed() {
//        startActivity(new Intent(this,MainActivity.class));
//        finish();
//    }
//}
