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

public class Welcome extends AppCompatActivity {

    private TextView mName;
    private Button mBtnFeed, mBtnInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mName = (TextView) findViewById(R.id.main_txt);

        mBtnFeed = (Button) findViewById(R.id.btn_feed);
        mBtnInfo = (Button) findViewById(R.id.btn_more_info);

        String name = getIntent().getStringExtra("user_name");
        String cin = getIntent().getStringExtra("user_cin");


        mName.setText("Congrats " + name);

        mBtnFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Feed.class);
                intent.putExtra("user_cin", cin);
                startActivity(intent);
            }
        });

        mBtnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Moreinfo.class);
                intent.putExtra("user_cin", cin);
                startActivity(intent);

            }
        });
    }

    public void logOut (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}