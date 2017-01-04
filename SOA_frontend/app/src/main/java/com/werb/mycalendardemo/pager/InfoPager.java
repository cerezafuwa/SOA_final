package com.werb.mycalendardemo.pager;

import android.app.Activity;
import android.view.View;

import com.werb.mycalendardemo.R;
import com.werb.mycalendardemo.utils.BusProvider;
import com.werb.mycalendardemo.utils.Events;

import butterknife.ButterKnife;

import static com.werb.mycalendardemo.R.id.about_bg;

/**
 * Created by apple on 17/1/2.
 */



public class InfoPager extends BasePager implements View.OnClickListener  {
    public InfoPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.aboutme_pager, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View view) {

    }
}
