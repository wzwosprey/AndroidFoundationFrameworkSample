package com.example.wangzw.mybase.greendao.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.wangzw.mybase.greendao.gen.DaoMaster;
import com.example.wangzw.mybase.greendao.gen.UserInfoGreenDaoBeanDao;


/**
 * 创建和控制数据库的版本
 * 注：继承DaoMaster.OpenHelper 用于正式上线，需要迁移旧表数据
 * 注：继承DaoMaster.DevOpenHelper 用于开发环境，数据升级不保留数据库数据
 */
class DaoOpenHelper extends DaoMaster.OpenHelper {
    DaoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //TODO 别忘了更新时SCHEMA_VERSION自动加1,不然不会执行更新
        //TODO 在build.gradle中配置  SCHEMA_VERSION 加1
        MigrationHelper.getInstance().migrate(wrap(db),UserInfoGreenDaoBeanDao.class);

    }
}

