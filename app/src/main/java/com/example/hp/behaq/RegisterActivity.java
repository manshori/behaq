package com.example.hp.behaq;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.behaq.Helper.AppConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ProgressDialog progress;
    Gson gson;
    RequestQueue requestQueue;
    EditText username, email, password, nik, first_name, last_name, tag;
    String[] tags = {"Ekonomi", "Agama", "Ilmiah", "Politik", "Hukum"};
    Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progress=new ProgressDialog(RegisterActivity.this);
        progress.setMessage("Please Wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);

        spin = (Spinner) findViewById(R.id.spinner_tags1);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,tags);
        aa.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(aa);


        Button button = (Button)findViewById(R.id.buttonRegister);
        username = (EditText)findViewById(R.id.editTextUsernameRegister);
        email = (EditText)findViewById(R.id.editTextEmailRegister);
        password = (EditText)findViewById(R.id.editTextPasswordRegister);
        first_name = (EditText) findViewById(R.id.editTextFirstNameRegister);
        last_name = (EditText) findViewById(R.id.editTextLastNameRegister);
        nik = (EditText) findViewById(R.id.editTextNikRegister);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.show();
                Signup();
            }
        });

        findViewById(R.id.textViewLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }

    public void Signup(){
        requestQueue = Volley.newRequestQueue(RegisterActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.register;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        Log.d("Response", response);
                        ResponSignUp posts = new ResponSignUp();
                        try {
                            posts = gson.fromJson(response, ResponSignUp.class);
                        }catch (Exception e){
                            Log.d("lihat respon", "Respon gagal");
                        }
                        if(posts.isStatus() == true){
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(RegisterActivity.this,"Tidak bisa", Toast.LENGTH_SHORT).show();
                            Log.d("Response :",response);
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("nik", nik.getText().toString());
                params.put("username", username.getText().toString());
                params.put("email", email.getText().toString());
                params.put("first_name", first_name.getText().toString());
                params.put("last_name", last_name.getText().toString());
                params.put("tags", (String)spin.getSelectedItem());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        requestQueue.add(postRequest);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), tags[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class ResponSignUp{
        private boolean status;
        private String data;

        public ResponSignUp(boolean status, String data) {
            this.status = status;
            this.data = data;
        }

        public ResponSignUp() {

        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
