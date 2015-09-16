package com.heyya.heyya;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Unreal_KZ on 29.05.2015.
 */
public class ContactActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_layout);

        String[] contacts = {"Ainur","Asel","Baurzhan","Zhandos","Baubek","Bota","Aset"};

        ListAdapter theAdapter = new MyAdapter(this, contacts);
        ListView theListView = (ListView) findViewById(R.id.contactListView);

        theListView.setAdapter(theAdapter);

        theListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contactPiked = "You selected " +
                        String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(ContactActivity.this, contactPiked, Toast.LENGTH_SHORT).show();

            }
        });

        /*Intent goToMainActivity = getIntent();
        String messageFromMain = goToMainActivity.getExtras().getString("MainActivity");
        TextView myTxtView = (TextView) findViewById(R.id.txtViewSId);
        myTxtView.append("\n" + messageFromMain);*/

    }
}
