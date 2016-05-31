package com.dxy.sociallogin;

import android.app.Application;

import com.umeng.socialize.PlatformConfig;

/**
 * Created by dangxueyi on 16/5/31.
 */
public class SocialLoginApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initConfigs();
    }

    /**
     * weibo
     *App Key：2017430504
     App Secret：6ad22bca549bbfe2dd0f25471270b4ec
     */
    public void initConfigs(){

        PlatformConfig.setSinaWeibo("2017430504","6ad22bca549bbfe2dd0f25471270b4ec");

    }
}
