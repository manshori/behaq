package com.example.hp.behaq;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.hp.behaq.Helper.AppConfig;
import com.example.hp.behaq.Helper.SharedPref;
import com.example.hp.behaq.Modal.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    ProgressBar progressBar;
    RequestQueue requestQueue;
    Button mButtonLogin;
    ProgressDialog progress;
    Gson gson;
    SharedPref sharedPref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPref = new SharedPref(this);

        String uname = sharedPref.loadData("username");
        String password = sharedPref.loadData("password");
        if(!uname.equalsIgnoreCase("")){
            startActivity(new Intent(this, MainActivity.class));
        }

        progressBar = (ProgressBar) findViewById(R.id.progressBarLogin);
        editTextUsername = (EditText) findViewById(R.id.editTextUsernameLogin);
        editTextPassword = (EditText) findViewById(R.id.editTextPasswordLogin);

        progress=new ProgressDialog(LoginActivity.this);
        progress.setMessage("Mohon Bersabar...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.setCanceledOnTouchOutside(false);


        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                progress.show();
                userLogin();
            }
        });

        //TODO menyelesaikan login ke API
        //if user presses on not registered
        findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //open register screen
                finish();
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });

        //if everything is fine
    }

    private void userLogin() {
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        //validating inputs
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Mohon masukkan username");
            editTextUsername.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Mohon masukkan password");
            editTextPassword.requestFocus();
            return;
        }

        requestQueue = Volley.newRequestQueue(LoginActivity.this);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();

        String url = AppConfig.login;
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        progress.hide();
                        ResponseLogin posts =  new ResponseLogin();
                        try{
                            posts =  gson.fromJson(response, ResponseLogin.class);
                        }catch(Exception e){
                            Log.d("lihatjson", "Gson gagal");
                            Toast.makeText(LoginActivity.this, "Wrong Username/Password !!!!", Toast.LENGTH_SHORT).show();
                        }
                        Log.d("lihatjson", "onResponse: "+posts.getStatus());
                        if(posts.getStatus() == true){
                            sharedPref.saveData("username",username);
                            sharedPref.saveData("password" ,password);
                            Intent i = new Intent(LoginActivity.this, FiturActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(LoginActivity.this, "Tidak bisa login", Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", error.toString());
                        Toast.makeText(LoginActivity.this, "error listener", Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", editTextUsername.getText().toString());
                params.put("password", editTextPassword.getText().toString());
//                String username = editTextUsername.getText().toString();
//                String password = editTextPassword.getText().toString();
//
//                User users = new User();
//                users.setUsername(username);
//                users.setPassword(password);
                return params;
            }
        };
        requestQueue.add(postRequest);

    }

    public class ResponseLogin{
        private Boolean status;
        private User data ;

        public ResponseLogin(Boolean status, User data) {
            this.status = status;
            this.data = data;
        }

        public ResponseLogin() {

        }

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public User getData() {
            return data;
        }

        public void setData(User data) {
            this.data = data;
        }
    }
}
