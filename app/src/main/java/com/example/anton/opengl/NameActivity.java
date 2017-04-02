package com.example.anton.opengl;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class NameActivity extends AppCompatActivity {
    EditText name;
    Button btn, btn1, btn2;
    TextView welcome, welcome1;
    String name1, savedText;
    final String SAVED_TEXT = "saved_text";
    SharedPreferences sPref;
    Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sPref = getSharedPreferences("Name", 0);
        savedText = sPref.getString(SAVED_TEXT, "");

        if (savedText.equals("")) {
            setContentView(R.layout.activity_name);
            if (t != null)
                t.cancel();
            welcome = (TextView) findViewById(R.id.welcome);
            btn = (Button) findViewById(R.id.start);
            name = (EditText) findViewById(R.id.name);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(btn.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    name1 = name.getText().toString();
                    if (name1.equals("")) {
                        welcome.setText("Введите имя!");
                        return;
                    }
                    saveText();
                    welcome.setText("Добро пожаловать в LearnMath, " + name1);
                    timer(MenuLM.class);
                }
            });
        } else {
            setContentView(R.layout.activity_start);
            if (t != null)
                t.cancel();
            btn1 = (Button) findViewById(R.id.start1);
            btn2 = (Button) findViewById(R.id.remove);
            welcome1 = (TextView) findViewById(R.id.welcome1);
            welcome1.setText("Добро пожаловать в LearnMath, " + savedText);
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(NameActivity.this, MenuLM.class);
                    startActivity(intent);
                    finish();
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    removeText();
                    welcome1.setText("Успешно удален");
                    btn1.setEnabled(false);
                    timer(NameActivity.class);

                }
            });

        }
    }

    public void timer(final Class cl) {
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        t.cancel();
                        Intent intent = new Intent(NameActivity.this, cl);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }, 3500);

    }

    public void saveText() {
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_TEXT, name1);

        ed.commit();
    }

    public void removeText() {
        SharedPreferences.Editor ed = sPref.edit();
        ed.remove(SAVED_TEXT);

        ed.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    @Override
    public void onBackPressed() {
        return;
    }

}
