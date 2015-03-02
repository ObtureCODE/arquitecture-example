package com.obturecode.vallahackathon.domain;

import com.obturecode.vallahackathon.data.getInfoPhoto;
import com.obturecode.vallahackathon.data.getInterestingnessPhotos;
import com.obturecode.vallahackathon.domain.entity.Photo;

import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class GetInfoPhoto {

    public interface GetInfoPhotoDelegate{
        public void GetInfoPhotoResult(Photo photo);
        public void GetInfoPhotoError();
    }

    private GetInfoPhotoDelegate delegate;
    private getInfoPhoto data;

    public void get(Photo photo, GetInfoPhotoDelegate delegate){
        this.delegate = delegate;
        data = new getInfoPhoto();

        data.get(photo,new getInfoPhoto.getInfoPhotosDelegate(){
                     @Override
                     public void infoPhotosResult(Photo photo) {
                         GetInfoPhoto.this.delegate.GetInfoPhotoResult(photo);
                     }

                     @Override
                     public void infoPhotoError() {
                        GetInfoPhoto.this.delegate.GetInfoPhotoError();
                     }
                 }
        );

    }
    public void cancel(){
        data.cancel();
        data= null;
    }
}


