package com.letscombine.threadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class LongTime5 extends AppCompatActivity {

    int mValue;
    TextView mText;
    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_time3);

        mText = findViewById(R.id.text);
    }

    public void mOnClick(View v) {
        new AccumulateTask().execute(100);
    }

    class AccumulateTask extends AsyncTask<Integer, Integer, Integer> {
        @SuppressWarnings("deprecation")
        protected void onPreExecute(){
            mValue = 0;
            mProgressDialog = new ProgressDialog(LongTime5.this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setTitle("Updating");
            mProgressDialog.setMessage("Wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);
            mProgressDialog.setButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cancel(true);
                }
            });
            mProgressDialog.show();
        }

        protected Integer doInBackground(Integer... arg0) {
            while (isCancelled() == false) {
                mValue++;
                if (mValue <= 100) {
                    publishProgress(mValue);
                }else {
                    break;
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return mValue;
        }

        protected void onProgressUpdate(Integer... progress) {
            mProgressDialog.setProgress(progress[0]);
            mText.setText(Integer.toString(progress[0]));
        }

        protected void onPostExecute(Integer result) {
            mProgressDialog.dismiss();
        }

        protected void onCancelled () {
            mProgressDialog.dismiss();
        }
    }
}