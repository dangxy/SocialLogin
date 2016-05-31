package com.dxy.sociallogin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.view.UMFriendListener;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private Context mContext;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initViews();
    }

    public void initViews() {

        findViewById(R.id.tv_weibo_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                weiboLogin();
            }
        });
    }

    public void weiboLogin() {

        umShareAPI = UMShareAPI.get(mContext);

        SHARE_MEDIA platform = SHARE_MEDIA.SINA;
        umShareAPI.doOauthVerify(this, platform, umAuthListener);
        umShareAPI.getPlatformInfo(this, platform, umAuthListener);

        umShareAPI.getFriend(this, SHARE_MEDIA.SINA, umFriendListener);


    }


    private UMFriendListener umFriendListener = new UMFriendListener() {
        @Override
        public void onComplete(SHARE_MEDIA share_media, int i, Map<String, Object> map) {
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Log.e("dang -------", "key=" + key + " value=" + value);
            }


        }

        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

        }

        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {

        }
    };

    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
            Iterator it = data.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                Log.e("dang", "key=" + key + " value=" + value);
            }

            /**
             *  key=com.sina.weibo.intent.extra.NICK_NAME value=小时候的阳光0
             E/dang: key=access_token value=2.00j768OEgpvWMCb36667155b0lOEmN
             E/dang: key=com.sina.weibo.intent.extra.USER_ICON value=null
             E/dang: key=uid value=3877932645
             E/dang: key=userName value=小时候的阳光0
             E/dang: key=_weibo_appPackage value=com.sina.weibo
             E/dang: key=expires_in value=157679999
             E/dang: key=_weibo_transaction value=1464679753112
             E/dang: key=refresh_token value=2.00j768OEgpvWMC3251eee2990dhcA1
             *
             *授权成功后可以登录获取用户的信息
             *
             * key=result value={"id":3877932645,"idstr":"3877932645","class":1,"screen_name":"小时候的阳光0","name":"小时候的阳光0","province":"11","city":"8","location":"北京 海淀区","description":"爱生活，爱编程，做一个积极向上的人。","url":"","profile_image_url":"http://tva2.sinaimg.cn/crop.0.0.320.320.50/e7248e65jw8ef25eb8oxxj208w08wmxe.jpg","cover_image_phone":"http://ww1.sinaimg.cn/crop.0.0.640.640.640/549d0121tw1egm1kjly3jj20hs0hsq4f.jpg","profile_url":"u/3877932645","domain":"","weihao":"","gender":"m","followers_count":48,"friends_count":581,"pagefriends_count":2,"statuses_count":58,"favourites_count":2,"created_at":"Wed Oct 30 09:30:34 +0800 2013","following":false,"allow_all_act_msg":false,"geo_enabled":true,"verified":false,"verified_type":-1,"remark":"","status":{"created_at":"Thu May 26 20:04:53 +0800 2016","id":3979475237131731,"mid":"3979475237131731","idstr":"3979475237131731","text":"毕业是分手吗？","source_allowclick":0,"source_type":1,"source":"<a href=\"http://app.weibo.com/t/feed/3o33sO\" rel=\"nofollow\">iPhone 6</a>","favorited":false,"truncated":false,"in_reply_to_status_id":"","in_reply_to_user_id":"","in_reply_to_screen_name":"","pic_urls":[],"geo":null,"annotations":[{"mapi_request":true}],"reposts_count":0,"comments_count":0,"attitudes_count":0,"isLongText":false,"mlevel":0,"visible":{"type":0,"list_id":0},"biz_feature":0,"darwin_tags":[],"hot_weibo_tags":[],"text_tag_tips":[],"userType":0},"ptype":0,"allow_all_comment":true,"avatar_large":"http://tva2.sinaimg.cn/crop.0.0.320.320.180/e7248e65jw8ef25eb8oxxj208w08wmxe.jpg","avatar_hd":"http://tva2.sinaimg.cn/crop.0.0.320.320.1024/e7248e65jw8ef25eb8oxxj208w08wmxe.jpg","verified_reason":"","verified_trade":"","verified_reason_url":"","verified_source":"","verified_source_url":"","follow_me":false,"online_status":0,"bi_followers_count":21,"lang":"zh-cn","star":0,"mbtype":2,"mbrank":1,"block_word":0,"block_app":0,"credit_score":80,"user_ability":0,"urank":14}
             *
             *
             * */

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        umShareAPI.onActivityResult(requestCode, resultCode, data);
    }
}
