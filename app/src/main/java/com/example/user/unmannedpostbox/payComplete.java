package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class payComplete extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_complete);
        Handler mHandler = new Handler();
            mHandler.postDelayed(new Runnable() {
            //Do Something
            @Override
            public void run() {
            // TODO Auto-generated method stub
            Intent i = new Intent(payComplete.this, BarcodeActivity.class); // xxx가 현재 activity, //yyy가 이동할 activity
            startActivity(i);
            finish();
        }
    }, 2000); // 2000ms
}

}
