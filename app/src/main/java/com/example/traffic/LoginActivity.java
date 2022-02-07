package com.example.traffic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public static DataBase mDataBase;

    String usuario, contraseña;
    TextView txtUsuario, txtPassword;

    Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDataBase = new DataBase(this, Credentials.DB_NAME, null, Credentials.versionDB);

        txtUsuario = findViewById(R.id.usuario);
       txtPassword = findViewById(R.id.password);

        Login = findViewById(R.id.login);
        //btnRegister.setEnabled(false);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }

    public void Login(){

        usuario = txtUsuario.getText().toString();

        contraseña =  txtPassword.getText().toString();

        if (usuario.isEmpty() || contraseña.isEmpty()){
            Log.i("d_exception:", "Lllene todos los campos  " );
        }else {

            if (Validar_Usuario(usuario, contraseña)){
                login();
            }else {
                Log.i("d_exception:", "Usuario/Contraseña incorrectos  " );
            }
        }
    }

    public boolean Validar_Usuario(String nombre, String password){
        String Query = "select correo, contraseña from usuarios";

        Cursor cursor = mDataBase.getWritableDatabase().rawQuery(Query, null);

        String correo, contraseña;

        boolean validar = false;

        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {

                correo = cursor.getString(0);
                contraseña = cursor.getString(1);

                if (correo.equals(usuario) && contraseña.equals(password)){
                    validar = true;
                    break;
                }else {
                    validar = false;
                    cursor.moveToNext();
                }
            }
        }

        return validar;
    }

    public void login(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void register(View v){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);

    }
}