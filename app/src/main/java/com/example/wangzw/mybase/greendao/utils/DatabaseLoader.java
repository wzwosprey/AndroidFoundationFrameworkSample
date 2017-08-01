package com.example.wangzw.mybase.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wangzw.mybase.greendao.gen.DaoMaster;
import com.example.wangzw.mybase.greendao.gen.DaoSession;


/**
 * 整个数据库创建的总入口
 */
public class DatabaseLoader {
    private static DaoSession daoSession;

    public static void setupDataBase(Context context, String databaseName) {
        //创建数据库
        DaoOpenHelper helper = new DaoOpenHelper(context, databaseName, null);
        SQLiteDatabase db = helper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    static DaoSession getDaoSession() {
        return daoSession;
    }
}
