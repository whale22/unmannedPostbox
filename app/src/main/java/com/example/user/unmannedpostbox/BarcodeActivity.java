package com.example.user.unmannedpostbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);
        int num, price, quantity;
        String phone, product, addr, order, recipient, company, name,id;
    }
    private static String CLIENT_ID = "부여받은 Client Id";
    private static String CLIENT_PASSWORD = "부여받은 pwd";


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                this.finish();
                break;
            case R.id.submit_action:
                //new IntentIntegrator(this).initiateScan();
                //윗줄 추가
                break;
        }
    }
}
