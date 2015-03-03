package com.obturecode.vallahackathon.domain;

import com.obturecode.vallahackathon.data.error.ApiError;
import com.obturecode.vallahackathon.data.error.ParserError;
import com.obturecode.vallahackathon.data.error.ResponseError;
import com.obturecode.vallahackathon.data.getExifPhoto;
import com.obturecode.vallahackathon.domain.entity.Exif;
import com.obturecode.vallahackathon.domain.entity.Photo;
import com.obturecode.vallahackathon.domain.error.GenericError;
import com.obturecode.vallahackathon.domain.error.InternetError;
import com.obturecode.vallahackathon.domain.error.PermissionError;

/**
 * Created by husky on 02/03/15.
 */
public class GetInfoPhoto {

    public interface GetInfoPhotoDelegate{
        public void GetInfoPhotoResult(Photo photo);
        public void GetInfoPhotoError(Error e);
    }

    private GetInfoPhotoDelegate delegate;
    private getExifPhoto data;
    private Photo photo;

    public void get(Photo photo, GetInfoPhotoDelegate delegate){
        this.delegate = delegate;
        this.photo = photo;
        data = new getExifPhoto();

        data.get(photo,new getExifPhoto.getExifPhotoDelegate(){
                    @Override
                    public void exifPhotoResult(Exif exif) {
                        GetInfoPhoto.this.photo.setExif(exif);
                        GetInfoPhoto.this.delegate.GetInfoPhotoResult(GetInfoPhoto.this.photo);
                    }

                    @Override
                    public void exifPhotoError(Error e) {
                        if(e instanceof ParserError || e instanceof ResponseError)
                            GetInfoPhoto.this.delegate.GetInfoPhotoError(new GenericError());
                        else if(e instanceof ApiError)
                            GetInfoPhoto.this.delegate.GetInfoPhotoError(new PermissionError());
                        else
                            GetInfoPhoto.this.delegate.GetInfoPhotoError(new InternetError());
                    }
                }
        );

    }
    public void cancel(){
        data.cancel();
        data= null;
    }
}


