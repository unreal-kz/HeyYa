package com.heyya.heyya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Unreal_KZ on 29.05.2015.
 */
public class PhotoActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_layout);
        Intent goToMainActivity = getIntent();
        String messageFromMain = goToMainActivity.getExtras().getString("MainActivity");

        TextView myTxtView = (TextView) findViewById(R.id.txtViewSId);

        myTxtView.append("\n" + messageFromMain);
    }

    public void onGoBackClicked(View view) {
        Intent goToBack = new Intent();
        finish();
    }
}
