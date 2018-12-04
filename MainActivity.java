package com.casper.memorableplacesversion20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    static ArrayList<String> places = new ArrayList<String>();
    static ArrayAdapter<String> arrayAdapter;
    static ArrayList<LatLng> latLngArrayList = new ArrayList<LatLng>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        places.add("Add a new Place ...");
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, places);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
        
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                if (position != 0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setIcon(R.drawable.common_full_open_on_phone)
                            .setTitle("Delete?")
                            .setMessage("Do you really wanna delete it?")
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    places.remove(position);
                                    arrayAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("NO", null)
                            .show();
                }

                return true;
            }
        });


    }
}
