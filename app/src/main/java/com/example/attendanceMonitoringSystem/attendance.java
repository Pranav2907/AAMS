package com.example.attendanceMonitoringSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class attendance extends AppCompatActivity {

    FirebaseUser firebaseUser;
    DatabaseReference reference;
    EditText  Subject;
    Button btnmark,btnReport;
    String strsub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        Subject=findViewById(R.id.editTextTextPersonName);
        btnmark=findViewById(R.id.button);
        btnReport=findViewById(R.id.button2);
        btnmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strsub = Subject.getText().toString();
                reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Attendance");
                Date c = Calendar.getInstance().getTime();

                DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                String time=dateFormat.format(c);


                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
                String formattedDate = df.format(c);
                if(strsub.isEmpty())
                {
                    Toast.makeText(attendance.this, "Enter the Subject", Toast.LENGTH_SHORT).show();
                }
                else {

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("Subject", strsub);
                    hashMap.put("Time", time);

                    reference.push().child(formattedDate).push().updateChildren(hashMap);
                    Toast.makeText(attendance.this, "Attendance Marked Sucessfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(attendance.this,Report.class);
                startActivity(i);
            }
        });




    }
}