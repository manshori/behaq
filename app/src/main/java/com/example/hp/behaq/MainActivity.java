package com.example.hp.behaq;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.hp.behaq.R;

public class MainActivity extends AppCompatActivity {

    private Button mButtonAnalyze;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonAnalyze = (Button) findViewById(R.id.btn_analyze);

        mButtonAnalyze.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this, FiturActivity.class);
                startActivity(intent);
            }
        });
    }
}
