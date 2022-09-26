package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        usuarioc.setText(usuarioc.getText().toString()+" "+getIntent().getStringExtra("sname") +  " rol " + getIntent().getStringExtra("srol"));

    }
}