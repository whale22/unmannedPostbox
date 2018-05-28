package com.example.user.unmannedpostbox;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Password extends AppCompatActivity implements View.OnClickListener  {

    BackgroundTask task;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        findViewById(R.id.submit_action).setOnClickListener(this);
        findViewById(R.id.cancel_action).setOnClickListener(this);
    }

    //서버로 사용자가 입력한 비밀번호를 보내고 php가 판별한 뒤 신호를 보냄
    //서버가 no를 보냈으면 open 실행불가, yes를 보냈으면 실행가능


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
    public void HttpPostData() {
        try {

            String response = null;
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL("http://192.168.174.131:8080/pass/");       // URL 설정
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
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            //buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            //buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            //buffer.append("title").append("=").append(myTitle).append("&");           // 변수 구분은 '&' 사용
            buffer.append("pass").append("=").append("123456");

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
            }
            TextView tv = findViewById(R.id.resultText);
            tv.setText(builder);
            Toast.makeText(Password.this, "비밀번호 전송 완료", 0).show();
        } catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            //
        } // try
    } // HttpPostData

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_action:
                this.finish();
                break;
            case R.id.submit_action:

                task = new Password.BackgroundTask();
                task.execute(); // 서버와 자료 주고받기
                break;
        }
    }
}