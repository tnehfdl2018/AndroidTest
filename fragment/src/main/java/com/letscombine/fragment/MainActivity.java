package com.letscombine.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static class CounterFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View root = inflater.inflate(R.layout.count_fragment, container, false);

            Button btnIncrease = root.findViewById(R.id.btn_increase);
            final TextView textCounter = root.findViewById(R.id.txt_counter);

            btnIncrease.setOnClickListener(new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(textCounter.getText().toString());
                    textCounter.setText(Integer.toString(count + 1));
                }
            });
            return root;
        }
    }
}