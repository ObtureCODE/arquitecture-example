package com.obturecode.vallahackathon.view.viewcells;

import android.content.Context;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.obturecode.vallahackathon.MyApplication;
import com.obturecode.vallahackathon.R;
import com.obturecode.vallahackathon.domain.entity.Photo;

/**
 * Created by Cris on 2/3/15.
 */
public class PhotoCell extends LinearLayout {

    private ImageView image;
    private TextView title;

    public PhotoCell(Context context){
        super(context);
        inflate(context, R.layout.cell_photo, this);
        initView();
    }

    public void initView(){
        image = (ImageView) findViewById(R.id.cell_photoImage);
        title = (TextView) findViewById(R.id.cell_photoText);
    }

    public void fillData(Photo photo){
        image.setImageBitmap(null);
        title.setText(photo.getTitle());
        ImageLoader iLoader = ImageLoader.getInstance();
        DisplayImageOptions imageOptions = MyApplication.getDefaultImageOptions();
        iLoader.displayImage(photo.getUrl(), image, imageOptions);

    }
}
