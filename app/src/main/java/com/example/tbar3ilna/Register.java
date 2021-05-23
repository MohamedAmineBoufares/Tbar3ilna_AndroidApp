package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity {

    private EditText mFullName, mBirthDate, mCin, mBlood, mPassword;
    private Button mSigninBtn;
    private TextView mLoginTxt;
    private FirebaseAuth fAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = (EditText) findViewById(R.id.name_edit);
        mBirthDate = (EditText) findViewById(R.id.birth_edit);
        mCin = (EditText) findViewById(R.id.cin_edit);
        mBlood = (EditText) findViewById(R.id.blood_edit);
        mPassword = (EditText) findViewById(R.id.password_edit);

        mSigninBtn = (Button) findViewById(R.id.btn_signin) ;

        mLoginTxt = (TextView) findViewById(R.id.login_txt);

        fAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.signin_bar);

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        mSigninBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String fullName = mFullName.getText().toString().trim();
                String birthDate = mBirthDate.getText().toString().trim();
                String cin = mCin.getText().toString().trim();
                String bloodGroup = mBlood.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(fullName) | TextUtils.isEmpty(birthDate) |
                        TextUtils.isEmpty(cin) | TextUtils.isEmpty(bloodGroup) | TextUtils.isEmpty(password)) {

                    Toast.makeText(getApplicationContext(), "There is a missing field", Toast.LENGTH_SHORT).show();

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(cin+"@tbar3ilna.tn", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                        } else {

                            Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

        mLoginTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
    }
}