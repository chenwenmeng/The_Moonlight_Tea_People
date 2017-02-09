package com.bwie.fragment;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import android.widget.AdapterView;
import android.widget.ListView;


import android.widget.Toast;


import com.bwie.adapter.Myadapter_HomeFragment_left_list;

import com.bwie.adapter.Myadapter_homeFragment_right_list;
import com.bwie.bean.Bean;

import com.bwie.tea_people.MyApp;
import com.bwie.tea_people.R;


import com.bwie.util.Analysis_Json_Util;



import com.bwie.util.PathUtil;
import com.google.gson.Gson;


import java.util.ArrayList;

import java.util.List;

/**
 * 类描述:首页
 * 作者：陈文梦
 * 时间:2017/2/8 21:42
 * 邮箱:18310832074@163.com
 */

public
class
Home_Fragment extends Fragment {


    private View view;
    private ListView rightListView;
    private List<Bean.DataBean.CategoriesBean> categoriesBeanList = new ArrayList<>();
    private ListView leftListView;
    private Myadapter_HomeFragment_left_list myadapter_homeFragment_left_list;
    private Myadapter_homeFragment_right_list myadapter_homeFragment_right_list;
    private List<Bean.DataBean.CategoriesBean.ProductsBean> productsBeanList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rightListView = (ListView) view.findViewById(R.id.home_fragment_right_list);
        leftListView = (ListView) view.findViewById(R.id.home_fragment_left_list);
        MyAnalysis myAnalysis=new MyAnalysis();
        myAnalysis.getdate(PathUtil.homepath);
        //点击按钮操作数据
        leftListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyApp.num=position;
                myadapter_homeFragment_left_list.notifyDataSetChanged();
                productsBeanList.clear();
                productsBeanList.addAll(categoriesBeanList.get(position).getProducts());
                myadapter_homeFragment_right_list.notifyDataSetChanged();
            }
        });

    }

    //接受解析数据
    class MyAnalysis extends Analysis_Json_Util {

        @Override
        public void analysis_Json(String s) {
            //Gson解析数据
            Bean bean = new Gson().fromJson(s, Bean.class);
            categoriesBeanList.addAll(bean.getData().getCategories());
            myadapter_homeFragment_left_list = new Myadapter_HomeFragment_left_list(categoriesBeanList,getActivity());
            leftListView.setAdapter(myadapter_homeFragment_left_list);
            productsBeanList.addAll(categoriesBeanList.get(0).getProducts());
            myadapter_homeFragment_right_list = new Myadapter_homeFragment_right_list(productsBeanList);
            rightListView.setAdapter(myadapter_homeFragment_right_list);
        }
    }
}
