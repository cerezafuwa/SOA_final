package com.werb.mycalendardemo.models;

import com.werb.mycalendardemo.utils.CalendarManager;
import com.werb.mycalendardemo.utils.DateHelper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by acer-pc on 2016/4/9.
 */
public class DayItem implements Serializable {

    private Date mDate; //日期
    private int mValue; //相当于Day_OF_Month,一月中的第几天
    private int mDayOfTheWeek; //一个星期中的第几天
    private boolean mToday; //是否是今天
    private boolean mFirstDayOfTheMonth; //是否是当前月的第一天
    private boolean mSelected;//是否选中
    private String mMonth; // 月？不确定

    // region Constructor

    public DayItem(Date date, int value, boolean today, String month) {
        this.mDate = date;
        this.mValue = value;
        this.mToday = today;
        this.mMonth = month;
    }

    // endregion

    // region Getters/Setters

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        this.mDate = date;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
    }

    public boolean isToday() {
        return mToday;
    }

    public void setToday(boolean today) {
        this.mToday = today;
    }

    public boolean isSelected() {
        return mSelected;
    }

    public void setSelected(boolean selected) {
        this.mSelected = selected;
    }

    public boolean isFirstDayOfTheMonth() {
        return mFirstDayOfTheMonth;
    }

    public void setFirstDayOfTheMonth(boolean firstDayOfTheMonth) {
        this.mFirstDayOfTheMonth = firstDayOfTheMonth;
    }

    public String getMonth() {
        return mMonth;
    }

    public void setMonth(String month) {
        this.mMonth = month;
    }

    public int getDayOftheWeek() {
        return mDayOfTheWeek;
    }

    public void setDayOftheWeek(int mDayOftheWeek) {
        this.mDayOfTheWeek = mDayOftheWeek;
    }

    public String transMonth(){
        if(getMonth().equals("1月")){return "01";}
        if(getMonth().equals("2月")){return "02";}
        if(getMonth().equals("3月")){return "03";}
        if(getMonth().equals("4月")){return "04";}
        if(getMonth().equals("5月")){return "05";}
        if(getMonth().equals("6月")){return "06";}
        if(getMonth().equals("7月")){return "07";}
        if(getMonth().equals("8月")){return "08";}
        if(getMonth().equals("9月")){return "09";}
        if(getMonth().equals("10月")){return "10";}
        if(getMonth().equals("11月")){return "11";}
        if(getMonth().equals("12月")){return "12";}
        return null;
    }

    // region Public methods

    /**
     * 将Calendar转化为DayItem对象
     * @param calendar
     * @return
     */
    public static DayItem buildDayItemFromCal(Calendar calendar) {
        Date date = calendar.getTime();
        boolean isToday = DateHelper.sameDate(calendar, CalendarManager.getInstance().getToday());
        int value = calendar.get(Calendar.DAY_OF_MONTH);
        DayItem dayItem = new DayItem(date, value, isToday, CalendarManager.getInstance().getMonthHalfNameFormat().format(date));
        if (value == 1) {
            dayItem.setFirstDayOfTheMonth(true);
        }
        dayItem.setToday(isToday);
        return dayItem;
    }

    // endregion

    @Override
    public String toString() {
        return "DayItem{"
                + "Date='"
                + mDate.toString()
                + ", value="
                + mValue
                + '}';
    }
}
