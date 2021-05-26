package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Feed extends AppCompatActivity {

    private TextView mMoreInfo;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        String adminName = getIntent().getStringExtra("admin_userName");

        ListView listView = (ListView) findViewById(R.id.listview_feed);

        mMoreInfo = (TextView) findViewById(R.id.add_info_txt);

        String cin = getIntent().getStringExtra("user_cin");

        ArrayList<Item> arrayList = new ArrayList<>();
        ItemAdapter itemAdapter = new ItemAdapter(this, R.layout.customlayout, arrayList);


        reference = FirebaseDatabase.getInstance().getReference().child("Feed");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // for each child of "Feed" add the Blood Grp, fullName and location to the arrayList
                for (DataSnapshot count : snapshot.getChildren()) {

                    String fullName = count.child("fullName").getValue().toString();
                    String location = count.child("location").getValue().toString();
                    String bloodGrp = count.child("bloodGrp").getValue().toString();

                    arrayList.add(new Item(bloodGrp, fullName, location, count.getKey()));

                }

                listView.setAdapter(itemAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        /*reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // for each child of "Feed" add the Blood Grp, fullName and location to the arrayList
                for (DataSnapshot count : snapshot.getChildren()) {

                    String fullName = count.child("fullName").getValue().toString();
                    String location = count.child("location").getValue().toString();
                    String bloodGrp = count.child("bloodGrp").getValue().toString();

                    arrayList.add(new Item(bloodGrp, fullName, location, count.getKey()));

                }

                listView.setAdapter(itemAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        mMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Moreinfo.class);
                intent.putExtra("user_cin", cin);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                TextView txt = view.findViewById(R.id.textView_hidden);
                String ID_txt = txt.getText().toString();

                //Integer counter = reference_2.child("numOfInteractions").getVa;
                reference.child(ID_txt).child("numOfInteractions").setValue(1);


                Toast.makeText(getApplicationContext(),  adminName, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void logOut (View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}