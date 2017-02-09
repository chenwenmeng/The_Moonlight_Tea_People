package com.bwie.fragment;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.ListView;


import com.bwie.bean.Bean;
import com.bwie.tea_people.R;


import com.bwie.util.Analysis_Json_Util;


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

    }

    //接受解析数据
    class MyAnalysis extends Analysis_Json_Util {

        @Override
        public void analysis_Json(String s) {
            //Gson解析数据
            Bean bean = new Gson().fromJson(s, Bean.class);
            categoriesBeanList.addAll(bean.getData().getCategories());


        }
    }
}
