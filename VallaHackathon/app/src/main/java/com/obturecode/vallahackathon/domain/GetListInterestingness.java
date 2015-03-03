package com.obturecode.vallahackathon.domain;

import com.obturecode.vallahackathon.data.error.ParserError;
import com.obturecode.vallahackathon.data.error.ResponseError;
import com.obturecode.vallahackathon.data.getInterestingnessPhotos;
import com.obturecode.vallahackathon.domain.entity.Photo;
import com.obturecode.vallahackathon.domain.error.GenericError;
import com.obturecode.vallahackathon.domain.error.InternetError;

import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class GetListInterestingness {
    public interface GetListInterestingnessDelegate{
        public void GetListInterestingnessResult(ArrayList<Photo> listPhotos);
        public void GetListInterestingnessError(Error e);
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
            public void interestingnessPhotosError(Error e) {
                if(e instanceof ParserError || e instanceof ResponseError)
                    GetListInterestingness.this.delegate.GetListInterestingnessError(new GenericError());
                else
                    GetListInterestingness.this.delegate.GetListInterestingnessError(new InternetError());
            }
        }
        );

    }
    public void cancel(){
        data.cancel();
        data= null;
    }
}
