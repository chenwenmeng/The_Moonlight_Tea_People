package com.bwie.adapter;

import android.graphics.Paint;


import android.text.TextUtils;
import android.view.View;


import android.view.ViewGroup;
import android.widget.BaseAdapter;


import android.widget.ImageView;


import android.widget.TextView;


import com.bwie.bean.Bean;


import com.bwie.tea_people.MyApp;


import com.bwie.tea_people.R;



import com.bwie.util.ImageLoaderUtils;
import com.lidroid.xutils.BitmapUtils;


import com.nostra13.universalimageloader.core.DisplayImageOptions;


import com.nostra13.universalimageloader.core.ImageLoader;


import java.util.List;

/**
 * 类描述:
 * 作者：陈文梦
 * 时间:2017/2/9 19:04
 * 邮箱:18310832074@163.com
 */

public
class
Myadapter_homeFragment_right_list extends BaseAdapter {

    private List<Bean.DataBean.CategoriesBean.ProductsBean> productsBeanList;
    private DisplayImageOptions displayImageOptions;
    public Myadapter_homeFragment_right_list(List<Bean.DataBean.CategoriesBean.ProductsBean> productsBeanList) {
        this.productsBeanList = productsBeanList;
        displayImageOptions= ImageLoaderUtils.initOptions();
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
        ViewHolder v = null;
        if (convertView == null) {

            v = new ViewHolder();
            convertView = View.inflate(MyApp.context, R.layout.home_fragment_right_list_item, null);
            v.imageView = (ImageView) convertView.findViewById(R.id.right_list_item_image);
            v.right_list_item_title = (TextView) convertView.findViewById(R.id.right_list_item_title);
            v.right_list_item_price = (TextView) convertView.findViewById(R.id.right_list_item_price);
            v.right_list_item_featured_price = (TextView) convertView.findViewById(R.id.right_list_item_featured_price);
            v.price_difference = (TextView) convertView.findViewById(R.id.price_difference);
            convertView.setTag(v);
        } else {

            v = (ViewHolder) convertView.getTag();
        }

        if (!TextUtils.isEmpty(productsBeanList.get(position).getFeatured_price())) {
            v.price_difference.setVisibility(View.VISIBLE);
            v.right_list_item_featured_price.setVisibility(View.VISIBLE);
            v.right_list_item_featured_price.setText("￥"+productsBeanList.get(position).getFeatured_price());
            v.right_list_item_featured_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //v.price_difference.setText("立减￥"+pricedifference(productsBeanList.get(position).getPrice(),productsBeanList.get(position).getFeatured_price()));

        } else {
            v.price_difference.setVisibility(View.GONE);
            v.right_list_item_featured_price.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(productsBeanList.get(position).getImage_small(),v.imageView,displayImageOptions);
        v.right_list_item_price.setText("￥"+productsBeanList.get(position).getPrice());
        v.right_list_item_title.setText(productsBeanList.get(position).getName());
        return convertView;
    }
    //求价格差
    private int pricedifference(String price, String featured_price) {

        if(!TextUtils.isEmpty(featured_price)){

            int i = Integer.valueOf(price).intValue();
            int i2 = Integer.valueOf(featured_price).intValue();
            return i-i2;
        }
        return 0;
    }
    class ViewHolder {

        ImageView imageView;
        TextView right_list_item_title, right_list_item_price,
                right_list_item_featured_price, price_difference;

    }
}
