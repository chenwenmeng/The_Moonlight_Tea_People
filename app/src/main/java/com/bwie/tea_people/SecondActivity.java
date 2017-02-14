package com.bwie.tea_people;

import android.app.Activity;


import android.os.Bundle;


import android.os.Handler;
import android.os.Message;


import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.MotionEvent;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


import android.widget.RelativeLayout;


import com.bwie.bean.ParticularsBean;
import com.bwie.fragment.PictureSlideFragment;
import com.bwie.util.Analysis_Json_Util;
import com.bwie.util.GlideImageLoader;

import com.bwie.util.PathUtil;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/13 19:14
 * 邮箱:18310832074@163.com
 */

public
class SecondActivity extends FragmentActivity {


    private ViewPager viewPager;
    //图片资源
    private List<ImageView> list;
    private LinearLayout line;
    private List<ImageView> imagelist;
    private int p;
    private ParticularsBean.DataBean.ProductBean productBean =
            new ParticularsBean.DataBean.ProductBean();
    private List<String> imageList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        line = (LinearLayout) findViewById(R.id.line2);
        //設置初始化的界面
        p = getIntent().getIntExtra("p", 0);
        //初始化小圆点
        Mygetdate mygetdate = new Mygetdate();
        int id = getIntent().getIntExtra("id1", 0);
        mygetdate.getdate(PathUtil.commodity_particulars + id);
        //监听换页变化
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                //遍历图片集合
                for (int i=0;i<imageList.size();i++){
                    //索引相同即为亮点
                    if (i==position){
                        imagelist.get(i).setImageResource(R.drawable.heise);
                    }else{
                        imagelist.get(i).setImageResource(R.drawable.baise);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initcircle() {
        //存放圆点
        imagelist = new ArrayList<>();
        for (int i=0;i<imageList.size();i++){
            ImageView imageView=new ImageView(this);
            //第一个默认为亮点
            if(i==p){
                imageView.setImageResource(R.drawable.heise);
            }else{
                imageView.setImageResource(R.drawable.baise);
            }
            //默认圆点高宽20
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(20,20);
            //设置间距（左上右下）
            params.setMargins(7,0,7,0);
            //放在容器中
            line.addView(imageView,params);
            imagelist.add(imageView);
        }
    }

    class Mygetdate extends Analysis_Json_Util {

        public void analysis_Json(String s) {

            ParticularsBean particularsBean = new Gson().fromJson(s, ParticularsBean.class);
            productBean = particularsBean.getData().getProduct();
            imageList.add(productBean.getApp_long_image1());
            imageList.add(productBean.getApp_long_image2());
            imageList.add(productBean.getApp_long_image3());
            imageList.add(productBean.getApp_long_image4());
            initcircle();
            //适配数据
            PictureSlidePagerAdapter mypageadapter=new PictureSlidePagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(mypageadapter);
            viewPager.setCurrentItem(p);
        }
    }
    private  class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PictureSlideFragment.newInstance(imageList.get(position));
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

    }
}
