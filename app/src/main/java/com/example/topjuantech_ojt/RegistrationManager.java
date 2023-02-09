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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import in.aabhasjindal.otptextview.OtpTextView;

public class RegistrationManager extends AppCompatActivity {
    //variables creation & initialization
    //    CountryCodePicker ccp;
    private EditText managerNameEditText, managerLastEditText,
            managerEmailEditText, managerPhoneEditText,
            managerPasswordEditText, managerConfirmPasswordEditText,
            editPhoneCode;
    String firstName, lastName, phone, email, password, confirmPassword, verificationId, otp;
    private Button btnContinueManager, btnOtp;
    private TextView alreadyHaveAccount;
    //    OtpTextView otp_view;
//    Context context;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //    String phonePattern = "^(09|\\+639)\\d{9}$";
    FirebaseAuth mAuth; //Firebase Auth Integration
    FirebaseUser mUser; //Firebase User Integration
    ProgressDialog progressDialog; //Progress Dialog Initialization
    FirebaseFirestore fStore; //Firestore Initialization
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_manager);
        //Instantiation
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        fStore=FirebaseFirestore.getInstance();
        //methods to find ids
        managerNameEditText = findViewById(R.id.editFirstNameManager);
        managerLastEditText = findViewById(R.id.editLastNameManager);
        managerEmailEditText = findViewById(R.id.editEmailManager);
        managerPhoneEditText = findViewById(R.id.editCellphoneManager);
        managerPasswordEditText = findViewById(R.id.editPassword1Manager);
        managerConfirmPasswordEditText = findViewById(R.id.editPassword2Manager);
//        btnOtp = findViewById(R.id.btnOtp);
//        editPhoneCode = findViewById(R.id.editPhoneCode);
//        otp_view = findViewById(R.id.otp_view);
        btnContinueManager = findViewById(R.id.btnContinueManager);
//        wrapper = findViewById(R.id.wrapper);
//        ccp = findViewById(R.id.ccp);
        alreadyHaveAccount = (TextView) findViewById(R.id.alreadyHaveAccount);
        alreadyHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationManager.this, Login.class);
                startActivity(intent);
            }
        });

        Button continueBtn = findViewById(R.id.btnContinueManager);
//        continueBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
////                Intent intent = new Intent(RegistrationManager.this, ManageOTP.class);
////                intent.putExtra("mobile", ccp.getFullNumberWithPlus().replace("",""));
////                startActivity(intent);
//            }
//            });
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() { //performauth method - function
        //getText in string format from edittext
//        String phoneCode = editPhoneCode.getText().toString();
        phone = managerPhoneEditText.getText().toString();
        firstName = managerNameEditText.getText().toString();
        lastName = managerLastEditText.getText().toString();
        email = managerEmailEditText.getText().toString();
        password = managerPasswordEditText.getText().toString();
        confirmPassword = managerConfirmPasswordEditText.getText().toString();

        //fields validation
        if (firstName.isEmpty()) {
            managerNameEditText.setError("Please fill all fields");
        } else if (lastName.isEmpty()) {
            managerLastEditText.setError("Please fill all fields");
        } else if (!email.matches(emailPattern)) {
            managerEmailEditText.setError("Enter correct Email!");
        } else if (password.isEmpty() || password.length() < 6) {
            managerPasswordEditText.setError("Enter Proper Password(6)!");
        } else if (!password.equals(confirmPassword)) {
            managerConfirmPasswordEditText.setError("Confirm Password not match!");
        }
//            }else if(phone!=phonePattern){
//                phoneEditText.setError("Enter Proper phone number!");}
        else {
            progressDialog.setMessage("Please wait...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //registration of user using credentials to be stored in db authentication
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    //storing of user data to firestore
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        progressDialog.dismiss();
                        DocumentReference df = fStore.collection("Users").document(user.getUid());
                        Map<String, Object> userInfo = new HashMap<>();
                        userInfo.put("First Name", managerNameEditText.getText().toString());
                        userInfo.put("Last Name", managerLastEditText.getText().toString());
                        userInfo.put("Email", managerEmailEditText.getText().toString());
                        userInfo.put("Phone", managerPhoneEditText.getText().toString());
                        userInfo.put("isAdmin", "2");
                        df.set(userInfo);
                        sendUserToNextActivity();
                        finish();
                        Toast.makeText(RegistrationManager.this, "Registration Successful", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegistrationManager.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                    }
                }
                //page redirection method
                private void sendUserToNextActivity() {
                    Intent i = new Intent(RegistrationManager.this, AdminDashboard.class);
                    i.setFlags((i.FLAG_ACTIVITY_CLEAR_TASK | i.FLAG_ACTIVITY_NEW_TASK));
                    startActivity(i);
                }
            });
        }
    }
}

