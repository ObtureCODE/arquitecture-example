package com.obturecode.vallahackathon.data;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.obturecode.vallahackathon.MyApplication;
import com.obturecode.vallahackathon.data.error.InternetError;
import com.obturecode.vallahackathon.data.error.ResponseError;
import com.obturecode.vallahackathon.data.parser.AsyncInterestingnessPhotosParser;
import com.obturecode.vallahackathon.domain.entity.Photo;
import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class getInterestingnessPhotos {

    public interface getInterestingnessPhotosDelegate{
        public void interestingnessPhotosResult(ArrayList<Photo> listPhotos);
        public void interestingnessPhotosError(Error e);
    }

    private static final String TAG = "InterestingnessPhotos";
    private RequestQueue queue;
    private getInterestingnessPhotosDelegate delegate;
    private AsyncInterestingnessPhotosParser parser;

    public void get(getInterestingnessPhotosDelegate delegate){
        this.delegate = delegate;
        queue = Volley.newRequestQueue(MyApplication.getAppContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.getUrl(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getInterestingnessPhotos.this.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse != null) {
                            getInterestingnessPhotos.this.delegate.interestingnessPhotosError(new ResponseError(error.networkResponse.statusCode));
                        }else {
                            getInterestingnessPhotos.this.delegate.interestingnessPhotosError(new InternetError());
                        }
                    }
                }
        );
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }

    public void cancel(){
        if (queue != null) {
            queue.cancelAll(TAG);
        }
        if(parser!=null)
            parser.cancel(true);

        this.delegate = null;
    }

    private void onResponse(String response){
        parser = new AsyncInterestingnessPhotosParser(new AsyncInterestingnessPhotosParser.AsyncInterestingnessPhotosParserDelegate() {
            @Override
            public void AsyncInterestingnessPhotosParserResult(ArrayList<Photo> photos) {
                getInterestingnessPhotos.this.delegate.interestingnessPhotosResult(photos);
            }

            @Override
            public void AsyncInterestingnessPhotosParserError(Error e) {
                getInterestingnessPhotos.this.delegate.interestingnessPhotosError(e);
            }
        });
        parser.execute(response);
    }

    private String getUrl(){
        return "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&extras=owner_name%2C+description&api_key="+MyApplication.getFlickrApiKey()+"&format=rest";
    }

}


