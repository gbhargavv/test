package com.borderedtextview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.gbhargavv.bordered_textview.BorderedTextView;
import com.gbhargavv.borderedtextview.R;

public class MainActivity extends AppCompatActivity {
    BorderedTextView borderedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        borderedTextView = findViewById(R.id.txt);

        borderedTextView.setBorderedColor(Color.GREEN);
        borderedTextView.setIncludeFontPadding(false);
        borderedTextView.setLineSpacing(0f, 0f);
        borderedTextView.setBorderTypeface(Typeface.createFromAsset(getAssets(), "Mont-Bold.ttf"));
        borderedTextView.setBorderWidth(3);
        borderedTextView.setInsetColor(Color.BLACK);
        borderedTextView.setTextSizes(30);
    }
}