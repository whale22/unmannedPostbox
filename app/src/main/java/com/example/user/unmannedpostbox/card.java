package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
    }
    public void onClick(View arg0) {
        Intent intent = null;

        switch (arg0.getId()) {

            case R.id.card:
                intent = new Intent(getApplicationContext(), card.class);
                startActivity(intent);
                break;
            case R.id.account:
                intent = new Intent(getApplicationContext(), account.class);
                startActivity(intent);
                break;
            case R.id.phone:
                intent = new Intent(getApplicationContext(), phone.class);
                startActivity(intent);
                break;

        }
    }
}
