package com.example.user.unmannedpostbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

//import com.google.zxing.integration.androidlIntegrator;
//import com.google.zxing.integration.android.IntentResult;
//윗 두줄 추가

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.HttpURLConnection;
import java.net.URL;


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
                startActivity(new Intent(this, payment.class));
                break;
        }
    }
}
