package com.example.bsccs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

public class Down_one extends AppCompatActivity {
    LinearLayout d1;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_one);
        d1 = findViewById(R.id.d1);
        fStore = FirebaseFirestore.getInstance();

    }


    public void back(View view) {
        startActivity(new Intent(getApplicationContext(), Pdf.class));
    }
}
