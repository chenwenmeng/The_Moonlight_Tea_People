package com.bwie.fragment;

import android.content.Intent;


import android.graphics.Paint;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.Button;

import android.widget.RelativeLayout;
import android.widget.TextView;


import android.widget.Toast;

import com.bwie.bean.ParticularsBean;
import com.bwie.tea_people.ParticularsActivity;
import com.bwie.tea_people.R;
import com.bwie.tea_people.SecondActivity;
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
 * 时间:2017/2/14 17:12
 * 邮箱:18310832074@163.com
 */

public
class
ParticularsFragment extends Fragment implements View.OnClickListener {

    private Banner banner;
    private ParticularsBean.DataBean.ProductBean productBean =
            new ParticularsBean.DataBean.ProductBean();
    private List<String> imageList = new ArrayList<>();
    private int id;
    private View view;
    private TextView particulars_name, particulars_price,
            particulars_f_price, particlar_maioshu;
    private RelativeLayout relativeLayout, jialiao, wendu, jiatang;
    private Button jiaoliao_btn;
    private Button wendu_btn;
    private Button jiatang_btn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.particular_fragment, null);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initview();
    }

    public void initview() {

        banner = (Banner) view.findViewById(R.id.banner1);
        Mygetdate mygetdate = new Mygetdate();
        id = getArguments().getInt("id1");
        mygetdate.getdate(PathUtil.commodity_particulars + id);
        banner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                int i = position;
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("p", i - 1);
                intent.putExtra("id1", id);
                startActivity(intent);
            }
        });

        particulars_name = (TextView) view.findViewById(R.id.particulars_name);
        particulars_price = (TextView) view.findViewById(R.id.particulars_price);
        particulars_f_price = (TextView) view.findViewById(R.id.particulars_f_price);
        particlar_maioshu = (TextView) view.findViewById(R.id.particlar_maioshu);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.par_p);
        jialiao = (RelativeLayout) view.findViewById(R.id.r_jiaoliao);
        wendu = (RelativeLayout) view.findViewById(R.id.r_wendu);
        jiatang = (RelativeLayout) view.findViewById(R.id.r_tangliang);

        jiaoliao_btn = (Button) view.findViewById(R.id.jiaoliao_btn);
        wendu_btn = (Button) view.findViewById(R.id.jiaoliao_btn);
        jiatang_btn = (Button) view.findViewById(R.id.jiaoliao_btn);
        jiaoliao_btn.setOnClickListener(this);
        wendu_btn.setOnClickListener(this);
        jiatang_btn.setOnClickListener(this);
    }

    class Mygetdate extends Analysis_Json_Util {

        public void analysis_Json(String s) {

            ParticularsBean particularsBean = new Gson().fromJson(s, ParticularsBean.class);
            productBean = particularsBean.getData().getProduct();
            imageList.add(productBean.getApp_long_image1());
            imageList.add(productBean.getApp_long_image2());
            imageList.add(productBean.getApp_long_image3());
            imageList.add(productBean.getApp_long_image4());
            //imageList.add(productBean.getApp_long_image5());
            //设置图片加载器
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(imageList);
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.start();
            particulars_name.setText(productBean.getName());
            if (!TextUtils.isEmpty(productBean.getFeatured_price())) {
                particulars_price.setVisibility(View.VISIBLE);
                particulars_price.setText("￥"+productBean.getPrice());
                particulars_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                particulars_f_price.setText("￥"+productBean.getFeatured_price());
            } else {
                particulars_price.setVisibility(View.GONE);
                particulars_f_price.setText("￥"+productBean.getPrice());
            }
            if (!TextUtils.isEmpty(productBean.getShort_description())) {
                particlar_maioshu.setVisibility(View.VISIBLE);
                particlar_maioshu.setText(productBean.getShort_description());
            } else {
                particlar_maioshu.setLines(2);
                particlar_maioshu.setVisibility(View.GONE);
            }

            List<ParticularsBean.DataBean.ProductBean.ProductAttrsBean> productAttrsBeanList =
                    new ArrayList<>();
            productAttrsBeanList.addAll(productBean.getProductAttrs());
            if (productAttrsBeanList.size() <= 0) {
                relativeLayout.setVisibility(View.GONE);
            } else if (productAttrsBeanList.size() == 2) {
                //relativeLayout.setVisibility(View.VISIBLE);
                if (productAttrsBeanList.get(0).getItem_id()==1&&productAttrsBeanList.get(1).getItem_id()==2){
                    jiatang.setVisibility(View.GONE);
                    jialiao.setVisibility(View.VISIBLE);
                    wendu.setVisibility(View.VISIBLE);
                }else
                if (productAttrsBeanList.get(0).getItem_id()==2&&productAttrsBeanList.get(1).getItem_id()==3){
                    jiatang.setVisibility(View.VISIBLE);
                    jialiao.setVisibility(View.GONE);
                    wendu.setVisibility(View.VISIBLE);
                }else
                if (productAttrsBeanList.get(0).getItem_id()==1&&productAttrsBeanList.get(1).getItem_id()==3){
                    jiatang.setVisibility(View.VISIBLE);
                    jialiao.setVisibility(View.VISIBLE);
                    wendu.setVisibility(View.GONE);
                }

            }else if(productAttrsBeanList.size()==3){
                relativeLayout.setVisibility(View.VISIBLE);
                jiatang.setVisibility(View.VISIBLE);
                jialiao.setVisibility(View.VISIBLE);
                wendu.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void onClick(View v) {


    }


}
