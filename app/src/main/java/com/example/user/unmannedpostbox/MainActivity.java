package com.example.user.unmannedpostbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    state s = new state();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.takeButton).setOnClickListener(this);
        findViewById(R.id.sendButton).setOnClickListener(this);

    }
    public void onClick(View v) {
        //Intent intent = null;
        switch (v.getId()) {
            case R.id.takeButton:
                if(s.getFlag()==0) { // 택배함이 열린 경우
                    new AlertDialog.Builder(this)
                            .setTitle("수령 불가")
                            .setMessage("택배함이 비어 있어 수령할 수 없습니다.\n\n")
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {
                                }
                            })
                            .show(); // 팝업창 보여줌
                }else{ // 택배함이 닫힌 경우
                    startActivity(new Intent(this,SendActivity.class));
                }
                break;
            case R.id.sendButton:
                if(s.getFlag()==0){ // 택배함이 열린경우 발송창
                    startActivity(new Intent(this,SendActivity.class));
                }else{ // 택배함이 닫힌 경우 발송불가
                    new AlertDialog.Builder(this)
                            .setTitle("발송 불가")
                            .setMessage("택배함에 이미 택배가 있어 발송할 수 없습니다.\n\n")
                            .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int sumthin) {
                                }
                            })
                            .show(); // 팝업창 보여줌
                }
                break;
        }
    }


}
