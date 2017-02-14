package com.bwie.tea_people;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.fragment.Dayday_fragment;
import com.bwie.fragment.Home_Fragment;
import com.bwie.fragment.My_fragment;
import com.bwie.fragment.Shopping_fragment;


import java.util.ArrayList;

import java.util.List;

public
class
MainActivity extends AppCompatActivity implements View.OnClickListener {


    private TextView mTitle, mHome, mDayday, mShopping, mMy;
    private TextView mHomebtn, mDaydaybtn, mShoppingbtn, mMybtn;
    private LinearLayout mHomebtn1, mDaydaybtn1, mShoppingbtn1, mMybtn1;
    private List<Fragment> fragmentList;
    private FragmentManager manager;
    private List<TextView> textViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //点击切换导航栏
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.m_line1:
                showorhide(0);
                break;
            case R.id.m_line2:
                showorhide(1);
                break;
            case R.id.m_line3:
                showorhide(2);
                break;
            case R.id.m_line4:
                showorhide(3);
                break;
        }
    }

    //界面的显示隐藏
    public void showorhide(int num) {

        for (int i = 0; i < fragmentList.size(); i++) {
            if (num == i) {
                manager.beginTransaction().show(fragmentList.get(i)).commit();
                textViewList.get(i).setTextColor(getResources().getColor(R.color.title));
            } else {

                manager.beginTransaction().hide(fragmentList.get(i)).commit();
                textViewList.get(i).setTextColor(getResources().getColor(R.color.dayday));
            }
            if (num == 0) {
                mTitle.setText("首页");
                mHomebtn.setBackgroundResource(R.mipmap.tab_home_s);
                mDaydaybtn.setBackgroundResource(R.mipmap.tab_topic);
                mShoppingbtn.setBackgroundResource(R.mipmap.main_index_cart_normal);
                mMybtn.setBackgroundResource(R.mipmap.main_index_my_normal);

            } else if (num == 1) {
                mTitle.setText("天天");
                mHomebtn.setBackgroundResource(R.mipmap.tab_home);
                mDaydaybtn.setBackgroundResource(R.mipmap.tab_topic_s);
                mShoppingbtn.setBackgroundResource(R.mipmap.main_index_cart_normal);
                mMybtn.setBackgroundResource(R.mipmap.main_index_my_normal);

            } else if (num == 2) {
                mTitle.setText("购物车");
                mHomebtn.setBackgroundResource(R.mipmap.tab_home);
                mDaydaybtn.setBackgroundResource(R.mipmap.tab_topic);
                mShoppingbtn.setBackgroundResource(R.mipmap.main_index_cart_pressed);
                mMybtn.setBackgroundResource(R.mipmap.main_index_my_normal);

            } else if (num == 3) {
                mTitle.setText("我的");
                mHomebtn.setBackgroundResource(R.mipmap.tab_home);
                mDaydaybtn.setBackgroundResource(R.mipmap.tab_topic);
                mShoppingbtn.setBackgroundResource(R.mipmap.main_index_cart_normal);
                mMybtn.setBackgroundResource(R.mipmap.main_index_my_pressed);

            }
        }

    }

    //初始化控件
    public void init() {

        mTitle = (TextView) findViewById(R.id.title1);
        mHome = (TextView) findViewById(R.id.text_home);
        mDayday = (TextView) findViewById(R.id.text_dayday);
        mShopping = (TextView) findViewById(R.id.text_shopping_car);
        mMy = (TextView) findViewById(R.id.text_my);

        mHomebtn = (TextView) findViewById(R.id.btn_home);
        mDaydaybtn = (TextView) findViewById(R.id.btn_dayday);
        mShoppingbtn = (TextView) findViewById(R.id.btn_shopping_car);
        mMybtn = (TextView) findViewById(R.id.btn_my);

        mHomebtn1= (LinearLayout) findViewById(R.id.m_line1);
        mDaydaybtn1= (LinearLayout) findViewById(R.id.m_line2);
        mShoppingbtn1= (LinearLayout) findViewById(R.id.m_line3);
        mMybtn1= (LinearLayout) findViewById(R.id.m_line4);

        mHomebtn1.setOnClickListener(this);
        mDaydaybtn1.setOnClickListener(this);
        mShoppingbtn1.setOnClickListener(this);
        mMybtn1.setOnClickListener(this);
        //初始化四个Fragment并放入集合
        fragmentList = new ArrayList<>();
        fragmentList.add(new Home_Fragment());
        fragmentList.add(new Dayday_fragment());
        fragmentList.add(new Shopping_fragment());
        fragmentList.add(new My_fragment());
        //将四个导航标题存入集合
        textViewList = new ArrayList<>();
        textViewList.add(mHome);
        textViewList.add(mDayday);
        textViewList.add(mShopping);
        textViewList.add(mMy);
        //默认显示首页
        manager = getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl, fragmentList.get(0))
                .add(R.id.fl, fragmentList.get(1))
                .add(R.id.fl, fragmentList.get(2))
                .add(R.id.fl, fragmentList.get(3))
                .commit();
        showorhide(0);

    }
}
