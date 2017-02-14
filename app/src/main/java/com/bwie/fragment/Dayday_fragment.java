package com.bwie.fragment;

import android.os.Bundle;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.widget.TextView;


import com.bwie.tea_people.R;

/**
 * 类描述:优惠界面
 * 作者：陈文梦
 * 时间:2017/2/9 16:22
 * 邮箱:18310832074@163.com
 */

public
class
Dayday_fragment extends Fragment implements View.OnClickListener{

    private View view;
    private Everyday_Fragment everyday_fragment=new Everyday_Fragment();
    private For_your_selection_Fragment for_your_selection_fragment=new For_your_selection_Fragment();
    private A_favorite_of_pro_Fragment a_favorite_of_pro_fragment=new A_favorite_of_pro_Fragment();
    private TextView dayday_text1, dayday_text2, dayday_text3,
            dayday_line1, dayday_line2, dayday_line3;


    private FragmentManager manager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daydayfragment, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.dayday_text1:
                manager.beginTransaction().show(everyday_fragment)
                .hide(for_your_selection_fragment).hide(a_favorite_of_pro_fragment)
                .commit();
                dayday_text1.setTextColor(getResources().getColor(R.color.title));
                dayday_text2.setTextColor(getResources().getColor(R.color.dayday));
                dayday_text3.setTextColor(getResources().getColor(R.color.dayday));
                dayday_line1.setVisibility(View.VISIBLE);
                dayday_line2.setVisibility(View.INVISIBLE);
                dayday_line3.setVisibility(View.INVISIBLE);
                break;
            case R.id.dayday_text2:
                manager.beginTransaction().show(for_your_selection_fragment)
                        .hide(everyday_fragment).hide(a_favorite_of_pro_fragment)
                        .commit();
                dayday_text1.setTextColor(getResources().getColor(R.color.dayday));
                dayday_text2.setTextColor(getResources().getColor(R.color.title));
                dayday_text3.setTextColor(getResources().getColor(R.color.dayday));
                dayday_line1.setVisibility(View.INVISIBLE);
                dayday_line2.setVisibility(View.VISIBLE);
                dayday_line3.setVisibility(View.INVISIBLE);
                break;
            case R.id.dayday_text3:
                manager.beginTransaction().show(a_favorite_of_pro_fragment)
                        .hide(everyday_fragment).hide(for_your_selection_fragment)
                        .commit();
                dayday_text1.setTextColor(getResources().getColor(R.color.dayday));
                dayday_text2.setTextColor(getResources().getColor(R.color.dayday));
                dayday_text3.setTextColor(getResources().getColor(R.color.title));
                dayday_line1.setVisibility(View.INVISIBLE);
                dayday_line2.setVisibility(View.INVISIBLE);
                dayday_line3.setVisibility(View.VISIBLE);
                break;
        }

    }
    //初始化控件
    public void init() {

        dayday_text1 = (TextView) view.findViewById(R.id.dayday_text1);
        dayday_text2 = (TextView) view.findViewById(R.id.dayday_text2);
        dayday_text3 = (TextView) view.findViewById(R.id.dayday_text3);
        dayday_text1.setOnClickListener(this);
        dayday_text2.setOnClickListener(this);
        dayday_text3.setOnClickListener(this);
        dayday_line1 = (TextView) view.findViewById(R.id.dayday_line1);
        dayday_line2 = (TextView) view.findViewById(R.id.dayday_line2);
        dayday_line3 = (TextView) view.findViewById(R.id.dayday_line3);
        manager = getActivity().getSupportFragmentManager();
        manager.beginTransaction().add(R.id.dayday_fl,everyday_fragment)
                .add(R.id.dayday_fl,for_your_selection_fragment)
                .hide(for_your_selection_fragment)
                .add(R.id.dayday_fl,a_favorite_of_pro_fragment)
                .hide(a_favorite_of_pro_fragment)
                .show(everyday_fragment)
                .commit();
    }
}
