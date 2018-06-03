package com.example.user.unmannedpostbox;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class BarcodeActivity extends AppCompatActivity implements View.OnClickListener  {
    static String bu;
    static int flag=0;
    TextView tv;
    private static String br;
    private IntentIntegrator qrScan;
    private Button buttonScan;
    private Button openButton;
    private TextView textViewResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        textViewResult = (TextView)findViewById(R.id.textViewResult);
        openButton=(Button)findViewById(R.id.openButton);
        findViewById(R.id.barOpen).setOnClickListener(this);
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
                    BackgroundTask tack = new BackgroundTask();
                    tack.execute();
                    br = result.getContents().toString();
                }catch(JSONException e){
                    e.printStackTrace();
                    textViewResult.setText(result.getContents());
                    br = result.getContents().toString();
                    BackgroundTask tack = new BackgroundTask();
                    tack.execute();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {
        @Override
        protected Integer doInBackground(Integer... integers) {
            // TODO Auto-generated method stub
            HttpPostData();
            return null;
        }

        protected void onPreExecute() {
        }

        protected void onPostExecute(Integer a) {
            //tv.setText(result);
            endPass();
        }

    }




    //------------------------------
    //   Http Post로 주고 받기
    //------------------------------
    public String HttpPostData() {
        StringBuilder builder = new StringBuilder();
        try {


            String response = null;
            //   URL 설정하고 접속하기
            //--------------------------
            URL url = new URL("http://192.168.1.79/barcodeCheck.php");       // URL 설정
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
            StringBuffer buffer = new StringBuffer();
            //buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            //buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            //buffer.append("name").append("=").append(nameText.getText().toString()).append("&");           // 변수 구분은 '&' 사용
            buffer.append("barcode").append("=").append(br);

            Log.d("RESPONSE", "The response2 is: " +buffer.toString());
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "UTF-8");
            Log.d("RESPONSE", "The response3 is: " +buffer.toString());
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            outStream.close();
            writer.close();
            //--------------------------
            //   서버에서 전송받기
            //--------------------------
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "UTF-8");
            BufferedReader reader = new BufferedReader(tmp);
            String str;
            str=reader.readLine();
            bu=str.toString();
            if(bu.equals("true")){
                Log.d("RESPONSE", "bu is true");
                flag=1;

            }
            else
                Log.d("RESPONSE", "bu is false");
            Log.d("RESPONSE", "The response4 is: " + bu);
            http.disconnect();
            /*while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것
            이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구
            }*/
            /*
            OutputStream os = http.getOutputStream(); //output스트림 개방
            InputStream is = http.getInputStream();        //input스트림 개방
            //--------------------------
            //   서버로 값 전송
            //--------------------------

            StringBuffer buffer = new StringBuffer();
            //buffer.append("id").append("=").append(myId).append("&");                 // php 변수에 값 대입
            //buffer.append("pword").append("=").append(myPWord).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            //buffer.append("title").append("=").append(myTitle).append("&");           // 변수 구분은 '&' 사용
            buffer.append("barcode").append("=").append(br);
            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            */

            //post로 바코드 값 전송
            //--------------------------
            //   서버에서 답 전송받기
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
            case R.id.barOpen:
                startActivity(new Intent(this,OpenCloseActivity.class));
                this.finish();
                break;
        }
    }
    public void endPass(){
        Button bt = (Button)findViewById(R.id.barOpen);
        //정보가 있으면 다음으로 아니면 toast
        if(flag==1){
            bt.setVisibility(View.VISIBLE);
            Toast.makeText(this, "인증 성공. 등록된 택배물입니다.", Toast.LENGTH_SHORT).show();
            flag=0;
        }else{
            bt.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "인증 실패! 등록된 택배물이 아닙니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
