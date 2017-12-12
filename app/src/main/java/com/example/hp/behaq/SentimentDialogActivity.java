package com.example.hp.behaq;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class SentimentDialogActivity extends Activity implements View.OnClickListener {

    Button dialog_showRevi, dialog_giveRevi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sentiment_dialog);

//        dialog_showRevi = (Button)findViewById(R.id.dlg_showReview);
//        dialog_giveRevi = (Button)findViewById(R.id.dlg_giveReview);

        dialog_showRevi.setOnClickListener((View.OnClickListener)this);
        dialog_giveRevi.setOnClickListener((View.OnClickListener)this);

    }

    @Override
    public void onClick(View f) {

//        switch (f.getId()) {
//            case R.id.dlg_showReview:
//
//                Intent i = new Intent(this, LoginActivity.class);
//                startActivity(i);
//
//                break;
//
//            case R.id.dlg_giveReview:
//
//                Intent j = new Intent(this, RegisterActivity.class);
//                startActivity(j);
//
//                break;
//        }

    }

    void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

}

