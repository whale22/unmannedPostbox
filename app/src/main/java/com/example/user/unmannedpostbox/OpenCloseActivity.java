package com.example.user.unmannedpostbox;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OpenCloseActivity extends AppCompatActivity implements View.OnClickListener {
    // 전역변수를 선언한다
    String myId, myPWord, myTitle, mySubject, myResult;
    TextView tv;
    BackgroundTask task;
    String resultTmp;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_close);
        findViewById(R.id.control_action).setOnClickListener(this);
        findViewById(R.id.cancel_action).setOnClickListener(this);
        tv = findViewById(R.id.resultText);
    }
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        protected void onPreExecute() {
        }

        @Override
        protected Integer doInBackground(Integer... arg0) {
            // TODO Auto-generated method stub
            HttpPostData();
            return null;
        }

        protected void onPostExecute(Integer a) {
            //tv.setText(result);
        }

    }




    //------------------------------
    //   Http Post로 주고 받기
    //------------------------------
    public String HttpPostData() {
        StringBuilder builder = new StringBuilder();
        try {

            String response = null;
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL("http://192.168.1.79:8080/");       // URL 설정
            HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                         // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");         // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            http.connect();
            response = http.getResponseMessage();
            Log.d("RESPONSE", "The response is: " + response);

            OutputStream os = http.getOutputStream(); //output스트림 개방
            InputStream is = http.getInputStream();        //input스트림 개방

            /*
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            buffer.append("title").append("=").append(myTitle).append("&");           // 변수 구분은 '&' 사용
            buffer.append("subject").append("=").append(mySubject);

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();*/
            //--------------------------
            //   서버에서 전송받기
            //--------------------------

            return builder.toString();
        } catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            //
        } // try
        return builder.toString();
    } // HttpPostData

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                Toast.makeText(OpenCloseActivity.this, "택배함 조작을 종료합니다.", 0).show();
                this.finish();
                break;
            case R.id.control_action:
                Toast.makeText(OpenCloseActivity.this, "개폐가 완료되었습니다.", 0).show();
                task = new BackgroundTask();
                task.execute(); // 서버와 자료 주고받기
                tv.setText(resultTmp);
                break;
        }
    }
}