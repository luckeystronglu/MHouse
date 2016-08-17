package com.qf.luckey.mhouse;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.qf.luckey.adapter.CityAdapter;
import com.qf.luckey.entity.CityEntity;
import com.qf.luckey.fragment.HomeFragment;
import com.qf.luckey.utils.Contants;
import com.qf.luckey.utils.JsonUitl;
import com.qfkf.base.BaseActivity;
import com.qfkf.util.HttpUtil;
import com.qfkf.widget.LibelView;
import com.qfkf.widget.SlideView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CityChoiceActivity extends BaseActivity implements AdapterView.OnItemClickListener, SlideView.OnSelectListener {
    private ListView listView_citys;
    private CityAdapter cityAdapter;

    private SlideView slideView;
    private LibelView libelView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_citychoice;
    }

    //初始化

    @Override
    protected void init() {
        listView_citys = findViewByIds(R.id.lv_city);
        cityAdapter = new CityAdapter(this);
        listView_citys.setAdapter(cityAdapter);
        listView_citys.setOnItemClickListener(this);

        slideView = findViewByIds(R.id.slideview);
        libelView = findViewByIds(R.id.labelview);
        slideView.setOnSelectListener(this);
    }

    //数据下载

    @Override
    protected void loadDatas() {
        HttpUtil.downJson(Contants.URL_SELECT_CITY, new HttpUtil.DownLoadListener() {
            @Override
            public void downSucc(String url, String json) {
                //解析Json
                List<CityEntity> listcitys = JsonUitl.getCitysByJson(json);
                if (listcitys != null){
                    cityAdapter.setDatas(listcitys);
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityEntity cityEntity = (CityEntity) cityAdapter.getItem(position);
        Intent intent = getIntent();
        intent.putExtra("cityentity",cityEntity);
        setResult(HomeFragment.RESULT_COED,intent);
        finish();
    }

    /**
     * 侧边字母控件的回调方法
     * @param s
     */
    @Override
    public void selector(String s) {
        libelView.setText(s);//中间显示选中的字母
        //确定选择字母的下标
        int index = cityAdapter.queryLabelIndex(s);
        if (index != -1){
            //跳到字母对应的下标位置
            listView_citys.setSelection(index);
//            listView_citys.smoothScrollToPositionFromTop(index,0);//滑到坐标位置
        }
    }

    //抬手未触摸时
    @Override
    public void uptouch() {
        libelView.setVisibility(View.GONE);
    }
}
