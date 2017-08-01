package com.example.wangzw.mybase.greendao.utils;

import android.text.TextUtils;

import com.example.wangzw.mybase.greendao.entity.UserInfoGreenDaoBean;
import com.example.wangzw.mybase.greendao.gen.UserInfoGreenDaoBeanDao;

import org.greenrobot.greendao.query.QueryBuilder;

/**
 * UserInfo操作类
 */

public class UserInfoOperator extends DataBaseOperator<UserInfoGreenDaoBeanDao, UserInfoGreenDaoBean> {

    private static UserInfoOperator instance = null;

    private UserInfoOperator() {
        try {
            dao = DatabaseLoader.getDaoSession().getUserInfoGreenDaoBeanDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static UserInfoOperator getInstance() {
        if (instance == null) {
            synchronized (UserInfoOperator.class) {
                if (instance == null) {
                    instance = new UserInfoOperator();
                }
            }
        }
        return instance;
    }

    @Override
    public boolean hasKey(String userId) {
        if (dao == null || TextUtils.isEmpty(userId)) {
            return false;
        }
        QueryBuilder<UserInfoGreenDaoBean> qb = dao.queryBuilder();
        qb.where(UserInfoGreenDaoBeanDao.Properties.UserId.eq(userId));
        long count = qb.buildCount().count();
        return count > 0;
    }

    //TODO 可以根据业务来写具体的增删改查 。。

}
