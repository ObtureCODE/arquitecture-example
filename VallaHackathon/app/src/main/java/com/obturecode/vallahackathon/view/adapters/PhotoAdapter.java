package com.obturecode.vallahackathon.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.obturecode.vallahackathon.R;
import com.obturecode.vallahackathon.domain.entity.Photo;
import com.obturecode.vallahackathon.view.viewcells.PhotoCell;

import java.util.ArrayList;

/**
 * Created by Cris on 2/3/15.
 */
public class PhotoAdapter extends ArrayAdapter<Photo> {

    private static final int LAYOUT_RESOURCE = R.layout.cell_photo;

    public PhotoAdapter(Context context){
        super(context, LAYOUT_RESOURCE);
    }

    public void addAll(ArrayList<Photo> listPhotos){
        setNotifyOnChange(false);
        for(Photo photo : listPhotos){
            add(photo);
        }
        notifyDataSetChanged();
    }

    public View getView(int position, View convertView, ViewGroup parent){
        PhotoCell photoCell = null;
        if(convertView == null){
            photoCell = new PhotoCell(parent.getContext());
        }else{
            photoCell = (PhotoCell) convertView;
        }

        photoCell.fillData(getItem(position));
        return  photoCell;
    }
}
