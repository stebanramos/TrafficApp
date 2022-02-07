package com.example.traffic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import com.example.traffic.Reporte.AdapterReport;
import com.example.traffic.Reporte.Report;

import java.util.ArrayList;
import java.util.List;

public class ReportsActivity extends AppCompatActivity {

    List<Report> listaProductos;

    private RecyclerView rvProductos;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;

    public static DataBase mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        mDataBase = new DataBase(this, Credentials.DB_NAME, null, Credentials.versionDB);

        rvProductos = findViewById(R.id.rvProductos);

        listaProductos = new ArrayList<Report>();



        String Query = "select imagen, direccion, accidente, cierre, lento from reportes";

        Cursor cursor = mDataBase.getWritableDatabase().rawQuery(Query, null);

        String imagen, direccion;
        boolean accidente, cierre, lento;

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                imagen = cursor.getString(0);
                direccion = cursor.getString(1);
                accidente = Boolean.valueOf(cursor.getString(2));
                cierre = Boolean.valueOf(cursor.getString(3));
                lento = Boolean.valueOf(cursor.getString(4));

                if (imagen.equals("Moto")){
                    listaProductos.add(new Report(R.mipmap.moto,direccion, accidente, cierre, lento));
                }
                if (imagen.equals("Auto")){
                    listaProductos.add(new Report(R.mipmap.auto,direccion, accidente, cierre, lento));
                }
                if (imagen.equals("Bus")){
                    listaProductos.add(new Report(R.mipmap.bus,direccion, accidente, cierre, lento));
                }

                cursor.moveToNext();
            }
        }

        rvProductos.setHasFixedSize(true);

        lManager = new LinearLayoutManager(this);
        rvProductos.setLayoutManager(lManager);

        adapter = new AdapterReport(listaProductos);
        rvProductos.setAdapter(adapter);
    }
}