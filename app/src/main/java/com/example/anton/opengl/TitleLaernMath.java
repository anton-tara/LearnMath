package com.example.anton.opengl;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class TitleLaernMath extends AppCompatActivity {
    Bitmap a;
    ImageView i;
    final Timer t = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_laern_math);
        a = BitmapFactory.decodeResource(getResources(), R.drawable.title);
        i = (ImageView) findViewById(R.id.imageView);
        i.setImageBitmap(a);
        ScaleAnimation animation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f);
        animation.setDuration(2000);
        i.startAnimation(animation);

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t.cancel();
                        Intent intent = new Intent(TitleLaernMath.this, NameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 3500);


    }
    @Override
    public void onBackPressed() {
        return;
    }
}
