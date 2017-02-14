package com.bwie.fragment;

import android.content.Intent;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.AdapterView;
import android.widget.ListView;


import com.bwie.adapter.Myadapter_DaydayFragment;
import com.bwie.bean.DaydayBean;

import com.bwie.tea_people.ParticularsActivity;
import com.bwie.tea_people.R;


import com.bwie.util.Analysis_Json_Util;
import com.bwie.util.PathUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述:天天优惠
 * 作者：陈文梦
 * 时间:2017/2/10 09:19
 * 邮箱:18310832074@163.com
 */

public
class
Everyday_Fragment extends Fragment {

    private View view;


    private ListView listView;


    List<DaydayBean.DataBean.ProductsBean> productsBeanlist = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.every_day_the_preferential_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) view.findViewById(R.id.every_day_list);
        MyJson myJson = new MyJson();
        myJson.getdate(PathUtil.everypath);

    }

    public void getdate1(String s) {
        if (!TextUtils.isEmpty(s)) {
            DaydayBean daydayBean = new Gson().fromJson(s, DaydayBean.class);
            productsBeanlist.addAll(daydayBean.getData().getProducts());
            Myadapter_DaydayFragment myadapter_daydayFragment = new Myadapter_DaydayFragment(productsBeanlist);
            listView.setAdapter(myadapter_daydayFragment);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ParticularsActivity.class);
                intent.putExtra("id", productsBeanlist.get(position).getId());
                startActivity(intent);
            }
        });
    }

    class MyJson extends Analysis_Json_Util {

        @Override
        public void analysis_Json(String s) {
            getdate1(s);
        }
    }

}
