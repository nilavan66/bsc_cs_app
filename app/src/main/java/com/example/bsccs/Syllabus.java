package com.example.bsccs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.net.URLEncoder;


public class Syllabus extends AppCompatActivity {

    WebView syllabus2;

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        syllabus2 = findViewById(R.id.syllabus2);

        //FirebaseDatabase db = FirebaseDatabase.getInstance();
       // DatabaseReference root = db.getReference().child("Syllabus");

        String url = "https://firebasestorage.googleapis.com/v0/b/b-sc-cs-a12ee.appspot.com/o/2017%20B.Sc.%20CS_Syllabus.pdf?alt=media&token=d7467a27-ba4d-44b0-8cbb-d005de51508b";
        try {
            url = URLEncoder.encode(url, "UTF-8");
        } catch (Exception ignored) {
        }

        syllabus2.getSettings().setJavaScriptEnabled(true);
        syllabus2.setWebViewClient(new WebViewClient());
        syllabus2.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);

    }

    public void back(View view) {
        Intent intent = new Intent(Syllabus.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
