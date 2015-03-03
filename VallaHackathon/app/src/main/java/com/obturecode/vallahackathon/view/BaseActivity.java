package com.obturecode.vallahackathon.view;

import android.support.v7.app.ActionBarActivity;
import android.view.View;

import com.obturecode.vallahackathon.R;

/**
 * Created by Cris on 2/3/15.
 */
public class BaseActivity extends ActionBarActivity {

    private View spinner;


    private void findSpinner(){
        if(spinner == null)
            spinner = findViewById(R.id.spinnerView);
    }

    protected void showSpinner(){
        findSpinner();
        spinner.setVisibility(View.VISIBLE);
    }

    protected void hideSpinner(){
        findSpinner();
        spinner.setVisibility(View.GONE);
    }

}
