package com.example.user.unmannedpostbox;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {
    state s = new state();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Switch sw = findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                s.switchState();
            }
            /*
            //위젯에 대한 참조.
            tv_outPut = (TextView) findViewById(R.id.tv_output);
            //url 설정
            String url="";

            //AsyncTask를 통해 HttpURLConnection 수행
            NetworkTask networkTask = new NetworkTask(url, null);
            networkTask.execute();
            */

        });
        findViewById(R.id.takeButton).setOnClickListener(this);
        findViewById(R.id.sendButton).setOnClickListener(this);
        findViewById(R.id.openButton).setOnClickListener(this);

    }
    /*
    public class NetworkTask extends AsyncTask<Void, Void, String> {
        private String url;
        private ContentValues values;

        public NetworkTast(String url, ContentValues values){
            this.url = url;
            this.values = values;
        }

        protected String doInBackground(Void... params){
            String result;//요청 결과를 저장할 변수
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values);//url에서 결과 받아오기
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv_outPut.setText(s);
        }
    }
    */
    public void onClick(View v) {
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
                    startActivity(new Intent(this,BarcodeActivity.class));
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
                case R.id.openButton:
                    startActivity(new Intent(this,OpenCloseActivity.class));
                break;
        }
    }


}
