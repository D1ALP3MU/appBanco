package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class addAccount extends AppCompatActivity {

    TextView snumberc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        // instanciar y referenciar los IDs del archivo xml
        EditText emailc = findViewById(R.id.etemailc);
        EditText fechac = findViewById(R.id.etfechac);
        EditText balancec = findViewById(R.id.etbalancec);
        Button agregarc = findViewById(R.id.btnagregarc);
        snumberc = (TextView) findViewById(R.id.tvnumberc);

        agregarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agregarCuenta(emailc.getText().toString(), fechac.getText().toString(), balancec.getText().toString(), snumberc.getText().toString());
            }
        });
    }

    private void agregarCuenta(String semail, String sdate, String sbalance, String numeroc) {
        //Crear Array para almacenar los datos de la consulta (query)
        ArrayList<String> dataAccount = new ArrayList<String>();

        //Instanciar la clases sqlBanco (SQLiteOpenHelper)
        sqlBanco ohBanco = new sqlBanco(this, "dbbanco", null, 1);

        //Instanciar la clase SQLiteDataBase para la crud
        SQLiteDatabase db = ohBanco.getReadableDatabase();

        //Crear variable para la consulta
        String sql = "Select email From account Where email = '"+ semail +"'";

        //Ejecutar la instrucción que contiene la variable sql, a través de un cursor
        Cursor cAccount = db.rawQuery(sql, null);

        //Chequear si la tabla cCustomer quedó con al menos un registro
        if (!cAccount.moveToFirst()){ //No lo encontró
            // Agregar el cliente con todos sus datos
            //Instanciar la BD en modo escritura porque se agregará un cliente
            SQLiteDatabase dbadd = ohBanco.getWritableDatabase();
            // Manejo de excepciones
            try {
                ContentValues cvAccount = new ContentValues();
                cvAccount.put("email", semail);
                cvAccount.put("date", sdate);
                cvAccount.put("balance", sbalance);
                dbadd.insert("account", null, cvAccount);
                //dbadd.close();
                Toast.makeText(getApplicationContext(), "Cuenta agregada correctamente", Toast.LENGTH_SHORT).show();

                Log.i("number cuenta", "la tabla cuenta " + cvAccount);

                String acsql = "Select accountnumber from account order by accountnumber desc limit 1;";
                Cursor sacNumber = db.rawQuery(acsql,null);
                if (sacNumber.moveToFirst()){
                    //Toast.makeText(getApplicationContext(), "Número de cuenta " + sacNumber.getInt(0), Toast.LENGTH_SHORT).show();
                    String numero = sacNumber.getString(0);
                    snumberc.setText(numero);
                }
                Log.i("NumberGenerated", "Hola mundo");

            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Este email ya tine una cuenta o no se ha registrado", Toast.LENGTH_SHORT).show();
        }
    }
}