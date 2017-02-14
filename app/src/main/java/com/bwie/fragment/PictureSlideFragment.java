package com.bwie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.bwie.tea_people.R;
import com.lidroid.xutils.BitmapUtils;


import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by Administrator on 2016/1/3.
 */
public class PictureSlideFragment extends Fragment {
    private String url;
    private PhotoView imageView;

    public static PictureSlideFragment newInstance(String url) {
        PictureSlideFragment f = new PictureSlideFragment();
        Bundle args = new Bundle();
        args.putString("url", url);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = getArguments() != null ? getArguments().getString("url") : "http://www.zhagame.com/wp-content/uploads/2016/01/JarvanIV_6.jpg";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment,container,false);
        imageView= (PhotoView) v.findViewById(R.id.iv_main_pic);
        new BitmapUtils(getActivity()).display(imageView,url);
        return v;
    }
}
