package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String [] rols = {"Administrador", "Usuario"};
    String rolSelect;

    EditText email, name, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Instanciar y referenciar
        email = (EditText)findViewById(R.id.etemailreg);
        name = (EditText)findViewById(R.id.etnamereg);
        password = (EditText)findViewById(R.id.etpasswordreg);
        Spinner rol = findViewById(R.id.sprolreg);
        Button register = findViewById(R.id.btnregister);
        ArrayAdapter adpRol = new ArrayAdapter(this, android.R.layout.simple_list_item_checked, rols);
        rol.setAdapter(adpRol);
        //Generar el evento para seleccionar un rol
        rol.setOnItemSelectedListener(this);
        //Eventoi click del boton registrar
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCustomer(email.getText().toString(), name.getText().toString(), password.getText().toString(), rolSelect);
            }
        });
    }

    private void searchCustomer(String sEmail, String sName, String sPassword, String srolSelect) {
        //Crear Array para almacenar los datos de la consulta (query)
        ArrayList<String> dataCustomer = new ArrayList<String>();

        //Instanciar la clases sqlBanco (SQLiteOpenHelper)
        sqlBanco ohBanco = new sqlBanco(this, "dbbanco", null, 1);

        //Instanciar la clase SQLiteDataBase para la crud
        SQLiteDatabase db = ohBanco.getReadableDatabase();

        //Crear variable para la consulta
        String sql = "Select email From customer Where email = '"+ sEmail +"'";

        //Ejecutar la instrucción que contiene la variable sql, a través de un cursor
        Cursor cCustomer = db.rawQuery(sql, null);

        //Chequear si la tabla cCustomer quedó con al menos un registro
        if (!cCustomer.moveToFirst()){ //No lo encontró
            // Agregar el cliente con todos sus datos
            //Instanciar la BD en modo escritura porque se agregará un cliente
            SQLiteDatabase dbadd = ohBanco.getWritableDatabase();
            // Manejo de excepciones
            try {
                ContentValues cvCustomer = new ContentValues();
                cvCustomer.put("email", sEmail);
                cvCustomer.put("name", sName);
                cvCustomer.put("password", sPassword);
                cvCustomer.put("rol", srolSelect);
                dbadd.insert("customer", null, cvCustomer);
                dbadd.close();
                Toast.makeText(getApplicationContext(), "Cliente agregado correctamente", Toast.LENGTH_SHORT).show();
                // Chequear si el rol es administrador o usuario
                if (srolSelect.equals("Administrador")){
                    startActivity(new Intent(getApplicationContext(), Cuenta.class));

                    //Limpiar las cajas de texto
                    email.setText("");
                    name.setText("");
                    password.setText("");
                } else {
                    startActivity(new Intent(getApplicationContext(), Usuario.class));

                    //Limpiar las cajas de texto
                    email.setText("");
                    name.setText("");
                    password.setText("");
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Email existente!. Iténtelo con otro", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        rolSelect = rols[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}