package com.example.wangzw.mybase.greendao.utils;


import android.text.TextUtils;

import com.example.wangzw.mybase.greendao.entity.BaseGreenDaoBean;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * @author wangzhiwen(email:wangzw@u-she.cn)
 * @lastModify wangzw
 * @date 2017-01-20 15:25
 * @package com.zhjy.neighborhoodapp.greendao.utils
 * @description DataBaseOperator  TODO(界面功能描述) 数据库操作基类
 * @params TODO(进入界面传参描述)
 */
public abstract class DataBaseOperator<D extends AbstractDao<T, String>, T extends BaseGreenDaoBean> {

    public D dao = null;

    public void addData(T t) {
        if (dao != null && t != null) {
            dao.insertOrReplace(t);
        }
    }


    public void deleteData(String id) {
        if (dao != null && !TextUtils.isEmpty(id)) {
            dao.deleteByKey(id);
        }
    }


    public T getDataById(String id) {
        if (dao != null && !TextUtils.isEmpty(id)) {
            return dao.load(id);
        }
        return null;
    }


    public List getAllData() {
        if (dao != null) {
            return dao.loadAll();
        }
        return null;
    }


    public abstract boolean hasKey(String id);


    public long getTotalCount() {
        if (dao == null) {
            return 0;
        }
        QueryBuilder<T> qb = dao.queryBuilder();
        return qb.buildCount().count();
    }

    public void deleteAll() {
        if (dao != null) {
            dao.deleteAll();
        }
    }
}
