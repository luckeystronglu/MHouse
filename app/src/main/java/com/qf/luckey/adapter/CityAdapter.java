package com.qf.luckey.adapter;

import android.content.Context;

import com.qf.luckey.entity.CityEntity;
import com.qf.luckey.mhouse.R;
import com.qfkf.base.AbsMoreItemBaseAdapter;

/**
 * Created by Administrator on 2016/8/7.
 */
public class CityAdapter extends AbsMoreItemBaseAdapter<CityEntity> {

    public CityAdapter(Context context) {
        super(context, R.layout.item_letter,R.layout.item_city);
    }

    @Override
    public void bindDatas(ViewHodler viewHodler, CityEntity data, int position) {
        if (data.getType() == 0){
            viewHodler.setTextView(R.id.tv_letter,data.getCityname());
        }else {
            viewHodler.setTextView(R.id.tv_city,data.getCityname());
        }
    }

    @Override
    public boolean isEnabled(int position) {
        return datas.get(position).getType() == 1;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }

    //查询label标签在数据源中的下标位置
    public int queryLabelIndex(String label){
        for (int i = 0;i < datas.size();i++){
            if (datas.get(i).getCityname().equals(label)){
                return i;
            }
        }
        return -1;
    }
}
