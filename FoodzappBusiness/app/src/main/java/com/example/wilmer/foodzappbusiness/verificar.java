package com.example.wilmer.foodzappbusiness;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wilmer.foodzappbusiness.Firebase.Referencias;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class verificar extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView mTextView,mTextView2,mTextView3,mTextView4;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar);

        mTextView=(TextView)findViewById(R.id.textView2);
        mTextView2=(TextView)findViewById(R.id.textView3);
        mTextView3=(TextView)findViewById(R.id.textView4);
        mTextView4=(TextView)findViewById(R.id.textView5);
        mButton=(Button)findViewById(R.id.button3);


        Mapeo();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(verificar.this,MainActivity.class));
            }
        });

    }

    public void Mapeo(){
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    mDatabase = FirebaseDatabase.getInstance().getReference().child(Referencias.BusinessUser_REFERENCE);
                    mDatabase.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mTextView.setText(String.valueOf(dataSnapshot.child("Empresa").getValue()));
                            mTextView2.setText(String.valueOf(dataSnapshot.child("Number").getValue()));
                            mTextView3.setText(String.valueOf(dataSnapshot.child("RNC").getValue()));
                            mTextView4.setText(String.valueOf(dataSnapshot.child("Descripcion").getValue()));

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

}
