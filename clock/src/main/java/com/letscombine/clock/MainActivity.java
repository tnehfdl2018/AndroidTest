package com.letscombine.clock;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static final String TIME_SERVER = "time.google.com";
    TextView realTime;
    Date date;

    ValueHandler handler = new ValueHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        realTime = findViewById(R.id.real_time);
        BackThread thread = new BackThread();
        thread.start();
    }

    class BackThread extends Thread {

        public void run() {
            while (true) {
                Message message = handler.obtainMessage();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

                NTPUDPClient ntpudpClient = new NTPUDPClient();

                try {
                    ntpudpClient.open();

                    InetAddress inetAddress = InetAddress.getByName(TIME_SERVER);
                    TimeInfo timeInfo = ntpudpClient.getTime(inetAddress);

                    long serverTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();

                    date = new Date(serverTime);

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    ntpudpClient.close();
                }
                String nowTime = simpleDateFormat.format(date);
                message.obj = nowTime;

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }finally {
                    handler.sendMessage(message);
                }
            }
        }
    }

    //핸들러구현한 객체(핸들러역할)
    class ValueHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            realTime.setText(msg.obj.toString());
        }
    }
}
