package com.johnmelodyme.gpstracker;

/**
 * @Copyright : Copyright © 2020 John Melody Melissa
 * ********************************************************
 * THIS APPLICATION IS MEANT FOR EDUCATIONAL PURPOSES ONLY
 * IS NOT INTENT TO SOMETHING SINISTER OR MALICIOUS, I WILL
 * NOT RESPONSIBLE IF MISUSE OF THIS TECHNOLOGY OR THE
 * ABUSIVE OF USAGE OF THIS PROGRAMME.
 * ********************************************************
 * @AUTHOR: John Melody Melissa
 * @REFERENCE: https://github.com/johnmelodyme/GPSTRACKER
 * This application inspired by @Cindy, My Baby.
 */

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthenticationActivity extends AppCompatActivity {
    private static final String TAG = "GPSTRACKER";
    private Button btnLogin, btnRegister;
    private ProgressBar progressBar;
    private EditText userEmail, userPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        Log.d(TAG, "Authentication...");

        // UI Declaration:
        btnLogin = findViewById(R.id.Login);
        btnRegister = findViewById(R.id.Register);
        progressBar = findViewById(R.id.progressBar);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);

        // TODO Firebase Declaration:
        firebaseAuth = FirebaseAuth.getInstance();

        // TODO REGISTRATION BUTTON LISTENER:
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Check Use Email and Password {format}
                final String email, password, err_email, err_pass, weakpass;

                // Get String from `EditText`
                email = userEmail.getText()
                        .toString()
                        .trim();

                password = userPassword.getText()
                        .toString()
                        .trim();

                // Get String Res:
                err_email = getResources().getString(R.string.pleaseenteremail);
                err_pass = getResources().getString(R.string.pleaseenterpass);
                weakpass = getResources().getString(R.string.weakP);

                // Make it `invalid` if $user leave EditText blank or weak password:
                if (TextUtils.isEmpty(email)) {
                    userEmail.setError(err_email);
                    Log.d(TAG, err_email);
                }

                if (TextUtils.isEmpty(password)) {
                    if (password.length() < 6) {
                        userPassword.setError(weakpass);
                    } else {
                        userPassword.setError(err_pass);
                    }
                }

                progressBar.setVisibility(View.VISIBLE);

                // TODO Create user Profile Firebase :
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> CREATE_AN_USER_ACCOUNT) {
                                // TODO Listen for accountability:
                                if (CREATE_AN_USER_ACCOUNT.isSuccessful()) {
                                    Log.d(TAG, "Welcome " + email);
                                    dispToast("Welcome " + email);
                                    Intent goToTrackerActivity;
                                    goToTrackerActivity = new Intent(AuthenticationActivity.this, TrackerActivity.class);
                                    startActivity(goToTrackerActivity);
                                    Log.d(TAG, "Redirecting to TrackingActivity.class");
                                } else {
                                    dispToast("failed to create user " + email);
                                    Log.d(TAG, "failed to create user " + email);
                                }
                            }
                        });
            }
        });

        // TODO LOGIN BUTTON LISTENER:
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email, password, err_email, err_pass;
                // Get String from `EditText`
                email = userEmail.getText()
                        .toString()
                        .trim();

                password = userPassword.getText()
                        .toString()
                        .trim();

                // Get String Res:
                err_email = getResources().getString(R.string.pleaseenteremail);
                err_pass = getResources().getString(R.string.pleaseenterpass);

                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    if (TextUtils.isEmpty(email)) {
                        userEmail.setError(err_email);
                    } else {
                        userPassword.setError(err_pass);
                    }
                }

                progressBar.setVisibility(View.VISIBLE);

                // TODO Attempt Login Registered Firebase User :
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthenticationActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> USER_LOGIN) {
                                if (USER_LOGIN.isSuccessful()) {
                                    Log.d(TAG, "Welcome back" + email);
                                    dispToast("Welcome back" + email);
                                    Intent goToTrackerActivity;
                                    goToTrackerActivity = new Intent(AuthenticationActivity.this, TrackerActivity.class);
                                    startActivity(goToTrackerActivity);
                                    Log.d(TAG, "Redirecting to TrackingActivity.class");
                                } else {
                                    dispToast("Invalid User");
                                    Log.d(TAG, "failed to Login " + email);
                                }
                            }
                        });
            }
        });
    }

    // TODO TOAST
    public void dispToast(String string) {
        Toast.makeText(getApplicationContext(), string,
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}