package com.qf.luckey.adapter;

import android.content.Context;

import com.qf.luckey.entity.NewsEntity;
import com.qf.luckey.mhouse.R;
import com.qfkf.base.AbsMoreItemBaseAdapter;

/**
 * Created by Administrator on 2016/8/7.
 */
public class NewsAdapter extends AbsMoreItemBaseAdapter<NewsEntity> {

    public NewsAdapter(Context context) {
        super(context, R.layout.item_news_pic,R.layout.item_news_pics);
    }

    @Override
    public void bindDatas(ViewHodler viewHodler, NewsEntity data, int position) {
        if (getItemViewType(position) == 0){
            viewHodler
                    .setImageView(R.id.new_img,data.getThumbnail())
                    .setTextView(R.id.tv_newstitle,data.getTitle())
                    .setTextView(R.id.tv_newscontent,data.getSummary())
                    .setTextView(R.id.tv_newspincount,data.getCommentcount()+"");
        }else {
            viewHodler
                    .setImageView(R.id.new_imgs,data.getGroupthumbnail())
                    .setTextView(R.id.tv_newstitle,data.getTitle())
                    .setTextView(R.id.tv_newspincount,data.getCommentcount()+"");
        }
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).getType();
    }
}
