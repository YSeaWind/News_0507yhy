package com.example.aNews_0507yhy.utils.Adapters;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.utils.childutils.UserData;

import java.util.List;

import androidx.annotation.Nullable;

public class MyJunShiAdapter extends BaseQuickAdapter <UserData, BaseViewHolder> {
    public MyJunShiAdapter(int layoutResId, @Nullable List<UserData> data) {
        super(layoutResId, data);
    }

    public MyJunShiAdapter(@Nullable List<UserData> data) {
        super(data);
    }

    public MyJunShiAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserData item) {
        int adapterPosition = helper.getAdapterPosition();
        if (adapterPosition%2==0){
            helper.setBackgroundColor(R.id.rl_content, Color.RED);
        }else {
            helper.setBackgroundColor(R.id.rl_content, Color.BLUE);
        }
        helper.setText(R.id.tvName,item.getUseName());
    }
}
