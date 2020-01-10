package com.letscombine.dbtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class AdapterActivity extends BaseAdapter {

    // 필드 생성
    Context context;
    ArrayList<PersonVO> arrayList;
    int layout;
    LayoutInflater inflater;

    // 생성자 context와 custom한 레이아웃을 받는다.
    public AdapterActivity(Context context, int layout) {
        this.context = context;
        this.layout = layout;
        //
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // db에서 조회
        DBHelper helper = new DBHelper(context);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        // 조회되서 나온 결과값을 cursor에 담는다.
        Cursor cursor = sqLiteDatabase.rawQuery("select name, phone from person", null);

        // array 객체 생성
        arrayList = new ArrayList<>();
        // moveToNext메소드를 이용하여 한 행씩 각각 vo에 담는다.
        while (cursor.moveToNext()) {
            PersonVO vo = new PersonVO();
            vo.setName(cursor.getString(0));
            vo.setTel(cursor.getString(1));
            arrayList.add(vo);
        }
    }

//    public AdapterActivity(Context context, ArrayList arrayList, int layout){
//        this.context = context;
//        this.arrayList = arrayList;
//        this.layout = layout;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    }

    @Override
    public int getCount() {
        // array의 크기를 확인하여 뿌려줄 리스트 갯수를 확인한다.
        return arrayList.size();
    }

    @Override
    // 각각 데이터를 가져온다.
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    // array의 index값
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // view가 있는지 확인 없으면 생성
        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);
        }

        // 각 뷰에 내용 셋팅
        TextView textName = convertView.findViewById(R.id.textName);
        textName.setText(arrayList.get(position).getName());
        TextView textTel = convertView.findViewById(R.id.textTel);
        textTel.setText(arrayList.get(position).getTel());
        // 완성된 뷰 리턴
        return convertView;
    }


}
