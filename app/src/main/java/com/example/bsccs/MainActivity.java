package com.example.bsccs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    TextView fullName, dno;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*----------Hooks--------------*/
        fullName = findViewById(R.id.profileName);
        dno = findViewById(R.id.profileDno);

        /*-----------Navigation View-----*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        /*----------Tool Bar--------------*/

        setSupportActionBar(toolbar);

        /*----------Navigation Drawer Menu--------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }
    

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            moveTaskToBack(true);
        }
    }

    public void logout(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.profile:
                Intent intent = new Intent(MainActivity.this, My_profile.class);
                startActivity(intent);
                finish();
        }
 
        return true;
    }

    public void timetable(View view) {
        Intent intent = new Intent(MainActivity.this, Timetable.class);
        startActivity(intent);
        finish();
    }

    public void pdf(View view) {
        Intent intent = new Intent(MainActivity.this, Pdf.class);
        startActivity(intent);
        finish();
    }

    public void syllabus(View view) {
        Intent intent = new Intent(MainActivity.this, Syllabus.class);
        startActivity(intent);
        finish();
    }

    public void erp(View view) {
        Intent intent = new Intent(MainActivity.this, CollegeErp.class);
        startActivity(intent);
        finish();
    }

    public void folder(View view) {
        Intent intent = new Intent(MainActivity.this, Folder.class);
        startActivity(intent);
        finish();
    }

    public void messages(View view) {
        Intent intent = new Intent(MainActivity.this, Message.class);
        startActivity(intent);
        finish();
    }

}
