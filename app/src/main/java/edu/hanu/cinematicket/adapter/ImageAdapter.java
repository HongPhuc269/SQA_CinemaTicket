package edu.hanu.cinematicket.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

import edu.hanu.cinematicket.R;

public class ImageAdapter extends PagerAdapter {

    private Context mContext;
    private int[] mImageIds = new int[]{ R.drawable.avenger, R.drawable.spiderman, R.drawable.venom};
    //    private LayoutInflater layoutInflater;
    public ImageAdapter(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageIds.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageButton) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View item = layoutInflater.inflate(R.layout.activity_main,container,false);
        ImageButton imageButton = new ImageButton(mContext);
        imageButton.setScaleType(ImageButton.ScaleType.CENTER_CROP);
        imageButton.setImageResource(mImageIds[position]);
        container.addView(imageButton, 0);
        return imageButton;
    }
}