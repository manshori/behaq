package com.example.hp.behaq;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class Dialog1Activity extends Activity implements View.OnClickListener {

    Button dialog_reg, dialog_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog1);

        dialog_reg = (Button) findViewById(R.id.dlg_register);
        dialog_log = (Button) findViewById(R.id.dlg_login);

        dialog_reg.setOnClickListener((View.OnClickListener) this);
        dialog_log.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.dlg_login:

                Intent i = new Intent(this, LoginActivity.class);
                startActivity(i);

                break;

            case R.id.dlg_register:

                Intent j = new Intent(this, RegisterActivity.class);
                startActivity(j);

                break;
        }

    }

    void showToastMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
                .show();
    }

}
