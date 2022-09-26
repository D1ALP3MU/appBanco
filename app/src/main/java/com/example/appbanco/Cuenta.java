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
        tv_username = (TextView)findViewById(R.id.tv_user_result);

        //recibimos el usuario que nos envia MainActivity
        String valor = getIntent().getStringExtra("Usuario");
        tv_username.setText(valor);
    }
}