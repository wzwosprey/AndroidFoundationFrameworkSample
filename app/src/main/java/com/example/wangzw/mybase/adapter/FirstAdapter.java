package com.example.wangzw.mybase.adapter;

import android.content.Context;

import com.example.wangzw.mybase.adapter.itemdelegate.ContentItemDelegate;
import com.example.wangzw.mybase.adapter.itemdelegate.TitleItemDelegate;
import com.example.wangzw.mybase.base.baseadapter.MultiItemTypeAdapter;
import com.example.wangzw.mybase.greendao.entity.UserInfoGreenDaoBean;

import java.util.List;

/**
 * Created by wangzw on 2017/8/3.
 */

public class FirstAdapter extends MultiItemTypeAdapter<UserInfoGreenDaoBean> {

    public FirstAdapter(Context context, List<UserInfoGreenDaoBean> datas) {
        super(context, datas);
        addItemViewDelegate(new TitleItemDelegate());
        addItemViewDelegate(new ContentItemDelegate());
    }
}
