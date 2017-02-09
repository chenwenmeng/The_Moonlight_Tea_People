package com.bwie.fragment;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import com.bwie.tea_people.R;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/9 16:22
 * 邮箱:18310832074@163.com
 */

public class Dayday_fragment extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daydayfragment, null);
        return view;
    }
}
