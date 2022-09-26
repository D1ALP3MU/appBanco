package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText user, pass;
    private Cursor infoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Instanciar y referenciar los IDs del login
        TextView reglink = findViewById(R.id.tvregister);
        user = (EditText)findViewById(R.id.etemail);
        pass = (EditText)findViewById(R.id.etpassword);

        // Evento click del reglink
        reglink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Invocar la activiad del registro
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

}
    // Metodo de ingreso
    public void IniciarSesion(View view){
        /*Creamos un objeto de la clase sqlBanco e
        instanciamos el constructor y damos el nombre de
        la base de datos y la version*/
        sqlBanco admin = new sqlBanco(this,"dbbanco",null,1);

        /*Abrimos la base de datos como escritura*/
        SQLiteDatabase db = admin.getWritableDatabase();

        /*Creamos dos variables string y capturamos los datos
         ingresado por el usuario y lo convertimos a string*/
        String usuario = user.getText().toString();
        String contrasena = pass.getText().toString();

        /*Inicializamos al cursor y llamamos al objeto de la base
        de datos para realizar un sentencia query where donde
        pasamos las dos variables nombre de usuario, name, password y el rol*/
        infoUser = db.rawQuery("select email, name, password, rol from customer where email='"+
                usuario +"' and password ='"+ contrasena+"'",null);

        /*Realizamos un try catch para captura de errores*/
        try {
            /*Condicional if preguntamos si cursor tiene algun dato*/
            if(infoUser.moveToFirst()){
                //capturamos los valores del cursos y lo almacenamos en variable
                String usu = infoUser.getString(0);
                String name = infoUser.getString(1);
                String psw = infoUser.getString(2);
                String rol = infoUser.getString(3);
                //preguntamos si los datos ingresados son iguales
                if (usuario.equals(usu) && contrasena.equals(psw)){
                    if ("Administrador".equals(rol)){
                        //Si son iguales entonces vamos a otra ventana dependiendo del rol
                        //Cuentas si el rol es Administrador
                        Intent ventana = new Intent(this, Cuenta.class);

                        //Mandamos el usuario
                        ventana.putExtra("Usuario", name);

                        //Lanzamos la actividad
                        startActivity(ventana);

                        //Limpiamos las cajas de texto
                        user.setText("");
                        pass.setText("");
                    } else {
                        //Si el rol es usuario vamos a la ventana de Usuario
                        Intent ventana = new Intent(this, Usuario.class);

                        //Lanzamos la actividad
                        startActivity(ventana);

                        //Limpiamos las las cajas de texto
                        user.setText("");
                        pass.setText("");
                    }
                }
            }//Si la primera condicion no cumple entonces que envie un mensaje toast
            else {
                Toast.makeText(getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {//capturamos los errores que ubieran
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

}