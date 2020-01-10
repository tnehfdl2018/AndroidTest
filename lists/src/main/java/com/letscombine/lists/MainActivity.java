package com.letscombine.lists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button btnAddPage;

    ArrayList<DataVO> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.mainListView);
        btnAddPage = findViewById(R.id.btnAddPage);

        // vo객체화 시켜 각각 데이터 입력
//        DataVO vo = new DataVO();
//        vo.setImgSrc(ContextCompat.getDrawable(this, R.drawable.panda));
//        vo.setName("팬더");
//        vo.setContent("우와 이게 판다라고?" );
//        vo.setDate("01/06");
//
        DataVO vo1 = new DataVO();
        vo1.setImgSrc(ContextCompat.getDrawable(this, R.drawable.dog));
        vo1.setName("강아지");
        vo1.setContent("왕왕왕");
        vo1.setDate("01/15");
//
//        DataVO vo2 = new DataVO();
//        vo2.setImgSrc(ContextCompat.getDrawable(this, R.drawable.bear));
//        vo2.setName("곰");
//        vo2.setContent("곰돌쓰~");
//        vo2.setDate("02/15");

        // SQLite를 사용하기 위해 미리 만들어놓은 DBHelper객체화
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();  //
        Cursor cursor = db.rawQuery("select name, content from test_table", null);

        arrayList = new ArrayList<>();
        arrayList.add(vo1);
        while (cursor.moveToNext()){
            DataVO vo = new DataVO();
            vo.setName(cursor.getString(0));
            vo.setContent(cursor.getString(1));
            arrayList.add(vo);
        }
        db.close();

        // 데이터가 들어있는 vo를 배열에 담는다.


//        arrayList.add(vo);
//        arrayList.add(vo1);
//        arrayList.add(vo2);

        //AdapterListActivity를 객체화 시킨다. (context, 데이터리스트, (int)레이아웃)
        AdapterListActivity adapterListActivity = new AdapterListActivity(this, arrayList, R.layout.activity_custom);
        listView.setAdapter(adapterListActivity);  // 리스트뷰에 adapter를 붙힌다.

        // 추가하는 페이지로 넘어가기
        btnAddPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