//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
//                    Intent intent = new Intent(RegistrationManager.this, RegistrationEstablishment.class);
//                    startActivity(intent);
//                } else if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    Intent intent = new Intent(RegistrationManager.this, RegistrationEstablishment.class);
//                    startActivity(intent);
//                }
//                return true;
//            }


//        Button btnContinueManager = (Button) findViewById(R.id.btnContinueManager);
//        btnContinueManager.setOnClickListener(v -> {
//                    String phoneCode = editPhoneCode.getText().toString();
//                    String phoneNumber = managerPhoneEditText.getText().toString();
//                    firstName = managerNameEditText.getText().toString();
//                    lastName = managerLastEditText.getText().toString();
//                    email = managerEmailEditText.getText().toString();
//                    password = managerPasswordEditText.getText().toString();
//                    confirmPassword = managerConfirmPasswordEditText.getText().toString();
//
//                    if (firstName.isEmpty()) {
//                        Toast.makeText(context, "Please input correct credentials", Toast.LENGTH_LONG).show();
//                        managerNameEditText.setError("Please input correct credentials");
//                        managerNameEditText.requestFocus();
//                    } else if (lastName.isEmpty()) {
//                        Toast.makeText(context, "Please input correct credentials", Toast.LENGTH_LONG).show();
//                        managerLastEditText.setError("Please input correct credentials");
//                        managerLastEditText.requestFocus();
//                    } else if (email.matches(emailPattern)) {
//                        Toast.makeText(context, "Please input correct credentials", Toast.LENGTH_LONG).show();
//                        managerEmailEditText.setError("Please input correct credentials");
//                        managerEmailEditText.requestFocus();
//                    } else if (password != confirmPassword) {
//                        Toast.makeText(context, "Please input correct credentialsd", Toast.LENGTH_LONG).show();
//                        managerPasswordEditText.setError("Please input correct credentials");
//                        managerPasswordEditText.requestFocus();
//                    } else if (confirmPassword.isEmpty()) {
//                        Toast.makeText(context, "Please input correct credentials", Toast.LENGTH_LONG).show();
//                        managerConfirmPasswordEditText.setError("Please input correct credentials");
//                        managerConfirmPasswordEditText.requestFocus();
//                    } else {
//                        phone = phoneCode + phoneNumber;
//                        Query query = database.child("users").orderByChild("phone").equalTo(phone);
//                        query.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                if(snapshot.exists()){
//                                    Toast.makeText(context,"Cellphone number already exists", Toast.LENGTH_LONG).show();
////                                    for(DataSnapshot item : snapshot.getChildren()){
////                                        UserData user = item.getValue(UserData.class);
////                                        if(user != null){
////
////                                        }
////                                    }
//                                }else {
//                                    sendVerification(phone);
//                                }
//
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                            }
//                        });
//                        sendVerification(phone);
//                    }
//                }
//        );
//        btnOtp.setOnClickListener(v->{
//            otp = otp_view.getOTP();
//            if (otp.isEmpty()){
//                Toast.makeText(context,"Please Enter correct OTP", Toast.LENGTH_LONG).show();
//            }else{
//                verifyCode(otp);
//            }
//        });
//    }
//
//    private void verifyCode(String otp) {
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
//        signInWithCredential(credential);
//    }
//
//    private void signInWithCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//                            String level = "user";
//                            database.child("users").push()
//                                    .setValue(
//                                            new UserData(
//                                                    firstName,
//                                                    lastName,
//                                                    phone,
//                                                    email,
//                                                    password,
//                                                    confirmPassword,
//                                                    "user"
//                                            )
//                                    ).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void unused) {
////                                            preference.setDataLogin(context, true);
////                                            preferences.setDataAs(context, "admin");
////                                            startActivity(new Intent(context, MainActivity.class ));
//                                            finish();
//
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception e) {
//                                            Toast.makeText(context, e.getMessage(),Toast.LENGTH_LONG).show();
//
//                                        }
//                                    });
//
//                        }else {
//                            Toast.makeText(context, task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//
//                });
//    }
//
//    private void sendVerification(String phone) {
//        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
//                .setPhoneNumber(phone)
//                .setTimeout(60L, TimeUnit.SECONDS)
//                .setActivity(this)
//                .setCallbacks(mCallBack)
//                .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//    }
//    protected PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//            String code = phoneAuthCredential.getSmsCode();
//            if (code != null){
//                otp_view.setOTP(code);
//                verifyCode(code);
//            }
//
//        }
//
//        @Override
//        public void onVerificationFailed(@NonNull FirebaseException e) {
//            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
//
//        }
//
//        @Override
//        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            rl_wrapper.setVisibility(View.GONE);
//            wrapper.setVisibility(View.VISIBLE);
//
//            verificationId = s;
//        }
//    };
