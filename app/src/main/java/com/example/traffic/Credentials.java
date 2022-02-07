package com.example.traffic;

import android.content.Context;
import android.os.Environment;

public class Credentials {

    static String APP_PACKAGE;

    static String DB_NAME = "Inventa";
    static String BACKUP_PATH;
    static int versionDB = 1;
    static String DB_PATH;
    static String STORAGE_PATH = "/Android/data/com.example.traffic/";


    public Credentials(Context context) {

        APP_PACKAGE = context.getPackageName();
        DB_PATH = "/user/0/" + APP_PACKAGE + "/databases/" + DB_NAME;
        ////Log.i("d_funciones","- DB_PATH = " + DB_PATH );
        BACKUP_PATH = Environment.getExternalStorageDirectory()  + "/Traffic";
        ////Log.i("d_funciones","- BACKUP_PATH = " + BACKUP_PATH );

    }


}
