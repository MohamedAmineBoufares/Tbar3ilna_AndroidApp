package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Moreinfo extends AppCompatActivity {

    private EditText mAddress, mPhone, mPhone2, mEmail;
    private TextView mUserName;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreinfo);

        String cin = getIntent().getStringExtra("user_cin");

        mUserName = (TextView) findViewById(R.id.info_txt);
        mUserName.setText(cin);

        mAddress = (EditText) findViewById(R.id.address_edit);
        mPhone = (EditText) findViewById(R.id.phone_edit);
        mPhone2 = (EditText) findViewById(R.id.phone_2_edit);
        mEmail = (EditText) findViewById(R.id.email_edit);

        reference = FirebaseDatabase.getInstance().getReference("users");

    }

    public void update(View view) {

        String cin = getIntent().getStringExtra("user_cin");

        reference.child(cin).child("Address").setValue(mAddress.getText().toString());
        reference.child(cin).child("Phone N°1").setValue(mPhone.getText().toString());
        reference.child(cin).child("Phone N°2").setValue(mPhone2.getText().toString());
        reference.child(cin).child("E-mail").setValue(mEmail.getText().toString());

        Toast.makeText(this, "Data changed", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(), Feed.class);
        startActivity(intent);

    }

}