package com.bwie.adapter;

import android.content.Context;

import android.view.View;


import android.view.ViewGroup;
import android.widget.BaseAdapter;


import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bwie.bean.Bean;


import com.bwie.tea_people.MyApp;
import com.bwie.tea_people.R;


import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/9 11:21
 * 邮箱:18310832074@163.com
 */

public
class
Myadapter_HomeFragment_left_list extends BaseAdapter {

    private List<Bean.DataBean.CategoriesBean> categoriesBeanList;
    private Context context;

    public Myadapter_HomeFragment_left_list(List<Bean.DataBean.CategoriesBean> categoriesBeanList, Context context) {
        this.categoriesBeanList = categoriesBeanList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoriesBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoriesBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder v = null;
        if (convertView == null) {
            v = new ViewHolder();
            convertView = View.inflate(context, R.layout.home_fragment_left_list_item, null);
            v.textView = (TextView) convertView.findViewById(R.id.home_fragment_left_item_text);
            v.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.r);
            convertView.setTag(v);
        } else {
            v = (ViewHolder) convertView.getTag();
        }
        if (MyApp.num == position) {
            v.relativeLayout.setBackgroundResource(R.color.home_fragment_left_list_item_bc);
        } else {
            v.relativeLayout.setBackgroundResource(R.color.home_fragment_left_list);
        }
        v.textView.setText(categoriesBeanList.get(position).getName());
        return convertView;
    }

    class ViewHolder {
        RelativeLayout relativeLayout;
        TextView textView;
    }
}
