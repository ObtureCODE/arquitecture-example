package com.obturecode.vallahackathon.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.obturecode.vallahackathon.R;
import com.obturecode.vallahackathon.domain.GetListInterestingness;
import com.obturecode.vallahackathon.domain.entity.Photo;
import com.obturecode.vallahackathon.view.adapters.PhotoAdapter;

import java.util.ArrayList;


public class ListActivity extends BaseActivity implements GetListInterestingness.GetListInterestingnessDelegate, AdapterView.OnItemClickListener {

    PhotoAdapter photoAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        initAdapter();
        initView();
        getData();
    }

    private void initView(){
        listView = (ListView) findViewById(R.id.activity_listList);
        listView.setOnItemClickListener(this);
        listView.setAdapter(photoAdapter);
    }

    private void initAdapter(){
        photoAdapter = new PhotoAdapter(this);
    }

    private void getData(){
        showSpinner();
        GetListInterestingness modelGetListInterestingness = new GetListInterestingness();
        modelGetListInterestingness.get(this);
    }

    public void GetListInterestingnessResult(ArrayList<Photo> listPhotos){
        hideSpinner();
        photoAdapter.addAll(listPhotos);
    }

    public void GetListInterestingnessError(Error error){
        hideSpinner();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detailActivityIntent = new Intent(this, DetailActivity.class);
        detailActivityIntent.putExtra(DetailActivity.PARAM_PHOTO_ENTITY, photoAdapter.getItem(position));
        startActivity(detailActivityIntent);
    }
}
