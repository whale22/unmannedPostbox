package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

//import com.google.zxing.integration.androidlIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//윗 두줄 추가


public class SendActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        findViewById(R.id.cancel_action).setOnClickListener(this);
        findViewById(R.id.submit_action).setOnClickListener(this);
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                this.finish();
                break;
            case R.id.submit_action:
                startActivity(new Intent(this, Password.class));
                break;
        }
    }
}
