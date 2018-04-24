package com.example.editview;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    View view,view1;
    EditText et,et1;
    String string;

    ValueAnimator valueAnimator;

    boolean flag = false;
    boolean flag1 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.et);
        view = findViewById(R.id.myview);

        et1 = findViewById(R.id.et1);
        view1 = findViewById(R.id.myview1);


        valueAnimator = ValueAnimator.ofInt(0, 1000);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {

                int currentValue = (Integer) animator.getAnimatedValue();

                if(flag){
                    view.getLayoutParams().width = currentValue;
                    view.requestLayout();
                }

                if(flag1){
                    view1.getLayoutParams().width = currentValue;
                    view1.requestLayout();
                }
            }
        });

        et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    flag = true;
                    valueAnimator.start();
                }else{
                    flag = false;
                }
            }
        });
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    flag1 = true;
                    valueAnimator.start();
                }else {
                    flag1 = false;
                }
            }
        });

        // 启动动画

    }
}
