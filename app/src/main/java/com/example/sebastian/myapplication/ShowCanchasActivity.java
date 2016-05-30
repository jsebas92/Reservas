package com.example.sebastian.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebastian on 30/05/16.
 */
public class ShowCanchasActivity extends Activity {

    private static final String TAG = ShowCanchasActivity.class.getSimpleName();


    private ProgressDialog pDialog;
    private List<Cancha> canchaList = new ArrayList<Cancha>();
    private ListView listView;
    private AdapterCancha adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cancha);

        Bundle bundle= getIntent().getExtras();
        String id = bundle.getString("idcomplejo");
        String url = "http://webserviceotro.azurewebsites.net/obtener_canchasbyid.php?idcanchas=";

        url= url+id+".php";
        Log.e("Url"," "+url);


        listView = (ListView) findViewById(R.id.list_cancha);
        adapter = new AdapterCancha(this, canchaList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();




        // Creating volley request obj

        JsonArrayRequest canchaReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();




                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Cancha cancha = new Cancha();

                                cancha.setIdCancha(obj.getInt("idcanchas"));
                                cancha.setPrecio(obj.getInt("precio"));

                                cancha.setDescripcion(obj.getString("descripcion"));

                                // Genre is json array


                                // adding movie to movies array
                                canchaList.add(cancha);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(canchaReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
