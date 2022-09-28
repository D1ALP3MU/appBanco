package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cuenta extends AppCompatActivity {
    TextView tv_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);
        tv_username = (TextView)findViewById(R.id.tvusuarioc);
        TextView usuarioc = findViewById(R.id.tvusuarioc);
        // Mostrar el nombre y el rol enviados desde el MainActivity.java
        usuarioc.setText(usuarioc.getText().toString()+" "+getIntent().getStringExtra("sname") +  " " + getIntent().getStringExtra("srol"));

        Button agregarc = findViewById(R.id.btnagregar);
        Button actualizarc = findViewById(R.id.btnactualizar);
        Button eliminarc = findViewById(R.id.btneliminar);
        Button buscarc = findViewById(R.id.btnbuscar);

        agregarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AgregarCuenta.class));
            }
        });



    }
}