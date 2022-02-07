package com.example.traffic;

import android.content.Context;
import android.database.sqlite.SQLiteAbortException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {
    private static final String currentActivity = DataBase.class.getSimpleName();
    // Aqui se entra cada vez que se invoque
    public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        try{
            Log.i("d_funciones","+ DataBase DataBase(): Instancia mDb " + name + ":" + version);
        }
        catch (Exception e){
            Log.i("d_exception:","++++ DataBase DataBase() EXCEPTION =  " + e );

        }
    }
    /**
     * Aqui se entra SOLO la primera vez que se invoque (en toda la vida de la app)
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("d_funciones","* DataBase onCreate() primera instancia");
        try {
            db.execSQL("create table reportes(reporte_id integer primary key, imagen text, direccion text, lento text, accidente text, cierre text )");
            db.execSQL("create table usuarios(usuarios_id integer primary key, nombre text, correo text, contraseña text)");

        }
        catch (SQLiteAbortException e){
            Log.i("d_exception:","++++ DataBase onCreate() EXCEPTION =  " + e );

        }
    }

    // Aqui se entra SOLO cuando hay una actuaizacion
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("d_funciones","+ DataBase onUpgrade(): oldVersion = " + oldVersion + ", newVersion = " + newVersion);


        try {


        }
        catch (SQLiteAbortException e){
            Log.i("d_exception:","++++ DataBase onUpgrade() EXCEPTION =  " + e );

        }
    }
}
