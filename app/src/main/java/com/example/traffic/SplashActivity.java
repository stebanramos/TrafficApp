package com.example.traffic;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    public static DataBase mDataBase;

    public static Credentials mCredentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Instancia de objetos
        mCredentials = new Credentials(this);

        // Primera instancia de la db (version = calculada desde el dispositivo)
        mDataBase = new DataBase(this, Credentials.DB_NAME, null, Credentials.versionDB);

        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}