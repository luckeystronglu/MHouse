package com.qf.luckey.mhouse;

import android.content.Intent;
import android.os.Handler;

import com.qfkf.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/5.
 */
public class WelcomeActivity extends BaseActivity {
    //定义一个handler
    private Handler handler = new Handler();

    @Override
    public int getContentViewId() {
        return R.layout.activity_welcome;
    }

    //初始化


    @Override
    protected void init() {
        //2s调用intent跳转
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //在主线程中执行
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
                //设置Activity的过程动画
                overridePendingTransition(R.anim.anim_activity_right_in,R.anim.anim_activity_left_out);
                finish();
            }
        },2000);
    }
}
