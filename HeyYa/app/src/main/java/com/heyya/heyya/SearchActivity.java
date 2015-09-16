package com.heyya.heyya;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

/**
 * Created by Unreal_KZ on 28.05.2015.
 */
public class SearchActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener{

    private static SeekBar seek_bar;
    private static TextView txtView;
    private int progress_value = 16;
    private int initialProgressNumber = 0;
    private GoogleMap mMap;
    Spinner mapTypeSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);

        seek_bar = (SeekBar) findViewById(R.id.seekBar);
        //txtView = (TextView) findViewById(R.id.txtViewProgressId);
        mapTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.mapTypes, android.R.layout.simple_spinner_item);
        mapTypeSpinner.setAdapter(adapter);
        mapTypeSpinner.setOnItemSelectedListener(this);

        try {
            setUpMapIfNeeded();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        //Intent goToMainActivity = getIntent();
    }

    private void setUpMapIfNeeded() {
            if (mMap == null){
                //mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
                mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
                if (mMap != null){
                    setUpMap();
                }
            }
    }

    private void setUpMap() {
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locManager.getBestProvider(criteria, true);
        Location myLocation = locManager.getLastKnownLocation(provider);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double myLatitude = myLocation.getLatitude();
        double myLongitude = myLocation.getLongitude();
        LatLng myLatLongPos = new LatLng(myLatitude,myLongitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLatLongPos));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(progress_value));
        //txtView.setText("Progress: " + seek_bar.getProgress());
        initialProgressNumber = seek_bar.getProgress();
        seek_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //int ZoomingNumber = 0;
                        if (seekBar.getProgress() > initialProgressNumber) {
                            progress_value += (seekBar.getProgress()-initialProgressNumber);
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(progress_value));
                            initialProgressNumber = seekBar.getProgress();
                        } else {
                            progress_value -= (initialProgressNumber-seekBar.getProgress());
                            mMap.animateCamera(CameraUpdateFactory.zoomTo(progress_value));
                            initialProgressNumber = seekBar.getProgress();
                        }
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );


        mMap.addMarker(new MarkerOptions().position(new LatLng(myLatitude, myLongitude)).title("You are here!"));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        TextView selectedText = (TextView) view;
        switch (selectedText.getText().toString()){
            case "MAP_TYPE_HYBRID":
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            case "MAP_TYPE_NONE":
                mMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                break;
            case "MAP_TYPE_NORMAL":
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case "MAP_TYPE_SATELLITE":
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case "MAP_TYPE_TERRAIN":
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            default:
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
