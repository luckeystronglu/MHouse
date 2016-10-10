package com.qf.luckey.mhouse;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.qf.luckey.adapter.CityAdapter;
import com.qf.luckey.entity.CityDbEntity;
import com.qf.luckey.entity.CityEntity;
import com.qf.luckey.fragment.HomeFragment;
import com.qf.luckey.utils.Contants;
import com.qf.luckey.utils.DownUtil;
import com.qf.luckey.utils.JsonUitl;
import com.qfkf.base.BaseActivity;
import com.qfkf.widget.LibelView;
import com.qfkf.widget.SlideView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CityChoiceActivity extends BaseActivity implements AdapterView.OnItemClickListener, SlideView.OnSelectListener, DownUtil.OnDownListener {
    private ListView listView_citys;
    private CityAdapter cityAdapter;

    private SlideView slideView;
    private LibelView libelView;

//    List<CityEntity> listcitys = new ArrayList<>();

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
        List<CityDbEntity> cidataentity = new Select().from(CityDbEntity.class).where("ctype>=?",0).execute();
        if (cidataentity.size() == 0) {
            //如果数据库总没有数据，则下载城市列表的json
            new DownUtil().setOnDownListener(this).downJSON(Contants.URL_SELECT_CITY);
        } else {
            List<CityEntity> cityentity = new ArrayList<>();
            //CityEntity cityEntity = new CityEntity();
            for (int i = 0; i < cidataentity.size(); i++) {
                CityEntity cityEntity = new CityEntity();
                CityDbEntity citydb = cidataentity.get(i);
                //将数据库中的数据添加进城市对象中
                cityEntity.setCityid(citydb.getCid());
                cityEntity.setCityname(citydb.getCname());
                cityEntity.setType(citydb.getCtype());
                //将对象放入集合中
                cityentity.add(cityEntity);

            }
            //将数据放入适配器中
            cityAdapter.setDatas(cityentity);

        }


    }

//    @Override
//    public void downSucc(String url, String json) {
//        listcitys = JsonUitl.getCitysByJson(json);
//        if (listcitys != null) {
//            for (CityEntity listcity : listcitys) {
////                listcity.save();
//            }
////            for (int i = 0 ; i < listcitys.size() ; i++){
////                CityEntity cityEntity = listcitys.get(i);
////                cityEntity.save();
////            }
//
//            cityAdapter.setDatas(listcitys);
//        }
//    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CityEntity cityEntity = (CityEntity) cityAdapter.getItem(position);
        Intent intent = getIntent();
        intent.putExtra("cityentity", cityEntity);
        setResult(HomeFragment.RESULT_COED, intent);
        finish();
    }

    /**
     * 侧边字母控件的回调方法
     *
     * @param s
     */
    @Override
    public void selector(String s) {
        libelView.setText(s);//中间显示选中的字母
        //确定选择字母的下标
        int index = cityAdapter.queryLabelIndex(s);
        if (index != -1) {
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


    @Override
    public Object paresJson(String json) {
        if (json != null) {
            return JsonUitl.getCitysByJson(json);
        }
        return null;
    }

    @Override
    public void downSucc(Object object) {
        if (object != null) {
            List<CityEntity> datas = (List<CityEntity>) object;
            for (int i = 0; i < datas.size(); i++) {
                CityEntity cityEntity = datas.get(i);
                CityDbEntity cityEntityDb = new CityDbEntity(cityEntity.getCityid(),cityEntity.getCityname(), cityEntity.getType());
                cityEntityDb.save();
            }
            cityAdapter.setDatas(datas);
        }
    }
}