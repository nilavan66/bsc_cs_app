package com.example.bsccs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import static com.example.bsccs.R.anim.bottom_animation;
import static com.example.bsccs.R.anim.top_animation;

public class Splash extends AppCompatActivity {

    private static int SPLASH_SCREEN = 4000;

    //variables
    Animation topanim, bottomanim;
    ImageView image;
    TextView title, owner;
    ProgressBar progressBar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        fAuth = FirebaseAuth.getInstance();

        //Animation
        topanim = AnimationUtils.loadAnimation(this,top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,bottom_animation);

        //Hooks

        image = findViewById(R.id.imageView);
        title = findViewById(R.id.textView);
        owner = findViewById(R.id.owner);
        progressBar = findViewById(R.id.progressBar2);

        image.setAnimation(topanim);
        title.setAnimation(bottomanim);
        progressBar.setAnimation(bottomanim);
        owner.setAnimation(bottomanim);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                if (fAuth.getCurrentUser() !=null)
                {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        },SPLASH_SCREEN);
    }
}
