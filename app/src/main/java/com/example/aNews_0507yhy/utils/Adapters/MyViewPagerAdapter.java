package com.example.aNews_0507yhy.utils.Adapters;

import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * viewpager 如果要加载fragment，使用FragmentPagerAdapter
 */
public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> childfragmentList;

    public MyViewPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Fragment> childfragmentList) {
        super(fm, behavior);
        this.childfragmentList = childfragmentList;
    }

    /**
     * 获取当前的页面
     *
     * @param position
     * @return
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return childfragmentList.get(position);
    }

    /**
     * 设置viewpager的页数
     *
     * @return
     */
    @Override
    public int getCount() {
        return childfragmentList != null ? childfragmentList.size() : 0;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
    }

}
