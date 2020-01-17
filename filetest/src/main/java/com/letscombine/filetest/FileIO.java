package com.letscombine.filetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class FileIO extends AppCompatActivity {

    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_io);

        editText = findViewById(R.id.edit_text);
    }

    public void mOnClick (View v) {

        switch (v.getId()) {
            case R.id.save :
                try {
                    FileOutputStream fos = openFileOutput("test.txt", Context.MODE_PRIVATE);
                    String str = "Android File IO Test";
                    fos.write(str.getBytes());
                    fos.close();
                    editText.setText("write success");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.load :
                try {
                    FileInputStream fis = openFileInput("test.txt");
                    byte[] data = new byte[fis.available()];
                    while (fis.read(data) != -1) {}
                    fis.close();
                    editText.setText(new String(data));
                } catch (Exception e) {
                    editText.setText("File Not Found");
                }
                break;
            case R.id.loadres :
                try {
                    InputStream fres = getResources().openRawResource(R.raw.restext);
                    byte[] data = new byte[fres.available()];
                    while (fres.read(data) != -1) {}
                    fres.close();
                    editText.setText(new String(data));
                } catch (Exception e) {}
                break;
            case R.id.delete :
                if (deleteFile("test.txt")) {
                    editText.setText("delete success");
                }else {
                    editText.setText("delete failed");
                }
        }
    }
}
