package com.letscombine.fragment;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SaveFragmentState extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_fragment_state);
    }

    public static class CounterFragment extends Fragment {
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstancestate) {
            View root = inflater.inflate(R.layout.count_fragment, container, false);

            Button btnIncrease = root.findViewById(R.id.btn_increase);
            final TextView textCounter = root.findViewById(R.id.txt_counter);

            if (saveInstancestate != null) {
                textCounter.setText(Integer.toString(saveInstancestate.getInt("counter")));
            }

            btnIncrease.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int count = Integer.parseInt(textCounter.getText().toString());
                    textCounter.setText(Integer.toString(count + 1));
                }
            });
            return root;
        }

        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            TextView textCounter = getView().findViewById(R.id.txt_counter);
            int a = Integer.parseInt(textCounter.getText().toString());
            outState.putInt("counter", a);
        }
    }
}
