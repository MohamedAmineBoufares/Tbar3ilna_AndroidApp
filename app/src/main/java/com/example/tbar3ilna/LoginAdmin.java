package com.example.tbar3ilna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginAdmin extends AppCompatActivity {

    private NotificationManagerCompat notificationManager;

    private EditText mUserName, mPassword;
    private Button mLogin;
    private ProgressBar progressBar;

    private FirebaseAuth fAuth;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        notificationManager = NotificationManagerCompat.from(this);

        mUserName = (EditText) findViewById(R.id.name_admin_edit);
        mPassword = (EditText) findViewById(R.id.password_login_admin_edit);

        mLogin = (Button) findViewById(R.id.btn_login_admin);

        progressBar = (ProgressBar) findViewById(R.id.progress_login_admin);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Feed.class));
            finish();
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = mUserName.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if ( TextUtils.isEmpty(userName)  | TextUtils.isEmpty(password) ) {

                    Toast.makeText(getApplicationContext(), "There is a missing field", Toast.LENGTH_SHORT).show();

                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(userName+"@tbar3ilna.tn", password).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), DonationReq.class);
                            intent.putExtra("admin_userName", userName);
                            startActivity(intent);

                           /* Notification notification = new NotificationCompat.Builder( getApplicationContext(), App.CHANNEL_1_ID)
                                    .setSmallIcon(R.mipmap.tbar3ilna_icon)
                                    .setContentTitle("Tbar3ilna")
                                    .setContentText("You have a Donor")
                                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                                    .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                    .setColor(Color.BLUE)
                                    .setAutoCancel(true)
                                    .setOnlyAlertOnce(true)
                                    .build();

                            notificationManager.notify(1, notification); */

                        } else {

                            Toast.makeText(getApplicationContext(), "There is a one missing field"
                                            + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            progressBar.setVisibility(View.GONE);

                        }

                    }
                });

                reference = FirebaseDatabase.getInstance().getReference().child("Feed");

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        // for each child of "Feed" add the Blood Grp, fullName and location to the arrayList
                        for (DataSnapshot count : snapshot.getChildren()) {

                            String fullName = count.child("addedBy").getValue().toString();

                            if (fullName.equals(userName)) {

                                Toast.makeText(getApplicationContext(), "True" + count.getKey(), Toast.LENGTH_SHORT).show();

                                Notification notification = new NotificationCompat.Builder( getApplicationContext(), App.CHANNEL_1_ID)
                                        .setSmallIcon(R.mipmap.tbar3ilna_icon)
                                        .setContentTitle("Tbar3ilna")
                                        .setContentText("You have a Donor")
                                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                        .setColor(Color.BLUE)
                                        .setAutoCancel(true)
                                        .setOnlyAlertOnce(true)
                                        .build();

                                notificationManager.notify(1, notification);


                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });

    }
}