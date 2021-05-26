package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class DonationReq extends AppCompatActivity {

    private EditText mFullName, mBloodGrp, mLocation;
    private TextView mSeeFeed;
    private Button mAdd;
    private ProgressBar progressBar;

    private FirebaseDatabase rootNode;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_req);

        String adminName = getIntent().getStringExtra("admin_userName");

        mFullName = (EditText) findViewById(R.id.name_donationReq_edit);
        mBloodGrp = (EditText) findViewById(R.id.bloodGrp_donationReq_edit);
        mLocation = (EditText) findViewById(R.id.location_donationReq_edit);

        mSeeFeed = (TextView) findViewById(R.id.seeFeed_donationReq_txt);

        mAdd = (Button) findViewById(R.id.btn_add_donationReq);

        progressBar = (ProgressBar) findViewById(R.id.progress_donationReq);

        mSeeFeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Feed.class);
                intent.putExtra("admin_userName", adminName);
                startActivity(intent);
            }
        });

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting my DB instance => DB name
                rootNode = FirebaseDatabase.getInstance();

                // getting my DB field which I want to upload to
                reference = rootNode.getReference("Feed");

                String fullName = mFullName.getText().toString().trim();
                String bloodGroup = mBloodGrp.getText().toString().trim();
                String location = mLocation.getText().toString().trim();


                // this class contains the fields that need to be sent to DB
                AdminHelper adminHelper = new AdminHelper(fullName, location,bloodGroup, adminName, 0);

                // if any of the fields is empty, display a toast and don't do nothing
                if (TextUtils.isEmpty(fullName) | TextUtils.isEmpty(bloodGroup) | TextUtils.isEmpty(location)) {

                    Toast.makeText(getApplicationContext(), "There is a missing field", Toast.LENGTH_SHORT).show();

                    return;
                }
                else {

                    Random rand = new Random(); //instance of random class
                    String ID = String.valueOf(rand.nextInt(1000));

                    reference.child(ID).setValue(adminHelper);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(getApplicationContext(), "Added to feed", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}