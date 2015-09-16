package com.heyya.heyya;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Unreal_KZ on 01.06.2015.
 */
public class MyAdapter extends ArrayAdapter<String> {
        public MyAdapter(Context cont, String[] values){
            super(cont,R.layout.row_layout_2,values);

        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());
        View theView = theInflater.inflate(R.layout.row_layout_2, parent, false);
        String contactShow = getItem(position);
        TextView theTxtView = (TextView) theView.findViewById(R.id.textView1);
        theTxtView.setText(contactShow);
        ImageView theImgView  = (ImageView) theView.findViewById(R.id.imgView1);
        theImgView.setImageResource(R.drawable.ic_launcher);

        return theView;
    }
}
