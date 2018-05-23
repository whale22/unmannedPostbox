package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class phone extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        findViewById(R.id.button).setOnClickListener(this);
    }


    public void onClick(View v) {
        Intent intent = null;

        switch (v.getId()) {

            case R.id.button:
                intent = new Intent(getApplicationContext(), payComplete.class);
                startActivity(intent);
                break;

        }
    }

}
