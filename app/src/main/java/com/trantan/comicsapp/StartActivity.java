package com.trantan.comicsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        RunAnimation();
    }

    public void onStartButton(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.text_anim);
        a.reset();
        Button btnStart = findViewById(R.id.btnStart);
        btnStart.clearAnimation();
        btnStart.startAnimation(a);
    }
}
