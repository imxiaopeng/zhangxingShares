package sharedsdk.zhangxing.com.zhangxingshares;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.wechat.friends.Wechat;

public class MainActivity extends AppCompatActivity {
    Button shareBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ShareSDK.initSDK(this);
        setContentView(R.layout.activity_main);
        shareBtn = (Button) findViewById(R.id.shareBtn);
        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnekeyShare oks = new OnekeyShare();
                oks.setTitle("趣分享");
//                oks.setText("我为技术带盐，我骄傲，我自豪");
//                oks.setImageUrl("http://7sby7r.com1.z0.glb.clouddn.com/CYSJ_02.jpg");
                oks.setTitleUrl("http://wwww.baidu.com");
                oks.setShareContentCustomizeCallback(new ShareContentCustomizeCallback() {
                    @Override
                    public void onShare(Platform platform, Platform.ShareParams paramsToShare) {
                        if (platform.getId() == 3) {
                            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
                            Wechat.ShareParams sp = new Wechat.ShareParams();
                            sp.setText("哈哈哈");
                            sp.setShareType(Platform.SHARE_TEXT);
                            wechat.setPlatformActionListener(new PlatformActionListener() {
                                @Override
                                public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
Log.e("message",i+"");
                                }

                                @Override
                                public void onError(Platform platform, int i, Throwable throwable) {
                                    Log.e("message",i+"");
                                }

                                @Override
                                public void onCancel(Platform platform, int i) {
                                    Log.e("message",i+"");
                                }
                            });
                            wechat.authorize();
                            wechat.share(sp);
                        }
                    }
                });
                oks.show(MainActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ShareSDK.stopSDK(this);
    }
}
