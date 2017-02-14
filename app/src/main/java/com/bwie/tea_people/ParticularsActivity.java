package com.bwie.tea_people;


import android.app.Activity;



import android.content.Intent;
import android.os.Bundle;


import android.support.v4.app.Fragment;


import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AlertDialog;
import android.text.TextUtils;



import android.util.Log;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;


import com.bwie.bean.ParticularsBean;


import com.bwie.fragment.ParticularsFragment;
import com.bwie.fragment.PictureSlideFragment;
import com.bwie.util.Analysis_Json_Util;


import com.bwie.util.GlideImageLoader;



import com.bwie.util.LogUtil;
import com.bwie.util.PathUtil;
import com.google.gson.Gson;


import com.youth.banner.Banner;


import com.youth.banner.BannerConfig;


import com.youth.banner.listener.OnBannerClickListener;


import java.util.ArrayList;

import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/14 10:16
 * 邮箱:18310832074@163.com
 */

public
class
ParticularsActivity extends FragmentActivity {



    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commodity_particulars);
        initview();
    }

    public void initview() {

        id = getIntent().getIntExtra("id", 0);
        FragmentManager manager=getSupportFragmentManager();
        ParticularsFragment fragment=new ParticularsFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("id1",id);
        fragment.setArguments(bundle);
        manager.beginTransaction().add(R.id.fl2,fragment).commit();

    }


}
