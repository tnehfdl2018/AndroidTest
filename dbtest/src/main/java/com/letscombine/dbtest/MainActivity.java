package com.letscombine.dbtest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 필드 생성
    EditText editTextName;
    EditText editTextTel;
    Button saveBtn;
    Button lookupBtn;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 생성
        editTextName = findViewById(R.id.editName);
        editTextTel = findViewById(R.id.editTel);
        saveBtn = findViewById(R.id.saveBtn);
        lookupBtn = findViewById(R.id.lookUpBtn);
        listView = findViewById(R.id.showList);


        // 저장하기 버튼 클릭
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 데이터 베이스 추가
                String name = editTextName.getText().toString();
                String tel = editTextTel.getText().toString();

                DBHelper helper = new DBHelper(MainActivity.this);
                SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();

                // insert문 실행 (?,?) 안에 들어가는 값은 new String[]{}안에
                sqLiteDatabase.execSQL("insert into person (name, phone) values(?, ?)", new String[]{name, tel});
                sqLiteDatabase.close(); // db객체 환원

                Toast.makeText(getApplicationContext(), name + "DB입력 완료", Toast.LENGTH_SHORT).show();

            }
        });

        // 조회 버튼 클릭시
        lookupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // adapterActivity 객체화 매개변수로 context와 custom한 레이아웃
                AdapterActivity activity = new AdapterActivity(MainActivity.this, R.layout.activity_custom);
                listView.setAdapter(activity); // 작업이 완료되 리턴된 adapter를 리스트뷰에 붙힌다.
            }
        });
    }
}