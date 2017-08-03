package com.example.wangzw.mybase;

import android.app.Application;
import android.content.Context;

import com.example.wangzw.mybase.greendao.utils.DataBaseConstant;
import com.example.wangzw.mybase.greendao.utils.DatabaseLoader;
import com.example.wangzw.mybase.utils.AppUtils;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * Created by wangzw on 2017/5/26.
 * 自定义application 应用入口 全局类，系统创建单例
 * 需要在AndroidManifest.xml清单文件中配置<applicatin 的name属性
 * application方法的执行流程：①构造方法 ②attachBaseContext(Context base) ③onCreate()
 * 生命周期随着应用程序的销毁而销毁。
 * App的进程被创建时，这个类就会被实例化，onCreate()方法就会被执行，是整个程序的入口
 */

public class MyApplication extends Application {

    private static MyApplication app;


    public static MyApplication getInstance() {
        return app;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(base); //TODO  MultiDex 分包 需先开启分包功能
    }


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        //初始化工具类
        AppUtils.init(app);
        //初始化日志工具
        initLogger();
        //初始化数据库
        DatabaseLoader.setupDataBase(app, DataBaseConstant.MYBASE_DATEBASE_NAME);

    }

    /**
     * 初始化日志功能
     */
    private void initLogger() {
        Logger.init("my_tag")                   // 如果仅仅调用 init 不传递参数，默认标签是 PRETTYLOGGER
                .methodCount(3)                 // 显示调用方法链的数量，默认是2
                .hideThreadInfo()               // 隐藏线程信息，默认是隐藏
                .logLevel(LogLevel.FULL)        // 日志等级，其实就是控制是否打印，默认为 LogLevel.FULL,LogLevel.NONE不打印
                .methodOffset(2);               // default 0
    }
}
