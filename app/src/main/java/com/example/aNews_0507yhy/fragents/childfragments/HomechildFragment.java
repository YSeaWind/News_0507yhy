package com.example.aNews_0507yhy.fragents.childfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.aNews_0507yhy.R;
import com.example.aNews_0507yhy.utils.childutils.ChildNewsMessage;
import com.example.aNews_0507yhy.utils.Adapters.MyRecyclerViewAdapter2;

import java.util.ArrayList;
import java.util.List;


public class HomechildFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<ChildNewsMessage> userList;
    private View view;//给一个活动环境，以便调用一些方法
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_homechild, container, false);

        //注意加载的顺序
        initListData();
        initView();
        return view;
    }

    private void initView() {//初始化recyclerView
        recyclerView = view.findViewById(R.id.recyclerViewchild);
        MyRecyclerViewAdapter2 myRecyclerViewAdapter2 = new MyRecyclerViewAdapter2(userList, getActivity());//由于没有上下文环境  所以使用getActivity方法获取上下文对象
        recyclerView.setAdapter(myRecyclerViewAdapter2);




        //线性排列的管理者
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        //设置排列的顺序
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //网格布局管理者
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        //瀑布流
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        //设置分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

        //把创建好的排列方式设置到recyclercview里面
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.scrollToPosition(userList.size()-1);//默认滑动样式

        myRecyclerViewAdapter2.setOnItemClickListener(new MyRecyclerViewAdapter2.OnItemClickListener() {
            @Override
            public void OnItemClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);
                Toast.makeText(getActivity(),position+"666",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initListData() {
        userList = new ArrayList<>();
        for (int i =1;i<=100;i++){
            ChildNewsMessage childnews;
            if (i%2==0){
                childnews = new ChildNewsMessage(R.mipmap.ic_launcher,"user" + i);//由于数据的内容是自定义的 所以里面赋值的顺序要注意
            }else {
                childnews = new ChildNewsMessage(R.mipmap.p1, "user" + i);
            }

            userList.add(childnews);
        }
    }
}
