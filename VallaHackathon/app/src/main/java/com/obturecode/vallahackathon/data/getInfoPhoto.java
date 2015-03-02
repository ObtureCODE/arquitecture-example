package com.obturecode.vallahackathon.data;

import android.os.AsyncTask;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.obturecode.vallahackathon.MyApplication;
import com.obturecode.vallahackathon.data.parser.InfoPhotoParser;
import com.obturecode.vallahackathon.data.parser.InterestingnessPhotosParser;
import com.obturecode.vallahackathon.domain.entity.Photo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by husky on 02/03/15.
 */
public class getInfoPhoto {

    public interface getInfoPhotosDelegate{
        public void infoPhotosResult(Photo photo);
        public void infoPhotoError();
    }

    private static final String TAG = "InterestingnessPhotos";
    private RequestQueue queue;
    private getInfoPhotosDelegate delegate;

    public void get(Photo photo,getInfoPhotosDelegate delegate){
        this.delegate = delegate;
        queue = Volley.newRequestQueue(MyApplication.getAppContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.getUrl(photo.getId()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getInfoPhoto.this.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        getInfoPhoto.this.delegate.infoPhotoError();
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

    private String getUrl(String idPhoto){
        return "https://api.flickr.com/services/rest/?method=flickr.photos.getInfo&api_key="+MyApplication.getFlickrApiKey()+"&photo_id="+idPhoto+"&format=rest";
    }

    private class AsyncParser extends AsyncTask<String, Void, Photo> {
        protected Photo doInBackground(String... params) {
            try {
                return InfoPhotoParser.parse(params[0]);
            } catch (XmlPullParserException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Photo photo) {
            if(photo==null)
                getInfoPhoto.this.delegate.infoPhotoError();
            else
                getInfoPhoto.this.delegate.infoPhotosResult(photo);
        }
    }
}
