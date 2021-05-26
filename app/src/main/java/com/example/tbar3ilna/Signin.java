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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signin extends AppCompatActivity {

    private EditText mFullName, mBirthDate, mCin, mBlood, mPassword;
    private Button mSigninBtn;
    private ProgressBar progressBar;

    private FirebaseAuth fAuth;
    private FirebaseDatabase rootNode;
    private DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mFullName = (EditText) findViewById(R.id.name_edit);
        mBirthDate = (EditText) findViewById(R.id.birth_edit);
        mCin = (EditText) findViewById(R.id.cin_edit);
        mBlood = (EditText) findViewById(R.id.blood_edit);
        mPassword = (EditText) findViewById(R.id.password_edit);

        mSigninBtn = (Button) findViewById(R.id.btn_signin) ;

        fAuth = FirebaseAuth.getInstance();
        progressBar = (ProgressBar) findViewById(R.id.signin_bar);


        mSigninBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                // getting my DB instance => DB name
                rootNode = FirebaseDatabase.getInstance();

                // getting my DB field which I want to upload to
                reference = rootNode.getReference("users");

                String fullName = mFullName.getText().toString().trim();
                String birthDate = mBirthDate.getText().toString().trim();
                String cin = mCin.getText().toString().trim();
                String bloodGroup = mBlood.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                // this class contains the fields that need to be sent to DB
                UserHelper userHelper = new UserHelper(fullName, birthDate, cin, bloodGroup);

                // if any of the fields is empty, display a toast and don't do nothing
                if (TextUtils.isEmpty(fullName) | TextUtils.isEmpty(birthDate) |
                        TextUtils.isEmpty(cin) | TextUtils.isEmpty(bloodGroup) | TextUtils.isEmpty(password)) {

                    Toast.makeText(getApplicationContext(), "There is a missing field", Toast.LENGTH_SHORT).show();

                    return;
                }

                // set progress bar to Visible
                progressBar.setVisibility(View.VISIBLE);

                fAuth.createUserWithEmailAndPassword(cin+"@tbar3ilna.tn", password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_SHORT).show();

                            // if all good then send data to DB
                            reference.child(cin).setValue(userHelper);

                            Intent intent = new Intent(getApplicationContext(), Welcome.class);
                            intent.putExtra("user_name", fullName);
                            intent.putExtra("user_cin", cin);
                            startActivity(intent);

                        } else {

                            Toast.makeText(getApplicationContext(), "Error ! " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);

                        }
                    }
                });
            }
        });

    }
}