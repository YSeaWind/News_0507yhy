package com.example.aNews_0507yhy.fragents.childfragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.example.aNews_0507yhy.NewsActivity;
import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.Services.MyService;
import com.example.aNews_0507yhy.utils.bean.Data;
import com.example.aNews_0507yhy.utils.bean.MyJson;
import com.example.aNews_0507yhy.utils.childutils.CustomerLoadMoreView;
import com.example.aNews_0507yhy.utils.Adapters.LishiAdapter;

import java.util.ArrayList;
import java.util.List;


public class LishiFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private View view;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LishiAdapter lishiAdapter;
    private List<MyJson> data= new ArrayList<>();//关于MyJson的数组，存放自己网络上要存放的数据
    private List<Data> mydata = new ArrayList<>();
    private int count;//当前item数量
    private int loadMoreCount = 0;//记录加载的条数的标识
    Intent intent;
    private LishiMyServer myServer;
    private Context context;
    MyJson myJson;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null){
            view = inflater.inflate(R.layout.fragment_lishi, container, false);
            //开启服务
            context=getActivity().getApplicationContext();
            sendServerValue();
        }
        //清除viewPager的缓存
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }




        swipeRefreshLayout = view.findViewById(R.id.swipfresulayout_lishi);
        recyclerView = view.findViewById(R.id.recyclerView_lishi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        //初始化adpater
        lishiAdapter = new LishiAdapter(mydata);
        //加载  <下拉加载> 页面布局
        lishiAdapter.setLoadMoreView(new CustomerLoadMoreView());
        //recyclerView item点击事件
        lishiAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Data myJsondata = lishiAdapter.getItem(position);
                Intent intentNews = new Intent(context, NewsActivity.class);
                intentNews.putExtra("key",myJsondata);
                startActivity(intentNews);

            }
        });

        lishiAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //获取到 数据集合
                        List<Data> dataList = getDatas(false);
                        if (loadMoreCount==1){
                            //正常加载
                            lishiAdapter.addData(dataList);
                            lishiAdapter.loadMoreComplete();
                        }else if (loadMoreCount==2){
                            //加载失败
                            lishiAdapter.loadMoreFail();
                        }else if (loadMoreCount==3){
                            //加载到最后
                            lishiAdapter.loadMoreEnd();
                        }
                    }
                },3000);
            }
        },recyclerView);
        recyclerView.setAdapter(lishiAdapter);
        swipeRefreshLayout.setOnRefreshListener(this);
        onRefresh();
        return view;
    }

    private void sendServerValue() {
        intent = new Intent(context, MyService.class);
        intent.putExtra("type","top");
        context.startService(intent);
        initReceiver();//触发广播接收者
    }
    private class LishiMyServer extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.e("4654564",action);
            if(MyService.ACTION.equals(action)){
                myJson = (MyJson) intent.getSerializableExtra("top");
//赋值给数组
                mydata = myJson.getResult().getData();
//              判断数组长度  进行加载
                for (int i = 0; i < mydata.size(); i++) {
                    Data data = mydata.get(i);
                    if (mydata.get(i).getThumbnail_pic_s03() != null) {
                        data.setItemType(1);
                    } else {
                        data.setItemType(2);
                    }
                }
            }
        }
    }
//注册广播接收者
    private void initReceiver() {
        myServer = new LishiMyServer();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyService.ACTION);
        context.registerReceiver(myServer,filter);
    }

    @Override
    //在滑动时关闭服务
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(context!=null) {
                //开启服务
                sendServerValue();
                Log.e("setUserVisibleHint: ", "可视");
            }
        }else {

            if (context!=null&&intent!=null){

                //关闭服务
                //intent = new Intent(context, MyService.class);
                context.stopService(intent);
                Log.e("NotsetUserVisibleHint: ", "不可视");
            }


        }
    }
    private List<Data> getDatas(boolean isRefresh) {
        if (isRefresh) {
            count = 0;
        }
        List<Data> dataList = new ArrayList<>();
//        for (int i = count; i < count + 10; i++) {
//            if (isRefresh) {
//                loadMoreCount = 0;
//                dataList.add(new Data(1));
//
//            } else {
//                dataList.add(new Data(2));
//
//            }
//        }
        if (!isRefresh) {
            loadMoreCount++;
        }
        count += 10;
        return mydata;
    }
        //加载数据  刷新页面
        //下拉刷新是时执行,手动调用时.数据局发生变化
        public void onRefresh() {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<Data> userDataList = getDatas(true);
                    lishiAdapter.setNewData(userDataList);
                    lishiAdapter.loadMoreComplete();
                    //视图是否显示刷新的进度
                    swipeRefreshLayout.setRefreshing(false);
                }
            }, 3000);

        }

}
