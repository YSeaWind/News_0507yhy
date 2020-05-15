package com.example.aNews_0507yhy.fragents.childfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.utils.childutils.CustomerLoadMoreView;
import com.example.aNews_0507yhy.utils.Adapters.MyJunShiAdapter;
import com.example.aNews_0507yhy.utils.childutils.UserData;

import java.util.ArrayList;
import java.util.List;


public class JunshiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MyJunShiAdapter myJunShiAdapter;
    private List<UserData> userData= new ArrayList<>();
    private int count;//当前item数量
    private int loadMoreCount = 0;//记录加载的条数的标识
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_junshi, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipfresulayout_junshi);
        recyclerView = view.findViewById(R.id.recyclerView_junshi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //初始化adpater
        myJunShiAdapter = new MyJunShiAdapter(R.layout.item_recyclerview,userData);
        //加载  <下拉加载> 页面布局
        myJunShiAdapter.setLoadMoreView(new CustomerLoadMoreView());
        //recyclerView item点击事件
        myJunShiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserData userData = myJunShiAdapter.getItem(position);
                Log.e("6666",userData.getUseName());
            }
        });

        myJunShiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //获取到 数据集合
                        List<UserData> userDataList = getDatas(false);
                        if (loadMoreCount==1){
                            //正常加载
                            myJunShiAdapter.addData(userDataList);
                            myJunShiAdapter.loadMoreComplete();
                        }else if (loadMoreCount==2){
                            //加载失败
                            myJunShiAdapter.loadMoreFail();
                        }else if (loadMoreCount==3){
                            //加载到最后
                            myJunShiAdapter.loadMoreEnd();
                        }
                    }
                },3000);
            }
        },recyclerView);
        recyclerView.setAdapter(myJunShiAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }
    private List<UserData> getDatas(boolean isRefresh){
        if (isRefresh){
            count = 0;
        }
        List<UserData> dataList = new ArrayList<>();
        for (int i=count;i<count+10;i++){
            if (isRefresh){
                loadMoreCount = 0;
                dataList.add(new UserData("下拉刷新"+i));

            }else {
                dataList.add(new UserData("上拉加载的数据"+i));
            }
        }
        if (!isRefresh){
            loadMoreCount++;
        }
        count += 10;
        return dataList;
    }

    //加载数据  刷新页面
    //下拉刷新是时执行,手动调用时.数据局发生变化
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<UserData> userDataList = getDatas(true);
                myJunShiAdapter.setNewData(userDataList);
                myJunShiAdapter.loadMoreComplete();
                //视图是否显示刷新的进度
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);

    }

}
