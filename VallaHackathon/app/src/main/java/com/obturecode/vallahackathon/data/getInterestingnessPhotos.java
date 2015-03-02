package com.obturecode.vallahackathon.data;

import com.obturecode.vallahackathon.domain.entity.Photo;

import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class getInterestingnessPhotos {
    public interface getInterestingnessPhotosDelegate{
        public void InterestingnessPhotosResult(ArrayList<Photo> listPhotos);
        public void InterestingnessPhotosError();
    }

    public void get(){}
}


