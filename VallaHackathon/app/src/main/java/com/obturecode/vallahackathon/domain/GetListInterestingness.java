package com.obturecode.vallahackathon.domain;

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

    public void get(GetListInterestingnessDelegate delegate){
        this.delegate = delegate;

    }
    public void cancel(){}
}
