package com.example.wangzw.mybase.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.wangzw.mybase.R;
import com.example.wangzw.mybase.adapter.FirstAdapter;
import com.example.wangzw.mybase.base.baseadapter.CommonAdapter;
import com.example.wangzw.mybase.greendao.entity.UserInfoGreenDaoBean;
import com.example.wangzw.mybase.greendao.utils.UserInfoOperator;
import com.example.wangzw.mybase.utils.ToastUtils;
import com.just.library.ActivityManager;
import com.just.library.PixelActivityUnion;
import com.just.library.PointActivity;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private CommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testOnePixel();
        testLogger();
        testDB();

        List allData = UserInfoOperator.getInstance().getAllData();
        mListView = (ListView) findViewById(R.id.id_listview_list);
        mListView.setDivider(null);
        mListView.setAdapter(new FirstAdapter(this, allData));
    }

    private void testDB() {
        for (int i = 0; i < 100; i++) {
            UserInfoGreenDaoBean userInfoGreenDaoBean = new UserInfoGreenDaoBean();
            userInfoGreenDaoBean.setUserId("00" + i);
            userInfoGreenDaoBean.setAge(i);
            userInfoGreenDaoBean.setGender(i % 2);
            UserInfoOperator.getInstance().addData(userInfoGreenDaoBean);
        }
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
            ToastUtils.setBgResource(R.drawable.bg_custom_toast);
            ToastUtils.showLong(person.toString());
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
