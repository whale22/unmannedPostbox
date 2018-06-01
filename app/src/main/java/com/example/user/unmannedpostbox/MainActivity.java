package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener  {
    state s = new state();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Switch sw = findViewById(R.id.switch1);
        findViewById(R.id.takeButton).setOnClickListener(this);
        findViewById(R.id.sendButton).setOnClickListener(this);
        findViewById(R.id.openButton).setOnClickListener(this);
        findViewById(R.id.passButton).setOnClickListener(this);
        findViewById(R.id.usermode_button).setOnClickListener(this);

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
        final Button sb=(Button)findViewById(R.id.sendButton);
        final Button tb=(Button)findViewById(R.id.takeButton);
        final Button um=(Button)findViewById(R.id.usermode_button);
        switch (v.getId()) {
            case R.id.takeButton:
                if(s.getFlag()==0) { // 사용자모드
                    startActivity(new Intent(this,Password.class));
                }else{ // 택배기사 모드
                    startActivity(new Intent(this,Password.class));
                }
                break;
            case R.id.sendButton:
                if(s.getFlag()==0){ // 사용자모드
                    startActivity(new Intent(this,SendActivity.class));
                }else{ // 택배기사모드
                    startActivity(new Intent(this,BarcodeActivity.class));
                }
                break;
            case R.id.openButton:
                startActivity(new Intent(this,OpenCloseActivity.class));
                break;
            case R.id.passButton:
                startActivity(new Intent(this,Password.class));
                break;
            case R.id.usermode_button:
                s.switchState();
                if(s.getFlag()==1){
                    um.setText("택배기사 모드");
                    sb.setText("택배 배송");
                    tb.setText("택배 수거");
                }
                else if(s.getFlag()==0){
                    um.setText("사용자 모드");
                    sb.setText("택배 발송");
                    tb.setText("택배 수령");
                }
        }
    }


}
