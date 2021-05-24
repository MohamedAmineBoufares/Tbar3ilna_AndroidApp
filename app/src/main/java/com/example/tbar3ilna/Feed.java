package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed extends AppCompatActivity {

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ListView listView = (ListView) findViewById(R.id.listview_feed);

        ArrayList<Item> arrayList = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.customlayout, arrayList);


        reference = FirebaseDatabase.getInstance().getReference().child("Feed");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // for each child of "Feed" add the Blood Grp, fullName and location to the arrayList
                for (DataSnapshot count : snapshot.getChildren()) {

                    String fullName = count.child("fullName").getValue().toString();
                    String location = count.child("location").getValue().toString();
                    String bloodGrp = count.child("bloodGrp").getValue().toString();

                    arrayList.add(new Item(bloodGrp, fullName, location));

                }

                listView.setAdapter(itemAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public void logOut (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}