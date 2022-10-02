package com.example.appbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class viewUsers extends AppCompatActivity {
    private ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_users);

        lv1 = (ListView)findViewById(R.id.lv1);

        ArrayList<String> ranking = new ArrayList<>();

        sqlBanco ohBanco = new sqlBanco(this, "dbbanco", null, 1);
        SQLiteDatabase bd = ohBanco.getWritableDatabase();
        Cursor fila = bd.rawQuery("select accountnumber,email,balance,date from account", null);
        if(fila.moveToFirst()){
            do{
                ranking.add(fila.getString(0) + " - " + fila.getString(1)+ " - " + fila.getString(2)+ " - " + fila.getString(3));
            }while(fila.moveToNext());
        }
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ranking);
        lv1.setAdapter(adapter);
    }
}