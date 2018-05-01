package com.example.user.unmannedpostbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.nfc.NfcAdapter;
import android.widget.Toast;

public class NFCActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        findViewById(R.id.cancel_action).setOnClickListener(this);

        // NFC 관련 객체 생성
        NfcAdapter mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (!mNfcAdapter.isEnabled())
        {
            Toast.makeText(getApplicationContext(), "NFC가 꺼져있습니다. 안드로이드 설정에서 NFC설정을 ON으로 해 주세요.", Toast.LENGTH_LONG).show();
            startActivity(new Intent(android.provider.Settings.ACTION_NFC_SETTINGS));
        }
        else{
            startActivity(new Intent(this,OpenCloseActivity.class));
            this.finish();
        }
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                this.finish();
                break;
        }
    }
}
