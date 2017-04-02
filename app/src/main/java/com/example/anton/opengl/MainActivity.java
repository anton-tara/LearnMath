package com.example.anton.opengl;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ConfigurationInfo;
import android.graphics.Typeface;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {


    private TextView view, view1, view2;
    final Timer t = new Timer();
    SharedPreferences sPref;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sPref = getSharedPreferences("Name", 0);
        String savedText = sPref.getString(SAVED_TEXT, "");
        if (!savedText.equals("")) {
            Intent i = new Intent(MainActivity.this, TitleLaernMath.class);
            startActivity(i);
            finish();
        }
        view = (TextView) findViewById(R.id.text1);
        view1 = (TextView) findViewById(R.id.text2);
        view2 = (TextView) findViewById(R.id.text3);

        view.setPadding(0, 100, 0, 0);
        textView(view, 100, "FatTony" + "\n" + "and", "lol.ttf");
        textView(view1, 90, "McNaggets", "lol.ttf");
        textView(view2, 100, " presents", "lol.ttf");

        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t.cancel();
                        Intent intent = new Intent(MainActivity.this, TitleLaernMath.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 3500, 3500);

    }

    public void textView(TextView t, int textViewSize, String text, String ttf) {
        t.setTextSize(textViewSize);
        t.setText(text);
        t.setTypeface(Typeface.createFromAsset(
                getAssets(), ttf));

    }

    @Override
    protected void onPause() {
        super.onPause();
        view.clearAnimation();
    }

    @Override
    public void onBackPressed() {
        return;
    }
}