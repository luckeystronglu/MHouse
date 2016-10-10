package com.qf.luckey.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.qf.luckey.mhouse.MyMapLocation;
import com.qf.luckey.mhouse.R;
import com.qfkf.base.BaseFragment;

/**
 * Created by Administrator on 2016/8/7.
 */
public class DisCoverFragment extends BaseFragment implements View.OnClickListener {
    private ImageView imageView;
    @Override
    protected int getContentView() {
        return R.layout.fragment_discover;
    }

    @Override
    protected void init(View view) {
        imageView = (ImageView) view.findViewById(R.id.img_discover);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getActivity(), MyMapLocation.class));
    }
}
