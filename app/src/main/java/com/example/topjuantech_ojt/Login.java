package com.example.topjuantech_ojt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
//import com.example.topjuantech_ojt.databinding.ActivityLoginBinding;
public class Login extends AppCompatActivity {
    //Variable creation and Initialization
    private TextView forgotPassword, createNewAccount;
    private EditText firstNameEditText, lastNameEditText,
            emailEditText, phoneEditText, passwordEditText,
            confirmPasswordEditText, editPhoneCode, editTextEmailAdd, editTextPassword;
    private TextView alreadyHaveAccount;
    String firstName, lastName, phone, email, password, confirmPassword, loginEmail, loginPassword;

    Context context;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePattern = "^(09|\\+639)\\d{9}$";
    FirebaseAuth mAuth; //FirebaseAuth Integration
    FirebaseUser mUser; //FIrebaseUser Integration
    FirebaseFirestore fStore; //Firestore Integration
    ProgressDialog progressDialog; //ProgressDialog Initialization
    Button btnLogin;
//    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //method to find ids
        editTextEmailAdd = findViewById(R.id.editTextEmailAdd);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        //Instantiation
        mAuth = FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        mUser = mAuth.getCurrentUser();
        progressDialog = new ProgressDialog(this);



        forgotPassword = findViewById(R.id.forgotPassword);
//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, ForgotPassword.class);
//                startActivity(intent);
//            }
//        });
        createNewAccount = (TextView) findViewById(R.id.createNewAccount);
        createNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });
    }
    //Perform login method - function
    private void performLogin() {
        //get text in string format
        email = editTextEmailAdd.getText().toString();
        password = editTextPassword.getText().toString();

        //fields validation
        if (!email.matches(emailPattern)) {
            editTextEmailAdd.setError("Enter correct Email!");
//        } else if (!email.matches(phonePattern)) {
//            editTextEmailAdd.setError("Enter correct Phone!");
        } else if (password.isEmpty() || password.length() < 6) {
            editTextPassword.setError("Enter Proper Password(6)!");
        } else {
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //Signing In w the users credentials
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this, "Login  Successful", Toast.LENGTH_LONG).show();
                    checkUserAccessLevel(authResult.getUser().getUid());
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    //checkUserAccessLevel method
    //determining if user or admin
    private void checkUserAccessLevel(String uid) {
        DocumentReference df=fStore.collection("Users").document(uid);
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG","onSuccess: " + documentSnapshot.getData());
                if(documentSnapshot.getString("isUser")!= null){
                    startActivity(new Intent(getApplicationContext(),UserDashboard.class));
                    finish();
                }
                if(documentSnapshot.getString("isAdmin")!= null){
                    startActivity(new Intent(getApplicationContext(),AdminDashboard.class));
                    finish();
                }
            }
        });
    }

}