package com.example.aNews_0507yhy.utils.Adapters;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.utils.bean.Data;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class LishiAdapter extends BaseMultiItemQuickAdapter<Data, BaseViewHolder> {
    private ImageView imageView;//定义imageview方便获取和赋值
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public LishiAdapter(List<Data> data) {
        super(data);
        //加载item  给加载的item贴标签
        addItemType(1, R.layout.recyclerview_item3);
        addItemType(2,R.layout.recyclerview_item4);
    }
    Bitmap Imgbitmap = null;
    @Override
    protected void convert(BaseViewHolder helper, Data item) {
        //判断不同的item 加载控件并复制            注意：set方法是原插件写的      系统方法里没有
        switch (helper.getItemViewType()){

            case 1:
                //item_shishi01修改数据
                helper.setText(R.id.shishi01_title,item.getTitle());
                helper.setText(R.id.shishi01_author,item.getAuthor_name());
                helper.setText(R.id.shishi01_time,item.getDate());
//                Imgbitmap = setPosterBitmap(item.getThumbnail_pic_s());
//                helper.setImageBitmap(R.id.shishi01_img01,Imgbitmap);
//                Imgbitmap = setPosterBitmap(item.getThumbnail_pic_s02());
//                helper.setImageBitmap(R.id.shishi01_img02,Imgbitmap);
//                Imgbitmap = setPosterBitmap(item.getThumbnail_pic_s03());
//                helper.setImageBitmap(R.id.shishi01_img03,Imgbitmap);

                imageView = helper.itemView.findViewById(R.id.shishi01_img01);
                Picasso.get().load(item.getThumbnail_pic_s()).into(imageView);
                imageView = helper.itemView.findViewById(R.id.shishi01_img02);
                Picasso.get().load(item.getThumbnail_pic_s02()).into(imageView);
                imageView = helper.itemView.findViewById(R.id.shishi01_img03);
                Picasso.get().load(item.getThumbnail_pic_s03()).into(imageView);
                break;
            case 2:
                //item_shishi02修改数据
                // Imgbitmap 第一次为空
                Imgbitmap = setPosterBitmap(item.getThumbnail_pic_s());
                helper.setText(R.id.shishi02_title,item.getTitle());
                helper.setText(R.id.shishi02_author,item.getAuthor_name());
                helper.setText(R.id.shishi02_time,item.getDate());
//                helper.setImageBitmap(R.id.shishi02_img,Imgbitmap);
                imageView = helper.itemView.findViewById(R.id.shishi02_img);
                Picasso.get().load(item.getThumbnail_pic_s()).into(imageView);
                break;

        }
    }

    /**
     * 使用picasso讲url转换成bitmap
     * @param url
     */
    Bitmap setPosterBitmap(String url){
        final Bitmap[] mBitmap = new Bitmap[1];
        Picasso.get().load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                mBitmap[0] = bitmap;
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
        return mBitmap[0];
    }
}
