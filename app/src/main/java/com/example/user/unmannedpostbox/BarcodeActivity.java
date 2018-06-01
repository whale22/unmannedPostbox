package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class BarcodeActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    private Button buttonScan;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewResult = (TextView)findViewById(R.id.textViewResult);
        qrScan = new IntentIntegrator(this);

        buttonScan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                qrScan.setPrompt("Scanning...");
                qrScan.initiateScan();
            }
        });
    }

    /*private static String CLIENT_ID = "부여받은 Client Id";
    private static String CLIENT_PASSWORD = "부여받은 pwd";*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(BarcodeActivity.this, "취소!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BarcodeActivity.this, "스캔완료!", Toast.LENGTH_SHORT).show();
                try{
                    JSONObject obj = new JSONObject(result.getContents());
                }catch(JSONException e){
                    e.printStackTrace();
                    textViewResult.setText(result.getContents());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

   /* public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                this.finish();
                break;
            case R.id.submit_action:
                //new IntentIntegrator(this).initiateScan();
                //윗줄 추가
                break;
        }
    }*/
}
