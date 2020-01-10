package com.letscombine.lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListActivity extends BaseAdapter {

    Context context;
    ArrayList<DataVO> arrayList;
    int layout;
    LayoutInflater inflater;


    // 생성자
    public AdapterListActivity (Context context, ArrayList arrayList, int layout){
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
        // layoutInflater 생성
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size(); // 넘어온 데이터의 크기를 확인 (리스트의 갯수를 리턴)
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position); // 각 row에 담을 데이터 추출
    }

    @Override
    public long getItemId(int position) {
        return position; // 각 데이터별 id
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){ // view값이 null인지 확인 후 null이면 생성
            convertView = inflater.inflate(layout, parent, false);
            // 최초 한번만 생성한다.
        }

//        Log.i("이름 : ", arrayList.get(position).getName());
//        Log.i("내용 : ", (String) arrayList.get(position));
//        Log.i("날짜 : ", (String) arrayList.get(position));

        // custom뷰 각 위치에 데이터 첨부
        ImageView imageView = convertView.findViewById(R.id.cusImageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageDrawable(arrayList.get(position).getImgSrc());
        TextView textViewName = convertView.findViewById(R.id.cusTextViewName);
        textViewName.setText(arrayList.get(position).getName());
        TextView textViewContent = convertView.findViewById(R.id.cusTextViewContent);
        textViewContent.setText(arrayList.get(position).getContent());
        TextView textViewDate = convertView.findViewById(R.id.cusTextViewDate);
        textViewDate.setText(arrayList.get(position).getDate());


        return convertView; // 데이터까지 완료된 뷰 리턴
    }
}
