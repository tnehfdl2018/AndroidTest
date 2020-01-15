package com.letscombine.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.letscombine.fragment.SaveFragmentState.CounterFragment;

public class FragmentManagerTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_manager_test);
    }

//    public mOnClick(View v) {
//        FragmentManager fm = getFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.frame);
//
//        switch (v.getId()) {
//            case R.id.btn_add :
//                if (fragment == null) {
//                    FragmentTransaction tr = fm.beginTransaction();
//                    CounterFragment cf = new CounterFragment();
//                    tr.add(R.id.frame, cf, "counter");
//                    tr.commit();
//                }else {
//                    Toast.makeText(getApplicationContext(), "이미 추가되어 있습니다.", Toast.LENGTH_LONG).show();
//                }
//                break;
//            case R.id.btn_remove:
//                if (fragment == null) {
//                    Toast.makeText(getApplicationContext(), "프래그먼트가 없습니다.", Toast.LENGTH_LONG).show();
//                }else {
//                    FragmentTransaction tr = fm.beginTransaction();
//                    tr.remove(fragment);
//                    tr.commit();
//                }
//                break;
//            case R.id.btn_replace:
//                if (fragment == null) {
////                    Toast.makeText(getApplicationContext(), "프래그먼트가 없습니")
//                }
//            case R.id.btn_hide_show:
//
//        }
//    }
}
