package com.letscombine.clockasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

//new Times().execute(nowTime);
public class MainActivity extends AppCompatActivity {
    // 서버시간을 받아올 url준비 및 사용 변수 준비
    static final String TIME_SERVER = "time.bora.net";
    TextView realTime;
    InetAddress inetAddress;
    TimeInfo timeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 시간을 보여줄 TextView 생성
        realTime = findViewById(R.id.real_time);

        // asyncTask 호출
        new Times().execute();
    }

    public class Times extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            while (true) {

                // 시간 형식을 만든다.
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                // 시간을 가져올 NTPUDPClient 객체 생성
                NTPUDPClient ntpudpClient = new NTPUDPClient();
                try {
                    // inetAddress를 이용하여 서버의 IP를 획득
                    inetAddress = InetAddress.getByName(TIME_SERVER);
                    // inetAddress로 얻은 ip를 이용하여 NTPUDPClient에서 시간 정보를 가져온다.
                    timeInfo = ntpudpClient.getTime(inetAddress);

                    // 실제 서버시간 획득
                    long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();

                    // 서버시간을 Date 형태로 변환
                    Date date = new Date(serverTime);

                    // 시간 속성인 date를 String으로 변환
                    String nowTime = simpleDateFormat.format(date);

                    publishProgress(nowTime); // onProgressUpdate로 전달

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        @Override
        protected void onProgressUpdate(String... values) {
            realTime.setText(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }
}