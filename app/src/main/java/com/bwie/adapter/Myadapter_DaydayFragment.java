package com.bwie.adapter;

import android.graphics.Paint;

import android.text.TextUtils;

import android.view.View;


import android.view.ViewGroup;
import android.widget.BaseAdapter;


import android.widget.ImageView;


import android.widget.TextView;


import com.bwie.bean.DaydayBean;


import com.bwie.tea_people.MyApp;

import com.bwie.tea_people.R;
import com.bwie.util.ImageLoaderUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;


import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/10 10:47
 * 邮箱:18310832074@163.com
 */

public
class
Myadapter_DaydayFragment extends BaseAdapter {

    private List<DaydayBean.DataBean.ProductsBean> productsBeanList;
    private DisplayImageOptions options;

    public Myadapter_DaydayFragment(List<DaydayBean.DataBean.ProductsBean> productsBeanList) {
        this.productsBeanList = productsBeanList;
        options = ImageLoaderUtils.initOptions();
    }

    @Override
    public int getCount() {
        return productsBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsBeanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = View.inflate(MyApp.context, R.layout.every_day_list_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.every_day_list_item_image);
            viewHolder.every_day_list_item_title = (TextView) convertView.findViewById(R.id.every_day_list_item_title);
            viewHolder.every_day_list_item_price_difference = (TextView) convertView.findViewById(R.id.every_day_list_item_price_difference);
            viewHolder.every_day_list_item_featured_price = (TextView) convertView.findViewById(R.id.every_day_list_item_featured_price);
            viewHolder.every_day_list_item_price = (TextView) convertView.findViewById(R.id.every_day_list_item_price);
            viewHolder.every_day_list_item_describe = (TextView) convertView.findViewById(R.id.every_day_list_item_describe);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (!TextUtils.isEmpty(productsBeanList.get(position).getShort_description())) {
            viewHolder.every_day_list_item_describe.setVisibility(View.VISIBLE);
            viewHolder.every_day_list_item_describe.setText(productsBeanList.get(position).getShort_description());
            viewHolder.every_day_list_item_describe.setEllipsize(TextUtils.TruncateAt.END);
        } else {
            viewHolder.every_day_list_item_describe.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(productsBeanList.get(position).getFeatured_price())) {
            viewHolder.every_day_list_item_featured_price.setText("￥"+productsBeanList.get(position).getFeatured_price());
            viewHolder.every_day_list_item_price.setText("￥"+productsBeanList.get(position).getPrice());
            viewHolder.every_day_list_item_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            viewHolder.every_day_list_item_price.setVisibility(View.VISIBLE);
            viewHolder.every_day_list_item_price_difference.setVisibility(View.VISIBLE);
            viewHolder.every_day_list_item_price_difference.setText("  立减￥"+pricedifference(productsBeanList.get(position).getPrice(),productsBeanList.get(position).getFeatured_price())+"0  ");
        }else {

            viewHolder.every_day_list_item_price.setVisibility(View.GONE);
            viewHolder.every_day_list_item_price_difference.setVisibility(View.GONE);
            viewHolder.every_day_list_item_featured_price.setText("￥"+productsBeanList.get(position).getPrice());
        }

        viewHolder.every_day_list_item_title.setText(productsBeanList.get(position).getName());
        ImageLoader.getInstance().displayImage(productsBeanList.get(position).getImage_small(),viewHolder.imageView,options);

        return convertView;
    }
    //求价格差
    private double pricedifference(String price, String featured_price) {

        if(!TextUtils.isEmpty(featured_price)){

            double i = Double.parseDouble(price);
            double i2 = Double.parseDouble(featured_price);
            return i-i2;
        }
        return 0;
    }
    class ViewHolder {
        ImageView imageView;
        TextView every_day_list_item_title, every_day_list_item_describe,
                every_day_list_item_featured_price, every_day_list_item_price,
                every_day_list_item_price_difference;
    }

}
