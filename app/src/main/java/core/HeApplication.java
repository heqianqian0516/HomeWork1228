package core;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;

public class HeApplication extends Application {
    private static HeApplication instance;
    private SharedPreferences mSharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mSharedPreferences = getSharedPreferences("application",
                Context.MODE_PRIVATE);
        /*JPushInterface.setDebugMode(true);
        JPushInterface.init(this);*/     		// 初始化 JPush
        Fresco.initialize(this);
    }

    public static HeApplication getInstance() {
        return instance;
    }

    public SharedPreferences getShare() {
        return mSharedPreferences;
    }


}
