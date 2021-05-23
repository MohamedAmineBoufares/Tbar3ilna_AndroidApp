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

public class Login extends AppCompatActivity {

    private EditText mCin, mPassword;
    private Button mLogin;
    private TextView mSignin;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mCin = (EditText) findViewById(R.id.cin_login_edit);
        mPassword = (EditText) findViewById(R.id.password_login_edit);
        mLogin = (Button) findViewById(R.id.btn_login);
        progressBar = (ProgressBar) findViewById(R.id.progress_login);

        mSignin = (TextView) findViewById(R.id.signin_txt);

        fAuth= FirebaseAuth.getInstance();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cin = mCin.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if ( TextUtils.isEmpty(cin)  | TextUtils.isEmpty(password) ) {

                    Toast.makeText(getApplicationContext(), "There is a missing field", Toast.LENGTH_SHORT).show();

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(cin+"@tbar3ilna.tn", password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Logged In", Toast.LENGTH_SHORT).show();
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

        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });
    }
}