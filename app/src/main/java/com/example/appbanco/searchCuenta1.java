package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

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

    EditText numeroCbuscar;
    TextView nombreBuscado, fechabuscada, balanceBuscado;

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

        buscarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscarCuenta(numeroCbuscar.getText().toString());
            }
        });
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
            numeroCbuscar.setText("");

        }
        else {
            Toast.makeText(this,"No existe una cuenta con ese número",Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}
