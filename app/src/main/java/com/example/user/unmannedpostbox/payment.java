package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class payment extends AppCompatActivity implements View.OnClickListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        findViewById(R.id.account).setOnClickListener(this);
        findViewById(R.id.phone).setOnClickListener(this);
        findViewById(R.id.card).setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.account:
                startActivity(new Intent(this,account.class));
                //this.finish();
                break;
            case R.id.phone:
                startActivity(new Intent(this,phone.class));
                //this.finish();
                break;
            case R.id.card:
                startActivity(new Intent(this,card.class));
                //this.finish();
                break;
        }
    }
}
