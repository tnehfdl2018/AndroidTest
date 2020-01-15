package com.letscombine.animationtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListAnim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_anim);

        ArrayAdapter<CharSequence> Adapter;
        Adapter = ArrayAdapter.createFromResource(this, R.array.listanim, android.R.layout.simple_list_item_1);
        ListView list = findViewById(R.id.list);

        list.setAdapter(Adapter);

        AnimationSet set = new AnimationSet(true);
        Animation rt1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f);

        rt1.setDuration(1000);
        set.addAnimation(rt1);

        Animation alpha = new AlphaAnimation(0.0f, 1.0f);
        alpha.setDuration(1000);
        set.addAnimation(alpha);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        list.setLayoutAnimation(controller);
    }
}
