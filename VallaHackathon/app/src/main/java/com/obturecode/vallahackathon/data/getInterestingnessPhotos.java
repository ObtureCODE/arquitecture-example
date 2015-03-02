package com.obturecode.vallahackathon.data;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.obturecode.vallahackathon.MyApplication;
import com.obturecode.vallahackathon.data.parser.InterestingnessPhotosParser;
import com.obturecode.vallahackathon.domain.entity.Photo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class getInterestingnessPhotos {

    public interface getInterestingnessPhotosDelegate{
        public void InterestingnessPhotosResult(ArrayList<Photo> listPhotos);
        public void InterestingnessPhotosError();
    }

    private static final String TAG = "InterestingnessPhotos";
    private RequestQueue queue;
    private getInterestingnessPhotosDelegate delegate;

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
                        getInterestingnessPhotos.this.delegate.InterestingnessPhotosError();
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
        this.delegate = null;
    }

    private void onResponse(String response){
        new AsyncParser().execute(response);
    }

    private String getUrl(){
        return "https://api.flickr.com/services/rest/?method=flickr.interestingness.getList&api_key="+MyApplication.getFlickrApiKey()+"&format=rest";
    }

    private class AsyncParser extends AsyncTask<String, Void, ArrayList<Photo>> {
        protected ArrayList<Photo> doInBackground(String... params) {
            try {
                return InterestingnessPhotosParser.parse(params[0]);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(ArrayList<Photo> photos) {
            if(photos==null)
                getInterestingnessPhotos.this.delegate.InterestingnessPhotosError();
            else
                getInterestingnessPhotos.this.delegate.InterestingnessPhotosResult(photos);
        }
    }

}


