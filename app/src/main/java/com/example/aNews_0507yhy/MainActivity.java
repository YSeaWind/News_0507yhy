package com.example.aNews_0507yhy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.aNews_0507yhy.fragents.GengduoFragment;
import com.example.aNews_0507yhy.fragents.HomeFragment;
import com.example.aNews_0507yhy.fragents.MineFragment;
import com.example.aNews_0507yhy.fragents.SetFragment;
import com.example.aNews_0507yhy.utils.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ViewPager mViewPager;
    FragmentManager fragmentManager;
    private RadioGroup rgMenu;
    private RadioButton rbHome, rbSet, rbgengduo,rbmine;


    private FragmentTransaction fragmentTransaction;


    private DrawerLayout drawerLayout;
    private ImageView menuIv;
//recyclerView的加载

    List<Message> messageList;

    //用于存放fragment的集合

    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }
    private void initView() {

        mViewPager = findViewById(R.id.viewpager);
        rgMenu = findViewById(R.id.rg_menu);
        rbHome = findViewById(R.id.rb_home);
        rbSet = findViewById(R.id.rb_set);
        rbgengduo = findViewById(R.id.rb_gengduo);
        rbmine = findViewById(R.id.rb_mine);
        manager = getSupportFragmentManager();
//显示Fragment
        ShowFragment(firstFragment);
        rgMenu.setOnCheckedChangeListener(this);

        menuIv = findViewById(R.id.main_iv_menu);
        drawerLayout = findViewById(R.id.drawerLayout);
        menuIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

    }
    private List<Fragment> fragmentList;// 存储所有的Fragment
    private int firstFragment = 0; //第一次加载的Fragment
    private void ShowFragment(int position) {
        FragmentTransaction transaction = manager.beginTransaction();
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new GengduoFragment());
        fragmentList.add(new SetFragment());
        fragmentList.add(new MineFragment());
        transaction.replace(R.id.frag,fragmentList.get(position));
        transaction.commit();
    }
//    private void addFragmentList() {
//        fragmentList = new ArrayList<>();
//        fragmentList.add(new HomeFragment());
//        fragmentList.add(new GengduoFragment());
//        fragmentList.add(new SetFragment());
//        fragmentList.add(new MineFragment());
//    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        fragmentTransaction = manager.beginTransaction();
            switch (i) {
            case R.id.rb_home:
//               fragmentTransaction.replace(R.id.frag,new HomeFragment());

                ShowFragment(0);
                rbHome.setChecked(true);
                break;
            case R.id.rb_gengduo:
                ShowFragment(1);
                rbgengduo.setChecked(true);
//                fragmentTransaction.replace(R.id.frag,new GengduoFragment());
                break;
            case R.id.rb_set:
                ShowFragment(2);
                rbSet.setChecked(true);
//                fragmentTransaction.replace(R.id.frag,new SetFragment());
                break;
            case R.id.rb_mine:
                ShowFragment(3);
                rbmine.setChecked(true);
//                fragmentTransaction.replace(R.id.frag,new MineFragment());
                break;
        }
//        fragmentTransaction.commit();
    }





}
