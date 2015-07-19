package com.example.jeremy.naturephotographynow.activity;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.example.jeremy.naturephotographynow.R;

/**
 * Created by Jeremy on 7/18/2015.
 */
public class GalleryImageAdapter extends BaseAdapter
{
    private Context mContext;

    private Integer[] mImageIds = {
            R.drawable.naturesamplepic,
            R.drawable.sample_0,
            R.drawable.naturephotographynowwebsitepage,
            R.drawable.naturephotographynowwebsitepage2,
            R.drawable.sample_1,
            R.drawable.naturesamplepic,
            R.drawable.naturephotographynowwebsitepage,
            R.drawable.sample_3
    };

    public GalleryImageAdapter(Context context)
    {
        mContext = context;
    }

    public int getCount() {
        return mImageIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }


    // Override this method according to your need
    public View getView(int index, View view, ViewGroup viewGroup)
    {
        ImageView i = new ImageView(mContext);

        i.setImageResource(mImageIds[index]);
        i.setLayoutParams(new Gallery.LayoutParams(200, 200));

        i.setScaleType(ImageView.ScaleType.FIT_XY);

        return i;
    }
}
