package com.qf.luckey.mhouse;

import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import com.qfkf.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by Administrator on 2016/8/5.
 */
public class WelcomeActivity extends BaseActivity {
    private TextView textView;

    //定义一个handler
    private Handler handler = new Handler();

    @Override
    public int getContentViewId() {
        EventBus.getDefault().register(this);
        return R.layout.activity_welcome;
    }

    //初始化

    @Subscribe
    public void getEventCity(String city){
        textView.setText(city);
    }

    @Override
    protected void init() {
        textView = findViewByIds(R.id.tv_welcome);
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

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
