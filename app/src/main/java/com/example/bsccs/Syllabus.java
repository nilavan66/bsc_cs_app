package com.example.bsccs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;




public class Syllabus extends AppCompatActivity {
   // ImageView syllabus;
    WebView syllabus2;
    FirebaseFirestore fStore;
    //Button download;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        //syllabus = findViewById(R.id.syllabus);
        syllabus2 = findViewById(R.id.syllabus2);

        //download = findViewById(R.id.down);
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        /*final StorageReference profileRef = storageReference.child("lake.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(syllabus);
            }
        }); */


        syllabus2.getSettings().setJavaScriptEnabled(true);
        syllabus2.setWebViewClient(new WebViewClient());
        syllabus2.loadUrl("https://firebasestorage.googleapis.com/v0/b/b-sc-cs-a12ee.appspot.com/o/2017%20B.Sc.%20COMPUTER%20SCIENCE.pdf?alt=media&token=943508ba-c21e-4991-8113-6348d9e01698");


    }

    public void back(View view) {
        Intent intent = new Intent(Syllabus.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
