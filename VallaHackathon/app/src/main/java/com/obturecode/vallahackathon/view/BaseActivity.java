package com.obturecode.vallahackathon.view;

import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Toast;

import com.obturecode.vallahackathon.R;
import com.obturecode.vallahackathon.domain.error.InternetError;
import com.obturecode.vallahackathon.domain.error.PermissionError;

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

    protected void showError(Error e){
        String errorMessage = null;
        if(e instanceof InternetError){
            errorMessage = getString(R.string.error_internet);
        }else if(e instanceof PermissionError){
            errorMessage = getString(R.string.error_permission);
        }else{
            errorMessage = getString(R.string.error_generic);
        }
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

}
