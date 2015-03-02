package com.obturecode.vallahackathon.domain;

import com.obturecode.vallahackathon.data.getInterestingnessPhotos;
import com.obturecode.vallahackathon.domain.entity.Photo;

import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class GetListInterestingness {
    public interface GetListInterestingnessDelegate{
        public void GetListInterestingnessResult(ArrayList<Photo> listPhotos);
        public void GetListInterestingnessError();
    }

    private GetListInterestingnessDelegate delegate;
    private getInterestingnessPhotos data;
    public void get(GetListInterestingnessDelegate delegate){
        this.delegate = delegate;
        data = new getInterestingnessPhotos();

        data.get(new getInterestingnessPhotos.getInterestingnessPhotosDelegate(){

            @Override
            public void interestingnessPhotosResult(ArrayList<Photo> listPhotos) {
                GetListInterestingness.this.delegate.GetListInterestingnessResult(listPhotos);
            }

            @Override
            public void interestingnessPhotosError() {
                GetListInterestingness.this.delegate.GetListInterestingnessError();
            }
        }
        );

    }
    public void cancel(){
        data.cancel();
        data= null;
    }
}
