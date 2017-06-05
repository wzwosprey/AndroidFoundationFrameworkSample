package com.example.wangzw.mybase.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wangzw.mybase.R;
import com.just.library.ActivityManager;
import com.just.library.PixelActivityUnion;
import com.just.library.PointActivity;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        testOnePixel();
        testLogger();

    }

    /**
     * 测试一像素保活功能
     */
    private void testOnePixel() {
        //打开
        openOnePixelActivity();
        //关闭
//        closeOnePixelActivity();
    }

    /**
     * 测试logger日志功能
     */
    private void testLogger() {
        Logger.i("onCreate");
        Logger.v("onCreate");
        Logger.d("onCreate");
        Logger.e("onCreate");
        // 打印json格式
        String json = createJson().toString();
        Logger.json(json);
    }


    // 创建json测试数据
    private JSONObject createJson() {
        try {
            JSONObject person = new JSONObject();
            person.put("phone", "12315");
            JSONObject address = new JSONObject();
            address.put("country", "china");
            address.put("province", "fujian");
            address.put("city", "xiamen");
            person.put("address", address);
            person.put("married", true);
            return person;
        } catch (JSONException e) {
            Logger.e(e, "create json error occured");
        }
        return null;
    }

    /**
     * 打开一像素保活功能
     */
    private void openOnePixelActivity() {
        PixelActivityUnion
                .with(this)
                .targetActivityClazz(PointActivity.class)
                .args(null)
                .setActiviyManager(ActivityManager.getInstance())
                .start();
    }

    /**
     * 关闭一像素保活功能
     */
    private void closeOnePixelActivity() {
        PixelActivityUnion.quit();
    }
}
