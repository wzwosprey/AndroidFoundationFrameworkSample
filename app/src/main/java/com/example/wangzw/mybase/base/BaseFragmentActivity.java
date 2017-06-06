package com.example.wangzw.mybase.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

/**
 * Created by wangzw on 2017/6/5.
 * fragmentactiivity 基类
 */

public abstract class BaseFragmentActivity extends FragmentActivity {
    protected Activity mContext;
    protected Integer pageNo = 1;
    protected Integer pageSize = 10;
    protected Integer pageTotal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        beforeInflate(savedInstanceState);
        onMyCreate(savedInstanceState);
        initView();
        setListener();
        getData();
    }

    protected void beforeInflate(Bundle paramBundle) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    protected abstract void onMyCreate(Bundle paramBundle);

    protected abstract void initView();

    protected abstract void setListener();

    protected abstract void getData();

    protected void getFirstPageData() {
    }

    protected void getMorePageData() {
    }

    protected void updateView() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
