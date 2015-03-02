package com.obturecode.vallahackathon;

import android.app.Application;
import android.content.Context;

/**
 * Created by husky on 02/03/15.
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static String getFlickrApiKey(){
        return "948d63ba569419438d3d03c1b781fc4b";
    }

    public static String getFlickrSecretKey(){
        return "728966ba56fa2565";
    }
}
