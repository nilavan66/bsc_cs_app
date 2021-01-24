package com.example.bsccs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.bsccs.R.anim.bottom_animation;
import static com.example.bsccs.R.anim.left_animation;
import static com.example.bsccs.R.anim.right_animation;
import static com.example.bsccs.R.anim.top_animation;

public class Register extends AppCompatActivity {
    public static final String TAG = "TAG";
    //Variables
    Animation topanim, bottomanim, leftanim, rightanim;
    ImageView logo;
    TextView subtitle,mLoginbtn;
    Button mRegisterBtn;
    EditText mDno, mFullName, mEmail, mPassword, mPhone, mDob;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        subtitle = findViewById(R.id.textView3);
        logo = findViewById(R.id.logo);

        mLoginbtn = findViewById(R.id.logintxt);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mDno = findViewById(R.id.dno);
        mFullName = findViewById(R.id.fullname);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone = findViewById(R.id.phone);
        mDob = findViewById(R.id.dob);
        progressBar = findViewById(R.id.progressBar);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        if (fAuth.getCurrentUser() !=null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }


        //Animation

        topanim = AnimationUtils.loadAnimation(this,top_animation);
        bottomanim = AnimationUtils.loadAnimation(this,bottom_animation);
        leftanim = AnimationUtils.loadAnimation(this, left_animation);
        rightanim = AnimationUtils.loadAnimation(this,right_animation);

        //Animation functions
        logo.setAnimation(topanim);
        subtitle.setAnimation(topanim);

        mLoginbtn.setAnimation(bottomanim);
        mRegisterBtn.setAnimation(bottomanim);
        /* dno.setAnimation(leftanim);
        fname.setAnimation(rightanim);
        email.setAnimation(leftanim);
        password.setAnimation(rightanim);
        phone.setAnimation(leftanim);
        dob.setAnimation(rightanim);*/

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = mEmail.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String fullName = mFullName.getText().toString();
                final String phone = mPhone.getText().toString();
                final String dno = mDno.getText().toString();
                final String dob = mDob.getText().toString();

                if(TextUtils.isEmpty(dno))
                {
                    mDno.setError("Register number is required");
                    return;
                }

                if(dno.length()<8)
                {
                    mDno.setError("Type full Register Number");
                    return;
                }
                if(TextUtils.isEmpty(fullName))
                {
                    mFullName.setError("Name is required");
                    return;
                }

                if (TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email is Required");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password id Required");
                    return;
                }
                if(password.length() <6)
                {
                    mPassword.setError("Password must be in 6 characters");
                    return;
                }

                if(phone.length() <10)
                {
                    mPhone.setError("Phone number is invalid");
                    return;
                }

                if(phone.length() >10)
                {
                    mPhone.setError("Phone number is invalid");
                    return;
                }
                if(dob.length()<10)
                {
                    mDob.setError("incorrect Date of birth");
                    return;
                }
                if(dno.length()>10)
                {
                    mDob.setError("incorrect Date of birth");
                    return;
                }
                if (TextUtils.isEmpty(dob))
                {
                    mDob.setError("Date of birth is Required");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                // register the user in the firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Register.this, "User Created Sucessfully", Toast.LENGTH_SHORT).show();
                            userID = fAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = fStore.collection("users").document(userID);

                            Map<String, Object> user = new HashMap<>();
                            user.put("fname",fullName);
                            user.put("email", email);
                            user.put("pwd", password);
                            user.put("phone", phone);
                            user.put("dno",dno);
                            user.put("dob",dob);

                            //inseting into database
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

                                    Log.d(TAG,"OnSuccess: User Profile is created for " + userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });


                            startActivity(new Intent(getApplicationContext(), Login.class));

                        }else
                        {
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }


                    }
                });




            }
        });

        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

    }
}
