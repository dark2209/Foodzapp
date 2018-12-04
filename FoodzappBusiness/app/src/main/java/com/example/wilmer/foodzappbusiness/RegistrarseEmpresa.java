package com.example.wilmer.foodzappbusiness;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.wilmer.foodzappbusiness.Firebase.BusinessUser;
import com.example.wilmer.foodzappbusiness.Firebase.Referencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarseEmpresa extends AppCompatActivity {

    private static final String TAG = "Antut";
    private Button registrar;
    private Button logout;
    private EditText NombreEmpresa;
    private EditText PassWord;
    private EditText Number;
    private EditText Email;
    private EditText RNC;
    private EditText Descripcion;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse_empresa);

        //variable para la autentificacion
        mAuth = FirebaseAuth.getInstance();
        //boton para registrar
        registrar = (Button) findViewById(R.id.button6);
        //Edit Texts
        NombreEmpresa = (EditText) findViewById(R.id.editText4);
        PassWord = (EditText) findViewById(R.id.editText8);
        Number = (EditText) findViewById(R.id.editText10);
        Email = (EditText) findViewById(R.id.editText2);
        RNC = (EditText) findViewById(R.id.editText6);
        Descripcion = (EditText) findViewById(R.id.editText5);
        //boton logout
        logout=(Button)findViewById(R.id.button5);


        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Registrar();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(RegistrarseEmpresa.this,MainActivity.class));
            }
        });

    }



    private void Registrar() {
        final String mNombreEmpresa = NombreEmpresa.getText().toString();
        final String mPassWord = PassWord.getText().toString();
        final String mNumber = Number.getText().toString();
        final String mEmail = Number.getText().toString();
        final String mRNC = RNC.getText().toString();
        final String mDescripcion = Descripcion.getText().toString();


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference(Referencias.BusinessUser_REFERENCE);
        BusinessUser BU = new BusinessUser(
                //Agregando valores
                mNombreEmpresa,
                mPassWord,
                mNumber,
                mEmail,
                mRNC,
                mDescripcion);
        myref.child(Referencias.newBusinessUser_REFERENCE).push().setValue(BU);
    }


}




