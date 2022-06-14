package com.example.autotextswitcherlibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;

import com.masoud.autotextswitcher.AutoTextSwitcher;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoTextSwitcher textSwitcher = findViewById(R.id.textSwitcher);
        textSwitcher.setFactory(() ->
        {
            AppCompatTextView textView = new AppCompatTextView(MainActivity.this);
            textView.setTextAppearance(MainActivity.this, R.style.InfoTextAppearanceStyle);
            textView.setMinLines(2);
            textView.setPadding(10, 10, 10, 10);
            textView.setShadowLayer(12, 0, -5.5f, Color.parseColor("#65FFFFFF"));
            textView.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            return textView;
        });
    }
}