package com.example.wangzw.mybase.adapter.itemdelegate;

import com.example.wangzw.mybase.R;
import com.example.wangzw.mybase.base.baseadapter.ItemViewDelegate;
import com.example.wangzw.mybase.base.baseadapter.ViewHolder;
import com.example.wangzw.mybase.greendao.entity.UserInfoGreenDaoBean;

/**
 * Created by wangzw on 2017/8/3.
 */

public class ContentItemDelegate implements ItemViewDelegate<UserInfoGreenDaoBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.iten_content;
    }

    @Override
    public boolean isForViewType(UserInfoGreenDaoBean item, int position) {
        return item.getGender() == 1;
//        return position%2==1;
    }

    @Override
    public void convert(ViewHolder holder, UserInfoGreenDaoBean s, int position) {
        holder.setText(R.id.tv_content, s.getUserId());
    }
}
