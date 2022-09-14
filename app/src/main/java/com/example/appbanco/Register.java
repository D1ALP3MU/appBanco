package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String [] rols = {"Administrador", "Usuario"};
    String rolSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Instanciar y referenciar
        EditText email = findViewById(R.id.etemailreg);
        EditText name = findViewById(R.id.etnamereg);
        EditText password = findViewById(R.id.etpasswordreg);
        Spinner rol = findViewById(R.id.sprolreg);
        Button register = findViewById(R.id.btnregister);
        ArrayAdapter adpRol = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, rols);
        rol.setAdapter(adpRol);
        //Generar el evento para seleccionar un rol
        rol.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rolSelect = rols[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}