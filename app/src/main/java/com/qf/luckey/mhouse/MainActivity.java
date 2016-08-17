package com.qf.luckey.mhouse;

import android.widget.RadioGroup;

import com.qf.luckey.fragment.DisCoverFragment;
import com.qf.luckey.fragment.HomeFragment;
import com.qfkf.base.BaseActivity;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    //初始化
    @Override
    protected void init() {
        radioGroup = findViewByIds(R.id.rg_tab_bottom);//找到RadioGroup控件
        radioGroup.setOnCheckedChangeListener(this);//设置点击监听
        radioGroup.getChildAt(0).performClick();//模拟点击rg第一个rb
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_shouye:
                fragmentManager(R.id.framely_flagment,new HomeFragment(),"home");
                break;
            case R.id.rb_discover:
                fragmentManager(R.id.framely_flagment,new DisCoverFragment(),"discover");
                break;
        }
    }
}
