package com.star.demo110101;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter<String> adapter;
    ArrayList<String> mylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(adapter);
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        final StringRequest request = new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
//                        try {
//                            JSONArray array = new JSONArray(response);
//                            int i;
//                            for (i = 0; i < array.length(); i++)
//                            {
//                                JSONObject obj = array.getJSONObject(i);
//                                Log.d("NET", i + ":" + obj.getString("district"));
//                                mylist.add(obj.getString("district"));
//                            }
//                            Log.d("NET", String.valueOf(mylist.size()));
//                            adapter.notifyDataSetChanged();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
                        Gson gson = new Gson();
                        AnimalHome[] ah = gson.fromJson(response, AnimalHome[].class);
                        for (int i=0;i<ah.length;i++)
                        {
                            mylist.add(ah[i].district);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(request);
        queue.start();

    }
}

