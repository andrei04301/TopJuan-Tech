package com.example.topjuantech_ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_login, btn_signupUser, btn_signupManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_signupUser = (Button) findViewById(R.id.btn_signupUser);
        btn_signupManager = (Button) findViewById(R.id.btn_signupManager);

        btn_signupUser.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    Intent intent = new Intent(MainActivity.this, RegistrationUser.class);
                    startActivity(intent);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    Intent intent = new Intent(MainActivity.this, RegistrationUser.class);
                    startActivity(intent);
                }

                return true;
            }
        });

        btn_signupManager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    Intent intent = new Intent(MainActivity.this, RegistrationManager.class);
                    startActivity(intent);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    Intent intent = new Intent(MainActivity.this, RegistrationManager.class);
                    startActivity(intent);
                }
                return true;
            }
        });
        btn_login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                else if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
                    Intent intent = new Intent(MainActivity.this, Login.class);
                    startActivity(intent);
                }
                return true;
            }
        });
    }
}