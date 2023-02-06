package com.example.topjuantech_ojt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ForgotPassword extends AppCompatActivity {
    private TextView backButtonForgot;
    Button forgotPasswordSubmit;
    EditText forgot_password_input;
//    ActivityLoginBinding binding;
    ProgressDialog progressDialog = new ProgressDialog(this);
    FirebaseAuth mAuth;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String email;
    FirebaseFirestore fStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //        binding=ActivityLoginBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        mAuth = FirebaseAuth.getInstance();
//        fStore= FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//
//
//        forgot_password_input = findViewById(R.id.forgot_password_input);
//        forgotPasswordSubmit = findViewById(R.id.forgotPasswordSubmit);
//        backButtonForgot = findViewById(R.id.backButtonForgot);
//
//        forgotPasswordSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PerformAuth();
//            }
//        });
//    }
//
//    private void PerformAuth() {
////        String phoneCode = editPhoneCode.getText().toString();
//        email = forgot_password_input.getText().toString();
//
//        if (email.isEmpty()) {
//            forgot_password_input.setError("Please input your email!");
//        } else if (!email.matches(emailPattern)) {
//            forgot_password_input.setError("Enter Proper phone number!");
//        } else {
//            progressDialog.show();
//            mAuth.sendPasswordResetEmail(binding.editTextEmailAdd.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    progressDialog.dismiss();
//                    if (task.isSuccessful()) {
//                        startActivity(new Intent(ForgotPassword.this,Login.class));
//                        finish();
//                        Toast.makeText(ForgotPassword.this, "Reset Password Successful, Please Check your Email", Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(ForgotPassword.this, "Please Enter Registered Email", Toast.LENGTH_LONG).show();
//                    }
//                }
//
////                private void sendUserToNextActivity() {
////                    Intent i = new Intent(ForgotPassword.this, Login.class);
////                    i.setFlags((i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK));
////                    startActivity(i);
////                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(ForgotPassword.this, e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });

//        backButtonForgot=(TextView)findViewById(R.id.forgotPassword);
//        backButtonForgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });

//        backButtonForgot=(TextView)findViewById(R.id.forgotPassword);
//        backButtonForgot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ForgotPassword.this,MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
