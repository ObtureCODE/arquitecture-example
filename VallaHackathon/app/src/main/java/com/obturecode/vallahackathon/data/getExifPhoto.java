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
import com.obturecode.vallahackathon.data.parser.AsyncExifPhotoParser;
import com.obturecode.vallahackathon.domain.entity.Exif;
import com.obturecode.vallahackathon.domain.entity.Photo;



/**
 * Created by husky on 02/03/15.
 */
public class getExifPhoto {

    public interface getExifPhotoDelegate{
        public void exifPhotoResult(Exif exif);
        public void exifPhotoError(Error e);
    }

    private static final String TAG = "InterestingnessPhotos";
    private RequestQueue queue;
    private getExifPhotoDelegate delegate;
    private AsyncExifPhotoParser parser;

    public void get(Photo photo,getExifPhotoDelegate delegate){
        this.delegate = delegate;
        queue = Volley.newRequestQueue(MyApplication.getAppContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.getUrl(photo.getId()),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        getExifPhoto.this.onResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(error.networkResponse != null) {
                            getExifPhoto.this.delegate.exifPhotoError(new ResponseError(error.networkResponse.statusCode));
                        }else {
                            getExifPhoto.this.delegate.exifPhotoError(new InternetError());
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
        parser = new AsyncExifPhotoParser(new AsyncExifPhotoParser.AsyncExifPhotoParserDelegate() {
            @Override
            public void AsyncExifPhotoParserResult(Exif exif) {
                getExifPhoto.this.delegate.exifPhotoResult(exif);
            }

            @Override
            public void AsyncExifPhotoParserError(Error e) {
                getExifPhoto.this.delegate.exifPhotoError(e);
            }
        });

        parser.execute(response);
    }

    private String getUrl(String idPhoto){
        return "https://api.flickr.com/services/rest/?method=flickr.photos.getExif&api_key="+MyApplication.getFlickrApiKey()+"&photo_id="+idPhoto+"&format=rest";
    }
}
