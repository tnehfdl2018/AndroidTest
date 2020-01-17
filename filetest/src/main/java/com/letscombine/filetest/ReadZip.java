package com.letscombine.filetest;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class ReadZip extends AppCompatActivity {

    TextView result;
    String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_zip);

        mPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ZipTest.zip";
        result = findViewById(R.id.result);

        CopyAsset(this, "ZipTest.zip", "ZipTest.zip");
    }

    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.btn_list :
                ShowList();
                break;
            case R.id.btn_a :
                ShowA();
                break;
            case R.id.btn_b :
                ShowB();
                break;
        }
    }

    void ShowList() {
        try {
            ZipFile zip = new ZipFile(mPath);
            String s = "";
            s = "size = " + zip.size() + "\n";
            ZipEntry e;

            Enumeration<? extends ZipEntry> ent = zip.entries();
            while (ent.hasMoreElements()) {
                e = ent.nextElement();
                s = s + "name = " + e.getName() + ", size = " + e.getSize() + ", Compsize = " + e.getCompressedSize() + "\n";
            }
            result.setText(s);
        } catch (IOException e) {
            return;
        }
    }

    void ShowA() {

        try {
            ZipFile zip;
            zip = new ZipFile(mPath);

            InputStream is = zip.getInputStream(zip.getEntry("a.txt"));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int len;
            for (;;) {
                len = is.read(buffer);
                if (len <= 0) break;
                baos.write(buffer, 0, len);
            }
            is.close();
            result.setText(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void ShowB() {

        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(mPath));
            for (;;) {
                ZipEntry ze = zin.getNextEntry();
                if (ze == null) break;
                if (ze.getName().equals("subdir/b.txt")) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];

                    int len;
                    for (;;) {
                        len = zin.read(buffer);
                        if (len <= 0) break;
                        baos.write(buffer, 0, len);
                    }
                    result.setText(baos.toString());
                    break;
                }
            }
            zin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean CopyAsset(Context context, String src, String dest ) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return false;
        }
        String root = Environment.getExternalStorageDirectory().getAbsolutePath();

        String destpath = root + "/" + dest;
        File f = new File(destpath);
        if (f.exists()) {
            return true;
        }

        AssetManager am = context.getAssets();
        try {
            InputStream is = am.open(src);
            FileOutputStream os = new FileOutputStream(destpath);
            byte buffer[] = new byte[1024];
            for (;;) {
                int read = is.read(buffer);
                if (read <= 0) break;
                os.write(buffer, 0, read);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
