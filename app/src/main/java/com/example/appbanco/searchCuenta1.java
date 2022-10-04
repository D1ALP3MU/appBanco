package com.example.appbanco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
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

public class searchCuenta1 extends AppCompatActivity {

    EditText numeroCbuscar, actualizarBalance;
    TextView nombreBuscado, fechabuscada, balanceBuscado;
    Button botonActualizarb, botonBorrarBal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_cuenta1);


        // Instanciar los Ids del archivo xml
        numeroCbuscar = (EditText)findViewById(R.id.etnumbercBus);
        nombreBuscado = (TextView)findViewById(R.id.tvnamebus);
        fechabuscada = (TextView)findViewById(R.id.tvfechabus);
        balanceBuscado = (TextView)findViewById(R.id.tvbalancebus);
        Button buscarCuenta = findViewById(R.id.btnSearchc);

        actualizarBalance = (EditText)findViewById(R.id.etbalancecAct);
        botonActualizarb = (Button) findViewById(R.id.btnActualizaBal);
        botonBorrarBal = (Button) findViewById(R.id.btnBorrarBal);


        //poner invisible el boton antes de buscar
        botonActualizarb.setVisibility(View.INVISIBLE);
        botonBorrarBal.setVisibility(View.INVISIBLE);


        buscarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCuenta(numeroCbuscar.getText().toString());
            }
        });

        botonActualizarb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Instanciar la clase sqlBanco (SQLiteOpenHelper)
                sqlBanco ohBanco = new sqlBanco(getApplicationContext(), "dbbanco", null, 1);

                SQLiteDatabase obaccount = ohBanco.getWritableDatabase();
                obaccount.execSQL("UPDATE account SET balance = '" + actualizarBalance.getText().toString() + "' WHERE accountnumber = '" + numeroCbuscar.getText().toString() +"'");
                Toast.makeText(getApplicationContext(),"Balance actualizado correctamente...", Toast.LENGTH_SHORT).show();
                actualizarBalance.setText("");
            }
        });
        botonBorrarBal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(searchCuenta1.this);
                alertDialogBuilder.setMessage("Está seguro de eliminar esta cuenta "+numeroCbuscar.getText().toString()+"?");
                alertDialogBuilder.setPositiveButton("Sí",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                sqlBanco ohBanco = new sqlBanco(getApplicationContext(), "dbbanco", null, 1);
                                SQLiteDatabase obde = ohBanco.getWritableDatabase();
                                obde.execSQL("DELETE FROM account WHERE accountnumber = '" + numeroCbuscar.getText().toString() +"'");
                                Toast.makeText(getApplicationContext(),"Cuenta Eliminada correctamente...",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), Cuenta.class));

                            }
                        });

                alertDialogBuilder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void editarCuenta(View view){
        sqlBanco ohBanco = new sqlBanco(getApplicationContext(), "dbbanco", null, 1);

        SQLiteDatabase db = ohBanco.getWritableDatabase();
        //Establecemos los campos-valores a actualizar

        String val = actualizarBalance.getText().toString();
        String val_dos = numeroCbuscar.getText().toString();
        //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("balance",val);

        //Actualizamos el registro en la base de datos
        db.update("account", valores, val_dos, null);
        Toast.makeText(getApplicationContext(),"Balance actualizado correctamente...", Toast.LENGTH_SHORT).show();
        actualizarBalance.setText("");
    }

    private void buscarCuenta(String toString){
        ArrayList<String> datosb = new ArrayList<String>();

        //Instanciar la clase sqlBanco (SQLiteOpenHelper)
        sqlBanco ohBanco = new sqlBanco(this, "dbbanco", null, 1);

        SQLiteDatabase db = ohBanco.getReadableDatabase();

        String sql = "Select accountnumber, email, date, balance From account Where accountnumber = '"+ numeroCbuscar.getText().toString()+"'";
        Cursor cAccuont = db.rawQuery(sql,null);
        if (cAccuont.moveToFirst()) {

            nombreBuscado.setText(cAccuont.getString(1));
            fechabuscada.setText(cAccuont.getString(2));
            balanceBuscado.setText(cAccuont.getString(3));

            // Limpiar caja de texto
            //numeroCbuscar.setText("");
            botonActualizarb.setVisibility(View.VISIBLE);
            botonBorrarBal.setVisibility(View.VISIBLE);


        }
        else {
            Toast.makeText(this,"No existe una cuenta con ese número",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
