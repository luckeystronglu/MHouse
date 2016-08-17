package com.qf.luckey.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.qf.luckey.adapter.NewsAdapter;
import com.qf.luckey.entity.CityEntity;
import com.qf.luckey.entity.NewsEntity;
import com.qf.luckey.mhouse.CityChoiceActivity;
import com.qf.luckey.mhouse.R;
import com.qf.luckey.utils.Contants;
import com.qf.luckey.utils.JsonUitl;
import com.qfkf.base.BaseFragment;
import com.qfkf.util.HttpUtil;
import com.qfkf.util.ShareUtil;

import java.util.List;

/**
 * 首页展示的Fragment
 * Created by Administrator on 2016/8/6.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener, HttpUtil.DownLoadListener {

    private TextView tv_selectcity;

    public static final int REQUEST_COED = 0x001;
    public static final int RESULT_COED = 0x002;
    //当前默认的城市ID
    private int cityid = 1;
    //当前新闻条数
    private int newsCount = 10;
    //请求链接
    private String newsUrl;

    //首页列表
    private ListView newsListView;

    //数据源
    private List<NewsEntity> datalist;

    //适配器
    private NewsAdapter newsAdapter;

    @Override
    protected int getContentView() {
        return R.layout.fragment_home;
    }

    //初始化

    @Override
    protected void init(View view) {
        tv_selectcity = (TextView) view.findViewById(R.id.htv_selectcity);
        tv_selectcity.setOnClickListener(this);

        //从共享参数中取出数据
        int cityid = ShareUtil.getInt("cityid");
        String cityname = ShareUtil.getString("cityname");
        if (cityid != -1 && cityname != null){
            this.cityid = cityid;
            tv_selectcity.setText(cityname);
        }

        //首页列表
        newsListView = (ListView) view.findViewById(R.id.lv_homenews);
        newsAdapter = new NewsAdapter(getActivity());
        newsListView.setAdapter(newsAdapter);
    }

    @Override
    protected void loadDatas() {
        //加载相应的城市数据
        newsUrl = String.format(Contants.LIST_NEWS,newsCount,0,0,cityid);
        HttpUtil.downJson(newsUrl,this);

    }

    @Override
    public void onClick(View view) {
        startActivityForResult(new Intent(getActivity(), CityChoiceActivity.class),REQUEST_COED);
        getActivity().overridePendingTransition(R.anim.anim_activity_bottem_in,R.anim.anim_activity_bottom_keep);
    }

    //获取城市选择页面的消息返回


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_COED && resultCode == RESULT_COED){
            CityEntity cityEntity = (CityEntity) data.getSerializableExtra("cityentity");
            //把当前的城市ID和城市名称保存进共享参数
            ShareUtil.putString("cityname",cityEntity.getCityname());
            ShareUtil.putInt("cityid",cityEntity.getCityid());

            tv_selectcity.setText(cityEntity.getCityname());
            cityid = cityEntity.getCityid();

        }
    }

    @Override
    public void downSucc(String url, String json) {
        if (url.equals(newsUrl) && json != null){
            //说明请求的是ListView新闻列表
            datalist = JsonUitl.getNewsByJson(json);
            if (datalist != null){
                newsAdapter.setDatas(datalist);
            }
        }
    }
}
