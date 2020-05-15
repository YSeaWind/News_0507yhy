package com.example.aNews_0507yhy.fragents;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.fragents.childfragments.HomechildFragment;
import com.example.aNews_0507yhy.fragents.childfragments.JunshiFragment;
import com.example.aNews_0507yhy.fragents.childfragments.LishiFragment;
import com.example.aNews_0507yhy.fragents.childfragments.TuijianFragment;
import com.example.aNews_0507yhy.fragents.childfragments.YuleFragment;
import com.example.aNews_0507yhy.utils.Adapters.MyFvAdapter;
import com.example.aNews_0507yhy.utils.Message;
import com.example.aNews_0507yhy.utils.Adapters.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private View view;
    //用于存放fragment的集合
    private List<Fragment> childfragList;
    private FragmentManager manager;
    private RecyclerView recyclerView;
    private List<Message> messageList;
    MyFvAdapter.MyRecyclerViewAdapter myRecyclerViewAdapter;
    public HomeFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initListData();
        initView();
        return view;
    }

    private void initListData() {
        messageList = new ArrayList<>();
        Message message1 = new Message("首页");
        Message message2 = new Message("军事");
        Message message3 = new Message("历史");
        Message message4 = new Message("推荐");
        Message message5 = new Message("娱乐圈");

        messageList.add(message1);
        messageList.add(message2);
        messageList.add(message3);
        messageList.add(message4);
        messageList.add(message5);
    }
    private void initView() {
        viewPager = view.findViewById(R.id.viewpager);
        initChildFragmentList();
        manager = getChildFragmentManager();
        MyViewPagerAdapter myAdapter = new MyViewPagerAdapter(manager, 0, childfragList);
        viewPager.setAdapter(myAdapter);
        viewPager.addOnPageChangeListener(this);

        //初始化recyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        myRecyclerViewAdapter = new MyFvAdapter.MyRecyclerViewAdapter(messageList, getActivity());
        recyclerView.setAdapter(myRecyclerViewAdapter);
        //使用recyclerview的加载功能
        //线性排列的管理者
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置排列的顺序
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//把创建好的排列方式设置到recyclercview里面
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.scrollToPosition(messageList.size()-1);//默认滑动样式

        myRecyclerViewAdapter.setOnItemClickListener(new MyFvAdapter.MyRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                viewPager.setCurrentItem(position);
            }
        });


    }

    private void initChildFragmentList() {

            JunshiFragment junshiFragment = new JunshiFragment();
            LishiFragment lishiFragment = new LishiFragment();
            TuijianFragment tuijianFragment = new TuijianFragment();
            YuleFragment yuleFragment = new YuleFragment();
            HomechildFragment homechildFragment = new HomechildFragment();

        childfragList = new ArrayList<>();
            childfragList.add(homechildFragment);
            childfragList.add(junshiFragment);
            childfragList.add(lishiFragment);
            childfragList.add(tuijianFragment);
            childfragList.add(yuleFragment);

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        recyclerView.smoothScrollToPosition(position);
        myRecyclerViewAdapter.setBg(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
