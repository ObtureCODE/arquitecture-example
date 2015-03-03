package com.obturecode.vallahackathon;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by husky on 02/03/15.
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate(){
        super.onCreate();
        MyApplication.context = getApplicationContext();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.
                Builder(getApplicationContext()).
                tasksProcessingOrder(QueueProcessingType.LIFO).
                writeDebugLogs().
                build();
        ImageLoader.getInstance().init(config);
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

    public static DisplayImageOptions getDefaultImageOptions(){
        DisplayImageOptions imageOptions = new DisplayImageOptions.Builder().
                //showImageOnLoading(R.drawable.profile_default).
                //showImageOnFail(R.drawable.profile_default).
                //showImageForEmptyUri(R.drawable.profile_default).
                displayer(new FadeInBitmapDisplayer(1000)).
                build();
        return imageOptions;
    }
}
