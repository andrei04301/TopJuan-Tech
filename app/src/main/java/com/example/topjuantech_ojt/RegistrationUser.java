package com.example.topjuantech_ojt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationUser extends AppCompatActivity {
    private EditText firstNameEditText, lastNameEditText,
            emailEditText, phoneEditText, passwordEditText,
            confirmPasswordEditText,editPhoneCode;
    private TextView alreadyHaveAccount;
    String firstName, lastName, phone, email, password, confirmPassword;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //    String phonePattern="^(09|\\+639)\\d{9}$";
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_user);

        fStore=FirebaseFirestore.getInstance();

        firstNameEditText =  findViewById(R.id.editFirstName);
        lastNameEditText =  findViewById(R.id.editLastName);
        emailEditText =  findViewById(R.id.editEmail);
        phoneEditText = findViewById(R.id.editCellphone);
        passwordEditText =  findViewById(R.id.editPassword1);
        confirmPasswordEditText =  findViewById(R.id.editPassword2);
//        editPhoneCode =  findViewById(R.id.editPhoneCode);
        Button signupButton = findViewById(R.id.btnSignup);
        alreadyHaveAccount=findViewById(R.id.alreadyHaveAccount);

        progressDialog=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationUser.this,Login.class);
                startActivity(intent);
            }
        });
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }
    private void PerformAuth() {
//        String phoneCode = editPhoneCode.getText().toString();
        String phoneNumber = phoneEditText.getText().toString();
        firstName = firstNameEditText.getText().toString();
        lastName = lastNameEditText.getText().toString();
        email = emailEditText.getText().toString();
        phone = phoneEditText.getText().toString();
        password = passwordEditText.getText().toString();
        confirmPassword = confirmPasswordEditText.getText().toString();

        if(firstName.isEmpty()){
            firstNameEditText.setError("Please fill all fields");
        }else if(lastName.isEmpty()){
            lastNameEditText.setError("Please fill all fields");
        }else if(!email.matches(emailPattern)){
            emailEditText.setError("Enter correct Email!");
        }else if(password.isEmpty() || password.length()<6){
            passwordEditText.setError("Enter Proper Password(6)!");
        }else if(!password.equals(confirmPassword)){
            confirmPasswordEditText.setError("Confirm Password not match!");}
//            }else if(phone!=phonePattern){
//                phoneEditText.setError("Enter Proper phone number!");}
        else {
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        FirebaseUser user=mAuth.getCurrentUser();
                        progressDialog.dismiss();
                        DocumentReference df = fStore.collection("Users").document(user.getUid());
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("First Name",firstNameEditText.getText().toString());
                        userInfo.put("Last Name",lastNameEditText.getText().toString());
                        userInfo.put("Email",emailEditText.getText().toString());
                        userInfo.put("Phone",phoneEditText.getText().toString());
                        userInfo.put("isUser", "1");
                        df.set(userInfo);
                        sendUserToNextActivity();
                        finish();
                        Toast.makeText(RegistrationUser.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(RegistrationUser.this, ""+task.getException(), Toast.LENGTH_LONG).show();
                    }
                }

                private void sendUserToNextActivity() {
                    Intent i = new Intent(RegistrationUser.this, UserDashboard.class );
                    i.setFlags((i.FLAG_ACTIVITY_CLEAR_TASK|i.FLAG_ACTIVITY_NEW_TASK));
                    startActivity(i);
                }
            });
        }
    }

//        Button signupButton = (Button) findViewById(R.id.btnSignup);
//        signupButton.setOnClickListener(v ->{
//            String phoneCode = editPhoneCode.getText().toString();
//            String phoneNumber = phoneEditText.getText().toString();
//            String firstName = firstNameEditText.getText().toString();
//            String lastName = lastNameEditText.getText().toString();
//            String email = emailEditText.getText().toString();
//            String phone = phoneEditText.getText().toString();
//            String password = passwordEditText.getText().toString();
//            String confirmPassword = confirmPasswordEditText.getText().toString();
//
//            if(firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()){
//                Toast.makeText(context, "Please input correct credentials", Toast.LENGTH_LONG).show();
//                firstNameEditText.setError("Please correct credentials");
//                lastNameEditText.setError("correct credentials");
//            }
//            else if(email.matches(emailPattern)){
//                Toast.makeText(context, "Please input your Name", Toast.LENGTH_LONG).show();
//                emailEditText.setError("Please input a valid email");
//            }
//            else if(phone.matches(phonePattern)){
//                Toast.makeText(context, "Please input your Name", Toast.LENGTH_LONG).show();
//                phoneEditText.setError("Please input a valid email");
//            }
//            else if(password != confirmPassword){
//                Toast.makeText(context, "Wrong Password", Toast.LENGTH_LONG).show();
//                confirmPasswordEditText.setError("Wrong Password");
//            }
//            else {
//                Intent intent = new Intent(RegistrationUser.this,MainActivity.class);
//                startActivity(intent);
//            }
//        }
//    );
//    {
//            @Override
//            public void onClick(View v) {
//                String firstName = firstNameEditText.getText().toString();
//                String lastName = lastNameEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String phone = phoneEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//            }
//        });
}