package com.example.vinayak.firebase_basic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView value , key,r_value,r_key;
    Button add,fetch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        value = (TextView)findViewById(R.id.editText);
        key= (TextView) findViewById(R.id.editText2);
        add=(Button)findViewById(R.id.button4);
        fetch = (Button) findViewById(R.id.button5) ;
        r_value = (TextView)findViewById(R.id.textView4);
        r_key = (TextView) findViewById(R.id.textView5);

        /* The firebase connection */
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRefRoot = database.getReference();
        final DatabaseReference myRef = myRefRoot.child("User");

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String s_value,s_key;
                s_value=value.getText().toString();
                s_key=key.getText().toString();

                /*Two things*/

                DatabaseReference myRefChild = myRef.child(s_value);
                myRefChild.setValue(s_key);

            }
        });

        /* Getting value from direct child*/

        final DatabaseReference myRef1 = myRef.child("Name");

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                myRef1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        r_key.setText(value);
                        r_value.setText("Name:");
                        // Log.d(TAG, "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

            }
        });

        myRefRoot.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                dataSnapshot.getValue();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
