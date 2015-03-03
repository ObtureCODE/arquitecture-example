package com.obturecode.vallahackathon.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.obturecode.vallahackathon.MyApplication;
import com.obturecode.vallahackathon.R;
import com.obturecode.vallahackathon.domain.GetInfoPhoto;
import com.obturecode.vallahackathon.domain.entity.Photo;

/**
 * Created by Cris on 2/3/15.
 */
public class DetailActivity extends BaseActivity implements GetInfoPhoto.GetInfoPhotoDelegate {

    public static final String PARAM_PHOTO_ENTITY = "photo";

    private Photo photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        processParameters();
        getPhotoInfo();
    }

    private void processParameters(){
        this.photo = (Photo) getIntent().getSerializableExtra(PARAM_PHOTO_ENTITY);
    }

    private void getPhotoInfo(){
        showSpinner();
        GetInfoPhoto modelGetInfoPhoto = new GetInfoPhoto();
        modelGetInfoPhoto.get(photo, this);
    }

    private void fillView(Photo photo){

        ImageView image = (ImageView) findViewById(R.id.activity_detailImage);
        ImageLoader iLoader = ImageLoader.getInstance();
        DisplayImageOptions imageOptions = MyApplication.getDefaultImageOptions();
        iLoader.displayImage(photo.getUrl(), image, imageOptions);

        ((TextView)findViewById(R.id.activity_detailTextISO)).setText(String.format(getString(R.string.text_ISO), photo.getExif().getIso()));
        ((TextView)findViewById(R.id.activity_detailTextFocalLenght)).setText(photo.getExif().getFocalLength());
        ((TextView)findViewById(R.id.activity_detailTextAperture)).setText(String.format(getString(R.string.text_aperture), photo.getExif().getAperture()));
        ((TextView)findViewById(R.id.activity_detailTextMain)).setText(String.format(getString(R.string.text_main_photo), photo.getTitle(), photo.getOwnerName()));
        if(photo.getExif().getCamera() != null)
            ((TextView)findViewById(R.id.activity_detailTextCamera)).setText(String.format(getString(R.string.text_camera), photo.getExif().getCamera()));
        if(photo.getExif().getLens() != null)
            ((TextView)findViewById(R.id.activity_detailTextPhotoLens)).setText(String.format(getString(R.string.text_lens), photo.getExif().getLens()));
    }

    @Override
    public void GetInfoPhotoResult(Photo photo) {
        hideSpinner();
        fillView(photo);
    }

    @Override
    public void GetInfoPhotoError(Error e) {
        hideSpinner();

    }
}
