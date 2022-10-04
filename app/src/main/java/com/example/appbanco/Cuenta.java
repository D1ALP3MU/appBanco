package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cuenta extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        TextView usuarioc = findViewById(R.id.tvusuarioc);
        // Mostrar el nombre y el rol enviados desde el MainActivity.java
        usuarioc.setText(usuarioc.getText().toString()+" "+getIntent().getStringExtra("sname") +  " " + getIntent().getStringExtra("srol"));

        Button agregar = findViewById(R.id.btnagregar);
        Button actualizarc = findViewById(R.id.btnactualizar);
        Button eliminarc = findViewById(R.id.btneliminar);
        Button BuscarOne = findViewById(R.id.btnbuscarOne);
        Button listarc = findViewById(R.id.btnbuscar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), addAccount.class));
            }
        });

        BuscarOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), searchCuenta1.class));
            }
        });

        listarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), viewUsers.class));
            }
        });



    }


}