package com.qfkf.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * 所有Activity的父类
 * Created by Administrator on 2016/8/2.
 */
public abstract class BaseActivity extends AppCompatActivity {
    private FragmentManager fragmentManager;
    private Fragment showfragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        //初始化FragmentManager对象
        fragmentManager = getSupportFragmentManager();
        init();
        loadDatas();
    }

    //返回当前activity所显示的布局ID
    public abstract int getContentViewId();
    //初始化方法
    protected void init() {
    }
    //加载数据的方法
    protected void loadDatas() {
    }

    /**
     * 查找页面布局ID所对应的控件对象，不用强制转换
     * @param resId
     * @param <T>
     * @return
     */
    protected  <T> T findViewByIds(int resId){
        return (T) findViewById(resId);
    }

    //管理fragment的显示与隐藏的方法
    protected void fragmentManager(int resId, Fragment fragment, String tag){
        //开始事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //隐藏正在显示的fragment对象
        if (showfragment != null){
            fragmentTransaction.hide(showfragment);
        }
        Fragment loadFragment = fragmentManager.findFragmentByTag(tag);
        if (loadFragment != null){
            fragmentTransaction.show(loadFragment);
        }else {
            loadFragment = fragment;
            fragmentTransaction.add(resId,fragment,tag);
        }
        showfragment = loadFragment;
        fragmentTransaction.commit();
    }

}
