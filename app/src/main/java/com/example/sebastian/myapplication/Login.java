package com.example.sebastian.myapplication;

/**
 * Created by Edwin Garcia on 30/05/2016.
 */

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button btnLogin;
    EditText username;
    EditText passwd;
    JsonObjectRequest array;
    RequestQueue mRequestQueue;
    SharedPreferences spPersonalData;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        username = (EditText) findViewById(R.id.username);
        passwd = (EditText) findViewById(R.id.pasword);
        btnLogin = (Button) findViewById(R.id.btnlogin);

        SharedPreferences sp = getSharedPreferences("spPersonalData", MODE_PRIVATE);
        if (sp.getBoolean("KeepMeSigned", false)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRequestQueue = VolleySingleton.getInstance().getmRequestQueue();
                if (username.getText().toString().trim().length() > 0 && passwd.getText().toString().trim().length() > 0) {
                    String url = "http://webserviceotro.azurewebsites.net/obtener_usuario.php?email="+String.valueOf(username.getText());

                    JsonArrayRequest Loginreq = new JsonArrayRequest(url,
                            new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {

                                    // Parsing json
                                    for (int i = 0; i < response.length(); i++) {
                                        try {

                                            JSONObject obj = response.getJSONObject(i);
                                            try {
                                                if (obj.getString("mensaje") != "") {

                                                    Toast.makeText(getApplicationContext(), "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();

                                                }

                                            } catch (Exception e) {
                                                Log.d("ERROR", "error");
                                            }
                                            if (obj.getString("pass") != "") {
                                                if (obj.getString("pass").equals(passwd.getText().toString())) {

                                                    CheckBox chckBxKeppMeSigned = (CheckBox) findViewById(R.id.chckBxKeppMeSigned);
                                                    boolean KeepMeSigned = chckBxKeppMeSigned.isChecked();

                                                    SharedPreferences sp = getSharedPreferences("spPersonalData", MODE_PRIVATE);
                                                    SharedPreferences.Editor editor = sp.edit();
                                                    editor.putBoolean("KeepMeSigned",KeepMeSigned);
                                                    editor.putString("Username", String.valueOf(username.getText()));
                                                    editor.putString("Password", String.valueOf(passwd.getText()));
                                                    editor.apply();
                                                    Intent intento = new Intent (Login.this, MainActivity.class);
                                                    startActivity(intento);
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Usuario o clave incorrectos1", Toast.LENGTH_SHORT).show();
                                                }

                                            }


                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }


                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    });

                    // Adding request to request queue
                    AppController.getInstance().addToRequestQueue(Loginreq);

                } else {
                    Toast.makeText(getApplicationContext(), "Campos Vacios", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void Signup(View v) {
        Intent intento = new Intent(Login.this, MainActivity.class);
        startActivity(intento);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sebastian.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.sebastian.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
