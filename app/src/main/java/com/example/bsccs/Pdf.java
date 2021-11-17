package com.example.bsccs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Pdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

    public void cn(View view) {
        startActivity(new Intent(getApplicationContext(), Cn.class));
        finish();
    }

    public void or(View view) {
        startActivity(new Intent(getApplicationContext(), Or.class));
        finish();
    }

    public void os(View view) {
        startActivity(new Intent(getApplicationContext(), Os.class));
        finish();
    }
}
